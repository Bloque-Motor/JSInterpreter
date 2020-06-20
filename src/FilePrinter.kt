import java.io.File

class FilePrinter {

    private val tokenFileName = """/Users/inigoar/Downloads/PDL/token.txt"""
    private val symbolTableFile = """/Users/inigoar/Downloads/PDL/tablaSimbolos.txt"""
    private val errorFile = """/Users/inigoar/Downloads/PDL/errores.txt"""
    private val parseFileName = """/Users/inigoar/Downloads/PDL/parser.txt"""

    var assignments = mutableListOf<String>()
    var delimiters = mutableListOf<String>()
    var keywords = mutableListOf<String>()
    var logicOps = mutableListOf<String>()
    var ariOps = mutableListOf<String>()
    var relationOps = mutableListOf<String>()

    var symbolTable = mutableMapOf<String,List<Identifier>>()
        get() = field
        set(value) {
            field = value
        }
    var tokenStream = mutableListOf<Token>()
        get() = field
    var auxSymbolMap = HashMap<String, Int>()

    var tokenList = mutableListOf<String>()
    var errors = mutableListOf<String>()

    var boolFunction = false
    var local = 0
    var localLevel = "Global"
    var localFunctionName = "Global"
    var numId = 0

    fun addToken(type: Int, token: String){
        var line = ""
        when(type){
            1->{
                return
            }
            2-> {
                line = "<number, $token>"
                tokenStream.add(Token("number", token))
            }
            3->{
                line = "<cadena, $token>"
                tokenStream.add(Token("cadena", token))
            }
            4->{
                line = "<$token, >"
                tokenStream.add(Token(token, ""))
                if(token.equals("function")){
                    boolFunction = true
                }

                if(boolFunction && token.equals("{")){
                    local++
                }

                if(boolFunction && token.equals("}")){
                    local--
                    if(local == 0){
                        boolFunction = false
                        localFunctionName = "Global"
                        numId = 0

                    }
                }
            }
            5->{

                if (!auxSymbolMap.containsKey(token)){
                    if(boolFunction){
                        if(numId == 1) {
                            localFunctionName = token
                        }
                        numId = 1
                    }

                    var symbol = Identifier(symbolTable.size, token)
                    auxSymbolMap.put(token, symbolTable.size)
                    var auxList = mutableListOf<Identifier>()
                    if(symbolTable.containsKey(localFunctionName)){
                       for(iden in symbolTable.get(localFunctionName)!!){
                           auxList.add(iden)
                       }
                        auxList.add(symbol)
                        symbolTable.put(localFunctionName, auxList)
                    }else {
                        auxList.add(symbol)
                        symbolTable.put(localFunctionName, auxList)
                    }
                }
                line = "<id, ${auxSymbolMap.get(token)}>"
                tokenStream.add(Token("id", auxSymbolMap.get(token).toString()))
            }
        }

        tokenList.add(line)
    }

    fun isKeyword(word: String): Boolean{
        return keywords.contains(word)
    }

    fun addError(message: String){
        errors.add(message)
    }

    fun makeErrorFile(){
        val ef = File(errorFile)
        ef.createNewFile()
        ef.printWriter().use { out ->
            for(lines in errors){
                out.println(lines)
            }

        }
    }

    fun makeTokenFile(){
        val tf = File(tokenFileName)
        tf.createNewFile()
        tf.printWriter().use { out ->
            for(lines in tokenList){
                out.println(lines)
            }

        }
    }

    fun makeSymbolTableFile(){
        val sf = File(symbolTableFile)
        var tableNumber = 1
        sf.createNewFile()
        sf.printWriter().use { out ->
            out.println("TABLA GLOBAL #$tableNumber:")
            out.println()
            for (symbol in symbolTable.get("Global")!!){
                out.println("* LEXEMA : '${symbol.lex}'")
                out.println("  ATRIBUTOS :")
                if(symbol.type == Identifier.Type.FUNCTION){
                    out.println("  + tipo: 'funcion'")
                    when(symbol.returnType){
                        Identifier.Type.INT -> out.println("  + tipoRetorno: 'entero'")
                        Identifier.Type.BOOLEAN -> out.println("  + tipoRetorno: 'logico'")
                        Identifier.Type.STRING -> out.println("  + tipoRetorno: 'cadena'")
                        else->out.println("  + tipoRetorno: 'void'")
                    }
                    var params = ""
                    for (param in symbol.parameterList){
                        when(param){
                            Identifier.Type.INT -> params += "entero, "
                            Identifier.Type.BOOLEAN -> params += "logico, "
                            Identifier.Type.STRING -> params += "cadena, "
                        }
                    }
                    params = params.dropLast(2)
                    out.println("  + tipoParametros: '$params'")
                    out.println("  + numParametros: '${symbol.parameterCount}'")
                }else{
                    when(symbol.type){
                        Identifier.Type.INT -> out.println("  + tipo: 'entero'")
                        Identifier.Type.BOOLEAN -> out.println("  + tipo: 'logico'")
                        Identifier.Type.STRING -> out.println("  + tipo: 'cadena'")
                    }
                }
                out.println("  + id: ${symbol.id}")
                out.println("-------------------------")
            }
            tableNumber++
        }
    }


    fun makeOutputDir() {
        val outDir = File("/Output/")
        outDir.mkdirs()
    }

    fun makeParseFile(parseOrder: MutableList<Int>) {
        val pf = File(parseFileName)
        pf.createNewFile()
        pf.printWriter().use { out ->
            out.print("D")
            for(lines in parseOrder){
                out.print(" $lines")
            }

        }
    }

    init {
        val assignmentsFile = File("tkns/assignments.txt")
        val delimiterFile = File("tkns/delimiter.txt")
        val keywordsFile = File("tkns/keywords.txt")
        val logicOpsFile = File("tkns/logicOps.txt")
        val ariOpsFile = File("tkns/ariOps.txt")
        val relationOpsFile = File("tkns/relationOps.txt")

        var bufferedReader = assignmentsFile.bufferedReader()
        var text = bufferedReader.readLines()
        for (line in text) {
            assignments.add(line)
        }
        bufferedReader = delimiterFile.bufferedReader()
        text = bufferedReader.readLines()
        for (line in text) {
            delimiters.add(line)
        }
        bufferedReader = keywordsFile.bufferedReader()
        text = bufferedReader.readLines()
        for (line in text) {
            keywords.add(line)
        }
        bufferedReader = logicOpsFile.bufferedReader()
        text = bufferedReader.readLines()
        for (line in text) {
            logicOps.add(line)
        }
        bufferedReader = ariOpsFile.bufferedReader()
        text = bufferedReader.readLines()
        for (line in text) {
            ariOps.add(line)
        }
        bufferedReader = relationOpsFile.bufferedReader()
        text = bufferedReader.readLines()
        for (line in text) {
            relationOps.add(line)
        }
    }

}
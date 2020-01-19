import java.io.File

class FilePrinter {

    private val tokenFileName = """/Users/inigoar/Downloads/PDL/Tokens.txt"""
    private val symbolTableFile = """/Users/inigoar/Downloads/PDL/SymbolTable.txt"""
    private val errorFile = """/Users/inigoar/Downloads/PDL/Errors.txt"""
    private val parseFileName = """/Users/inigoar/Downloads/PDL/ParseFile.txt"""

    var assignments = mutableListOf<String>()
    var delimiters = mutableListOf<String>()
    var keywords = mutableListOf<String>()
    var logicOps = mutableListOf<String>()
    var ariOps = mutableListOf<String>()
    var relationOps = mutableListOf<String>()

    var symbolTable = mutableListOf<Identifier>()
        get() = field
        set(value) {
            field = value
        }
    var tokenStream = mutableListOf<Token>()
        get() = field
    var auxSymbolMap = HashMap<String, Int>()

    var identifiers = mutableListOf<String>()
    var tokenList = mutableListOf<String>()
    var errors = mutableListOf<String>()


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

            }
            5->{
                if (!auxSymbolMap.containsKey(token)){
                    var symbol = Identifier(symbolTable.size, token)
                    auxSymbolMap.put(token, symbolTable.size)
                    symbolTable.add(symbol)
                }
                line = "<id, ${identifiers.indexOf(token)}>"
                tokenStream.add(Token("id", identifiers.indexOf(token).toString()))
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
        sf.createNewFile()
        sf.printWriter().use { out ->
            out.println("TABLA DE IDENTIFICADORES #1:")
            out.println()
            for (symbol in symbolTable){
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
                    out.print("  + valor: '")
                    if(symbol.value != null) {
                        out.println(symbol.value.toString())
                    }else{
                        out.println("null'")
                    }
                }
                out.println("  + id: ${symbol.id}")
                out.println("-------------------------")
            }
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
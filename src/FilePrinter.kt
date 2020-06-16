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

    var symbolTable = mutableListOf<Identifier>()
        get() = field
        set(value) {
            field = value
        }
    var tokenStream = mutableListOf<Token>()
        get() = field
    var auxSymbolMap = HashMap<String, Int>()

    var tokenList = mutableListOf<String>()
    var errors = mutableListOf<String>()

    var anidadas = mutableListOf<TSAnidada>()

    class TSAnidada{
        var funcName = ""
        var numTS = 0
        var lexema = ""
        var tipo = ""
        var desplazamiento = 0

    }


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

    fun makeSymbolTableFile() {
        val sf = File(symbolTableFile)
        var desplazamiento = 0 //variable desplazamiento para la TS
        var tableNumber = 1 // numero identificador tabla
        var functionName = ""
        var checkFunction = false
        var index = 0
        var numParam = 0
        var indexSymbol = 0
        var actualLexema = ""
        var actualTipo = ""

        sf.createNewFile()
        sf.printWriter().use { out ->
            out.println("TABLA GLOBAL #$tableNumber:")
            out.println()
            tableNumber++
            for (token in tokenStream) {
                    if(!checkFunction) {
                        if (token.type == "id") {
                            out.println()
                            index = token.value.toInt()
                            out.println("* LEXEMA : '${symbolTable[index].lex}'")
                            out.println("  ATRIBUTOS :")
                            when (symbolTable[index].type) {
                                Identifier.Type.INT -> out.println("  + tipo: 'entero'")
                                Identifier.Type.BOOLEAN -> out.println("  + tipo: 'logico'")
                                Identifier.Type.STRING -> out.println("  + tipo: 'cadena'")
                            }
                        } else if(isKeyword(token.type)){
                            out.println()
                            out.println("* LEXEMA : '${symbolTable[index].lex}'")
                            out.println("  ATRIBUTOS :")
                            if (token.type == "function") {
                                functionName = symbolTable[index].lex
                                checkFunction = true
                                out.println("  + tipo: 'funcion'")
                                when (symbolTable[index].returnType) {
                                    Identifier.Type.INT -> out.println("  + tipoRetorno: 'entero'")
                                    Identifier.Type.BOOLEAN -> out.println("  + tipoRetorno: 'logico'")
                                    Identifier.Type.STRING -> out.println("  + tipoRetorno: 'cadena'")
                                    else -> out.println("  + tipoRetorno: 'void'")
                                }
                            }
                            else{
                                when (symbolTable[index].type) {
                                    Identifier.Type.INT -> out.println("  + tipo: 'entero'")
                                    Identifier.Type.BOOLEAN -> out.println("  + tipo: 'logico'")
                                    Identifier.Type.STRING -> out.println("  + tipo: 'cadena'")
                                }
                            }
                        }
                    }else{
                        if(token.type == "("){
                            numParam++
                        }
                        else if (token.type == ","){
                            numParam++
                            var anid = TSAnidada()
                            anid.numTS = tableNumber
                            anid.funcName = functionName
                            anid.desplazamiento = desplazamiento
                            anid.lexema = actualLexema
                            anid.tipo = actualTipo
                            anidadas.add(anid)
                            actualLexema = ""
                            functionName = ""
                            actualTipo = ""
                        }
                        else if(token.type == ")"){
                            var anid2 = TSAnidada()
                            anid2.numTS = tableNumber
                            anid2.funcName = functionName
                            anid2.desplazamiento = desplazamiento
                            anid2.lexema = actualLexema
                            anid2.tipo = actualTipo
                            anidadas.add(anid2)
                            actualLexema = ""
                            functionName = ""
                            actualTipo = ""
                            checkFunction = false
                            out.println("  + numParametros: '$numParam'")
                            tableNumber++
                            numParam = 0
                        }
                        else if(token.type == "boolean" || token.type == "string" || token.type == "int"){
                            actualTipo = token.type
                        }
                        else{
                            indexSymbol = token.value.toInt()
                            actualLexema = symbolTable[indexSymbol].lex
                        }
                    }
            }
            var actualNum = 0
            for (word in anidadas){
                if(actualNum == 0 || actualNum != word.numTS) {
                    out.println()
                    out.println("-------------------------")
                    out.println()
                    out.println("TABLA DE LA FUNCION ${word.funcName} # ${word.numTS}")
                    out.println()
                }
                    actualNum = word.numTS
                    out.println("* LEXEMA : '${word.lexema}'")
                    out.println("   + TIPO : '${word.tipo}'")
                    out.println("   + DESPLAZAMIENTO : '${word.desplazamiento}'")
                    out.println()
            }
            out.println("-------------------------")
        }
    }

    fun makeSymbolTableFile2() {
        val sf = File(symbolTableFile)
        var desplazamiento = 0 //variable desplazamiento para la TS
        var tableNumber = 1 // numero identificador tabla
        var tablaGlobal = true
        var functionName = ""
        var symbolTableIterator = symbolTable.iterator()
        var i = 0
        sf.createNewFile()
        sf.printWriter().use { out ->
            out.println("TABLA GLOBAL #$tableNumber:")
            out.println()
            for (symbol in symbolTableIterator) {

                if (tablaGlobal) {
                    out.println("* LEXEMA : '${symbol.lex}'")
                    out.println("  ATRIBUTOS :")
                    if (symbol.type == Identifier.Type.FUNCTION) {
                        out.println("  + tipo: 'funcion'")
                        when (symbol.returnType) {
                            Identifier.Type.INT -> out.println("  + tipoRetorno: 'entero'")
                            Identifier.Type.BOOLEAN -> out.println("  + tipoRetorno: 'logico'")
                            Identifier.Type.STRING -> out.println("  + tipoRetorno: 'cadena'")
                            else -> out.println("  + tipoRetorno: 'void'")
                        }
                        var params = ""

                        for (param in symbol.parameterList) {
                            symbol.addParameter(param)

                            when (param) {
                                Identifier.Type.INT -> params += "entero, "
                                Identifier.Type.BOOLEAN -> params += "logico, "
                                Identifier.Type.STRING -> params += "cadena, "
                            }
                        }
                        params = params.dropLast(2)
                        out.println("  + tipoParametros: '$params'")
                        out.println("  + numParametros: '${symbol.parameterCount}'")
                        out.println("  + id: ${symbol.id}")
                        tableNumber++
                        tablaGlobal = false
                        functionName = symbol.lex

                    } else {
                        when (symbol.type) {
                            Identifier.Type.INT -> out.println("  + tipo: 'entero'")
                            Identifier.Type.BOOLEAN -> out.println("  + tipo: 'logico'")
                            Identifier.Type.STRING -> out.println("  + tipo: 'cadena'")

                        }
                        out.println("  + id: ${symbol.id}")
                    }

                    out.println("-------------------------")
                } else {

                    out.println("-------------------------")
                    out.println("TABLA DE LA FUNCION $functionName # $tableNumber")
                    out.println("* LEXEMA : '${symbolTable[0].lex}'")
                    out.println("-------------------------")
                    tablaGlobal = true

                }
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
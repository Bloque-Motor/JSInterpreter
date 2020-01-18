import java.io.File
import kotlin.collections.mutableListOf;

class TokenPrinter (){

    private val tokenFileName = """Output\Tokens.txt"""
    private val symbolTableFile = """Output\SymbolTable.txt"""
    private val errorFile = """Output\Errors.txt"""

    var assignments = mutableListOf<String>()
    var delimiters = mutableListOf<String>()
    var keywords = mutableListOf<String>()
    var logicOps = mutableListOf<String>()
    var ariOps = mutableListOf<String>()
    var relationOps = mutableListOf<String>()

    var tokenStream = mutableListOf<Token>()
        get() = field

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
                line = "<string, $token>"
                tokenStream.add(Token("string", token))
            }
            4->{
                line = "<$token, >"
                tokenStream.add(Token(token, ""))

            }
            5->{
                if(!identifiers.contains(token)) identifiers.add(token)
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

    fun makeSymbolTable(){
        val sf = File(symbolTableFile)
        sf.createNewFile()
        sf.printWriter().use { out ->
            out.println("TABLA DE IDENTIFICADORES #1:")
            out.println()
            for (line in identifiers){
                out.println("* LEXEMA : '$line'")
                out.println("  ATRIBUTOS :")
                out.println("-------------------------")
            }
        }
    }

    fun makeOutputDir() {
        val outDir = File("/Output/")
        outDir.mkdirs()
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


import java.io.File

class TokenPrinter(){
    var tokenList = mutableListOf(String)
    var operators = mutableListOf(String)
    var comments = mutableListOf(String)
    var numbers = mutableListOf(String)
    var strings = mutableListOf(String)
    var logicLit = mutableListOf(String)
    var relations = mutableListOf(String)
    var identifiers = mutableListOf(String)
    var logicals = mutableListOf(String)
    var assignments = mutableListOf(String)
    var declarations = mutableListOf(String)
    var keywords = mutableListOf(String)

    init {
        val assignmentsFile = File("tkns/assignments.txt")
        val delimiterFile = File("tkns/delimiter.txt")
        val keywordsFile = File("tkns/keywords.txt")
        val logicOpsFile = File("tkns/logicOps.txt")
        val operatorsFile = File("tkns/operators.txt")
        val relationOpsFile = File("tkns/relationOps.txt")
        val bufferedReader = assignmentsFile.bufferedReader()
        val text: List<String> = bufferedReader.readLines()
        for (line in text) {
            assignments.add(line)
        }
    }

    fun addToken(token: String, type: Int){
        var line = "<"

    }

    fun makeTokenFile(outFileName: String?){
        File(outFileName).printWriter().use { out ->
            for(lines in tokenList){
                out.println("$lines")
            }

        }
    }
}


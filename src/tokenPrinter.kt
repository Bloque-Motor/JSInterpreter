import java.io.File

class TokenPrinter(){
    var assignments = mutableListOf<String>()
    var delimiters = mutableListOf<String>()
    var keywords = mutableListOf<String>()
    var logicOps = mutableListOf<String>()
    var ariOps = mutableListOf<String>()
    var relationOps = mutableListOf<String>()

    var strings = mutableListOf<String>()
    var identifiers = mutableListOf<String>()
    var tokenList = mutableListOf<String>()
    //var declarations = mutableListOf<String>()
    //var logicLit = mutableListOf<String>()

    fun addToken(type: Int, token: String){
        var line = ""
        when(type){
            1->{
                line = "<comment, $token>"
            }
            2-> {
                line = "<number, $token>"
            }
            3->{
                line = "<string, ${strings.size}>"
                strings.add(token)
            }
/*            4->{}//Newline
            5->{}//Tab
            6->{}//Quotes escape: \"
            7->{}//Logical literal (true/false). Included in 16 for now */
            8->{
                line = "<ariOp, ${ariOps.indexOf(token)}"
            }
            9->{
                line = "<relationOp, ${relationOps.indexOf(token)}>"
            }
            10->{
                line = "<logicOp, ${logicOps.indexOf(token)}>"
            }
            11->{
                line = "<assignment, ${assignments.indexOf(token)}>"
            }
            12->{
                if(!identifiers.contains(token)) identifiers.add(token)
                line = "<id, ${identifiers.indexOf(token)}>"
            }
/*            13->{} //Declarations. Included in 16 for now
            14->{} //Variable types. Included in 16 for now  */
            15->{
                line = "<delimiter, ${delimiters.indexOf(token)}>"
            }
            16->{
                line = "<keyword, ${keywords.indexOf(token)}>"
            }
        }
        tokenList.add(line)
    }

    fun makeTokenFile(outFileName: String?){
        File(outFileName).printWriter().use { out ->
            for(lines in tokenList){
                out.println("$lines")
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


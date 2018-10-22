import java.io.File
import java.io.PrintWriter

fun main(args : Array<String>) {
    var tp = TokenPrinter()
    var at = Automata(tp)
    println("Enter filename to parse:")
    val fileName: String? = readLine()
    val file = File(fileName)
    println("Enter output filename:")
    val outFileName: String? = readLine()
    var lines = 0
    if(file.exists()){
        println("$fileName does exist. Reading file...")
        println()
        val bufferedReader = file.bufferedReader()
        val text:List<String> = bufferedReader.readLines()
        for(line in text){
            println(line)
            var currentLine = line.toCharArray()
            var char: Char?
            for (char in currentLine){
                    at.process(char)
                }
            lines++
        }

        File(outFileName).printWriter().use { out ->
            out.println("Number of lines: $lines")
        }

    } else {
        println("$fileName does not exist.")
    }


}


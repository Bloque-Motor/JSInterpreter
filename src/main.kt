import java.io.File
import java.lang.Exception
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var tp = TokenPrinter()
    var at = Automata(tp)
    println("Enter filename to parse:")
    val fileName: String? = readLine()
    val file = File(fileName)
    var lines = 1
    if (!file.exists()) {
        println("$fileName does not exist.")
        exitProcess(-1)
    }

    println("$fileName found. Reading file...")
    println()
    val bufferedReader = file.bufferedReader()
    val text: List<String> = bufferedReader.readLines()
    loop@ for (line in text) {
        try {
            var currentLine = line.toCharArray()
            for (char in currentLine) {
                at.process(char)
            }
            lines++
            at.process('\n')
        } catch (e: Exception) {
            if(!(e.message == "comment")) println("Error at line $lines: ${e.message}")
            continue@loop
        }
    }

    tp.makeTokenFile()
    tp.makeSymbolTable()


}


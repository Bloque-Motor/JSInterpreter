import java.io.File
import java.io.PrintWriter
import java.lang.Exception
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var tp = TokenPrinter()
    var at = Automata(tp)
    println("Enter filename to parse:")
    val fileName: String? = readLine()
    val file = File(fileName)
    println("Enter output filename:")
    val outFileName = readLine()
    var lines = 0
    if (!file.exists()) {
        println("$fileName does not exist.")
        exitProcess(-1)
    }

    println("$fileName found. Reading file...")
    println()
    val bufferedReader = file.bufferedReader()
    val text: List<String> = bufferedReader.readLines()
    for (line in text) {
        try {
            var currentLine = line.toCharArray()
            var char: Char?
            for (char in currentLine) {
                at.process(char)
            }
            lines++
        } catch (e: Exception) {
            println("Error at line $lines: ${e.message}")
        }
    }

    tp.makeTokenFile(outFileName)


}


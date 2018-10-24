import java.io.File
import java.lang.Exception
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var tp = TokenPrinter()
    var at = Automata(tp)
    println("Enter filename to parse:")
    val fileName: String? = readLine()
    val file = File(fileName)
    var lines = 0
    var errors = 0
    if (!file.exists()) {
        println("$fileName does not exist.")
        exitProcess(-1)
    }

    println("$fileName found. Reading file...")
    println()
    val bufferedReader = file.bufferedReader()
    val text: List<String> = bufferedReader.readLines()
    loop@ for (line in text) {
        lines++
        try {
            var currentLine = line.toCharArray()
            for (char in currentLine) {
                at.process(char)
            }
            at.process('\n')
        } catch (e: Exception) {
            if(e.message == "comment") continue@loop
            errors++
            tp.addError("Error at line $lines: ${e.message}")
            continue@loop
        }
    }

    if(errors > 0) tp.makeErrorFile()
    tp.makeTokenFile()
    tp.makeSymbolTable()


}


import java.io.File
import java.lang.Exception
import kotlin.system.exitProcess

fun main() {
    println("Enter filename to parse:")
    var tokenStream = mutableListOf<Token>()
    val fileName: String? = readLine()
    val file = File(fileName)
    var lines = 0
    var errors = 0
    if (!file.exists()) {
        println("$fileName does not exist.")
        exitProcess(-1)
    }
    var tp = TokenPrinter()
    var la = LexicalAnalyzer(tp)

    println("$fileName found. Reading file...")
    println()
    val bufferedReader = file.bufferedReader()
    val text: List<String> = bufferedReader.readLines()
    loop@ for (line in text) {
        lines++
        try {
            var currentLine = line.toCharArray()
            for (char in currentLine) {
                la.process(char)
            }
            la.process('\n')
        } catch (e: Exception) {
            if(e.message == "comment") continue@loop
            errors++
            tp.addError("Error at line $lines: ${e.message}")
            continue@loop
        }
    }

    tp.makeOutputDir()
    if(errors > 0) tp.makeErrorFile()
    tp.makeTokenFile()
    tp.makeSymbolTable()
    tokenStream = tp.tokenStream



}


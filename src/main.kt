import java.io.File
import kotlin.system.exitProcess

fun main() {
    println("Enter filename to parse:")
    var tokenStream: MutableList<Token>
    var symbolTable: MutableList<Identifier>
    val fileName: String? = readLine()
    val file = File(fileName)
    var lines = 0
    var errors = 0
    if (!file.exists()) {
        println("$fileName does not exist.")
        exitProcess(-1)
    }
    var fp = FilePrinter()
    var la = LexicalAnalyzer(fp)

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
            fp.addError("Error at line $lines: ${e.message}")
            continue@loop
        }
    }

    fp.makeOutputDir()
    if(errors > 0) fp.makeErrorFile()
    fp.makeTokenFile()
    symbolTable = fp.symbolTable
    tokenStream = fp.tokenStream
    tokenStream.add(Token("eof", ""))
    var sa = SyntaxAnalyzer(tokenStream, symbolTable)
    var parseOrder = sa.parse()
    fp.makeParseFile(parseOrder)
    fp.symbolTable = sa.symbolTable as MutableList<Identifier>
    fp.makeSymbolTableFile()




}


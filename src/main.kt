import java.io.File
import java.io.PrintWriter

fun main(args : Array<String>) {
    println("Enter filename to parse:")
    val fileName: String? = readLine()
    val file = File(fileName)
    println("Enter output filename:")
    val outFileName: String? = readLine()
    val output = PrintWriter(outFileName)
    if(file.exists()){
        println("$fileName does exist. Reading file...")
        println()
        val bufferedReader = file.bufferedReader()
        val text:List<String> = bufferedReader.readLines()
        var caps = 0
        var small = 0
        var other = 0
        var numbers = 0
        var lines = 0
        for(line in text){
            println(line)
            var currentLine = line.toCharArray()
            var char: Char?
            for (char in currentLine){
                    when{
                        (char in 'a'..'z')-> small++

                        (char in 'A'..'Z')-> caps++

                        (char in '0'..'9')-> numbers++

                        else-> other++
                    }
                }
            lines++
        }

        println()
        println("___________________________________")
        File(outFileName).printWriter().use { out ->
            out.println("Chacacter count")
        }
        println()
        println("Caps: $caps")
        println("Small letters: $small")
        println("Numbers: $numbers")
        println("Other characters: $other")
        println("""Total number of characters: ${small + caps + numbers + other}""")
        println("Number of lines: $lines")
    } else {
        println("$fileName does not exist.")
    }


}


import java.io.File

fun main(args : Array<String>) {
    println("Enter filename to parse:")
    val fileName: String? = readLine()
    val file = File(fileName)
    var fileExists = file.exists()
    if(fileExists){
        println("$fileName does exist.")
        val bufferedReader = file.bufferedReader()
        val text:List<String> = bufferedReader.readLines()
        for(line in text){
            println(line)
        }
    } else {
        println("$fileName does not exist.")
    }

}
package converter

import java.lang.Exception
import java.util.*
import kotlin.system.exitProcess

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter what you want to convert (or exit): ")
    while (scanner.hasNext()) {
        val input = scanner.nextLine()
        val parser = InputParser()
        when {
            input == "exit" -> {
                exitProcess(1)
            }
            input.toLowerCase().contains("(degrees?|kelvins?|\\s\\bd.\\b\\s?|\\s\\bc\\b\\s?|\\s\\bk\\b\\s?|\\s\\bf\\b\\s?|\\s\\bfahrenheit\\b\\s?|\\s\\bcelsius\\b\\s?)".toRegex()) -> {
                try {
                    parser.parseTemp(input)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            else -> {
                try {
                    parser.parseUnit(input)
                }catch (e: Exception) {
                    println(e.message)
                }
            }
        }
        print("Enter what you want to convert (or exit): ")
    }

}

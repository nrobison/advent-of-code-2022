import java.io.File
import java.io.InputStream

class AdventDay2 {
    private var selectionPointValueFirst = mapOf(Pair("X", 1), Pair("Y", 2), Pair("Z", 3))
    private var winLoseValues = mapOf(Pair("X",0),Pair("Y",3),Pair("Z",6))
    private var aSelection = mapOf(Pair("X","Z"),Pair("Y","X"),Pair("Z","Y"))
    private var bSelection = mapOf(Pair("X","X"),Pair("Y","Y"),Pair("Z","Z"))
    private var cSelection = mapOf(Pair("X","Y"),Pair("Y","Z"),Pair("Z","X"))

    fun firstStrategyGuide() {
        val inputStream: InputStream = File("resrouces/day_2_input.txt").inputStream()
        var userPointTotal = 0
        inputStream.bufferedReader().forEachLine {
            val lineSplitBySpace = it.split(" ")
            val elfSelection = lineSplitBySpace[0]
            val userSelection = lineSplitBySpace[1]
            userPointTotal += selectionPointValueFirst[userSelection]!!
            when (elfSelection) {
                "A" -> {
                    if (userSelection == "X") userPointTotal += 3
                    if (userSelection == "Y") userPointTotal += 6
                }
                "B" -> {
                    if (userSelection == "Y") userPointTotal += 3
                    if (userSelection == "Z") userPointTotal += 6
                }
                "C" -> {
                    if (userSelection == "Z") userPointTotal += 3
                    if (userSelection == "X") userPointTotal += 6
                }
            }
        }
        inputStream.close()
        println("Point total = $userPointTotal")
    }

    fun secondStrategyGuide(){
        val inputStream: InputStream = File("resrouces/day_2_input.txt").inputStream()
        var userPointTotal = 0
        var correctSelection = ""
        inputStream.bufferedReader().forEachLine {
            val lineSplitBySpace = it.split(" ")
            val elfSelection = lineSplitBySpace[0]
            val userShouldSelect = lineSplitBySpace[1]

            when(elfSelection){
                "A" -> {
                    correctSelection = aSelection[userShouldSelect]!!
                    userPointTotal += winLoseValues[userShouldSelect]!!
                    userPointTotal += selectionPointValueFirst[correctSelection]!!
                }
                "B" ->{
                    correctSelection = bSelection[userShouldSelect]!!
                    userPointTotal += winLoseValues[userShouldSelect]!!
                    userPointTotal += selectionPointValueFirst[correctSelection]!!
                }
                "C" ->{
                    correctSelection = cSelection[userShouldSelect]!!
                    userPointTotal += winLoseValues[userShouldSelect]!!
                    userPointTotal += selectionPointValueFirst[correctSelection]!!
                }
            }
        }
        println("Strategy Two total: $userPointTotal")
        inputStream.close()
    }

}
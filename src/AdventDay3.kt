import java.io.File
import java.io.InputStream

class AdventDay3 {
    private val completeAlphabet = (CharRange('a','z') + CharRange('A','Z')).toMutableList()
    fun partOneRucksacks() {

        var pointValues = 0
        val inputStream: InputStream = File("resources/day_3_compartments.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            val lengthToSplit = if (it.length % 2 == 0) it.length / 2 else (it.length / 2) + 1
            val splitStringFirst = it.subSequence(0, lengthToSplit).toList()
            val splitStringSecond = it.subSequence(lengthToSplit, it.length).toList()
            for (character in splitStringFirst) {
                if (splitStringSecond.contains(character)) {
                    pointValues += returnAlphabetValue(character)
                    break
                }
            }
        }
        inputStream.close()
        println("Sum of items in rucksacks: $pointValues")
    }
    fun partTwoRucksacks(){
        var pointValues = 0
        var lineList = mutableListOf<String>()
        val inputStream: InputStream = File("resources/day_3_compartments.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            lineList.add(it)
            if(lineList.size == 3){
                val firstSplit = lineList[0].substring(0)
                for(character in firstSplit) {
                    if (lineList[1].contains(character) && lineList[2].contains(character)) {
                        pointValues += returnAlphabetValue(character)
                        break
                    }
                }
                lineList.clear()
            }
        }
        inputStream.close()
        println("Value of 3 line racksacks Day 3: $pointValues")

    }

    private fun returnAlphabetValue(character: Char): Int {
        return completeAlphabet.indexOf(character) + 1
    }
}


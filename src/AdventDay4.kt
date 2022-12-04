import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class AdventDay4 {
    fun CleanupOverlapOne(){
        var numberOfCompleteOverlap = 0
        var numberOfAnyOverlap = 0
        val inputStream : InputStream = File("resources/day_4_cleanup.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            val elfCleanupSplit = it.split(',')
            val firstElfRange = splitLinesIntoRange(elfCleanupSplit[0])
            val secondElfRange = splitLinesIntoRange(elfCleanupSplit[1])
            var rangeOfFirstElf = IntRange(firstElfRange[0].toInt(),firstElfRange[1].toInt()).toSet()
            var rangeOfSecondElf = IntRange(secondElfRange[0].toInt(), secondElfRange[1].toInt()).toSet()
            if(rangeOfFirstElf.containsAll(rangeOfSecondElf) or rangeOfSecondElf.containsAll(rangeOfFirstElf)){
                numberOfCompleteOverlap += 1
            }
            if(rangeOfFirstElf.any(rangeOfSecondElf::contains)){
                numberOfAnyOverlap += 1
            }
        }
        inputStream.close()
        println("Day 4 #1 number of  complete overlap = $numberOfCompleteOverlap")
        println("Day 4 #1 number of some overlap = $numberOfAnyOverlap")
    }

    private fun splitLinesIntoRange(line : String): List<String> {
        return line.split('-')
    }
}
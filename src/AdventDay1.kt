import java.io.File
import java.io.InputStream

class AdventDay1 {

    fun calculateCalories(){
        var numberOfHighestCalories = 0
        var currentElfCalories = 0
        var numberOfElves = 1
        val inputStream: InputStream = File("resources/day_1_calories.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
                val lineValue = it.toIntOrNull()
                if(lineValue == null){
                    if (currentElfCalories > numberOfHighestCalories){
                        numberOfHighestCalories = currentElfCalories
                    }
                    currentElfCalories = 0
                    numberOfElves += 1
                }
            else{
                currentElfCalories += lineValue
                }
            }
        inputStream.close()
        numberOfElves += 1
        println("Number of Highest Calories: $numberOfHighestCalories and number of elves $numberOfElves")
    }
    fun calculateTopThreeCalories() {
        val inputStream: InputStream = File("resources/day_1_calories.txt").inputStream()
        var currentElfCalories = 0
        var topThree = IntArray(3) { 0 }
        inputStream.bufferedReader().forEachLine {
            val lineValue = it.toIntOrNull()
            if(lineValue == null) {
                topThree = swapTopThree(topThree, currentElfCalories)
                currentElfCalories = 0
            }
            else{
                currentElfCalories += lineValue
            }
        }
        inputStream.close()
        val totalTopThree = topThree[0] + topThree[1] +  topThree[2]
        println("Top Three Elves total Calories = $totalTopThree")
    }

    fun swapTopThree(valueArray : IntArray, newValue: Int): IntArray {
        var swapValue = newValue
        for(i in 0..2){
            if(swapValue > valueArray[i]){
                var tempValue = valueArray[i]
                valueArray[i] = swapValue
                swapValue = tempValue
            }
        }
        return valueArray
    }

}
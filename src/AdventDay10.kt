import java.io.File
import java.io.InputStream

class AdventDay10 {
    private var xRegister = 1
    private var cycle = 0
    private var totalPartOne = 0
    private var partTwoPixels = mutableListOf<MutableList<Char>>()
    fun partOne(filePath : String){
        val inputStream : InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val instructions = it.split("\\s+".toRegex())
            cycle += 1
            if(instructions[0] == "addx"){
                printCyclePartOne()
                cycle += 1
                printCyclePartOne()
                xRegister += instructions[1].toInt()
            }
            //NOOP increase cycle 1
            else{
                printCyclePartOne()
            }
        }
        println("Total for x Register values part one: $totalPartOne")
    }

    fun partTwo(filePath: String){
        generatePixelList()
        val inputStream : InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val instructions = it.split("\\s+".toRegex())
            cycle += 1
            if (instructions[0] == "addx") {
                shouldDrawPixel()
                cycle += 1
                shouldDrawPixel()
                xRegister += instructions[1].toInt()
            }
            else{
                shouldDrawPixel()
            }
        }
        for(lists in partTwoPixels){
            //Overkill just removes the , printing
            var generateString = ""
            for(item in lists){
                generateString += item
            }
            println(generateString)
        }
    }
    fun shouldDrawPixel(){
        //xValue needs to be < 40
        var xValue = cycle - (((cycle-1) / 40) * 40)
        if(xValue in xRegister..xRegister+2){
            val yPosition = (cycle-1) / 40
            //xValue -1 since 0 -39
            partTwoPixels[yPosition][xValue-1] = '#'
        }
    }

    private fun generatePixelList(){
        for(number in 0..5){
            var aList = MutableList<Char>(40){'.'}
            partTwoPixels.add(aList)
        }
    }

    private fun printCyclePartOne(){
        if(cycle == 20 ||  (cycle - 20) % 40 == 0  ){
            totalPartOne += cycle * xRegister
            //println("DURING Cycle #$cycle value is ${cycle * xRegister}")
        }
    }

    fun clearData(){
        xRegister = 1
        cycle = 0
        totalPartOne = 0
    }
}
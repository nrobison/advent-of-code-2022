import com.sun.org.apache.xpath.internal.operations.Bool
import java.io.File
import kotlin.math.absoluteValue

class AdventDay14 {
    private var xOffset = 0
    private var yOffset = 0
    private var layout = mutableListOf<MutableList<Char>>()
    private var completeEnd = false
    private var count = 0
    private var startingPoint = 500
    fun generateInput(filePath : String, hasFloor : Boolean){
        val lines = File(filePath).readLines()
        var pairs = mutableListOf <List<Pair<Int,Int>>>()
        for(line in lines){
            var currentLine = mutableListOf<Pair<Int,Int>>()
            var splitItem = line.split("->")
            for(item in splitItem){
                var subSplit = item.trim().split(',')
                var pairItem = Pair(subSplit[0].toInt(), subSplit[1].toInt())
                currentLine.add(pairItem)
            }
            pairs.add(currentLine)
        }
        xOffset = pairs.flatten().minBy { it.first }.first
        val maxX = pairs.flatten().maxBy { it.first }.first
        yOffset = pairs.flatten().minBy { it.second }.second
        var maxY = pairs.flatten().maxBy { it.second }.second
        if(hasFloor) maxY += 2
        layout = MutableList<MutableList<Char>>(maxY+1){MutableList<Char>(maxX - xOffset+1){'.'}}
        pairs.forEach { pair ->
            var currentPoint = pair[0]
            for(index in 1 until pair.size){
                var nextPoint = pair[index]
                //check up/down or left/right
                generateRangeToFill(currentPoint,nextPoint).forEach{
                    layout[it.second][it.first] = '#'
                }
                currentPoint = nextPoint

            }
        }
        if(hasFloor){
            for(index in 0 until maxX - xOffset+1)
            layout[maxY][index] = '#'
        }
        //println("Values")
    }

    fun solutionOne(){
        while(!completeEnd){
            count += 1
            dropSandOne(500)
        }
     //   prettyPrint()
        println("Part 1: Number of Sands $count" )
    }

    fun solutionTwo(){
        var sandAtSource = false
        while(!sandAtSource){
            count += 1
            sandAtSource = dropSandTwo()
        }
     //   prettyPrint()
        println("Part 2 : Number of Sands $count" )
    }

    fun resetValues(){
        xOffset = 0
        yOffset = 0
        layout = mutableListOf<MutableList<Char>>()
        completeEnd = false
        count = 0
    }

    private fun prettyPrint(){
        var line = 0
        layout.forEach {
            var lineOutput = ""
            it.forEach { char -> lineOutput += char }
            println("$lineOutput ::: Line $line")
            line +=1
        }
        println("************** SAND COUNT $count **************")
    }
    fun dropSandOne(startingPoint : Int) {
        var hitEnd = false
        var xValue = startingPoint-xOffset
        var yValue = -1
        while(!hitEnd){
            //Check the next
            var nextPoint = layout[yValue+1][xValue]
            if(checkIfSpaceIsOccupied(nextPoint)){
                //Check left
                if(checkIfOutOfBounds(xValue-1,yValue +1)){
                    hitEnd = true
                    completeEnd = true
                    layout[yValue][xValue] = 'o'
                }
                else if(!checkIfSpaceIsOccupied(layout[yValue+1][xValue-1])){
                    xValue -= 1
                    yValue += 1
                }
                //We out of bounds
                else if(checkIfOutOfBounds(xValue + 1,yValue+1) ){
                    hitEnd = true
                    completeEnd = true
                    layout[yValue][xValue] = 'o'
                }

                else if(!checkIfSpaceIsOccupied(layout[yValue+1][xValue + 1])) {
                    xValue += 1
                    yValue += 1
                }
                else{
                    hitEnd = true
                    layout[yValue][xValue] = 'o'
                }
            }
            else{
                yValue += 1
            }
        }
    }

    fun dropSandTwo() : Boolean {
        var hitEnd = false
        var xValue = startingPoint-xOffset
        var yValue = -1
        while(!hitEnd){
            var nextPoint = layout[yValue+1][xValue]
            if(checkIfSpaceIsOccupied(nextPoint)){
                if(checkIfOutOfBounds(xValue-1,yValue +1 ) || checkIfOutOfBounds(xValue + 1,yValue+1)){
                    var expandEnd = xValue -1 > 0
                    expandside( expandEnd)
                    if(!expandEnd){
                        xValue  += 1
                        startingPoint += 1
                    }
                }
                //Left
                else if(!checkIfSpaceIsOccupied(layout[yValue+1][xValue-1])){
                    xValue -= 1
                    yValue += 1
                }
                //Right
                else if(!checkIfSpaceIsOccupied(layout[yValue+1][xValue + 1])) {
                    xValue += 1
                    yValue += 1
                }
                else{
                    hitEnd = true
                    layout[yValue][xValue] = 'o'
                    if(yValue == 0){
                        return true
                    }
                }
            }
            else{
                yValue += 1
            }
        }
        return false
    }
    private fun expandside(atEnd : Boolean){
        layout.forEach {
            if(!atEnd) it.add(0,'.')
            else it.add('.')
        }
        if(!atEnd) layout[layout.size-1][0] = '#'
        else layout[layout.size-1][layout[0].size-1] = '#'
    }

    private fun checkIfSpaceIsOccupied(checkSpace : Char) : Boolean{
        if(checkSpace == '#' || checkSpace == 'o') return true
        return false
    }
    private fun checkIfOutOfBounds(xValue : Int, yValue: Int ) : Boolean{
        if(xValue < 0 || xValue > layout[0].size-1 || yValue > layout.size-1 ) return true
        return false
    }

    private fun generateRangeToFill(start : Pair<Int,Int>, end : Pair<Int,Int>) : List<Pair<Int,Int>>{
        //Determine if horizontal or vertical
        var completeRange = mutableListOf<Pair<Int,Int>>()
        //Vertical
        if(start.first == end.first){
            var yDiffernce = start.second - end.second
            var isPositive = yDiffernce > 0
            if(isPositive){
                for(range in 0 downTo -(start.second - end.second)){
                    completeRange.add(Pair(start.first- xOffset, start.second + range))
                }
            }
            else{
                for(range in 0 ..  (start.second - end.second).absoluteValue){
                    completeRange.add(Pair(start.first - xOffset, start.second + range ))
                }
            }
        }
        //Horizontal
        else{
            val xDifference = (start.first - end.first)
            var isPositive = xDifference > 0
            if(isPositive){
                for(range in 0 downTo -xDifference){
                    completeRange.add(Pair(start.first + range - xOffset, start.second))
                }
            }
            else{
                for(range in 0 .. xDifference.absoluteValue){
                    completeRange.add(Pair(start.first + range - xOffset, start.second))
                }
            }

        }
        return completeRange
    }
}
import java.io.File
import java.io.InputStream

class AdventDay9 {
    var positionOfHead = Pair<Int,Int>(0,0)
    var tailPosition = Pair<Int,Int>(0,0)
    var listOfTailPositions = mutableListOf<Pair<Int,Int>>()
    var largeRope = mutableListOf<Pair<Int,Int>>()
    fun partOne(filePath : String){
        listOfTailPositions.add(tailPosition)
        val inputStream : InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val instructions = it.split("\\s+".toRegex())
            moveHead(instructions[0],instructions[1].toInt())
        }
        println("Number of moves = ${listOfTailPositions.size}")
    }

    private fun moveHead(direction : String, spaces : Int ) {
        for(index in 1 ..spaces) {
            moveHeadSpaces(direction)
            if (needToMoveSpace(positionOfHead, tailPosition)){
                tailPosition = moveSpace(positionOfHead,tailPosition)
                if(!listOfTailPositions.contains(tailPosition)) {
                    listOfTailPositions.add(tailPosition)
                }
            }
        }
    }

    private fun moveHeadPartTwo(direction : String, spaces : Int ) {
        for(index in 1 ..spaces) {
            moveHeadSpaces(direction)
            if(needToMoveSpace(positionOfHead,largeRope[0])){
                largeRope[0] = moveSpace(positionOfHead,largeRope[0])
                for(values in 0..6){
                    if(needToMoveSpace(largeRope[values],largeRope[values +1]))
                        largeRope[values+1] = moveSpace(largeRope[values], largeRope[values +1])
                }
                if(needToMoveSpace(largeRope[7],tailPosition)){
                    tailPosition = moveSpace(largeRope[7], tailPosition)
                    if(!listOfTailPositions.contains(tailPosition)) {
                        listOfTailPositions.add(tailPosition)
                    }
                }
            }
        }
    }

    private fun moveSpace(headSpace : Pair<Int,Int>, tailSpace : Pair<Int, Int>): Pair<Int, Int> {
        var tailSpaceMoving = tailSpace.copy()
        //Negative means "up" (3,3) -> (3,2)
        if(headSpace.second - tailSpaceMoving.second < 0 ){
            tailSpaceMoving = tailSpaceMoving.copy(tailSpaceMoving.first,second = tailSpaceMoving.second-1)
        }
        else if(headSpace.second - tailSpaceMoving.second > 0){
            tailSpaceMoving = tailSpaceMoving.copy(tailSpaceMoving.first,second = tailSpaceMoving.second + 1)
        }
        //Move "Left" so negative X
        if(headSpace.first - tailSpaceMoving.first < 0){
            tailSpaceMoving = tailSpaceMoving.copy(first = tailSpaceMoving.first -1, tailSpaceMoving.second)
        }
        else if(headSpace.first - tailSpaceMoving.first > 0){
            tailSpaceMoving = tailSpaceMoving.copy(first = tailSpaceMoving.first +1, tailSpaceMoving.second)
        }
        return tailSpaceMoving
    }

    private fun needToMoveSpace(headSpace : Pair<Int,Int>, tailSpace : Pair<Int, Int>) : Boolean{
        var xDifference = headSpace.first - tailSpace.first
        var yDifference = headSpace.second - tailSpace.second
        var shouldMove = false
        if( xDifference !in -1 .. 1   ) shouldMove = true
        if( yDifference !in -1 .. 1) shouldMove = true
        return  shouldMove
    }
    private fun moveHeadSpaces(direction : String) {
        if(direction == "R") positionOfHead = Pair(positionOfHead.first + 1,positionOfHead.second)
        if(direction == "L") positionOfHead = Pair(positionOfHead.first - 1, positionOfHead.second)
        if(direction == "U") positionOfHead = Pair(positionOfHead.first, positionOfHead.second + 1)
        if(direction == "D") positionOfHead = Pair(positionOfHead.first, positionOfHead.second - 1)
    }
    fun clearOldData(){
        positionOfHead = Pair(0,0)
        tailPosition = Pair(0,0)
        listOfTailPositions = mutableListOf()
        largeRope = mutableListOf()
    }

    fun partTwo(filePath : String){
        listOfTailPositions.add(tailPosition)
        //Need 8 spots 9th is tail.
        for(item in 0..7){
            largeRope.add(tailPosition)
        }
        val inputStream : InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val instructions = it.split("\\s+".toRegex())
            moveHeadPartTwo(instructions[0],instructions[1].toInt())
        }
        println("Number tail positions part two moves = ${listOfTailPositions.size}")
    }

}
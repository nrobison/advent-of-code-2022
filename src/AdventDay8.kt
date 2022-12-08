import java.io.File
import java.io.InputStream

class AdventDay8 {
    private var grid = mutableListOf<MutableList<Int>>()
    private var numberOfTrees = 0
    private var locationsOfFoundTree = mutableListOf<Pair<Int,Int>>()

    fun generateGrid(filePath : String){
        val inputStream : InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            var newGridLine = mutableListOf<Int>()
            for(char in it.toCharArray()){
                newGridLine.add(char.code)
            }
            grid.add(newGridLine)
        }
    }

     fun partTwoSolution(){
        var maxScoreOfViewing = 0
        //Edges will have 0 due to multiplying by 0
        for (index in 1 until grid.size - 1) {
            for (rowItem in 1 until grid[index].size - 1) {
                var currentScore = getViewScore(index,rowItem, grid[index][rowItem])
                if (currentScore > maxScoreOfViewing){
                    maxScoreOfViewing = currentScore
                }
            }
        }
         println("Highest Score = $maxScoreOfViewing")
    }

     fun partOneSolution() {
         findNumberOfVisibleTrees()
         println("Number of Trees found for part 1: $numberOfTrees")
     }

    private fun findNumberOfVisibleTrees() {
        numberOfTrees = 0
        //Left and right edges
        numberOfTrees += (grid[0].size * 2) -4
        //Top and Bottom
        numberOfTrees += (grid.size * 2)
        //Start at second row as first row and last row accounted for
        for( index in 1 until grid.size -1){
            for(rowItem in 1 until grid[index].size-1) {
                var treeSize = grid[index][rowItem]

                    //If visible at all
                    var isVisibleAtAll = 0
                    isVisibleAtAll += checkLeft(index, rowItem, treeSize)
                    isVisibleAtAll += checkRight(index, rowItem, treeSize)
                    isVisibleAtAll += checkTop(index, rowItem, treeSize)
                    isVisibleAtAll += checkBottom(index, rowItem, treeSize)
                    if(isVisibleAtAll > 0 ) {
                        numberOfTrees += 1
                    }

            }
        }
    }

    private fun checkLeft(row :  Int, column : Int, treeSize: Int) : Int {
        //Column index -1 (our current tree should not be counted)
        for(index in column-1  downTo 0){
            if(grid[row][index] >= treeSize )
                return 0
        }
        return 1
    }

    private fun checkRight(row :  Int, column : Int, treeSize : Int) : Int {
        //Column index + 1 (our current tree should not be counted)
        for(index in column + 1 until grid[row].size){
            if(grid[row][index] >= treeSize )
                return 0
        }
        return 1
    }

    private fun checkTop(row :  Int, column : Int, treeSize: Int) : Int {
        //Current Row but up one
        for(index in row-1 downTo 0){
            if(grid[index][column] >= treeSize )
                return 0
        }
        return 1
    }

    private fun checkBottom(row :  Int, column : Int, treeSize: Int) : Int {
       //Current Row but down 1 to start
        for(index in row +1 until grid.size){
            if(grid[index][column] >= treeSize )
                return 0
        }
        return 1
    }

    private fun getViewScore(row: Int, column: Int, treeSize: Int) : Int{
        var leftCount = 0
        var rightCount = 0
        var topCount = 0
        var bottomCount = 0
        for(index in column-1  downTo 0) {
            leftCount += 1
            if (grid[row][index] >= treeSize){
                break
            }
        }
        for(index in column + 1 until grid[row].size){
            rightCount +=1
            if(grid[row][index] >= treeSize ) {
               break
            }
        }
        for(index in row-1 downTo 0){
            topCount += 1
            if(grid[index][column] >= treeSize ) {
               break
            }
        }
        for(index in row +1 until grid.size){
            bottomCount += 1
            if(grid[index][column] >= treeSize ){
                break
            }
        }
        return (leftCount * rightCount * topCount * bottomCount)
    }
}
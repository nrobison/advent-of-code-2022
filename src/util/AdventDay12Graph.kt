package util

import data.AdventDay12Point

class AdventDay12Graph(listPoints : List<String>){

    private var points = listPoints
    //Get the max width, so we don't go out of bounds in calculations
    private val widthOfGraph = points[0].length
    //Get max height, so we don't go out of bounds in calculations
    private val maxHeightOfGraph = points.size
    fun generateNeighbors(point : AdventDay12Point): List<AdventDay12Point> {
        var allPossibleNeighbors = listOf(
            //East
            AdventDay12Point(point.x-1, point.y),
            //North
            AdventDay12Point(point.x,point.y+1),
            //West
            AdventDay12Point(point.x +1, point.y),
            //South
            AdventDay12Point(point.x,point.y-1)
        )
        //We need to filter out any neighbors that might be out of graph bounds
        //from 0 to widthOfGraph and 0 to maxHeightOfGraph
        return allPossibleNeighbors.filter { it.x in 0 until widthOfGraph
                && it.y in 0 until maxHeightOfGraph }
    }

    fun getHeightOfPoint(point : AdventDay12Point) : Int {
        val intAtPoint = points[point.y][point.x].let{
            when(it){
                //E transform to z for height. E is the highest so z + 1
                'E' -> 'z'.code+1
                //S trasforms to a for height. S is the lowest value so a -1
                'S' -> 'a'.code-1
                //Not a special case we just return the char value
                else -> it.code
            }
        }
        return intAtPoint
    }

    //Used for finding first instance of a character
    fun getPointForChar(char: Char) : AdventDay12Point {
        //Get the first instance of the char pair stored
        return  getCharAtPoint().first { it.second == char }.first

    }
    fun getCharAtPoint(): List<Pair<AdventDay12Point, Char>> {
        //Todo come back here and look FlatMap, this could be be simplified
        var listOfPoints = mutableListOf<Pair<AdventDay12Point,Char>>()
        for(yLine in points.indices){
            for(xValue in points[yLine].indices){
                listOfPoints.add(Pair(AdventDay12Point(xValue,yLine),points[yLine][xValue]))
            }
        }
        return listOfPoints
    }


}
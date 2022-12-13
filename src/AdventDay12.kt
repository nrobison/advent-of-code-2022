import data.AdventDay12Point
import util.AdventDay12Graph
import java.io.File
import java.util.LinkedList

class AdventDay12 {

    fun solution(filePath : String){
        //Get a list of strings
        var allPoints = File(filePath).readLines()
        var graphOfPoints = AdventDay12Graph(allPoints)
        var startPoint = graphOfPoints.getPointForChar('S')
        var endPoint = graphOfPoints.getPointForChar('E')

        var resultOfSearchStartToEnd = breadthFirstSearch(startPoint,graphOfPoints, 1)

        //Shortest Path from star
        println("From S to E took " + resultOfSearchStartToEnd[endPoint])

        graphOfPoints = AdventDay12Graph(allPoints)
        //Start at Endpoint and move to S
        var resultOfSearchEndToStart = breadthFirstSearch(endPoint, graphOfPoints, -1)
        var resultWithLowestPoint = graphOfPoints.getCharAtPoint().filter { it.second == 'a' }.map{it.first}
        //Map the points the lowest point to the steps it took. Take the min value aka the shortest path
        var foundResultsMappedToSteps = resultWithLowestPoint.mapNotNull{ resultOfSearchEndToStart[it]}.min()
        println("Shortest Path to a from E $foundResultsMappedToSteps")

    }

    private fun breadthFirstSearch(startPoint : AdventDay12Point, graphToSearch : AdventDay12Graph, valueToMoveBy : Int) : Map<AdventDay12Point, Int>{
        val shortPath = HashMap<AdventDay12Point, Int>()
        val queue = LinkedList<AdventDay12Point>()
        queue.add(startPoint)
        //Start point = 0
        shortPath[startPoint] = 0
        //Basic implementation of BFS algorithm
        while(queue.isNotEmpty()){
            val currentPoint = queue.remove()
            //Get all the valid neighbors of the point
            var neighbors = graphToSearch.generateNeighbors(currentPoint)
            //Make sure it's not already in. Probably could chain these filters
            neighbors = neighbors.filter { it !in shortPath }
            //Remove any invalid points based on height (we can only move up 1)
            if(valueToMoveBy > 0) neighbors = neighbors.filter { graphToSearch.getHeightOfPoint(it) <= graphToSearch.getHeightOfPoint(currentPoint) + 1  }
            if(valueToMoveBy < 0)  neighbors = neighbors.filter { graphToSearch.getHeightOfPoint(it) >= graphToSearch.getHeightOfPoint(currentPoint) - 1  }
            for(item in neighbors){
                queue.add(item)
                shortPath[item] = shortPath.getValue(currentPoint) + 1
            }
        }
        return shortPath
    }
}
import javafx.scene.shape.MoveTo
import java.io.File
import java.io.InputStream
import java.util.*

class AdventDay5 {
    private var products = mutableListOf<Stack<String>>()
    fun craneMovement(){
        val inputStream : InputStream = File("resources/day_5_crane.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            var lineValues = it.split("\\s+".toRegex())
            var numberToMove = lineValues[1].toInt()
            var moveFrom = lineValues[3].toInt()-1
            var moveTo = lineValues[5].toInt()-1
            moveBoxes(numberToMove,moveFrom,moveTo)
        }
        printRows()
    }

    fun craneCrateMovement9001(){
        val inputStream : InputStream = File("resources/day_5_crane.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            var lineValues = it.split("\\s+".toRegex())
            var numberToMove = lineValues[1].toInt()
            var moveFrom = lineValues[3].toInt()-1
            var moveTo = lineValues[5].toInt()-1
            crateMover9001Move(numberToMove,moveFrom,moveTo)
        }
        printRows()
    }

    private fun moveBoxes(numberToMove: Int, moveFrom : Int, moveTo: Int) {
        for (i in 1..numberToMove){
                var moving = products[moveFrom].pop()
                products[moveTo].add(moving)
        }
    }

    private fun crateMover9001Move(numberToMove: Int, moveFrom : Int, moveTo: Int){
        var keepOrder = mutableListOf<String>()
        for (i in 1..numberToMove){
            var moving = products[moveFrom].pop()
            keepOrder.add(0, moving)
        }
        products[moveTo].addAll(keepOrder)
    }

    private fun printRows(){
        for(i in 0 until products.size){
            var row = "Row #$i TOP TO BOTTOM contains:"
            while(products[i].isNotEmpty()){
                var poppedValue = products[i].pop()
                row += " $poppedValue"
            }
            println(row)
        }
    }
    fun buildStacksForAdventChallenge() {
        /*
        There is probably a better way to implement this. Reading from the file is difficult
        Builds the 9 rows
         */
        generateSingleStackAddToProducts(listOf("F","C","P","G","Q","R"))
        generateSingleStackAddToProducts(listOf("W","T","C","P"))
        generateSingleStackAddToProducts(listOf("B","H","P","M","C"))
        generateSingleStackAddToProducts(listOf("L","T","Q","S","M","P","R"))
        generateSingleStackAddToProducts(listOf("P","H","J","Z","V","G","N"))
        generateSingleStackAddToProducts(listOf("D","P","J"))
        generateSingleStackAddToProducts(listOf("L","G","P","Z","F","J","T","R"))
        generateSingleStackAddToProducts(listOf("N","L","H","C","F","P","T","J"))
        generateSingleStackAddToProducts(listOf("G","V","Z","Q","H","T","C","W"))
    }

    private fun generateSingleStackAddToProducts(items : List<String>){
        var row = Stack<String>()
        row.addAll(items)
        products.add(row)
    }

    fun clearProductStacks(){
        products.clear()
    }
}
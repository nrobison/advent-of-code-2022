import javafx.scene.shape.MoveTo
import java.io.File
import java.io.InputStream
import java.util.*

class AdventDay5 {
    private var products = mutableListOf<MutableList<String>>()
    fun craneMovement(){
        val inputStream : InputStream = File("resources/day_5_crane.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            var lineValues = it.split("\\s+".toRegex())
            if(lineValues[0] == "move") {
                var numberToMove = lineValues[1].toInt()
                var moveFrom = lineValues[3].toInt() - 1
                var moveTo = lineValues[5].toInt() - 1
                moveBoxes(numberToMove, moveFrom, moveTo)
            }
        }
        printRows()
    }

    fun craneCrateMovement9001(){
        val inputStream : InputStream = File("resources/day_5_crane.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            var lineValues = it.split("\\s+".toRegex())
            if(lineValues[0] == "move") {
                var numberToMove = lineValues[1].toInt()
                var moveFrom = lineValues[3].toInt() - 1
                var moveTo = lineValues[5].toInt() - 1
                crateMover9001Move(numberToMove, moveFrom, moveTo)
            }
        }
        printRows()
    }

    private fun moveBoxes(numberToMove: Int, moveFrom : Int, moveTo: Int) {
        for (i in 1..numberToMove){
                var moving = products[moveFrom].removeLast()
                products[moveTo].add(moving)
        }
    }

    private fun crateMover9001Move(numberToMove: Int, moveFrom : Int, moveTo: Int){
        var keepOrder = mutableListOf<String>()
        for (i in 1..numberToMove){
            var moving = products[moveFrom].removeLast()
            keepOrder.add(0, moving)
        }
        products[moveTo].addAll(keepOrder)
    }

    private fun printRows(){
        for(i in 0 until products.size){
            var row = "Row #$i TOP TO BOTTOM contains:"
            while(products[i].isNotEmpty()){
                var poppedValue = products[i].removeLast()
                row += " $poppedValue"
            }
            println(row)
        }
    }


    fun clearProductStacks(){
        products.clear()
    }

    fun dynamicGenerate(){
        val inputStream : InputStream = File("resources/day_5_crane.txt").inputStream()
        inputStream.bufferedReader().forEachLine {
            var lineValues = it.toCharArray()
            if (lineValues.isNotEmpty() &&  lineValues[0] != 'm'){
                if(products.size != (it.length/4) + 1){
                    while(products.size != (it.length/4) + 1)
                        products.add(mutableListOf<String>())
                }
                if(!lineValues[1].isDigit()){
                    for(i in lineValues.indices){
                            if(lineValues[i].isLetter()) {
                                products[i/4].add(0,lineValues[i].toString())
                            }
                    }
                }
            }
            else{
                return@forEachLine
            }
        }
    }
}
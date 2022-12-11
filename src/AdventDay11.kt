import util.AdventDay11Monkey
import java.io.File
import java.io.InputStream

class AdventDay11{
    private var listOfMonkeys = mutableListOf<AdventDay11Monkey>()

    fun loadMonkeys(filePath : String) {
        var listOfItems = mutableListOf<Long>()
        var operation = mutableListOf<String>()
        var tests = mutableListOf<String>()
        var passToTrue = 0
        var passToFalse = 0
        val inputStream: InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            var lineSplit = it.trim().split("\\s+".toRegex())
            if(lineSplit[0] == "Monkey" && lineSplit[1] != "0:"){
                var monkeyToAdd = AdventDay11Monkey(listOfItems,operation,tests,passToTrue,passToFalse)
                listOfMonkeys.add(monkeyToAdd)
                //Clear would keep reference in other monkeys. NEW needed
                listOfItems = mutableListOf()
                operation = mutableListOf()
                tests = mutableListOf()
            }
            else if(lineSplit[0] == "Starting"){
                for(index in 2 until lineSplit.size){
                    var number = lineSplit[index].replace(",","").toLong()
                    listOfItems.add(number)
                }
            }
            else if(lineSplit[0] == "Operation:"){
                //left value
                operation.add(lineSplit[3])
                // operation
                operation.add(lineSplit[4])
                // right value
                operation.add(lineSplit[5])
            }
            else if(lineSplit[0] == "Test:"){
                //Operation
                tests.add(lineSplit[1])
                //value
                tests.add(lineSplit[3])
            }
            else if(lineSplit[0] == "If" && lineSplit[1] == "true:"){
                passToTrue = lineSplit[5].toInt()
            }
            else if(lineSplit[0] == "If" && lineSplit[1] == "false:"){
                passToFalse = lineSplit[5].toInt()
            }
        }
        var monkeyToAdd = AdventDay11Monkey(listOfItems,operation,tests,passToTrue,passToFalse)
        listOfMonkeys.add(monkeyToAdd)
    }

    fun solution(numberOfRounds : Int, worryDivision: Int = 1){
        //A lot of nested loops here not the most performant
        var leastCommonMultiple = getLCMForAll()
        for(round in 1 .. numberOfRounds){
            for(monkey in listOfMonkeys){
                for(number in 0 until monkey.itemsHolding.size){
                    //for(items in monkey.itemsHolding){
                    //Monkey Examines it
                    var worryValue = monkey.calculateOperation(monkey.itemsHolding[0])
                    //Worry level divided by worryDivision and mod it by LCM
                    worryValue =  (worryValue / worryDivision) % leastCommonMultiple
                    //Should throw?
                    if(monkey.shouldThrow(worryValue)) listOfMonkeys[monkey.passToTrue].addNewItem(worryValue)
                    else  listOfMonkeys[monkey.passToFalse].addNewItem(worryValue)
                    monkey.removeItemAfterToss(monkey.itemsHolding[0])

                }
            }
        }
        printLargestInspections()
    }

    fun clearData(){
        listOfMonkeys.clear()
    }
    fun printLargestInspections(){
        //Overkill for what is asked probably could use a sort
        var highestCount = Pair(0,0)
        var secondHighest = Pair(0,0)
        for( position in 0 until listOfMonkeys.size){
            if(listOfMonkeys[position].numberOfInspections > highestCount.second){
                secondHighest = highestCount.copy()
                highestCount = Pair(position,listOfMonkeys[position].numberOfInspections)
            }
            else if(listOfMonkeys[position].numberOfInspections > secondHighest.second){
                secondHighest = Pair(position,listOfMonkeys[position].numberOfInspections)
            }
        }
        var valueFound : Long = highestCount.second.toLong() * secondHighest.second.toLong()
        println("Value of two highest monkeys ${highestCount.first} and ${secondHighest.first} are : $valueFound")
    }

    fun getLCMForAll() : Int{
        var allCurrentInputs = mutableListOf<Int>()
        listOfMonkeys.forEach{
            allCurrentInputs.add(it.test[1].toInt())
        }
        var result = allCurrentInputs[0]
        for(index in 1 until allCurrentInputs.size){
            result = getLCM(result,allCurrentInputs[index])
        }
        return result
    }
    fun getLCM (valueOne : Int, valueTwo :Int) : Int {
        return valueOne * (valueTwo / gcd(valueOne,valueTwo))
    }

    private fun gcd(firstValue : Int, secondValue : Int) : Int{
        var first = firstValue
        var second = secondValue
        while (second > 0){
            var tempValue = second
            second = first % second
            first =  tempValue
        }
        return first
    }

}
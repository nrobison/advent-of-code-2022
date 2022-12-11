package util

class AdventDay11Monkey(items : MutableList<Long>, operations : MutableList<String>, test : MutableList<String>, isTrue : Int, isFalse : Int ) {
    var itemsHolding = items
        private set
    private var operation = operations
    var test = test
        private set
    var passToTrue = isTrue
        private set
    var passToFalse = isFalse
        private set
    var numberOfInspections = 0
        private set
    fun calculateOperation(itemToUpdate : Long) : Long {
        var leftValue : Long = if(operation[0] == "old") {
            itemToUpdate
        } else operation[0].toLong()
        var rightValue : Long = if(operation[2] == "old") {
            itemToUpdate
        } else operation[2].toLong()

        when(operation[1]){
            "*" -> {
                return leftValue * rightValue
            }
            "+" ->{
                return leftValue + rightValue
            }
            "/" -> {
                return leftValue / rightValue
            }
            "-" -> {
                return leftValue - rightValue
            }
        }
        return itemToUpdate
    }

    fun shouldThrow(valueToCheck : Long) : Boolean {
        numberOfInspections += 1
        when(test[0]){
            "divisible" ->{
                return valueToCheck % test[1].toLong() == 0.toLong()
            }
        }
        return false
    }

    fun removeItemAfterToss(removeValue : Long){
        itemsHolding.remove(removeValue)
    }

    fun addNewItem(item : Long){
        itemsHolding.add(item)
    }

}
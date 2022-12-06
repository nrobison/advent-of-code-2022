import java.util.Stack

fun main(args : Array<String>){
    var dayOne = AdventDay1()
    dayOne.calculateCalories()
    dayOne.calculateTopThreeCalories()
    var dayTwo = AdventDay2()
    dayTwo.firstStrategyGuide()
    dayTwo.secondStrategyGuide()
    var dayThree = AdventDay3()
    dayThree.partOneRucksacks()
    dayThree.partTwoRucksacks()
    var dayFour = AdventDay4()
    dayFour.CleanupOverlapOne()

    var dayFive = AdventDay5()
    dayFive.dynamicGenerate()
    dayFive.craneMovement()
    //Need to clear modified stack
    dayFive.clearProductStacks()
    //Build back original stack
    dayFive.dynamicGenerate()
    dayFive.craneCrateMovement9001()
}


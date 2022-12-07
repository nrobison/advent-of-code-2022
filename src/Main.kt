import java.util.Stack

fun main(args : Array<String>){
    val dayOne = AdventDay1()
    dayOne.calculateCalories()
    dayOne.calculateTopThreeCalories()
    val dayTwo = AdventDay2()
    dayTwo.firstStrategyGuide()
    dayTwo.secondStrategyGuide()
    val dayThree = AdventDay3()
    dayThree.partOneRucksacks()
    dayThree.partTwoRucksacks()
    val dayFour = AdventDay4()
    dayFour.CleanupOverlapOne()

    val dayFive = AdventDay5()
    dayFive.dynamicGenerate()
    dayFive.craneMovement()
    //Need to clear modified stack
    dayFive.clearProductStacks()
    //Build back original stack
    dayFive.dynamicGenerate()
    dayFive.craneCrateMovement9001()

    val daySix = AdventDay6()
    daySix.tuningTrouble(4)
    daySix.tuningTrouble(14)

}


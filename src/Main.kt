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

    val daySeven = AdventDay7()
    daySeven.generateFileStructure("resources/day_7_instructions.txt")
    daySeven.findLargeFiles()
    daySeven.findDirectoryToClear(70000000,30000000)

    val dayEight = AdventDay8()
    dayEight.generateGrid("resources/day_8_trees.txt")
    dayEight.partOneSolution()
    dayEight.partTwoSolution()

    val dayNine = AdventDay9()
    dayNine.partOne("resources/day_9_input.txt")
    dayNine.clearOldData()
    dayNine.partTwo("resources/day_9_input.txt")

    val dayTen = AdventDay10()
    dayTen.partOne("resources/day_10_input.txt")
    dayTen.clearData()
    dayTen.partTwo("resources/day_10_input.txt")


}


import java.io.File
import java.io.InputStream

class AdventDay6 {
    private var characterList = mutableListOf<Char>()
    fun tuningTrouble(messageLength : Int){
        val inputStream: InputStream = File("resources/day_6_communication.txt").inputStream()
        //Input is a single large line of text
        val inputCharArray = inputStream.bufferedReader().readLine().toCharArray()
        //Clear out List if not empty
        if(characterList.isNotEmpty()) characterList = mutableListOf<Char>()
        var indexOfStartOfMessage = 0
        for(charIndex in inputCharArray.indices){
            val currentCharacter = inputCharArray[charIndex]
            if(!characterList.contains(currentCharacter)){
                //Adding one more would be the correct size request so we can stop
                if(characterList.size == messageLength-1){
                    characterList.add(currentCharacter)
                    //Start of message is one further
                    indexOfStartOfMessage = charIndex+1
                    break
                }
                else{
                    characterList.add(currentCharacter)
                }
            }
            else{
                //Item exists in list. Remove items until we clear repeats
                //For example: ABJ new char is B so new stack should be JB
                val indexToRemoveUntil = characterList.indexOf(currentCharacter)
                removeItemsFromCharList(indexToRemoveUntil)
                characterList.add(currentCharacter)
            }
        }
        println("Found start of message: $indexOfStartOfMessage and items are: $characterList")
    }

    private fun removeItemsFromCharList(removeUntil : Int) {
        for(index in 0.. removeUntil){
            characterList.removeAt(0)
        }
    }

}
import data.Advent7File
import util.AdventDay7Directory
import java.io.File
import java.io.InputStream

class AdventDay7 {
    private var baseDirectory = AdventDay7Directory("/")
    private var currentDirectory = baseDirectory
    private var smallDirectories = 0
    private var directorySizeNeededToClear = 0
    private var smallestSizeNeededToMeetRequirements = 0
    fun generateFileStructure(filePath : String){
        val inputStream : InputStream = File(filePath).inputStream()
        inputStream.bufferedReader().forEachLine {
            val splitLineBySpace = it.split(("\\s+".toRegex()))
            if(splitLineBySpace[0] == "$"){
                    if(splitLineBySpace[1] == "cd") {
                        moveCurrentDirectory(splitLineBySpace[2])
                    }
                    //We don't really care about ls command currently.
                }
            if(splitLineBySpace[0] == "dir"){
                createDirectory(splitLineBySpace[1],currentDirectory)
            }

            if(splitLineBySpace[0].toIntOrNull() != null){
                createFile(splitLineBySpace[1],splitLineBySpace[0].toInt())
            }

        }
    }

    fun findLargeFiles(){
        for(directories in baseDirectory.subDirectories){
            buildAndFindFilesAtLeastOneHundredThousand(directories)
        }
        println("Number of directories at or under 100,000: $smallDirectories")
    }

    fun findDirectoryToClear(diskSize: Int, unusedSizeNeeded : Int){
        var baseDirectorySize = baseDirectory.calculateSize()
        var neededSpace = baseDirectorySize - unusedSizeNeeded
        directorySizeNeededToClear =  unusedSizeNeeded - neededSpace
        smallestSizeNeededToMeetRequirements = neededSpace
        for(directories in baseDirectory.subDirectories){
            directorySizeDifference(directories)
        }
        println("Smallest directory size to clear enough space: $smallestSizeNeededToMeetRequirements")
    }

    private fun directorySizeDifference(directory : AdventDay7Directory){
        var sizeOfDirectory = directory.calculateSize()
        if(sizeOfDirectory >= directorySizeNeededToClear) {
            calculateIfSizeIsSmaller(sizeOfDirectory)
            for (directories in directory.subDirectories) {
                directorySizeDifference(directories)
            }
        }
    }

    private fun calculateIfSizeIsSmaller(currentSize : Int){
            if(currentSize in directorySizeNeededToClear until smallestSizeNeededToMeetRequirements){
                    smallestSizeNeededToMeetRequirements = currentSize
            }
    }

    private fun buildAndFindFilesAtLeastOneHundredThousand(directoryToCount : AdventDay7Directory ){
        var sizeOfDirectory = directoryToCount.calculateSize()
        if( sizeOfDirectory <= 100000){
            smallDirectories += sizeOfDirectory
        }
        for(directory in directoryToCount.subDirectories){
                buildAndFindFilesAtLeastOneHundredThousand(directory)
            }
    }


    private fun createDirectory(directoryName : String, parentDirectory: AdventDay7Directory){
        if(currentDirectory.findDirectory(directoryName) == null) {
            var newDirectory = AdventDay7Directory(directoryName, currentDirectory)
            currentDirectory.addSubdirectory(newDirectory)
        }
    }

    private fun createFile(fileName : String, fileSize : Int) {
        if(currentDirectory.findFile(fileName) == null) {
            val file = Advent7File(fileName, fileSize)
            currentDirectory.addFile(file)
        }
    }

    private fun moveCurrentDirectory(path : String){
        if(path == ".." && currentDirectory.previousAdventDay7Directory != null){
            currentDirectory = currentDirectory.previousAdventDay7Directory!!
        }
        if( path == "/"){
            currentDirectory = baseDirectory
        }
        else{
            var newDirectory = currentDirectory.findDirectory(path)
            if(newDirectory != null){
                currentDirectory = newDirectory
            }
        }
    }


}
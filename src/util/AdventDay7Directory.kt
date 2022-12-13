package util

import data.Advent7File

class AdventDay7Directory(directoryName : String, previousDirectory: AdventDay7Directory? = null ) {
    val path = directoryName
    var diskSize = 0
    val previousAdventDay7Directory  = previousDirectory
    var subDirectories = mutableListOf<AdventDay7Directory>()
    private var fileList = mutableListOf<Advent7File>()


    fun calculateSize() :Int {
        var totalSize = diskSize
        for(directories in subDirectories){
            totalSize += directories.calculateSize()
        }
        return totalSize
    }

    fun addFile(newFileToAdd : Advent7File){
        fileList.add(newFileToAdd)
        diskSize += newFileToAdd.fileSize
    }

    fun addSubdirectory( newDirectory : AdventDay7Directory){
        subDirectories.add(newDirectory)
    }

    fun findDirectory(directoryName: String) : AdventDay7Directory? {
        return subDirectories.find{it.path == directoryName }
    }

    fun findFile(file : String) : Advent7File?{
        return fileList.find { it.fileName == file}
    }

}
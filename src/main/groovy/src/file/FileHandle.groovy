package src.file

import src.model.Area

interface FileHandle {
    List<Area> readFile(String filePath)
}

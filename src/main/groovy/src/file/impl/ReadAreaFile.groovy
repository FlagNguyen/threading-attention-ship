package src.file.impl

import src.constant.Constant
import src.file.FileHandle
import src.model.Area

import java.util.logging.Level
import java.util.logging.Logger
import org.apache.commons.io.*

class ReadAreaFile implements FileHandle {

    Logger logger = Logger.getLogger(ReadAreaFile.class.getName())

    @Override
    List<Area> readFile(String filePath) {
        List<Area> areas = new ArrayList<>()
        File inputAreaFile = new File(Constant.AREA_FILE_NAME)
        try {
            List areasLine = FileUtils.readLines(inputAreaFile, "UTF-8")
            for (line in areasLine) {
                if (!line.equals("")) {
                    String[] areaParts = line.split("\\|")
                    areas.add(new Area(areaParts[0], areaParts[1], areaParts[2], areaParts[3], areaParts[4]))
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Error: Can't found this file")
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error: Read line failure")
        }
        return areas
    }
}

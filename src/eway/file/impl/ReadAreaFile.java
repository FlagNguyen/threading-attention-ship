package eway.file.impl;

import eway.constant.Constant;
import eway.model.Area;
import eway.file.FileHandle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadAreaFile implements FileHandle {

    Logger logger = Logger.getLogger(ReadAreaFile.class.getName());

    @Override
    public List<Area> readFile(String filePath) {
        List<Area> areas = new ArrayList<>();
        File file = new File(Constant.AREA_FILE_NAME);
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (!(line=bufferedReader.readLine()).equals("")){
                String[] area = line.split("\\|");
                areas.add(new Area(area[0], area[1], area[2], area[3], area[4]));
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Error: Can't found this file");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error: Read line failure");
        }
        return areas;
    }
}

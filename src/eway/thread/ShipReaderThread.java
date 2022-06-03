package eway.thread;

import eway.Main;
import eway.constant.Constant;
import eway.model.Area;
import eway.model.Ship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipReaderThread implements Runnable {
    Logger logger = Logger.getLogger(ShipReaderThread.class.getName());
    File file = new File(Constant.POSITION_FILE_NAME);

    public void readShipPosition() {
        try {
            RandomAccessFile file = new RandomAccessFile(this.file, "r");
            System.out.println("Thread: Reading position file is working ...");
            while (true) {
                String line;
                while ((line = file.readLine()) == null) {
                    Thread.sleep(1000);
                }
                if (line.isEmpty()) {
                    file.seek(file.getFilePointer());
                    continue;
                }
                String[] lineArray = line.split("\\|");
                int longitude = Integer.parseInt(lineArray[1]);
                int latitude = Integer.parseInt(lineArray[2]);
                Ship ship = new Ship(lineArray[0], longitude, latitude, lineArray[3]);
                Main.SHIP_BLOCKING_QUEUE.put(ship);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Error: Can't found this file");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error: Can't read file");
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Error: Error when add into Blocking Queue");
        } finally {
            System.out.println("Thread: Shut down read position file");
        }
    }


    @Override
    public void run() {
        readShipPosition();
    }
}

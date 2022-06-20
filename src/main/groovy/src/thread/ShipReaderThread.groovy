package src.thread

import src.App
import src.constant.Constant
import src.model.Ship

import java.util.logging.Level
import java.util.logging.Logger

class ShipReaderThread implements Runnable {
    Logger logger = Logger.getLogger(ShipReaderThread.class.getName())
    File shipPositionFile = new File(Constant.POSITION_FILE_PATH)

    void readShipPosition() {
        try {
            RandomAccessFile shipPositionFile = new RandomAccessFile(this.shipPositionFile, "r")
            System.out.println("Thread: Reading position file is working ...")
            while (true) {
                String line
                while ((line = shipPositionFile.readLine()) == null) {
                    Thread.sleep(1000)
                }
                if (line.isEmpty()) {
                    shipPositionFile.seek(shipPositionFile.getFilePointer())
                    continue
                }
                String[] lineArray = line.split("\\|")
                int longitude = Integer.parseInt(lineArray[1])
                int latitude = Integer.parseInt(lineArray[2])
                Ship ship = new Ship(lineArray[0], longitude, latitude, lineArray[3])
                App.SHIP_BLOCKING_QUEUE.put(ship)
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Error: Can't found this file position file")
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error: Can't read file")
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Error: Error when add into Blocking Queue")
        } finally {
            System.out.println("Thread: Shut down read position file")
        }
    }


    @Override
    void run() {
        readShipPosition()
    }
}

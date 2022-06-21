package src.thread

import src.App
import src.model.Area
import src.model.Ship

import java.util.logging.Level
import java.util.logging.Logger

class CheckPositionThread implements Runnable {

    Logger logger = Logger.getLogger(ShipReaderThread.class.getName())

    CheckPositionThread() {}

    synchronized void checkPosition() {
        System.out.println("Thread: Check position is working ...")
        Ship ship

        while (true) {
            try {
                if (App.SHIP_BLOCKING_QUEUE.isEmpty()) {
                    Thread.sleep(1000)
                }

                ship = App.SHIP_BLOCKING_QUEUE.take()
                for (area in App.AREAS) {
                    if (ship.getLongitude() <= area.getLongitudeRight() && ship.getLongitude() >= area.getLongitudeLeft()
                            && (ship.getLatitude() <= area.getLatitudeTop() && ship.getLatitude() >= area.getLatitudeBottom())) {

                        String intrusionAttention = getAttention(ship.getShipCode(), ship.getLongitude(), ship.getLatitude(),
                                ship.getTime(), area.getAreaCode(), true)
                        println "$intrusionAttention"
                        App.ATTENTION_BLOCKING_QUEUE.put(intrusionAttention)
                    } else {
                        String exitAttention = getAttention(ship.getShipCode(), ship.getLongitude(), ship.getLatitude(),
                        ship.getTime(), area.getAreaCode(), false)
                        println "$exitAttention"
                        App.ATTENTION_BLOCKING_QUEUE.put(exitAttention)
                    }
                }
            } catch (InterruptedException ex) {
                logger.log(Level.WARNING, "Error: can't take element from Ship Blocking Queue")
            }
        }
    }

    String getAttention(String shipCode, int shipLongitude, int shipLatitude, String time, String areaCode, boolean status) {
        String shipStatus
        if (status == true) {
            shipStatus = "Canh bao xam nhap vung |"
        }else {
            shipStatus = "Canh bao di ra khoi vung |"
        }
        return shipCode + "|" + shipStatus + areaCode + "|" + shipLongitude + "|" + shipLatitude + "|" + time
    }

    @Override
    void run() {
        checkPosition()
    }
}

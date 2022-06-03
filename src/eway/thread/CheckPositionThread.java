package eway.thread;

import eway.Main;
import eway.model.Area;
import eway.model.Ship;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckPositionThread implements Runnable {

    Logger logger = Logger.getLogger(ShipReaderThread.class.getName());

    public CheckPositionThread() {
    }

    public synchronized void checkPosition() {
        System.out.println("Thread: Check position is working ...");
        Ship ship;


        while (true) {
            try {
                if (Main.SHIP_BLOCKING_QUEUE.isEmpty()) {
                    Thread.sleep(1000);
                }

                ship = Main.SHIP_BLOCKING_QUEUE.take();
                for (Area area : Main.AREAS) {
                    if (ship.getLongitude() <= area.getLongitudeRight() && ship.getLongitude() >= area.getLongitudeLeft() &&
                            (ship.getLatitude() <= area.getLatitudeTop() && ship.getLatitude() >= area.getLatitudeBottom())) {
                        String attention = ship.getShipCode() + "|" +
                                "Canh bao xam nhap vung" + "  |" +
                                area.getAreaCode() + "|" +
                                ship.getLongitude() + "|" +
                                ship.getLatitude() + "|" +
                                ship.getTime();
                        Main.ATTENTION_BLOCKING_QUEUE.put(attention);
                        System.out.println(attention);
                    } else {
                        String attention = ship.getShipCode() + "|" +
                                "Canh bao di ra khoi vung" + "|" +
                                area.getAreaCode() + "|" +
                                ship.getLongitude() + "|" +
                                ship.getLatitude() + "|" +
                                ship.getTime();
                        Main.ATTENTION_BLOCKING_QUEUE.put(attention);
                        System.out.println(attention);
                    }
                }
            } catch (InterruptedException ex) {
                logger.log(Level.WARNING, "Error: can't take element from Ship Blocking Queue");
            }
        }
    }


    @Override
    public void run() {
        checkPosition();
    }
}

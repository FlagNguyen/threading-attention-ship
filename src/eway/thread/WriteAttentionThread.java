package eway.thread;

import eway.Main;
import eway.constant.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteAttentionThread implements Runnable {

    Logger logger = Logger.getLogger(WriteAttentionThread.class.getName());

    public void writeIntoFile() {
        System.out.println("Thread: Write into file is working ...");
        while (true) {
            BufferedWriter bufferedWriter;
            try {
                File file = new File(Constant.OUTPUT_FILE_PATH);
                FileWriter fileWriter = new FileWriter(file, true);
                bufferedWriter = new BufferedWriter(fileWriter);

                if (Main.ATTENTION_BLOCKING_QUEUE.isEmpty()) {
                    Thread.sleep(1000);
                }

                String attention = Main.ATTENTION_BLOCKING_QUEUE.take();
                bufferedWriter.write(attention + "\n");
                bufferedWriter.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error when write into file");
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Error in thread write into file");
            }
//            try {
//                bufferedWriter.flush();
//            } catch (IOException e) {
//                logger.log(Level.WARNING, "Cannot close file");
//            }
        }
    }

    @Override
    public void run() {
        writeIntoFile();
    }
}

package src.thread


import src.App
import src.constant.Constant

import java.util.logging.Logger

class WriteAttentionThread implements Runnable {

    Logger logger = Logger.getLogger(WriteAttentionThread.class.getName())

    void writeIntoFile() {
        System.out.println("Thread: Write into file is working ...")
        while (true) {
            BufferedWriter bufferedWriter
            try {
                File outputAttentionFile = new File(Constant.OUTPUT_FILE_PATH)
                FileWriter fileWriter = new FileWriter(outputAttentionFile, true)
                bufferedWriter = new BufferedWriter(fileWriter)

                if (App.ATTENTION_BLOCKING_QUEUE.isEmpty()) {
                    Thread.sleep(1000)
                }

                String attention = App.ATTENTION_BLOCKING_QUEUE.take() + "\n"
                bufferedWriter.write(attention)
                bufferedWriter.close()
            } catch (IOException e) {
                logger.warning("Error when write into file")
            } catch (InterruptedException e) {
                logger.warning("Error in thread write into file")
            }
        }
    }

    @Override
    void run() {
        writeIntoFile()
    }
}

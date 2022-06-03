package eway;

import eway.constant.Constant;
import eway.file.FileHandle;
import eway.file.impl.ReadAreaFile;
import eway.model.Area;
import eway.model.Ship;
import eway.thread.CheckPositionThread;
import eway.thread.ShipReaderThread;
import eway.thread.WriteAttentionThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    //Create blocking queue for ship with unlimited capacity
    public static BlockingQueue<Ship> SHIP_BLOCKING_QUEUE = new ArrayBlockingQueue<>(10000);
    public static BlockingQueue<String> ATTENTION_BLOCKING_QUEUE = new ArrayBlockingQueue<>(100);
    static final FileHandle fileHandle = new ReadAreaFile();
    public static List<Area> AREAS = new ArrayList<>();


    public static void main(String[] args) {
        AREAS = fileHandle.readFile(Constant.AREA_FILE_PATH);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CheckPositionThread checkPositionThread = new CheckPositionThread();
        ShipReaderThread shipReaderThread = new ShipReaderThread();
        WriteAttentionThread writeAttentionThread = new WriteAttentionThread();

        executorService.execute(writeAttentionThread);
        executorService.execute(shipReaderThread);
        executorService.execute(checkPositionThread);
    }
}

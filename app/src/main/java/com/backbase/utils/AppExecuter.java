package com.backbase.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppExecuter {

    private static ExecutorService ioExecuter;

    private static LogHelper logHelper = new LogHelper(AppExecuter.class);

    public static ExecutorService getIoExecuter() {
        if (ioExecuter == null) {
            ioExecuter = Executors.newSingleThreadExecutor();
        }
        return ioExecuter;
    }


    public static void stopExecutor() {
        if (ioExecuter != null && !ioExecuter.isShutdown()) {
            try {
                ioExecuter.awaitTermination(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logHelper.e(e);
            }
        }
    }



}

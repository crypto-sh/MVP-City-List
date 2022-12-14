package com.backbase.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppExecuter {

    private static ExecutorService ioExecutor;

    private static LogHelper logHelper = new LogHelper(AppExecuter.class);

    public static ExecutorService getIoExecutor() {
        if (ioExecutor == null) {
            ioExecutor = Executors.newSingleThreadExecutor();
        }
        return ioExecutor;
    }


    public static void stopExecutor() {
        if (ioExecutor != null && !ioExecutor.isShutdown()) {
            try {
                ioExecutor.awaitTermination(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logHelper.e(e);
            }
        }
    }



}

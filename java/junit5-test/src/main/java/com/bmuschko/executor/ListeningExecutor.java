package com.bmuschko.executor;

import com.google.common.math.BigDecimalMath;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListeningExecutor {
    private static ListeningExecutorService executorService = null; // Has Transitives

    public ListeningExecutorService getExecutor() {
        if( executorService != null ) {
            return executorService;
        }

        ExecutorService execService = Executors.newSingleThreadExecutor();
        executorService = MoreExecutors.listeningDecorator(execService);
        return executorService;
    }

    public void closeExecutor() {
        if( executorService == null ) {
            return;
        }
        executorService.shutdown();
    }

    public Double getRandomDoubleValue() {
        try {
            ListenableFuture<Double> future = getExecutor().submit(() -> { // Has Transitives
                BigDecimal a = BigDecimal.valueOf(Math.random());
                BigDecimal b = BigDecimal.valueOf(Math.random());
                return BigDecimalMath.roundToDouble(a.add(b), RoundingMode.HALF_UP);
            });
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("You don't get a Random Double! - How unfortunate");
        }
        return null;
    }
}

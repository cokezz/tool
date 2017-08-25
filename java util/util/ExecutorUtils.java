package com.kec.smartx.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ExecutorUtils {
	public static ExecutorService exec = Executors.newFixedThreadPool(5);
	
	public static <T> Future<T> run(Callable<T> task){
		return exec.submit(task);
	}
	
	public static void run(Runnable task){
		exec.submit(task);
	}
}

package com.github.narh.sample;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.narh.sample.task.SimpleTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String... args) throws Exception {
		SimpleTask[] tasks = {
		   new SimpleTask(new URL("https://www.google.com"))
		  ,new SimpleTask(new URL("https://www.yahoo.co.jp"))
		  ,new SimpleTask(new URL("https://www.yahoo.com"))
		};

		ExecutorService service = Executors.newCachedThreadPool();
		if(log.isInfoEnabled()) log.info("-- start main.");
		Arrays.stream(tasks).forEach(service::submit);
		service.shutdown();
		if(log.isInfoEnabled()) log.info("-- end main.");
	}

}

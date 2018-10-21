package com.github.narh.sample.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTask implements Callable<String>{

	final URL url;

	public SimpleTask(final URL url) {
		this.url = url;
	}

	@Override
	public String call() throws Exception {
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		long begin = System.currentTimeMillis();
		if(log.isDebugEnabled()) log.info("-- [" + url + "] open connection.");
		conn.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder contents = new StringBuilder();
		String line;
		while(null != (line = reader.readLine())) contents.append(line);
		reader.close();
		conn.disconnect();
		if(log.isDebugEnabled()) log.info("-- [" + url + "] close connection.");
		if(log.isDebugEnabled()) log.info("-- ["
		  + url
		  + "] "
		  +  Long.toString(System.currentTimeMillis() - begin)
		  + " ms");
		return contents.toString();
	}

}

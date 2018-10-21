package com.github.narh.sample.task;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.URL;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTaskTest {

	@Test
	public void test_疎通確認() throws Exception {
		URL url = new URL("https://www.yahoo.co.jp");
		SimpleTask task = new SimpleTask(url);
		String contents = task.call();
		if(log.isInfoEnabled()) log.info(contents);
		assertThat("コンテンツ取得ができること", contents, is(notNullValue()));
	}
}

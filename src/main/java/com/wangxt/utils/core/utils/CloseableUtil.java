package com.wangxt.utils.core.utils;

import java.io.*;

/**
 * @author wangxt
 * @description 关流帮助类
 * @date 2022/1/24 10:48
 **/
public class CloseableUtil {

	public static void close(Closeable closer) {
		if (null == closer) {
			return;
		}
		try {
			closer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(InputStream closer) {
		if (null == closer) {
			return;
		}
		try {
			closer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(OutputStream closer) {
		if (null == closer) {
			return;
		}
		try {
			closer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(FileOutputStream closer) {
		if (null == closer) {
			return;
		}
		try {
			closer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

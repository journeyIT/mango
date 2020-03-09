package com.louis.mango.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author: journey
 * @Date: 2020/2/18
 * @Time: 1:41 下午
 * @Description: IO相关工具类
 */
public class IOUtils {

	/**
	 * 关闭对象，连接
	 * @param closeable
	 */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}

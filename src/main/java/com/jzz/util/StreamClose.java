package com.jzz.util;

import java.io.*;

/**
 * @author:jzz
 * @date:2020/6/30
 */
public class StreamClose {

    /**
     * 批量关闭流工具
     * @param closeables
     */
    private static void closeAll(Closeable...closeables){
        try {
            for (Closeable closeable : closeables) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量清空流
     * @param flushables
     */
    private static void flushAll(Flushable...flushables){
        try {
            for (Flushable flushable : flushables) {
                flushable.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(Writer writer) {
        flushAll(writer);
        closeAll(writer);
    }

    public static void close(Reader reader) {
        closeAll(reader);
    }

    public static void close(InputStream inputStream) {
        closeAll(inputStream);
    }

    public static void close(OutputStream outputStream) {
        flushAll(outputStream);
        closeAll(outputStream);
    }










}

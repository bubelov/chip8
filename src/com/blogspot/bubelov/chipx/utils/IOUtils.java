package com.blogspot.bubelov.chipx.utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: Igor Bubelov
 * Date: 6/07/12 11:18 PM
 */
public class IOUtils {
    public static void serializeObjectToFile(Object object, File file) throws Exception {
        OutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }

    public static Object deSerializeObjectFromFile(File file) throws Exception {
        InputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object result = objectInputStream.readObject();
        objectInputStream.close();
        return result;
    }

    public static byte[] getBytes(File file) {
        FileInputStream inputStream = null;
        FileChannel channel = null;

        try {
            inputStream = new FileInputStream(file);
            channel = inputStream.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            byte[] bytes = new byte[(int) channel.size()];
            buffer.get(bytes);
            return bytes;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (channel != null) {
                    channel.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

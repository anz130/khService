package com.zhy.zhylib.utils;

import android.database.Cursor;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class IOUtils {
    private IOUtils() {
    }

    private static void throwExceptionIfNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    private static void ensureFileExists(File file) throws IOException {
        throwExceptionIfNull(file);

        if (file.isDirectory()) {
            throw new IOException(file.getAbsolutePath() + " isn't a file.");
        }
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && parent.isDirectory() && !parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new IOException("Create directory "
                            + parent.getAbsolutePath() + " failed.");
                }
            }
            if (!file.createNewFile()) {
                throw new IOException("Create file " + file.getAbsolutePath()
                        + " failed.");
            }
        }
    }

    private static void writeByte(OutputStream os, int b) throws IOException {
        os.write(b);
        os.flush();
    }

    private static void writeByte(OutputStream os, byte[] data, int off, int len)
            throws IOException {
        os.write(data, off, len);
        os.flush();
    }

    public static void writeByte(OutputStream os, byte[] data)
            throws IOException {
        os.write(data);
        os.flush();
    }

    public static void writeByte(File file, byte[] data) throws IOException {
        ensureFileExists(file);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            writeByte(os, data);
        } finally {
            closeSilently(os);
        }

    }

    public static void writeByte(File parent, String filename, byte[] data)
            throws IOException {
        writeByte(new File(parent, filename), data);
    }

    public static void writeByte(String parent, String filename, byte[] data)
            throws IOException {
        writeByte(new File(parent, filename), data);
    }

    public static void writeByte(String pathname, byte[] data)
            throws IOException {
        writeByte(new File(pathname), data);
    }

    public static void writeString(OutputStream os, String text,
                                   String charsetName) throws IOException {
        writeByte(os, text.getBytes(charsetName));
    }

    public static void writeString(File file, String text, String charsetName)
            throws IOException {
        writeByte(file, text.getBytes(charsetName));
    }

    public static void writeString(String pathname, String text,
                                   String charsetName) throws IOException {
        writeByte(pathname, text.getBytes(charsetName));
    }

    public static void writeString(File parent, String filename, String text,
                                   String charsetName) throws IOException {
        writeByte(parent, filename, text.getBytes(charsetName));
    }

    public static void writeString(String parent, String filename, String text,
                                   String charsetName) throws IOException {
        writeByte(parent, filename, text.getBytes(charsetName));
    }

    public static void writeStream(OutputStream os, InputStream in)
            throws IOException {
        throwExceptionIfNull(os);
        throwExceptionIfNull(in);
        byte[] buffer = new byte[1024];
        int c;
        while ((c = in.read(buffer, 0, 1024)) != -1) {
            os.write(buffer, 0, c);
        }
        os.flush();
    }

    public static void writeStream(File target, InputStream in)
            throws IOException {
        ensureFileExists(target);
        FileOutputStream os = new FileOutputStream(target);
        writeStream(os, in);
        closeSilently(os);
    }

    public static void writeStream(String pathname, InputStream in)
            throws IOException {
        writeStream(new File(pathname), in);
    }

    public static void writeStream(String parent, String filename,
                                   InputStream in) throws IOException {
        writeStream(new File(parent, filename), in);
    }

    public static void writeStream(File parent, String filename, InputStream in)
            throws IOException {
        writeStream(new File(parent, filename), in);
    }

    public static void writeObject(OutputStream os, Object object)
            throws IOException {
        throwExceptionIfNull(os);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(object);
        oos.flush();
    }

    public static void writeObject(File file, Object object) throws IOException {
        ensureFileExists(file);
        FileOutputStream os = new FileOutputStream(file);
        writeObject(os, object);
        closeSilently(os);
    }

    public static void writeObject(String pathname, Object object)
            throws IOException {
        writeObject(new File(pathname), object);
    }

    public static void writeObject(String parent, String filename, Object object)
            throws IOException {
        writeObject(new File(parent, filename), object);
    }

    public static void writeObject(File parent, String filename, Object object)
            throws IOException {
        writeObject(new File(parent, filename), object);
    }

    public static byte[] readByte(InputStream in) throws IOException {
        throwExceptionIfNull(in);
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int c;
        while ((c = in.read(buffer, 0, 1024)) != -1) {
            writer.write(buffer, 0, c);
        }
        writer.flush();
        return writer.toByteArray();
    }

    public static byte[] readByte(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] result = readByte(in);
        closeSilently(in);
        return result;
    }

    public static byte[] readByte(String parent, String filename)
            throws IOException {
        return readByte(new File(parent, filename));
    }

    public static byte[] readByte(File parent, String filename)
            throws IOException {
        return readByte(new File(parent, filename));
    }

    public static byte[] readByte(String pathname) throws IOException {
        return readByte(new File(pathname));
    }

    public static String readString(InputStream in, String charsetName)
            throws IOException {
        return new String(readByte(in), charsetName);
    }

    public static String readString(File file, String charsetName)
            throws IOException {
        return new String(readByte(file), charsetName);
    }

    public static String readString(String parent, String filename,
                                    String charsetName) throws IOException {
        return new String(readByte(parent, filename), charsetName);
    }

    public static String readString(File parent, String filename,
                                    String charsetName) throws IOException {
        return new String(readByte(parent, filename), charsetName);
    }

    public static String readString(String pathname, String charsetName)
            throws IOException {
        return new String(readByte(pathname), charsetName);
    }

    private static Object readObject(InputStream in) throws IOException,
            ClassNotFoundException {
        throwExceptionIfNull(in);
        ObjectInputStream oin = new ObjectInputStream(in);
        return oin.readObject();
    }

    public static Object readObject(File file) throws IOException,
            ClassNotFoundException {
        FileInputStream in = null;
        Object result;
        try {
            in = new FileInputStream(file);
            result = readObject(in);
        } finally {
            closeSilently(in);
        }
        return result;
    }

    public static Object readObject(String parent, String filename)
            throws IOException, ClassNotFoundException {
        return readObject(new File(parent, filename));
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void closeCursorSilently(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
            }
        }
    }

    public static Object readObject(File parent, String filename) throws IOException,
            ClassNotFoundException {
        return readObject(new File(parent, filename));
    }

    public static Object readObject(String pathname) throws IOException,
            ClassNotFoundException {
        return readObject(new File(pathname));
    }

}
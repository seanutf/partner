package com.sean.partner.utils.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    private static final String LOG_TAG = FileUtils.class.getName();

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static String CRASH_PATH;
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";

    public static boolean writeStringToFile(String content, File dest) throws IOException {
        return writeStringToFile(content, dest, DEFAULT_ENCODING);
    }

    public static boolean writeStringToFile(String content, File dest, String encoding) throws IOException {
        OutputStream output = null;
        try {
            output = openOutputStream(dest);
            IOUtils.write(content, output, encoding);
            output.flush();
        } finally {
            IOUtils.closeQuietly(output);
            return true;
        }
    }

    public static boolean writeBitmapToFile(Bitmap image, File dest, int quality) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(dest);
            image.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
            return true;
        }
    }

    public static boolean writeBitmapToFile(Bitmap image, File dest) throws IOException {
        return writeBitmapToFile(image, dest, 100);
    }

    public static boolean writeBytesToFile(byte[] bytes, File dest) {
        OutputStream out = null;
        try {
            out = openOutputStream(dest);
            out.write(bytes);
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
            return true;
        }
    }

    public static byte[] readFileToBytes(File file) throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtils.toByteArray(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static Bitmap readFileToBitmap(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, DEFAULT_ENCODING);
    }

    public static String readFileToString(File file, String encoding) throws IOException {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtils.toString(in, encoding);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    private static InputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    private static FileOutputStream openOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            file.createNewFile();
        }
        return new FileOutputStream(file);
    }

    public static void deleteDirectory(File directory) throws IOException {
        if (!directory.exists() || !directory.isDirectory()) {
            return;
        }

        cleanDirectory(directory, null);
        if (!directory.delete()) {
            String message = "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    public static void deleteDirectory(File directory, FileFilter filter) throws IOException {
        if (!directory.exists() || !directory.isDirectory()) {
            return;
        }

        cleanDirectory(directory, filter);
        if (filter.accept(directory)) {
            if (!directory.delete()) {
                String message = "Unable to delete directory " + directory + ".";
                throw new IOException(message);
            }
        }
    }

    public static void cleanDirectory(File directory, FileFilter filter) throws IOException {
        if (!directory.exists() || !directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles(filter);
        if (files == null) {
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (filter.accept(file)) {
                try {
                    if (file.isDirectory())
                        deleteDirectory(file, filter);
                    else
                        deleteFile(file);
                } catch (IOException ioe) {
                    exception = ioe;
                }
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    public static boolean createDir(String base, String name) throws IOException {
        File rootDir = new File(base, name);
        if(!rootDir.exists())
            rootDir.mkdirs();
        return rootDir.exists();
    }

    public static void deleteFile(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (file.exists()) {
                if (!file.delete()) {
                    String message = "Unable to delete file: " + file;
                    throw new IOException(message);
                }
            }
        }
    }

//    public static boolean isFileOlder(File file, long timeMillis) {
//        if (file == null) {
//            throw new IllegalArgumentException("file is null");
//        }
//        if (!file.exists()) {
//            return false;
//        }
//        return file.lastModified() < timeMillis;
//    }

    public static long getLength(File file) {
        long result = 0;
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if(files != null && files.length > 0) {
                    for (File f : files) {
                        result += getLength(f);
                    }
                }
            } else {
                result = file.length();
            }
        }
        return result;
    }

    public static int writeStreamToFile(InputStream is, File dest) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(dest);
            return IOUtils.copy(is, out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

//    public static boolean renameFile(String oldFileUrl, String newFileUrl) {
//        File oldFile = new File(oldFileUrl);
//        File newFile = new File(newFileUrl);
//        return oldFile.renameTo(newFile);
//    }

//    public static boolean copyFile(String srcFile, String destFile) throws IOException {
//        File dest = new File(destFile);
//        if (dest.exists()) {
//            dest.delete();
//        }
//        dest.createNewFile();
//        return copyFile(new File(srcFile), dest);
//    }

    public static boolean copyFile(File srcFile, File destFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(srcFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(destFile));

            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
        }
        finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
        return true;
    }

    /**
     *  从assets目录中复制整个文件夹内容
     *  @param  context  Context 使用CopyFiles类的Activity
     *  @param  oldPath  String  原文件路径  如：/aa
     *  @param  newPath  String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount=0;
                while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
        }
    }

    public static synchronized File getAppCacheDir(final Context context) {
        File appCacheRootDir = null;
        //use external storage first
        // todo 这里需要改一下
//        if (mExternalStorageAvailable && mExternalStorageWriteable) {
//            File cacheRootDir = Environment.getExternalStoragePublicDirectory("cutt");
//            appCacheRootDir = new File(cacheRootDir, "com.cutt.zhiyue.android.app"+appId);
//        } else {
//            appCacheRootDir = context.getCacheDir();
//        }
        if (appCacheRootDir != null) {
            if (!appCacheRootDir.exists())
                appCacheRootDir.mkdirs();
            else {
                if (!appCacheRootDir.isDirectory()) {
                    appCacheRootDir.delete();
                    appCacheRootDir.mkdirs();
                }
            }
        }

        return appCacheRootDir;
    }

    public static File getCrashFile(final Context context, String timeStr){
        CRASH_PATH = context.getExternalFilesDir(null).getPath() + "/crash/";
        File dir = new File(CRASH_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(CRASH_PATH + FILE_NAME + timeStr + FILE_NAME_SUFFIX);

        return file;
    }
}

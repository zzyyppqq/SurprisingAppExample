package com.zyp.util;

import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangyipeng on 2017/6/15.
 */

public class FileUtils {

    /**
     * 复制文件夹活文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException
     */
    public static void copyFile2Folder(String sourceFile, String targetFile) {
        if (TextUtils.isEmpty(sourceFile) || TextUtils.isEmpty(targetFile)) return;
        // 创建目标文件夹
        File dir = new File(targetFile);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        // 获取源文件夹当前下的文件或目录
        File dir2 = new File(sourceFile);
        if (dir2.exists() && dir2.isFile()) {
            //复制文件
            copyFile(dir2, new File(targetFile + dir2.getName()));
            return;
        }
        //复制文件夹
        File[] file = dir2.listFiles();
        if (file == null) return;
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 复制文件
                copyFile(file[i], new File(targetFile + file[i].getName()));
            }
            if (file[i].isDirectory()) {
                // 复制目录
                String sourceDir = sourceFile + File.separator + file[i].getName();
                String targetDir = targetFile + File.separator + file[i].getName();
                copyDirectiory(sourceDir, targetDir);
            }
        }
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = null;
        BufferedInputStream inBuff = null;
        FileOutputStream output = null;
        BufferedOutputStream outBuff = null;
        try {
            input = new FileInputStream(sourceFile);
            inBuff = new BufferedInputStream(input);
            // 新建文件输出流并对它进行缓冲
            output = new FileOutputStream(targetFile);
            outBuff = new BufferedOutputStream(output);
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (inBuff != null) inBuff.close();
                if (outBuff != null) outBuff.close();
                if (output != null) output.close();
                if (input != null) input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir) {
        // 新建目标目录
        File dir = new File(targetDir);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        if (file == null) return;
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new
                        File(new File(targetDir).getAbsolutePath()
                        + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }



    /**
     * get file md5
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getFileMD5(File file) throws NoSuchAlgorithmException, IOException {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
            digest.update(buffer, 0, len);
        }
        in.close();
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }


    /**
     * 删除文件
     * @param fileDir
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileDir, String fileName) {
        try {
            File file = new File(fileDir, fileName);
            if (file.exists()) {
                file.delete();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

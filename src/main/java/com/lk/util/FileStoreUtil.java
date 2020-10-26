package com.lk.util;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 模拟区块链文件存储方式：Guava版
 * @Date 2020/6/28
 * @Author lk
 */
public class FileStoreUtil {

    /**
     * 定义区块链文件大小.
     * 单位KB
     **/
    private static final int FILE_SIZE = 1024;

    /**
     * 将文件写入目标文件：Guava方式.
     * @param targetFileName 目标文件名词
     * @param content 文件内容
     * @return void
     **/
    public static void writeIntoTargetFile(final String targetFileName, final String content) {
        final File newFile = new File(targetFileName);
        try {
            Files.write(content.getBytes(), newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件内容向后追加写入目标文件：FileWriter方式.
     * @param targetFileName 目标文件名词
     * @param content 文件内容
     * @return void
     **/
    public static void appendToTargetFile(final String targetFileName, final String content) {
        try {
            // true表示追加
            final FileWriter writer = new FileWriter(targetFileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件内容向后追加写入目标文件：Guava方式.
     * @param targetFileName 目标文件名词
     * @param content 文件内容
     * @return void
     **/
    public static void appendToTargetFileByGuava(final String targetFileName, final String content) {
        final File file = new File(targetFileName);
        try {
            Files.append(content, file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟区块链内容写入文件.
     * @param content 文件内容
     * @return void
     **/
    public static void writeIntoFile(final String content) {
        try {
            final File root = new File(".//");
            // 获取当前文件下的所有文件
            final File[] files = root.listFiles();
            if (files == null) {
                // 如果根目录下没有任何文件则创建新的文件
                final String targetFileNames = ".//blockchain-"
                        + System.currentTimeMillis() + ".loging";
                appendToTargetFileByGuava(targetFileNames, content);
                return;
            }
            // 如果根目录下有文件
            boolean has = false;
            for (File file : files) {
                final String name = file.getName();
                if (name.endsWith(".loging")
                        && name.startsWith("blockchain-")) {
                    // 有则继续写入
                    System.out.println(file.getPath());
                    appendToTargetFileByGuava(file.getPath(), content);
                    has = true;

                    // 写入后如果文件大小超过固定大小，则将loging后缀转为log后缀
                    if (file.length() >= FILE_SIZE) {
                        final String logPath = file.getPath().replace("loging", "log");
                        final File log = new File(logPath);
                        Files.copy(file, log);
                        // 删除已经写满的loging文件
                        file.delete();
                    }
                }
            }

            // 根目录没有文件则创建新的文件
            if (!has) {
                final String targetFileNames = ".//blockchain-"
                        + System.currentTimeMillis() + ".loging";
                appendToTargetFileByGuava(targetFileNames, content);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟区块链内容的写入.
     * @return void
     **/
    public static void writeIntoBlockFile() {
        List<String> list = new ArrayList<>();

        list.add("AI");
        list.add("BlockChain");
        list.add("BrainScience");

        for (int i = 0; i < 20; i++) {
            list.add(generateVCode(6));
        }
    }

    /**
     * 根据长度生成数字字符串.
     * @param length 长度
     * @return vCode
     **/
    private static String generateVCode(int length) {
        final Long vCode = new Double((Math.random() + 1) * Math.pow(10, length - 1))
                .longValue();
        return vCode.toString();
    }

    public static void main(String[] args) {
        writeIntoBlockFile();
        System.exit(0);
    }
}

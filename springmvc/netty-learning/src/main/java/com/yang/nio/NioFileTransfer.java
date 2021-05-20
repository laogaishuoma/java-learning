package com.yang.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NioFileTransfer {
    public static void main(String[] args) throws IOException {
        // 创建相关流
        File fileIn = new File("/Users/qiaoguoqiang/Downloads/素材/视频/美食.mp4");
        FileInputStream fileInputStream = new FileInputStream(fileIn);

        File fileOut = new File("/Users/qiaoguoqiang/workspace/learning/1.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);

        // 获取两个流对应的fileChannel
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        // 使用transferFrom完成拷贝
        fileOutputStreamChannel.transferFrom(fileInputStreamChannel, 0, fileInputStreamChannel.size());

        // 关闭相关通道和流
        fileInputStreamChannel.close();
        fileOutputStreamChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}

package com.currency.ly.j2se.base.nio.channel;


import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 需求：使用前面学习后的 ByteBuffer(缓冲)和 FileChannel(通道)， 将数据写入到 data.txt 中.
 */
public class ChannelTest {


    public void write(){
        try {
            // 1、字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("E:\\test\\data01.txt");
            // 2、得到字节输出流对应的通道Channel
            FileChannel channel = fos.getChannel();
            // 3、分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            for (int i = 0; i < 10; i++) {
                buffer.clear();//清空缓冲区
                buffer.put(("hello,使用Buffer和channel实现写数据到文件中"+i+"\r\n").getBytes());
                // 4、把缓冲区切换成写出模式
                buffer.flip();
                channel.write(buffer);//将缓冲区的数据写入到文件通道
            }
            channel.close();
            System.out.println("写数据到文件中！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

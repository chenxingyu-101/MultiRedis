package org.HUST.CXY.Reactor;

import com.alibaba.fastjson.JSONObject;
import org.HUST.CXY.Database.DbCommond;
import org.HUST.CXY.Database.DbExcutor;
import org.HUST.CXY.Database.MutilRedisDb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class IOhandler implements Runnable{
    SocketChannel channel;
    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

    IOhandler(SocketChannel socketChannel){
        channel=socketChannel;
    }
    void readAndWrite() throws IOException {
        while (channel.read(byteBuffer)>0){
            //这里好像有问题，如果数据太多，先头触发了read,后面还没到，这个read就结束了。
            //然后后面数据来，又会触发read，关键是如何把两次read在一起处理。
            byteBuffer.flip();
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder=charset.newDecoder();
            CharBuffer charBuffer=decoder.decode(byteBuffer);
            byteBuffer.clear();
            String jsonCommand=charBuffer.toString();
            DbCommond dbCommand= JSONObject.parseObject(jsonCommand, DbCommond.class);//解析命令（这里可以写成Decoder），会参生大量对象，对象池！！
            new DbExcutor(dbCommand,channel).run();
        }
    }
    @Override
    public void run() {
        try {
            readAndWrite();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.HUST.CXY;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import org.HUST.CXY.Database.DbCommond;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                try {
                    SocketChannel socketChannel = SocketChannel.open();
                    socketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 8888));
                    ByteBuffer buffer=ByteBuffer.allocate(1024);
                    DbCommond dbCommand=new DbCommond("Sset","msg","hello");
                    String jsonCommand= JSONObject.toJSONString(dbCommand);
                    buffer=ByteBuffer.wrap(jsonCommand.getBytes());
                    socketChannel.write(buffer);
                    buffer.clear();
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }).start();
        }
        while(true){
        }


    }
}

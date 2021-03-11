package org.HUST.CXY.Client;

import com.alibaba.fastjson.JSONObject;
import org.HUST.CXY.Database.DbCommond;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class UsageDemo {
    public static void main(String[] args) throws IOException {
        String hostname="127.0.0.1";
        int port=8888;
        MutilRediseClient client=new MutilRediseClient(hostname,port);
        String commond="Sset msg hello";
        client.excutor(commond);
    }
}

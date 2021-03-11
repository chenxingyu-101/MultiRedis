package org.HUST.CXY.Database;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DbExcutor implements Runnable{
    DbCommond dbCommand;
    SocketChannel channel;
    MutilRedisDb mutilRedisDb;
    ByteBuffer buffer=ByteBuffer.allocate(1024);
    public DbExcutor(DbCommond dbCommand, SocketChannel channel) {
        this.dbCommand = dbCommand;
        this.channel=channel;
        String config="applicationContext.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        mutilRedisDb=(MutilRedisDb) ac.getBean("db");
    }
    @Override
    public void run() {
        switch (dbCommand.getMethod()){
            case "Sset":{
                mutilRedisDb.dict.put(dbCommand.getKey(),dbCommand.getValue());
                Response response=new Response("OK","null");
                String jsonResponse= JSONObject.toJSONString(response);
                buffer=ByteBuffer.wrap(jsonResponse.getBytes());
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.clear();
                break;
            }
            case "Sget": {
                String jsonResponse;
                if(mutilRedisDb.dict.containsKey(dbCommand.getKey())){
                    String value=mutilRedisDb.dict.get(dbCommand.getKey());
                    Response response=new Response("OK",value);
                    jsonResponse= JSONObject.toJSONString(response);
                }else{
                    Response response=new Response("error","null");
                    jsonResponse= JSONObject.toJSONString(response);
                }
                buffer=ByteBuffer.wrap(jsonResponse.getBytes());
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.clear();
                break;
            }
            default:{
                Response response=new Response("error","null");
                String jsonResponse= JSONObject.toJSONString(response);

                buffer=ByteBuffer.wrap(jsonResponse.getBytes());
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.clear();
            }
        }
    }
}

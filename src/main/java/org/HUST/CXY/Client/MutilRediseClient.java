package org.HUST.CXY.Client;

import org.HUST.CXY.Database.DbCommond;

import java.util.concurrent.Callable;

public class MutilRediseClient implements Callable<String> {
    String hostname;
    int port;
    DbCommond dbCommond;
    public MutilRediseClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    public void excutor(String commond){
        dbCommond=decoder(commond);
    }
    DbCommond decoder(String commond){

        return dbCommond;
    }
    @Override
    public String call() throws Exception {
        return null;
    }
}

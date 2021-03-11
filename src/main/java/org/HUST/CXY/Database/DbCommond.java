package org.HUST.CXY.Database;
/*
* Sset Sget：用于字符串对象
*Ladd,Lget,：用于列表对象(以list模拟)
*/
public class DbCommond {
    String method;
    String key;
    String value;

    public DbCommond(String method, String key, String value) {
        this.method = method;
        this.key = key;
        this.value = value;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

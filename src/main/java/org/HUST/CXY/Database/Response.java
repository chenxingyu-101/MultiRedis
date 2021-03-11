package org.HUST.CXY.Database;

public class Response {
    String state;
    String value;

    public Response(String state, String value) {
        this.state = state;
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

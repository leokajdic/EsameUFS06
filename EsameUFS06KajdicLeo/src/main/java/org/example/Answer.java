package org.example;

import com.google.gson.Gson;

public class Answer {
    private Boolean result;
    private Object data;

    public Answer(Boolean result, Object data) {
        this.result = result;
        this.data = data;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String asJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

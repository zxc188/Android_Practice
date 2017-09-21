package com.example.calculator.Responsebody;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/9/11.
 */

public class API_Source {
    private LinkedList<Length> result;
    private String error_code;
    private String reason;
    public LinkedList<Length> getResult() {
        return result;
    }

    public void setResult(LinkedList<Length> result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "API_Source{" +
                "result=" + result +
                ", error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}

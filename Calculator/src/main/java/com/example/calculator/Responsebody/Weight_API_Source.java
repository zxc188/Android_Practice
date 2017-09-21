package com.example.calculator.Responsebody;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Weight_API_Source {
    private LinkedList<Weight_Body> result;
    private String error_code;
    private String reason;

    public LinkedList<Weight_Body> getResult() {
        return result;
    }

    public String getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }
}

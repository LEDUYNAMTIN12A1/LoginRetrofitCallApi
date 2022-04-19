package com.example.logincallapi;

import java.io.Serializable;

public class Result implements Serializable {
    private int ErrorCode;
    private String ErrorDesc;
    private String Token;
    private String TenCuDan;

    public Result(int errorCode, String errorDesc, String token, String tenCuDan) {
        ErrorCode = errorCode;
        ErrorDesc = errorDesc;
        Token = token;
        TenCuDan = tenCuDan;
    }



    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorDesc() {
        return ErrorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        ErrorDesc = errorDesc;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getTenCuDan() {
        return TenCuDan;
    }

    public void setTenCuDan(String tenCuDan) {
        TenCuDan = tenCuDan;
    }

    @Override
    public String toString() {
        return "result{" +
                "ErrorCode=" + ErrorCode +
                ", ErrorDesc='" + ErrorDesc + '\'' +
                ", Token='" + Token + '\'' +
                ", TenCuDan='" + TenCuDan + '\'' +
                '}';
    }
}

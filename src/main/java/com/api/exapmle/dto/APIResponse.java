package com.api.exapmle.dto;

public class APIResponse<T> {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;
}

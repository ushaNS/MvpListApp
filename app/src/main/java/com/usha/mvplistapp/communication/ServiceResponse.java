package com.usha.mvplistapp.communication;

public class ServiceResponse {
    private int code;
    private Object data;
    private ServiceError ServiceError;

    public ServiceResponse(int code, Object response) {
        this.code = code;
        this.data = response;
    }

    public ServiceResponse(ServiceError ServiceError) {
        this.ServiceError = ServiceError;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {

        return data;
    }
}

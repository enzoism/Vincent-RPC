package com.vincent.request;

import java.io.Serializable;

public class CalculateRpcRequest implements Serializable {

    private static final long serialVersionUID = 7503710091945320739L;
    public static final int PORT = 9091;

    private String method;
    private int a;
    private int b;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "CalculateRpcRequest{" +
                "method='" + method + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }

	public CalculateRpcRequest(String method, int a, int b) {
		super();
		this.method = method;
		this.a = a;
		this.b = b;
	}

	public CalculateRpcRequest() {
		super();
	}
    
}

package com.vincent.consumer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.vincent.provider.Calculator;
import com.vincent.request.CalculateRpcRequest;

public class CalculatorRemoteImpl implements Calculator {
	public int add(int a, int b)  {
        String address = "127.0.0.1";// 因为现在是一个服务，所以地址是写死的，以后用负载的话，对比Eureka，根据服务名，去找对应的端口，然后进行负载
        Socket socket = null;
        try {
        	socket = new Socket(address, CalculateRpcRequest.PORT);
			// 将请求序列化
			CalculateRpcRequest calculateRpcRequest = new CalculateRpcRequest("add", 1, 2);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			// 将请求发给服务提供方
			objectOutputStream.writeObject(calculateRpcRequest);
			// 将响应体反序列化
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			Object response = objectInputStream.readObject();
			System.out.println("消费者进行服务调用，结果为：" + response);
			if (response instanceof Integer) {
				return (Integer) response;
			} else {
				throw new InternalError();
			} 
		} catch (Exception e) {
		}finally{
			if (null!=socket) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
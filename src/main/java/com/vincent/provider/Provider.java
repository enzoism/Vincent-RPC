package com.vincent.provider;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.vincent.request.CalculateRpcRequest;
public class Provider {
	public static void main(String[] args) throws Exception {
		// 将服务提供出去，使用Socket进行模拟
		new Provider().privoderServer();
	}
    private void privoderServer() throws Exception{
    	// 创建Socket监听器
        ServerSocket listener = new ServerSocket(CalculateRpcRequest.PORT);
        Calculator calculator = new CalculatorImpl();
        System.out.println("----------成功将服务发布");
        try {
			// 然后执行监听外界的请求
			while (true) {
				Socket socket = listener.accept();
				// 将请求反序列化
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				Object object = objectInputStream.readObject();

				// 调用服务
				int result = 0;
				if (object instanceof CalculateRpcRequest) {
					CalculateRpcRequest calculateRpcRequest = (CalculateRpcRequest) object;
					if ("add".equals(calculateRpcRequest.getMethod())) {
						result = calculator.add(calculateRpcRequest.getA(), calculateRpcRequest.getB());
					} else {
						throw new UnsupportedOperationException();
					}
				}

				// 返回结果
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectOutputStream.writeObject(new Integer(result));
			} 
		} catch (Exception e) {

		} finally {
            listener.close();
        }
    }
 
}

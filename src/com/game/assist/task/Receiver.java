package com.game.assist.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Socket接收器 用于连续不断地将从套接字通道中接收的信息放入一个队列之中。 然后队列等待用户取出
 */
@SuppressWarnings("unchecked")
public class Receiver extends Thread {
	private Socket socket;
	private BlockingQueue queue;
	private ObjectInputStream in;

	public Receiver(Socket socket, BlockingQueue queue) throws IOException {
		this.socket = socket;
		this.queue = queue;
		in = new ObjectInputStream(socket.getInputStream());
	}

	public void run() {
		try {
			while (true) {
				try {
					Object object = in.readObject();
					queue.put(object);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
		} finally {// 当连接断开之后，不论何各情况，总是将套接字对象本身放入队列
			try {
				in.close();
				queue.put(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

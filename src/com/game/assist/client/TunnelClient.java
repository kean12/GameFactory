package com.game.assist.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.game.assist.task.ConfigUtil;

public class TunnelClient {

	public static Object get(Object msgOut) {
		String strUrl = "http://" + ConfigUtil.getValue("address") + "/servlet/LoginServer.shtml";
		Object msgIn = null;
		try {
			// 准备URL连接
			URL url = new URL(strUrl);
			URLConnection con = url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);

			// 输出流
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);

			// 输出到缓冲区
			out.writeObject(msgOut);
			out.flush();
			byte[] buf = byteOut.toByteArray();
			// 这里可以对缓冲区的内容进行加密处理，保证外网的信息传输安全

			// 输出消息到服务器
			con.setRequestProperty("Content-type", "application/octet-stream");
			con.setRequestProperty("Content-length", "" + buf.length);
			DataOutputStream dataOut = new DataOutputStream(con
					.getOutputStream());

			dataOut.write(buf);
			dataOut.flush();
			dataOut.close();

			// 响应消息流
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			// 接收响应对象消息
			msgIn = in.readObject();
			in.close();
		} catch (MalformedURLException e) {
			// URL url = new URL(strUrl);
			e.printStackTrace();
		} catch (IOException e) {
			// URLConnection con = url.openConnection();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Msg msgIn = (Msg) in.readObject();
			e.printStackTrace();
		}
		return msgIn;
	}
}

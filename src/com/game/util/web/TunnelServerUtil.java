package com.game.util.web;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.assist.server.OnlineUser;
import com.game.assist.task.ConfigUtil;

/**
 * Http协议的Java隧道通讯 对客户端访问的数据库进行处理
 */
public class TunnelServerUtil {

	/**
	 * 获得客户端的发送信息
	 */
	public static Object get(HttpServletRequest request) {
		// 来自客户端的信息
		Object obj = null;
		try {
			ObjectInputStream in = new ObjectInputStream(request.getInputStream());
			obj = in.readObject();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public static void set(Object obj, HttpServletResponse response) {
		try {
			// 输出流
			response.setContentType("application/octet-stream");
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			// 输出对象信息
			out.writeObject(obj);
			out.flush();
			byte[] buf = byteOut.toByteArray();
			// 可以做加密处理
			response.setContentLength(buf.length);
			ServletOutputStream servletOut = response.getOutputStream();
			servletOut.write(buf);
			servletOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置在线工具在线用户
	 */
	private static void setOnlineUser(Object msgOut) {
		String strUrl = "http://" + ConfigUtil.getValue("address") + "/servlet/OnlineUserServer.shtml";
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
			DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());

			dataOut.write(buf);
			dataOut.flush();
			dataOut.close();
			// 响应消息流
			con.getInputStream();
		} catch (MalformedURLException e) {
			// URL url = new URL(strUrl);
			e.printStackTrace();
		} catch (IOException e) {
			// URLConnection con = url.openConnection();
			e.printStackTrace();
		} catch (Exception e) {
			// Msg msgIn = (Msg) in.readObject();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void onlineUserServer(Map<Socket, String> socket_name_map) {
		String name_string = "";
		Iterator it = socket_name_map.keySet().iterator();
		while (it.hasNext()) {
			name_string += socket_name_map.get(it.next()) + ";;";
		}
		OnlineUser onlineUser = new OnlineUser(name_string);
		setOnlineUser(onlineUser);

	}

}
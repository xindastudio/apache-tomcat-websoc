package com.websoc.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 * @author wuliwei
 * 
 */
public class WebSocHandler extends StreamInbound {

	@Override
	protected void onBinaryData(InputStream is) throws IOException {
		System.out.println("on binary data");
	}

	@Override
	protected void onTextData(Reader reader) throws IOException {
		System.out.println("on test data");
		List<Character> cs = new ArrayList<Character>();
		char[] c = new char[1024];
		int len = -1;
		while (-1 != (len = reader.read(c))) {
			for (int i = 0; i < len; i++) {
				cs.add(c[i]);
			}
			System.out.println("cur len = " + cs.size());
		}
		c = new char[cs.size()];
		for (int i = 0; i < c.length; i++) {
			c[i] = cs.get(i);
		}
		send();
		System.out.println(new String(c));
	}

	@Override
	protected void onClose(int pStatus) {
		super.onClose(pStatus);
		System.out.println("on close");
	}

	@Override
	protected void onOpen(WsOutbound pOutbound) {
		super.onOpen(pOutbound);
		send();
		System.out.println("on open");
	}
	
	private void send() {
		CharBuffer msgCb = CharBuffer.allocate(1024);
		for (int i = 0; i < msgCb.capacity(); i++) {
			msgCb.append('a');
		}
		msgCb.flip();
		try {
			int i = 0;
			while (i < 1024 * 50) {
				getWsOutbound().writeTextMessage(msgCb);
				getWsOutbound().flush();
				msgCb.flip();
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

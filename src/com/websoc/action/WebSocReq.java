package com.websoc.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.websoc.core.WebSocHandler;

/**
 * @author wuliwei
 * 
 */
public class WebSocReq extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		return new WebSocHandler();
	}

}

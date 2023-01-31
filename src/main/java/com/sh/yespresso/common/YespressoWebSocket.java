package com.sh.yespresso.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class YespressoWebSocket {


	/**
	 * 사용자의 웹소켓 세션을 관리하는 맵
	 * Key - String memberId
	 * Value - Session session 
	 * 
	 * 멀티쓰레드환경에서 동기화(각 쓰레드가 사용하는 순서를 지정) 필수
	 */
	public static Map<String, Session> clientMap = Collections.synchronizedMap(new HashMap<>());
}

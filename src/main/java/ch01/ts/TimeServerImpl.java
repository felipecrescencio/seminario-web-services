package ch01.ts; // time server

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "ch01.ts.TimeServer")
public class TimeServerImpl implements TimeServer {
	public String getTimeAsString() {
		return new Date().toString();
	}
	public long getTimeAsElapsed() {
		return new Date().getTime();
	}
}

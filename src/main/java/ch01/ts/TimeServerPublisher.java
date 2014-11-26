package ch01.ts; // time server

import javax.xml.ws.Endpoint;


public class TimeServerPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://127.0.0.1:9876/ts", new TimeServerImpl());
	}
}

// /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
// java1.6.0_65
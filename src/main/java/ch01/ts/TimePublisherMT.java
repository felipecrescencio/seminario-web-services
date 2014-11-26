package ch01.ts; // time server

import javax.xml.ws.Endpoint;

public class TimePublisherMT { // MT for multithreaded
	private Endpoint endpoint;

	public static void main(String[] args) {
		TimePublisherMT self = new TimePublisherMT();
		self.create_endpoint();
		self.configure_endpoint();
		self.publish();
	}

	private void create_endpoint() {
		endpoint = Endpoint.create(new TimeServerImpl());
	}

	private void configure_endpoint() {
		endpoint.setExecutor(new MyThreadPool());
	}

	private void publish() {
		int port = 8888;
		String url = "http://localhost:"+ port +"/ts";
		endpoint.publish(url);
		System.out.println("Publishing TimeServer on port "+ port);
	}
}

// /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
// java1.6.0_65

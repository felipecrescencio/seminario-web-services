package ch01.ts; // time server

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TimeClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://127.0.0.1:9876/ts?wsdl");

		/*
		 * Nome qualificado do servico
		 * Primeiro argumento Ž o URI do servico
		 * Segundo Ž o nome publicado no WSDL
		 */

		QName qname = new QName("http://ts.ch01/", "TimeServerImplService");
		
		/*
		 * Cria, de fato, uma fabrica para o servico
		 */
		Service s = Service.create(url, qname);

		/*
		 * Extrai a interface endpoint, o servico port
		 */
		TimeServer eif = s.getPort(TimeServer.class);

		System.out.println(eif.getTimeAsString());
		System.out.println(eif.getTimeAsElapsed());
	}
}

// /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
// java1.6.0_65
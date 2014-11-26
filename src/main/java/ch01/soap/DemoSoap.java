package ch01.soap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

public class DemoSoap {
	private static final String LocalName = "TimeRequest";
	private static final String Namespace = "http://ch01/mysoap";
	private static final String NamespacePrefix = "ms";


	private ByteArrayOutputStream out;
	private ByteArrayInputStream in;

	public static void main(String[] args) {
		new DemoSoap().request();
	}

	private void request() {
		try {
			//Criar uma mensagem SOAP para enviar para um stream de saida
			SOAPMessage msg = create_soap_message();

			/* Injetar a informacao apropriada na mensagem
			 * Neste caso, apenas o cabecalho da mensagem (opcional) é usado e o corpo vai vazio
			 */
			SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
			SOAPHeader hdr = env.getHeader();

			//Adicionar um elemento ao cabecalho SOAP
			Name lookup_name = create_qname(msg);
			hdr.addHeaderElement(lookup_name).addTextNode("time_request");

			//Simular o envio da mensagem SOAP ao sistema remoto escrevendo-o em um ByteArrayOutputStream
			out = new ByteArrayOutputStream();
			msg.writeTo(out);

			trace("The sent SOAP message:", msg);
			
			SOAPMessage response = process_request();
			extract_contents_and_print(response);
		} catch (SOAPException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private SOAPMessage process_request() {
		process_incoming_soap();
		coordinate_streams();
		return create_soap_message(in);
	}

	private void process_incoming_soap() {
		try {
			//copiar stream de output em stream de input para simular streams coordenadas através de uma conexao de rede
			coordinate_streams();

			//Criar a mensagem SOAP recebida a partir do stream de entrada
			SOAPMessage msg = create_soap_message(in);

			//Inspecionar o cabecalho SOAP para a palavra-chave "time-request" e processar a solicitacao se a palavra-chave ocorrer
			Name lookup_name = create_qname(msg);

			SOAPHeader header = msg.getSOAPHeader();
			Iterator it = header.getChildElements(lookup_name);

			Node next = (Node) it.next();

			String value = (next == null) ? "Error!" : next.getValue();

			// Se a mensagem SOAP contiver solicitacao para a hora, criar uma nova mensagem SOAP com a hora atual no corpo
			if(value.toLowerCase().contains("time_request")) {
				//Extrair o corpo e adicionar a hora atual como um elemento
				String now = new Date().toString();
				SOAPBody body = msg.getSOAPBody();
				body.addBodyElement(lookup_name).addTextNode(now);
				msg.saveChanges();

				//Escrever a stream de saida
				msg.writeTo(out);
				trace("The receive/processed SOAP message: ", msg);
			}
		} catch (SOAPException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void extract_contents_and_print(SOAPMessage msg) {
		try {
			SOAPBody body = msg.getSOAPBody();

			Name lookup_name = create_qname(msg);
			Iterator it = body.getChildElements(lookup_name);
			Node next = (Node) it.next();

			String value = (next == null) ? "Error!" : next.getValue();
			System.out.println("\n\nReturned from server: "+ value);
		} catch(SOAPException e) {
			System.err.println(e);
		}
	}

	private SOAPMessage create_soap_message(){
		SOAPMessage msg = null;

		try {
			MessageFactory mf = MessageFactory.newInstance();
			msg = mf.createMessage();
		} catch (SOAPException e) {
			System.err.println(e);
		}
		return msg;
	}

	private SOAPMessage create_soap_message(InputStream in){
		SOAPMessage msg = null;

		try {
			MessageFactory mf = MessageFactory.newInstance();
			msg = mf.createMessage(null, //ignore cabecalhos MIME
					in); //stream de origem
		} catch (SOAPException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		return msg;
	}

	private Name create_qname(SOAPMessage msg) {
		Name name = null;

		try {
			SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
			name = env.createName(LocalName, NamespacePrefix, Namespace);
		} catch(SOAPException e) {
			System.err.println(e);
		}
		return name;
	}

	private void trace(String s, SOAPMessage m) {
		System.out.println("\n");
		System.out.println(s);

		try {
			m.writeTo(System.out);
		} catch (SOAPException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void coordinate_streams() {
		in = new ByteArrayInputStream(out.toByteArray());
		out.reset();
	}
}

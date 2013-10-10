package com.sample.client;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class EchoClient {
	public static void main(String args[]) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://localhost:9090/EchoService";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }
	/*
	private Element buildAuthHeader() {
        Element h = new Element().createElement(NAMESPACE, "Authenticator");

        Element username = new Element().createElement(NAMESPACE, "UserName");
        username.addChild(Node.TEXT, username);
        h.addChild(Node.ELEMENT, username);
        Element pass = new Element().createElement(NAMESPACE, "Password");
        pass.addChild(Node.TEXT, "password)
        h.addChild(Node.ELEMENT, pass);

        return h;
	}*/
	private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String namespaceURI = "http://service.sample.com/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ser", namespaceURI);
        
        SOAPHeader soapHeader = envelope.getHeader();
       // soapHeader.addAttribute(new QName("AuthorizationPolicy"), authorization.getAuthorization());
        /*
        Constructed SOAP Request Message:
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.sample.com/">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <ser:echo>
		         <!--Optional:-->
		         <arg0>?</arg0>
		      </ser:echo>
		   </soapenv:Body>
		</soapenv:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("echo", "ser");
        SOAPElement soapArgElem = soapBodyElem.addChildElement("fullName");
        soapArgElem.addTextNode("Ande Ravi");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", namespaceURI);
       // headers.addHeader("Authorization", authorization.getAuthorization());
        
        String username = "anderavi";
        String password = "andddde004";

        String authorization = new sun.misc.BASE64Encoder().encode((username+":"+password).getBytes());
        MimeHeaders hd = soapMessage.getMimeHeaders();
        hd.addHeader("Authorization", "Basic " + authorization);
        
        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        
        /*AuthorizationPolicy authorization = new AuthorizationPolicy();
        authorization.setUserName("anderavi");
        authorization.setPassword("ande004");
        
        soapMessage.setProperty(AuthorizationPolicy.class.getName(), authorization);*/
        return soapMessage;
    }
	
	private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }


}

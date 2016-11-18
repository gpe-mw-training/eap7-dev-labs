package org.jboss.gpse.eap6ws.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.jboss.gpse.eap6ws.domain.Customer;


/**
 * Test client for the Customer Resource RESTful service
 */
public class CustomerResourceTestClient 
{
	private String BASEURL;   //  = "http://1rhel72guiv108-gpterhel72eap7dev-tmtnis7o.srv.ravcloud.com:8080/RestfulService/rest-service/";
	JAXBContext ctx;
	

	/**
	 * exercise a series of methods to test the REST Service
	 * @throws Exception
	 */
	public void testCustomerResource() throws Exception {
		String host = System.getProperty("VMHOST");
		System.out.println(" ******************* Value of host is " + host);
		BASEURL = "http://" + host + ":8080/RestfulService/rest-service/";
		
		ctx = JAXBContext.newInstance(Customer.class);
		String custURL = createCustomer();
		getCustomer(custURL);
		updateCustomer(custURL);
		getCustomer(custURL);
	}
	
	/**
	 * Use the HTTP POST operation to add a customer to the service
	 * @return
	 * @throws Exception
	 */
	public String createCustomer() throws Exception {
		System.out.println(" **** POST to Create a Customer ****");
		Customer newCustomer = new Customer();
		newCustomer.setFirstName("Randy");
		newCustomer.setLastName("Thomas");
		newCustomer.setStreet("1234 Main St.");
		newCustomer.setCity("Boston");
		newCustomer.setZip("111222");
		newCustomer.setCountry("US");
		newCustomer.setMobileNumber("555-666-7777");
		newCustomer.setEmail("rt@EAP6WS.org");
		
		System.out.println("URL = " + BASEURL + "customers");
		URL postURL = new URL(BASEURL + "customers");
		HttpURLConnection connection = (HttpURLConnection) postURL.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		OutputStream os = connection.getOutputStream();
		os.write(stringCustomer(newCustomer).getBytes());
		os.flush();
		System.out.println("HTTP Response code: " + connection.getResponseCode());
		System.out.println("Location: " + connection.getHeaderField("Location"));
		String newCustURL = connection.getHeaderField("Location");

		connection.disconnect();
		// Return the new Customer resource reference
		return newCustURL;
	}
	
	/**
	 * Use the JAXBContext to convert the Customer class to XML
	 * @param c
	 * @return
	 */
	private String stringCustomer(Customer c)
	{
		StringWriter sw = new StringWriter();
		try {
			ctx.createMarshaller().marshal(c, sw);
		}
		catch (JAXBException jbe) {
			System.out.println(" ***************************** Error in stringCustomer: " + jbe);
			return "";
		}
		return sw.toString();
	}
	
	private Customer unmarshalCustomer(String xml)
	{
		try {
			return (Customer)ctx.createUnmarshaller().unmarshal(new StringReader(xml));
		}
		catch (JAXBException jbe) {
			System.out.println(" ***************************** Error in unmarshalCustomer: " + jbe);
			return null;
		}
	}
	/**
	 * Use the GET command to retrieve the Customer using the passed in connection
	 * @param newCustURL
	 * @throws Exception
	 */
	public void getCustomer(String newCustURL) throws Exception {
		System.out.println(" **** GET Created Customer ****");
		URL getURL = new URL(newCustURL);
		 HttpURLConnection connection = (HttpURLConnection) getURL.openConnection();
		connection.setRequestMethod("GET");
		System.out.println("Content-Type" + connection.getContentType()); 
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder xmlstr = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			xmlstr.append(line);
			line = reader.readLine();
		}
		System.out.println("XML: " + xmlstr.toString());
		Customer c = unmarshalCustomer(xmlstr.toString());
		System.out.println("HTTP Response code: " + connection.getResponseCode());
		System.out.println(c);
		connection.disconnect();
	}
	
	/**
	 * Use the PUT operation to update the customer.
	 * 
	 * The client must know the id of the resource to use the PUT operation.
	 * The operation is idempotent since sending the message more than once has 
	 * no affect on the service.
	 * 
	 * @param newCustURL
	 * @throws Exception
	 */
	public void updateCustomer(String updateCustURL) throws Exception {
		System.out.println(" **** Update A Customer ****");
		Customer updateCustomer = new Customer();
		updateCustomer.setFirstName("Randyolph");  // name changed from Randy to Randolph
		updateCustomer.setLastName("Thomas");
		updateCustomer.setStreet("1234 Main St.");
		updateCustomer.setCity("Boston");
		updateCustomer.setZip("111222");
		updateCustomer.setCountry("US");
		updateCustomer.setMobileNumber("555-666-7777");
		updateCustomer.setEmail("rt@EAP6WS.org");
		
		URL updateURL = new URL(updateCustURL);
		HttpURLConnection connection = (HttpURLConnection) updateURL.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", "application/xml");
		OutputStream os = connection.getOutputStream();
		os.write(stringCustomer(updateCustomer).getBytes());
		os.flush();
		System.out.println("HTTP Response code: " + connection.getResponseCode());
		System.out.println("Should be the HTTP_NO_CONTENT Response code: " + HttpURLConnection.HTTP_NO_CONTENT);
		System.out.println("Location: " + connection.getHeaderField("Location"));
		connection.disconnect();
	}
	
    public static void main( String[] args )
    {
    	CustomerResourceTestClient client = new CustomerResourceTestClient();
    	try {
			client.testCustomerResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

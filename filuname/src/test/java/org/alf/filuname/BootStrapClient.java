package org.alf.filuname;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class BootStrapClient {

	public static void main(String[] args) {

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target("http://localhost:8080");
		String responseMsg = target.path("/report/request").request().get(String.class);

		System.out.println(responseMsg);

	}

}

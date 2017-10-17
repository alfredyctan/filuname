package org.alf.filuname;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.alf.filuname.model.impl.HitCount;
import org.glassfish.jersey.client.ClientProperties;

public class BootStrapClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		WebTarget loginTarget = client.target("http://localhost:10080/login").property(ClientProperties.FOLLOW_REDIRECTS, "false");
		
		Form form = new Form();
		form.param("username", "admin");
		form.param("password", "admin");
		Response loginResponse = loginTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

		String jSessionID = loginResponse.getCookies().get("JSESSIONID").getValue();
		System.out.println("response cookies : " + jSessionID);
		
		
		WebTarget target = client.target("http://localhost:10080/servlet/report");
		List<HitCount> actual = target.path("/request").queryParam("date", "2016-01-06").request(MediaType.APPLICATION_JSON).cookie("JSESSIONID", jSessionID).get(new GenericType<List<HitCount>> () {});
		
		for (HitCount hitCount : actual) {
			System.out.println("HitCount : " +  hitCount);
		}
				
	}
}

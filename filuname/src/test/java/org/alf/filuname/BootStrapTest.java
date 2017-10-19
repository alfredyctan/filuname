package org.alf.filuname;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.alf.filuname.model.impl.HitCount;
import org.alf.filuname.util.JUnit4Util;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IDEFilunameLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
public class BootStrapTest {

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Test
	public void testGetTop5() {
		JUnit4Util.startCurrentTest(getClass());

		Client client = ClientBuilder.newClient();
		
		WebTarget loginTarget = client.target("http://localhost:" + port + "/login").property(ClientProperties.FOLLOW_REDIRECTS, "false");
		
		Form form = new Form();
		form.param("username", "admin");
		form.param("password", "admin");
		Response loginResponse = loginTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

		String jSessionID = loginResponse.getCookies().get("JSESSIONID").getValue();

		WebTarget target = client.target("http://localhost:" + port + "/servlet/report");
		List<HitCount> actual = target.path("/request").queryParam("date", "2016-01-06").request(MediaType.APPLICATION_JSON).cookie("JSESSIONID", jSessionID).get(new GenericType<List<HitCount>> () {});
		
		for (HitCount hitCount : actual) {
			System.out.println("HitCount : " +  hitCount);
		}
		
		assertThat("top 5", actual,
			containsInAnyOrder(
				new HitCount("2016-01-06", "ninemsn.com.au",    21734381),
				new HitCount("2016-01-06", "www.ebay.com.au",   19831166),
				new HitCount("2016-01-06", "www.bing.com",      14065457),
				new HitCount("2016-01-06", "www.wikipedia.org", 13246531),
				new HitCount("2016-01-06", "au.yahoo.com",      11492756)
			)
		);
		
		JUnit4Util.endCurrentTest(getClass());
	}
}

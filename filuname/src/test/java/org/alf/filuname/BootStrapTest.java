package org.alf.filuname;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.alf.filuname.model.impl.HitCount;
import org.alf.filuname.util.JUnit4Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilunameLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
public class BootStrapTest {

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Test
	public void testGetTop5() {
		JUnit4Util.startCurrentTest(getClass());
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target("http://localhost:" + port + "/servlet/report");
		List<HitCount> actual = target.path("/request").queryParam("date", "2016-01-06").request(MediaType.APPLICATION_JSON).get(new GenericType<List<HitCount>> () {});
		
		for (HitCount hitCount : actual) {
			System.out.println("HitCount : " +  hitCount);
		}
		
		assertThat("top 5", actual,
			containsInAnyOrder(
				new HitCount("2016-01-06", "www.facebook.com", 104346720),
				new HitCount("2016-01-06", "www.google.com", 26165099),
				new HitCount("2016-01-06", "mail.live.com", 21536612),
				new HitCount("2016-01-06", "www.ebay.com.au", 19831166),
				new HitCount("2016-01-06", "www.bing.com", 14065457)
			)
		);
		
		JUnit4Util.endCurrentTest(getClass());
	}
}

package com.demo.project;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.project.model.Campaign;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes = DemoProjectApplication.class)
class DemoProjectApplicationTests {


	@Test
	public void testAddCampaignSuccess() {
		RestTemplate restTemplate = new RestTemplate();
		Campaign campaign = new Campaign();
		campaign.setCampaignName("test1");
		campaign.setDescription("desc1");

		URI location = restTemplate.postForLocation("http://localhost:8080/campaign/save", campaign);

		Campaign campaign2 = restTemplate.getForObject(location, Campaign.class);
		MatcherAssert.assertThat(campaign2.getCampaignName(), Matchers.equalTo(campaign.getCampaignName()));
		MatcherAssert.assertThat(campaign2.getDescription(), Matchers.equalTo(campaign.getDescription()));
	}

	@Test
	public void testGetCampaignById() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Campaign> response = restTemplate.getForEntity("http://localhost:8080/campaign/1", Campaign.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getCampaignName(), Matchers.equalTo("test"));
	}


}

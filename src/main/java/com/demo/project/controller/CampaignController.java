package com.demo.project.controller;

import java.net.URI;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.project.model.Campaign;
import com.demo.project.service.interfaces.CampaignService;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

	private CampaignService campaignService;

	public CampaignController(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<URI> createCampaign(@RequestBody Campaign campaign) {
		try {
			logger.info("start createCampaign");
			campaignService.createCampaign(campaign);
			Long id = campaign.getCampaignId();

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			logger.info("finih createCampaign");
			return ResponseEntity.created(location).build();
		} catch (ConstraintViolationException ex) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		} catch (Exception ex2) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Campaign> getCampaign(@PathVariable("id") Long id) {
		try {
			logger.info("start getCampaign");
			Campaign campaign = campaignService.findByCampaignId(id);
			logger.info("finish getCampaign");
			return ResponseEntity.ok(campaign);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public ResponseEntity<List<Campaign>> getCampaignList() {
		try {
			logger.info("start getCampaignList");
			List<Campaign> campaign = campaignService.findAll();
			logger.info("finish getCampaignList");
			return ResponseEntity.ok(campaign);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

}

package com.demo.project.service.interfaces;

import java.util.List;

import com.demo.project.model.Campaign;

public interface CampaignService {
	
	public void createCampaign(Campaign campaign);
	public Campaign findByCampaignId(Long id);
	public List<Campaign> findAll();

}

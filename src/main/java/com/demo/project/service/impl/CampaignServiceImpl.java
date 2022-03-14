package com.demo.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.project.dao.CampaignRepository;
import com.demo.project.model.Campaign;
import com.demo.project.service.interfaces.CampaignService;

@Service
public class CampaignServiceImpl implements CampaignService {
	
	private CampaignRepository campaignRepository;
	
	public CampaignServiceImpl(CampaignRepository campaignRepository) {
			this.campaignRepository = campaignRepository;
	}

	@Override
	public void createCampaign(Campaign campaign) {
		campaignRepository.save(campaign);
		
	}
	
	@Override
	public Campaign findByCampaignId(Long id) {
		// TODO Auto-generated method stub
		return campaignRepository.findByCampaignId(id);
	}
	
	@Override
	public List<Campaign> findAll() {
		// TODO Auto-generated method stub
		return campaignRepository.findAll();
	}

}

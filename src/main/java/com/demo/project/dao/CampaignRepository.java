package com.demo.project.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.project.model.Campaign;

public interface CampaignRepository extends CrudRepository<Campaign,Long>{
  
	public Campaign findByCampaignId(Long id);
	public List<Campaign> findAll();
}

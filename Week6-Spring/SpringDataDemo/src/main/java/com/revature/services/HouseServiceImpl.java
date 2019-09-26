package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.House;
import com.revature.repositories.HouseRepository;

@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private HouseRepository houseRepository;

	@Override
	public List<House> findAllHouses() {
		return houseRepository.findAll();
	}

	@Override
	public House findHouseById(Integer id) {
		return houseRepository.getOne(id);
	}

	@Override
	public House addHouse(House h) {
		return houseRepository.save(h);
	}

	@Override
	public House updateHouse(House h) {
		return houseRepository.save(h);
	}

	@Override
	public House deleteHouse(House h) {
		houseRepository.delete(h);
		return h;
	}

	@Override
	public List<House> findHousesByState(String state) {
		return houseRepository.findHouseByState(state);
	}
	
	

}

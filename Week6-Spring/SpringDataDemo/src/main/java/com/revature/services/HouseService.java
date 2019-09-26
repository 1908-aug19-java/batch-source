package com.revature.services;

import java.util.List;

import com.revature.models.House;

public interface HouseService {
	
	public List<House> findAllHouses();
	public House findHouseById(Integer id);
	public House addHouse(House h);
	public House updateHouse(House h);
	public House deleteHouse(House h);
	public List<House> findHousesByState(String state);

}

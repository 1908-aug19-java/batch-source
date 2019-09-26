package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.House;

public interface HouseRepository extends JpaRepository<House, Integer> {
	
	public List<House> findHouseByState(String state);

}

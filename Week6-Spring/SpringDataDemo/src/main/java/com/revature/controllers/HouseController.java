package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.House;
import com.revature.services.HouseService;

@RestController
@RequestMapping("/houses")
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@GetMapping
	public List<House> getAll(@RequestParam(value="state",required=false)String state){
		if(state!=null) {
			System.out.println(state);
			return houseService.findHousesByState(state);
		}
		return houseService.findAllHouses();
	}
	
	@GetMapping("/{id}")
	public House getHouseById(@PathVariable("id")Integer id) {
		return houseService.findHouseById(id);
	}
	
	@PostMapping
	public ResponseEntity<House> addHouse(@RequestBody House house){
		houseService.addHouse(house);
		return new ResponseEntity<House>(house, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public House updateHouse(@PathVariable("id")Integer id, @RequestBody House house) {
		house.setPropertyId(id);
		return houseService.updateHouse(house);
	}
	
	@DeleteMapping("/{id}")
	public House deleteHouse(@PathVariable("id")Integer id) {
		return houseService.deleteHouse(new House(id));
	}
	
}

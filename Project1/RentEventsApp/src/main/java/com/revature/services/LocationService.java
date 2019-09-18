package com.revature.services;

import java.util.List;

import com.revature.daos.LocationDAO;
import com.revature.daos.methods.LocationDAOMethods;
import com.revature.model.Location;

public class LocationService {
	
	LocationDAO ldao = new LocationDAOMethods();
	
	public Location getLocation(int id) {
		
		return ldao.getLocationById(id);		
	}
	
	public List<Location> getAllLocations() {
		
		return ldao.getLocations();
	}
	
	public int createLocation(Location l) {
		return ldao.createLocation(l);
	}
	
	public int deleteLocation(String name) {
		return ldao.deleteLocation(name);
	}

}

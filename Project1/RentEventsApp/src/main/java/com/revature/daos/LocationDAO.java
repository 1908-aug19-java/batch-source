package com.revature.daos;

import java.util.List;

import com.revature.model.Location;

public interface LocationDAO {
	
	public Location getLocationById(int id);
	public List<Location> getLocations();
	public int createLocation(Location l);
	public int deleteLocation(String name);

}

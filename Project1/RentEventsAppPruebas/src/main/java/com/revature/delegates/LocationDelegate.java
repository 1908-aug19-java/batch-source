package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.LocationService;

public class LocationDelegate {
	
	private LocationService ls = new LocationService();
	
	public void getLocations(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException{
		
		
	} 

}

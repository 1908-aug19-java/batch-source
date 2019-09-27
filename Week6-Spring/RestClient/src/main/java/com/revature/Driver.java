package com.revature;

import org.springframework.web.client.RestTemplate;

import com.revature.models.House;

public class Driver {

	public static void main(String[] args) {
		String url = "http://localhost:8082/houses/2";
		
		RestTemplate restTemplate = new RestTemplate();
		
		House h = restTemplate.getForObject(url, House.class);
		System.out.println(h);

	}

}

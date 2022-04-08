package com.bibs.starwars.Star.Wars.Bootcamp.WomakersCode;

import com.bibs.starwars.service.JediService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarWarsBootcampWomakersCodeApplicationTests {

	@Autowired
	private JediService jediService;

	@Test
	void contextLoads() {
		jediService.a();
	}



}

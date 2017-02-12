package com.octus.test.repository;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.octus.common.config.AppConfig;
import com.octus.common.model.Octus;
import com.octus.common.repository.OctusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class TestAddOctus {
	@Autowired
	private OctusRepository octusRepository;

	@Test
	public void testAddOctus() {
		Octus octus = new Octus();
		octus.setTest("Testing String");
		octusRepository.addOctus(octus);

		Octus dboctus = octusRepository.findByString("Testing String");

		assertEquals(dboctus.getTest(), octus.getTest());
	}

}

package com.octus.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.octus.common.config.SpringMongoConfig;
import com.octus.common.model.Octus;
import com.octus.common.repository.OctusRepository;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringMongoConfig.class)
@IntegrationTest
public class TestFindAll {
	@Autowired
	private OctusRepository octusRepository;

	static final int QTY = 5;

	@Before
	public void init() {
		octusRepository.deleteAll();
		for (int i = 0; i < QTY; i++) {
			Octus octus = new Octus();
			octus.setTest("Testing String " + i);
			octusRepository.addOctus(octus);
		}
	}
 
	@Test
	public void testFindAll() {
		List<Octus> list = octusRepository.findAll();
		assertEquals(QTY, list.size());
	}
}

package com.octus.test.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
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

package com.octus.common.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.octus.common.model.Octus;
import com.octus.common.repository.OctusRepository;

@Service
@Component("octusService")
public class OctusService {

	@Autowired
	private OctusRepository octusRepository;

	public OctusService() {
	}

	public void create(Octus octus) {
		octusRepository.addOctus(octus);
	}

	public Octus update(String id, Octus octus) {
		Octus fromDb = octusRepository.updateOctus(id, octus);
		return fromDb;
	}

	public List<Octus> readAll() {
		List<Octus> result = new ArrayList<Octus>();
		Iterator<Octus> octusList = octusRepository.findAll().iterator();
		while (octusList.hasNext()) {
			result.add(octusList.next());
		}
		return result;
	}

	public String delete(String id) {
		return octusRepository.deleteOctus(id);
	}

	public Octus findByString(String test) {
		return octusRepository.findByString(test);
	}
}

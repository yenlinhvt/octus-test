package com.octus.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.octus.common.model.Octus;
import com.octus.common.service.OctusService;

@RestController
@Component
public class OctusRestController {

	@Autowired
	private OctusService octusService;

	@PostMapping("/octus")
	public ResponseEntity<Octus> createOctus(@RequestBody Octus octus) {

		octusService.create(octus);

		return new ResponseEntity<Octus>(octus, HttpStatus.CREATED);
	}

	@GetMapping("/octus")
	public List<Octus> getCustomers() {
		return octusService.readAll();
	}

	@PutMapping("/octus/update/{id}")
	public ResponseEntity<Octus> updateCustomer(@PathVariable String id, @RequestBody Octus octus) {

		octus = octusService.update(id, octus);

		if (null == octus) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Octus>(octus, HttpStatus.OK);
	}

	@DeleteMapping("/octus/delete/{id}")
	public ResponseEntity<Octus> deleteOctus(@PathVariable String id) {

		if (null == octusService.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
}

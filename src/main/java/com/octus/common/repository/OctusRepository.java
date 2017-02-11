package com.octus.common.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.octus.common.model.Octus;

@Repository
public class OctusRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	public void addOctus(Octus octus) {
		if (!mongoTemplate.collectionExists(Octus.class)) {
			mongoTemplate.createCollection(Octus.class);
		}
		octus.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(octus, "octus");
	}

	public List<Octus> findAll() {
		return mongoTemplate.findAll(Octus.class, "octus");
	}

	public String deleteOctus(String id) {
		Octus dbOctus = this.findById(id);
		if (dbOctus != null){
			mongoTemplate.remove(dbOctus, "octus");
			return id;
		}
		return null;
	}

	public Octus updateOctus(String id, Octus octus) {
		Octus dbOctus = this.findById(id);
		if (dbOctus != null){
			dbOctus.setTest(octus.getTest());
			mongoTemplate.save(dbOctus, "octus");
		}
		return dbOctus;
	}

	public Octus findById(String id) {
		return mongoTemplate.findById(id, Octus.class);
	}

	public void deleteAll() {
		mongoTemplate.dropCollection("octus");
	}
}

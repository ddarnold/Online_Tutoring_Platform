package de.thu.thutorium.repository;

import de.thu.thutorium.model.SampleEntity;
import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<SampleEntity, Integer> {}

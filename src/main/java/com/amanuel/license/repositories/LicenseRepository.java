package com.amanuel.license.repositories;
//import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.license.models.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {
//	ArrayList<License> findAll();
}

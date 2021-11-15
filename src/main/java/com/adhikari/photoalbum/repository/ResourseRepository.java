package com.adhikari.photoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adhikari.photoalbum.model.ResourceEntity;

@Repository
public interface ResourseRepository extends JpaRepository<ResourceEntity, String> {

}

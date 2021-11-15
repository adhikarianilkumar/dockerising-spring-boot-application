package com.adhikari.photoalbum.service;

import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.adhikari.photoalbum.model.ResourceEntity;

public interface ResourcesService {
	
	public ResourceEntity uploadResource(MultipartFile multi);
	
	public ResourceEntity downloadResource(String fileId);
	
	public Stream<ResourceEntity> getAllResources();

}

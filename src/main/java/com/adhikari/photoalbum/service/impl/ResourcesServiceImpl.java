package com.adhikari.photoalbum.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.adhikari.photoalbum.model.ResourceEntity;
import com.adhikari.photoalbum.repository.ResourseRepository;
import com.adhikari.photoalbum.service.ResourcesService;

@Service
public class ResourcesServiceImpl implements ResourcesService {
	
	@Value("${file.storage.local.location}")
	private String storeFileToLocalDir;
	
	@Autowired
	private ResourseRepository resourseRepository;
	
	//@Autowired
	//private ResourceEntity uploadedFile;

	@Override
	public ResourceEntity uploadResource(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			
			// Save to database
			ResourceEntity resourceEntity = new ResourceEntity();
			resourceEntity.setResourceContent(multipartFile.getBytes());
			resourceEntity.setResourceType(multipartFile.getContentType());
			resourceEntity.setResourceName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
			
			return resourseRepository.save(resourceEntity);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResourceEntity downloadResource(String fileId) {
		// TODO Auto-generated method stub
		return resourseRepository.findById(fileId).get();
	}

	@Override
	public Stream<ResourceEntity> getAllResources() {
		// TODO Auto-generated method stub
		return resourseRepository.findAll().stream();
	}

}

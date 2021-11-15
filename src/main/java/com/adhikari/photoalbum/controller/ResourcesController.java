package com.adhikari.photoalbum.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adhikari.photoalbum.model.ResourceEntity;
import com.adhikari.photoalbum.response.ResourceDownloadResponse;
import com.adhikari.photoalbum.response.ResourceUploadResponse;
import com.adhikari.photoalbum.service.ResourcesService;

@RestController
public class ResourcesController {

	private static Logger logger = LoggerFactory.getLogger(ResourcesController.class);

	@Autowired
	private ResourcesService resourcesService;

	@PostMapping("/uploadfile")
	public ResourceUploadResponse uploadResource(
			@RequestParam(value = "file", required = true) MultipartFile multipartFile) {

		ResourceUploadResponse resourceUploadResponse = new ResourceUploadResponse();
		logger.info("This is logger info example");

		ResourceEntity resourceUploaded = null;
		if (!StringUtils.isBlank(multipartFile.getOriginalFilename()))
			resourceUploaded = resourcesService.uploadResource(multipartFile);

		if (null == resourceUploaded) {
			resourceUploadResponse.setResourceName(multipartFile.getOriginalFilename());
			resourceUploadResponse.setMessage("Ooops! Something went wrong. Please upload again");
			logger.error("uploadedFile is null");
			return resourceUploadResponse;
		}

		resourceUploadResponse.setResourceName(resourceUploaded.getResourceName());
		resourceUploadResponse.setResourceDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadfile/").path(resourceUploaded.getResourceId()).toUriString());
		resourceUploadResponse.setResourceId(resourceUploaded.getResourceId());
		resourceUploadResponse.setResourceType(resourceUploaded.getResourceType());
		resourceUploadResponse.setResourceUploadStatus(true);
		resourceUploadResponse.setMessage("File uploaded successfully!");
		return resourceUploadResponse;
	}

	@GetMapping("/downloadfile/{id}")
	public ResponseEntity<Resource> downloadResource(@PathVariable("id") String resourceId) {

		ResourceEntity resourceDownloaded = resourcesService.downloadResource(resourceId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceDownloaded.getResourceType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename= " + resourceDownloaded.getResourceName())
				.body(new ByteArrayResource(resourceDownloaded.getResourceContent()));
	}

	@GetMapping("/downloadallfiles")
	public ResponseEntity<List<ResourceDownloadResponse>> getAllResources() {

		List<ResourceDownloadResponse> allResources = resourcesService.getAllResources().map(resource -> {

			ResourceDownloadResponse resourceDownloadResponse = new ResourceDownloadResponse();
			resourceDownloadResponse.setResourceId(resource.getResourceId());
			resourceDownloadResponse.setResourceName(resource.getResourceName());
			resourceDownloadResponse.setResourceDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/downloadfile/").path(resource.getResourceId()).toUriString());
			resourceDownloadResponse.setResourceType(resource.getResourceType());
			resourceDownloadResponse.setSize(resource.getResourceContent().length);

			return resourceDownloadResponse;
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(allResources);
	}

}

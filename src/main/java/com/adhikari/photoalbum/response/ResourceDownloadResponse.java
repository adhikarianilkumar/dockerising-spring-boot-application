package com.adhikari.photoalbum.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResourceDownloadResponse {
	
	private String resourceId;
	private String resourceName;
	private String resourceDownloadUri;
	private String resourceType;
	private long size;

}

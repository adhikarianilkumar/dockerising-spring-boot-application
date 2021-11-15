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
public class ResourceUploadResponse {

	private String resourceId;
	private String resourceName;
	private String resourceType;
	private String message;
	private boolean resourceUploadStatus;
	private String resourceDownloadUri;

}

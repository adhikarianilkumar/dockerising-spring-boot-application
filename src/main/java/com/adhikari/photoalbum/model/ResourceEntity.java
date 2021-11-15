package com.adhikari.photoalbum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "RESOURCE")
public class ResourceEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "RESOURCE_ID")
	private String resourceId;
	
	@Column(name = "RESOURCE_NAME")
	private String resourceName;
	
	@Column(name = "RESOURCE_TYPE")
	private String resourceType;
	
	@Lob
	@Column(name = "RESOURCE_CONTENT")
	private byte[] resourceContent;

}

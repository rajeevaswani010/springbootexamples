package com.rjasw.demo.fileupload.cfg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";
	
	public String getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}
}

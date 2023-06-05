package com.example.fileupload;

import com.example.fileupload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FileUploadApplication {

	@Autowired
	private StorageService storageService;

	@PostConstruct
	public void init(){
		storageService.init();
	}
	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

}

package com.example.fileupload.controller;

import com.example.fileupload.exception.StorageFileNotFoundException;
import com.example.fileupload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/")
public class FileController {

    @Autowired
    private StorageService storageService;

//    @GetMapping("all")
//    public ResponseEntity<?> fetchFileNames(){
//        return null;
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        String filePath = storageService.store(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(filePath);
    }

    @GetMapping("/{filename}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable("filename") String filename) throws StorageFileNotFoundException {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}

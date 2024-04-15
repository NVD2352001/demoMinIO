package com.example.demo;
import com.example.demo.model.item;
import com.example.demo.service.MinIOService;
import com.example.demo.service.pdfService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class pdfController {
    @Autowired
    private pdfService service;

    //    @GetMapping(value = "/demo-report", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> getReport() {
//        try {
//            return new ResponseEntity<byte[]>(service.getReport(), HttpStatus.OK);
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//@GetMapping("/download-image/{id}")
//public ResponseEntity<Resource> downloadImage(@PathVariable int id) {
//    try {
//        Resource imageResource = service.dowloadImage(id);
//        MediaType mediaType = MediaType.IMAGE_JPEG;
//        return ResponseEntity.ok()
//                .contentType(mediaType)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageResource.getFilename() + "\"")
//                .body(imageResource);
//    } catch (MalformedURLException e) {
//        e.printStackTrace();
//        return ResponseEntity.notFound().build();
//    }
//}
    @GetMapping("/download-image/{id}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable int id) {
        Optional<byte[]> imageDataOptional = service.getImageDataById(id);
        if (imageDataOptional.isPresent()) {
            byte[] imageData = imageDataOptional.get();
            ByteArrayResource resource = new ByteArrayResource(imageData);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDispositionFormData("attachment", "image.jpg");
            headers.setContentLength(imageData.length);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/demo-item", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReports(@RequestParam int id) {
        try {
            return new ResponseEntity<byte[]>(service.getReports(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public item add(@RequestBody item it) {
        return service.add(it);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        String bucketName = "image";
        String objectName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        return service.uploadImage(file,bucketName, objectName, inputStream, contentType);
    }

    @GetMapping("/getid")
    public ResponseEntity<byte[]> getImage(@RequestParam int id) {
        return service.getImage(id);
    }

    @GetMapping(value = "/demo-test", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReports() {
        try {
            return new ResponseEntity<byte[]>(service.gettest(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/demo-test1", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReports1(@RequestParam int id) {
        try {
            return new ResponseEntity<byte[]>(service.gettest1(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private MinIOService minIOService;

    @GetMapping("/")
    public List<String> getAllImageUrls() {
        try {
            return minIOService.getAllImageUrls();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @PostMapping("/upload_minio")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String bucketName = "image";
            String objectName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();
            minIOService.createObject(bucketName, objectName, inputStream, contentType);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }
}

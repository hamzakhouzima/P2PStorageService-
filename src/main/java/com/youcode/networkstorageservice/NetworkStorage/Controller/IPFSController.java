package com.youcode.networkstorageservice.NetworkStorage.Controller;


//import com.youcode.NetworkStorage.Service.FileStorageService;
//import com.youcode.NetworkStorageService.NetworkStorage.Service.MetaDataService;
import ch.qos.logback.core.pattern.FormattingConverter;
//import com.youcode.networkstorageservice.GlobalConverters.FormatConverter;
import com.youcode.networkstorageservice.GlobalConverters.FormatConverter;
import com.youcode.networkstorageservice.GlobalConverters.Templates.MockMultipartFile;
import com.youcode.networkstorageservice.GlobalConverters.Templates.PatientTemplate;
import com.youcode.networkstorageservice.GlobalException.IPFSException;
import com.youcode.networkstorageservice.NetworkStorage.Service.FileStorageService;
import com.youcode.networkstorageservice.NetworkStorage.Service.MetaDataService;
import org.json.JSONObject;
import org.json.XML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.SocketTimeoutException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

//import org.springframework.web.multipart.standard.StandardMultipartFile; // Deprecated


@RestController
public class IPFSController {


//    @Autowired
//    private FileStorage ipfsService;
//
@Autowired
private MetaDataService metaDataService;
//private FormatConverter fc;

@Autowired
private FileStorageService fileStorageService;

    private static final Logger logger = LoggerFactory.getLogger(IPFSController.class);




    @PostMapping(value="/upload")
    public ResponseEntity<String> uploadFile(@RequestBody String file) {
        try {
            MultipartFile convertedFile = FormatConverter.createJsonMultipartFile("convertedFile" , file);

            fileStorageService.uploadFile(convertedFile);

            String cid = metaDataService.storeMetaData(convertedFile);

            logger.info("File uploaded successfully. CID: {}", cid);

            return ResponseEntity
                    .ok("File uploaded successfully. CID: " + cid);
        } catch (IPFSException e) {
            logger.error("Error uploading file", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }


//    @PutMapping(value="/update")
//    public ResponseEntity<String> updateFile(@RequestParam("fileUpdate") MultipartFile file , @RequestParam("ID_Num") String idNum) {
//        try {
//            fileStorageService.uploadFile(file.getBytes());
//            String cid = fileStorageService.UpdateFile(file ,idNum );
//            return ResponseEntity.ok("File updated successfully. CID: " + cid);
//        }  catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating file: " + e.getMessage());
//        }
//    }

    @GetMapping(value = "/file/{hash}")
    public ResponseEntity<byte[]> getFile(@PathVariable("hash") String hash) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.ALL_VALUE);

        System.out.println(headers.getContentType());


//        Path filePath = Paths.get("C:", "Users", "Youcode", "Downloads", hash+".png");
        String fileExtension = getFileExtension(hash);
        Path filePath = Paths.get("C:", "Users", "Youcode", "Downloads", hash + ".json");
        byte[] bytes = fileStorageService.downloadFile(hash, filePath.toString());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);

    }


    @GetMapping(value = "/isAvailable/{hash}")
    public ResponseEntity<String> isAvailable(@PathVariable("hash") String hash) throws SocketTimeoutException {
        try {
            boolean isAvailable = fileStorageService.isAvailable(hash);
            if (isAvailable) {
                return ResponseEntity.ok("File is available");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }
        }catch(Exception e ){
                throw new SocketTimeoutException("No file found or timed out");
        }

    }


    @GetMapping(value = "/metaData/{hash}")
    public ResponseEntity<String> getMetaData(@PathVariable("hash") String hash) {
        try {
            Map<String, Object> metaData = fileStorageService.getFileMetadata(hash);
            StringBuilder response = new StringBuilder();
            metaData.forEach((key, value) -> response.append(key).append(": ").append(value).append("\n"));
            return ResponseEntity.ok(response.toString());
        } catch (Exception e) {
            System.out.println("exception triggered" + e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }




    ////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////

















////TODO  : handle the time out exception , because the ipfs network is slow
//    @GetMapping(value = "isAvailable/{hash}")
//    public ResponseEntity<String> isAvailable(@PathVariable("hash") String hash) {
//        boolean isAvailable = ipfsService.isAvailable(hash);
//        if (isAvailable) {
//            return ResponseEntity.ok("File is available");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
//        }
//    }



}

package com.youcode.networkstorageservice.NetworkStorage.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileStorageService {
    // Upload file to IPFS and return CID
    String uploadFile(MultipartFile file);

    String UpdateFile(MultipartFile file ,  String ID_number);

    // Download file content based on CID
    byte[] downloadFile(String cid, String downloadPath);


    // Check availability of a CID on the IPFS network
    boolean isAvailable(String cid);


    // Repin or reseed file content to IPFS based on CID
    boolean reseedFile(String cid);


    // Get metadata or information about a file based on CID
    Map<String, Object> getFileMetadata(String cid);




    // List files or content stored in IPFS directory
    List<String> listFilesInDirectory(String cid);


    // Pin a file or content to ensure persistence
    boolean pinFile(String cid);


    // Unpin a file or content from IPFS
    boolean unpinFile(String cid);


    // Publish or resolve names using IPNS for content identification
    void publishName(String name, String cid);

    String resolveName(String name);





//    void pinFile(String cid);
//
//    // Unpin a previously pinned file or content identified by its CID
//    void unpinFile(String cid);
//
//    // Check if a file or content identified by its CID is pinned
//    boolean isPinned(String cid);
//
//    // Get a list of all pinned content in the IPFS node
//    List<String> getPinnedContent();
}

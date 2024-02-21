package com.youcode.networkstorageservice.NetworkStorage.Service.Impl;

//import com.youcode.interplanetary.GlobalException.IpfsFileNotFound;
//import com.youcode.interplanetary.NetworkStorage.Entity.MetaData;
//import com.youcode.interplanetary.NetworkStorage.Repository.MetaRepository;
//import com.youcode.interplanetary.NetworkStorage.Service.FileStorageService;
//import com.youcode.interplanetary.NetworkStorage.Service.MetaDataService;
//import com.youcode.interplanetary.config.IPFSConfig;
import com.youcode.networkstorageservice.GlobalException.IpfsFileNotFound;
import com.youcode.networkstorageservice.NetworkStorage.Entity.MetaData;
import com.youcode.networkstorageservice.NetworkStorage.Repository.MetaRepository;
import com.youcode.networkstorageservice.NetworkStorage.Service.FileStorageService;
import com.youcode.networkstorageservice.NetworkStorage.Service.MetaDataService;
import com.youcode.networkstorageservice.config.IPFSConfig;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private String cid;

    @Autowired
    private IPFSConfig ipfsConfig;

    private FileStorageService fileStorage;

    @Autowired
    private MetaRepository dataRepository;



    //################DUBUG#############################//
    @Autowired
    @Lazy
    private MetaDataService mds;
//##########################################//



    
//    public FileStorageServiceImpl(MetaRepository dataRepository) {
//            this.dataRepository = dataRepository;
//    }

    //this method's parameter should be changed to byte[] and use ipfs.dag.put()

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileStorageServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file) {

        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());

            NamedStreamable.InputStreamWrapper is = new NamedStreamable.InputStreamWrapper(inputStream);
//                                        /\
            //  ||                    //  ||
            MerkleNode response = ipfsConfig.ipfs.add(is).get(0);

            cid = response.hash.toBase58();
            return cid;
        } catch (IOException ex) {
            throw new RuntimeException("Error while communicating with the IPFS node", ex);
        }
    }

    @Override
    public String UpdateFile(MultipartFile file , String ID_num) { //ToDo : we should add exception handling
        String NewCID = uploadFile(file);
        mds.updateMetaData(file , NewCID);
        return NewCID;
    }


    @Override
    public byte[] downloadFile(String cid, String downloadPath) {
        try {
            // convert the CID to Multihash
            Multihash filePointer = Multihash.fromBase58(cid);

            byte[] content = ipfsConfig.ipfs.cat(filePointer);
            Path path = Paths.get(downloadPath);
            Files.write(path, content);
            System.out.println("File downloaded successfully to: " + downloadPath);
        } catch (IpfsFileNotFound | IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public boolean isAvailable(String cid) {
        try {
            Multihash filePointer = Multihash.fromBase58(cid);
            ipfsConfig.ipfs.object.stat(filePointer);
            System.out.println("File is available on the IPFS network");
            return true;
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error while communicating with the IPFS node", e);
        }
    }

    @Override
    public boolean reseedFile(String cid) {

        return false;
    }

    @Override
    public Map<String, Object> getFileMetadata(String cid) {
        try {
            MetaData MD = dataRepository.findByUserCid(cid);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("fileName", MD.getFileName());
            metadata.put("fileSize", MD.getFileSize());
            metadata.put("userCid", MD.getUserCid());
            metadata.put("date", MD.getDate());
            return metadata;
        } catch (Exception e) {
            throw new RuntimeException("Error while communicating with the IPFS node Caused =>" + e);
        }
    }


    @Override
    public List<String> listFilesInDirectory(String cid) {
        return null;
    }

    @Override
    public boolean pinFile(String cid) {

        return false;
    }

    @Override
    public boolean unpinFile(String cid) {
        return false;
    }

    @Override
    public void publishName(String name, String cid) {

    }

    @Override
    public String resolveName(String name) {
        return null;
    }
    /////////////:tool methods/
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }


}

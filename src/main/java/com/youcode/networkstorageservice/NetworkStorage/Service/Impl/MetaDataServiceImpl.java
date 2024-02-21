package com.youcode.networkstorageservice.NetworkStorage.Service.Impl;

//import com.youcode.interplanetary.NetworkStorage.Entity.MetaData;
//import com.youcode.interplanetary.NetworkStorage.Repository.MetaRepository;
//import com.youcode.interplanetary.NetworkStorage.Service.MetaDataService;
import com.youcode.networkstorageservice.NetworkStorage.Entity.MetaData;
import com.youcode.networkstorageservice.NetworkStorage.Repository.MetaRepository;
import com.youcode.networkstorageservice.NetworkStorage.Service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class MetaDataServiceImpl implements MetaDataService {
    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private FileStorageServiceImpl ipfsFileStorage;
    Date date = new Date(); // Get the current date/time


//    private MetaRepository dataRepository;
    //TODO : Add the rest of the methods to store the data related to the content ( CID ,Date ,Content_relative )

//    String CID = Ipfs.getCid();


        @Override
        public String storeMetaData(MultipartFile file) {
            try{
                String CID = ipfsFileStorage.uploadFile(file);
                    MetaData metaData = MetaData.builder()
                            .userCid(CID)
                            .date(date)
                            .build();
                metaRepository.save(metaData);
                return CID;
            }catch(Exception e){
                throw new RuntimeException("Error while communicating with the IPFS node", e);
            }

        }

        @Override
        public String updateMetaData(MultipartFile file , String idNum){
            try{
                String CID = ipfsFileStorage.uploadFile(file);
                metaRepository.updateByIdNumber(CID, idNum);

                return CID;
            }catch(Exception e){
                throw new RuntimeException("Error while communicating with the IPFS node", e);
            }
        }


}

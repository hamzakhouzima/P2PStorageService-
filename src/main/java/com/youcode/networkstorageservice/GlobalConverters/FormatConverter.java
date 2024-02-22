package com.youcode.networkstorageservice.GlobalConverters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import org.springframework.mock.web.MockMultipartFile;
import com.youcode.networkstorageservice.Dto.PatientDto;
import com.youcode.networkstorageservice.GlobalConverters.Templates.MockMultipartFile;
import com.youcode.networkstorageservice.GlobalConverters.Templates.PatientTemplate;
import com.youcode.networkstorageservice.NetworkStorage.Controller.IPFSController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;




public class FormatConverter {




    private  static ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(FormatConverter.class);



////////////////////////////////////////////////////////////////////

    public static MultipartFile createJsonMultipartFile(String filename, PatientDto jsonRequest) throws IOException {
        // Convert PatientDto object to JSON bytes
        byte[] jsonBytes = objectMapper.writeValueAsBytes(jsonRequest);
        logger.info(String.valueOf(jsonRequest));
        // Create MultiValueMap representing multipart form data
        MultiValueMap<String, Object> multipartFormData = new LinkedMultiValueMap<>();
        multipartFormData.add("file", new MockMultipartFile(filename, filename, "application/json", jsonBytes));

        // Get the MultipartFile from MultiValueMap
        return (MultipartFile) multipartFormData.getFirst("file");
    }

////////////////////////////////////////////////////////////////////






}

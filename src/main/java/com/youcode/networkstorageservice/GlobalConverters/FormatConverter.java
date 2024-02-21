package com.youcode.networkstorageservice.GlobalConverters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import org.springframework.mock.web.MockMultipartFile;
import com.youcode.networkstorageservice.Dto.PatientDto;
import com.youcode.networkstorageservice.GlobalConverters.Templates.MockMultipartFile;
import com.youcode.networkstorageservice.GlobalConverters.Templates.PatientTemplate;
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


    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final XmlMapper XML_MAPPER = new XmlMapper();

    public static String toJson(Object object) throws Exception {
        return JSON_MAPPER.writeValueAsString(object);
    }

    public static <T> T fromJson(String json, Class<T> valueType) throws Exception {
        return JSON_MAPPER.readValue(json, valueType);
    }

    public static byte[] convertJsonToXml(String jsonString, Class<?> xmlRootClass) throws JAXBException, IOException {
        // Ensure you have a library for parsing JSON to Java objects (e.g., Jackson or Gson)
        Object xmlObject = createXmlObjectFromJson(jsonString, xmlRootClass); // Replace with your implementation

        JAXBContext jaxbContext = JAXBContext.newInstance(xmlRootClass);
        ByteArrayOutputStream xmlOutputStream = new ByteArrayOutputStream();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(xmlObject, xmlOutputStream);

        return xmlOutputStream.toByteArray();
    }

    // Replace this placeholder with your actual JSON to XML object mapping logic
    private static Object createXmlObjectFromJson(String jsonString, Class<?> xmlRootClass) throws JsonProcessingException {
         ObjectMapper mapper = new ObjectMapper();
         return mapper.readValue(jsonString, xmlRootClass);
    }

////////////////////////////////////////////////////////////////////
private  static ObjectMapper objectMapper = new ObjectMapper();

//objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    public static MultipartFile createJsonMultipartFile(String filename, PatientDto jsonRequest) throws IOException {
        // Convert PatientDto object to JSON bytes
        byte[] jsonBytes = objectMapper.writeValueAsBytes(jsonRequest);

        // Create MultiValueMap representing multipart form data
        MultiValueMap<String, Object> multipartFormData = new LinkedMultiValueMap<>();
        multipartFormData.add("file", new MockMultipartFile(filename, filename, "application/json", jsonBytes));

        // Get the MultipartFile from MultiValueMap
        return (MultipartFile) multipartFormData.getFirst("file");
    }
////////////////////////////////////////////////////////////////////





    public static <T> T fromXml(String xml, Class<T> valueType) throws Exception {
        return XML_MAPPER.readValue(xml, valueType);
    }
}

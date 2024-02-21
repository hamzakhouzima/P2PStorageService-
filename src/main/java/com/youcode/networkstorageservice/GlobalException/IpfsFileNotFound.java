package com.youcode.networkstorageservice.GlobalException;

public class IpfsFileNotFound extends RuntimeException {
    public IpfsFileNotFound(String message) {
        super(message);
    }
}

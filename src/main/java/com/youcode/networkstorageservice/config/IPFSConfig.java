package com.youcode.networkstorageservice.config;


import io.ipfs.api.IPFS;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IPFSConfig {

    public IPFS ipfs;

    public IPFSConfig() {
        ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    }

}
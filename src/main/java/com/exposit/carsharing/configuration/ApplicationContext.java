package com.exposit.carsharing.configuration;

import com.exposit.carsharing.cloud.CloudStorageClient;
import com.exposit.carsharing.cloud.DropboxClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import static com.exposit.carsharing.util.Constants.DROPBOX_ACCESS_TOKEN;
import static com.exposit.carsharing.util.Constants.DROPBOX_APP_KEY;

@Configuration
public class ApplicationContext {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CloudStorageClient cloudStorageClient() {
        return new DropboxClient(DROPBOX_APP_KEY, DROPBOX_ACCESS_TOKEN);
    }

    @Bean
    public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }
}

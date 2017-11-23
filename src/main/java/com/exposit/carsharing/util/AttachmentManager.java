package com.exposit.carsharing.util;

import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AttachmentManager {
    private static final Set<String> IMAGE_EXTENSION =
            Stream.of("jpeg", "jpg", "png", "bmp", "svg", "gif").collect(Collectors.toSet());

    public static String getFileExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public static void checkFileExtension(String fileExtension) {
        fileExtension = fileExtension.toLowerCase();
        if (!IMAGE_EXTENSION.contains(fileExtension)) {
            throw new InvalidParameterException(String.format("File extension %s not supported", fileExtension));
        }
    }

    public static void checkFormData(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
        if (uploadedInputStream == null || fileDetail == null) {
            throw new InvalidParameterException("Invalid form data");
        }
    }
}
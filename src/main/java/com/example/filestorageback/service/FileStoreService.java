package com.example.filestorageback.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public interface FileStoreService {
    String storeFile(byte[] bytes, String originalFilename, int subType) throws IOException, NoSuchAlgorithmException;

    byte[] getFile(UUID hash) throws IOException;

    Object getMetaFiles(int subtype);
}

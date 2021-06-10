package com.example.filestorageback.service;

import java.util.UUID;

public interface FileStoreService {
    String storeFile(byte[] bytes, String originalFilename, int subType);

    byte[] getFile(UUID hash);

    Object getMetaFiles(int subtype);
}

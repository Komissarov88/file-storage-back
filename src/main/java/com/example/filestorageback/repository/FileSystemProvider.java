package com.example.filestorageback.repository;

import java.io.IOException;
import java.util.UUID;

public interface FileSystemProvider {
    String storeFile(byte[] content, UUID md5, String fileName) throws IOException;

    byte[] getFile(String fullFileName) throws IOException;
    void deleteFile(String fileHash) throws IOException;
}

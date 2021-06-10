package com.example.filestorageback.repository;

import com.example.filestorageback.dto.FileMetaDTO;

import java.util.Collection;
import java.util.UUID;

public interface FileMetaProvider {
    String checkFileExists(UUID md5);

    void saveFileMeta(UUID md5, String fileName, int subFileType, int size);

    Collection<FileMetaDTO> getMetaFiles(int subtype);
}

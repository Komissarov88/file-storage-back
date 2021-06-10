package com.example.filestorageback.repository.impl;

import com.example.filestorageback.dto.FileMetaDTO;
import com.example.filestorageback.repository.FileMetaProvider;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.UUID;

@Repository
public class FileMetaProviderImpl implements FileMetaProvider {

    private static final String GET_FILES_META =
            "select hash, file_name as filename, size_bytes as size, create_time as creationTime" +
                    " from public.file_info_metadata" +
                    " where subtype =:subtype";

    private static final String GET_FILE_PATH_BY_HASH = "select file_name as filename from public.file_info_metadata" +
            " where hash =:hash";

    private static final String SAVE_FILE_META_DATA =
            "insert into public.file_info_metadata (hash, file_name, subtype, size_bytes, create_time)" +
                    " values (:hash, :file_name, :subtype, :size_bytes, current_timestamp)";

    private final Sql2o sql2o;

    public FileMetaProviderImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public String checkFileExists(UUID fileHash) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILE_PATH_BY_HASH, false)
                    .addParameter("hash", fileHash.toString())
                    .executeScalar(String.class);
        }
    }

    @Override
    public void saveFileMeta(UUID Hash, String fileName, int subType, int size) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery(SAVE_FILE_META_DATA, false)
                    .addParameter("hash", Hash.toString())
                    .addParameter("file_name", fileName)
                    .addParameter("subtype", subType)
                    .addParameter("size_bytes", size)
                    .executeUpdate();
        }
    }

    @Override
    public Collection<FileMetaDTO> getMetaFiles(int subType) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILES_META, false)
                    .addParameter("subtype", subType)
                    .executeAndFetch(FileMetaDTO.class);
        }
    }
}

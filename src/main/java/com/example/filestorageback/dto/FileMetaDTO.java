package com.example.filestorageback.dto;

import java.util.UUID;

public class FileMetaDTO {
    private UUID hash;

    private String fileName;
    private int size;
    private String creationTime;

    public FileMetaDTO(String fileName) {
        this.fileName = fileName;
    }

    public UUID getHash() {
        return hash;
    }

    public void setHash(UUID hash) {
        this.hash = hash;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}

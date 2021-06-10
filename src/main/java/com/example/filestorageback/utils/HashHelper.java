package com.example.filestorageback.utils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class HashHelper {
    public static UUID getMd5Hash(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] data = md5.digest(input);
        ByteBuffer bb = ByteBuffer.wrap(data);
        long high = bb.getLong();
        long low = bb.getLong();

        return new UUID(high, low);
    }
}
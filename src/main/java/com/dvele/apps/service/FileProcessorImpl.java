package com.dvele.apps.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileProcessorImpl implements FileProcessor {

    @Override
    public byte[] processFileToByteArray(String pathToFile) throws IOException {
        File initialFile = new File(pathToFile);
        InputStream is = new FileInputStream(initialFile);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[4096];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();

        return buffer.toByteArray();
    }
}

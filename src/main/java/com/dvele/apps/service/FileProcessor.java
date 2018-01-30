package com.dvele.apps.service;

import java.io.IOException;

public interface FileProcessor {

      byte[] processFileToByteArray(String pathToFile) throws IOException;
}

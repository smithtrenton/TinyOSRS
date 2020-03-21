package org.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class RSJar {
    private final Map<String, byte[]> entries = new HashMap<>();

    public RSJar(byte[] jarBytes) throws IOException {
        try (JarInputStream inputStream = new JarInputStream(new ByteArrayInputStream(jarBytes))) {
            JarEntry entry;
            while ((entry = inputStream.getNextJarEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer, 0, 1024)) > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                entries.put(entry.getName(), outputStream.toByteArray());
            }
        }
    }

    public byte[] getFileBytes(String name) {
        return entries.get(name);
    }
}

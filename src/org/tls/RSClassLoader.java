package org.tls;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RSClassLoader extends ClassLoader {
    private final Map<String, Class<?>> loadedClasses = new HashMap<>();
    private final RSJar jar;

    private RSClassLoader(byte[] rawJarBytes) throws IOException {
        jar = new RSJar(rawJarBytes);
    }

    public static RSClassLoader from(URL gamepackURL) throws IOException {
        return new RSClassLoader(downloadGamepack(gamepackURL));
    }

    private static byte[] downloadGamepack(URL gamepackURL) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (BufferedInputStream inputStream = new BufferedInputStream(gamepackURL.openStream())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return outputStream.toByteArray();
    }

    @Override
    protected final synchronized Class<?> findClass(String name) throws ClassNotFoundException {


        String className = name.replace(".", "/").concat(".class");
        byte[] classBytes = jar.getFileBytes(className);

        if (classBytes == null) {
            throw new ClassNotFoundException(name);
        }

        Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
        loadedClasses.put(className, clazz);
        return clazz;

    }
}

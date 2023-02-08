package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = null;
        Path source = Path.of(cachingDir, key);
        if (!Files.isRegularFile(source)) {
            throw new IllegalArgumentException(String.format("File %s doesn't exist", source));
        }
        try {
            result = Files.readString(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

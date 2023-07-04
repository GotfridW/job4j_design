package ru.job4j.ood.lsp.violations.mediastorage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class MediaStorage {
    protected final long sizeLimit;
    private final Map<String, MediaFile> files = new HashMap<>();

    public void upload(MediaFile file) {
        if (file.getSize() > sizeLimit) {
            throw new IllegalArgumentException("File exceeding the size limit");
        }
        files.put(file.getName(), file);
    }

    public MediaFile getFile(String name) {
        validateContaining(name);
        return files.get(name);
    }
    public void removeFile(String name) {
        files.remove(name);
    }

    private void validateContaining(String name) {
        if (!files.containsKey(name) || files.get(name) == null) {
            throw new IllegalArgumentException("No such file in the storage");
        }
    }
}

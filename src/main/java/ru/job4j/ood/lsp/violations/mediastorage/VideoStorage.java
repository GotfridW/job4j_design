package ru.job4j.ood.lsp.violations.mediastorage;

import java.util.HashMap;
import java.util.Map;

/*
В данном классе видео-хранилища есть два нарушения LSP, а именно ослабление и усиление предусловий:
1. в отличие от родительского метода upload(), здесь в этом методе также проверяется имя файла на
заданное(конкретное) расширение, таким образом усиливается предусловие в методе;
2. в методе getFile() данного класса, не вызывается валидация аргумента, в отличие от метода родителя, что является
ослаблением предусловия.
В обоих случаях подстановка объекта MediaStorage любого другого типа приведет
к ошибкам и таким образом к изменению поведения программы.
 */

public class VideoStorage extends MediaStorage {
    private final Map<String, MediaFile> videoFiles = new HashMap<>();

    public VideoStorage(long sizeLimit) {
        super(sizeLimit);
    }

    @Override
    public void upload(MediaFile file) {
        if (file.getSize() >= sizeLimit) {
            throw new IllegalArgumentException("File exceeding the size limit");
        }
        if (!file.getName().endsWith(".mp4")) {
            throw new IllegalArgumentException("File extension not supported");
        }
        videoFiles.put(file.getName(), file);
    }

    @Override
    public MediaFile getFile(String name) {
        return videoFiles.get(name);
    }

    @Override
    public void removeFile(String name) {
        super.removeFile(name);
    }
}

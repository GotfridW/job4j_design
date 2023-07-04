package ru.job4j.ood.lsp.violations.mediastorage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MediaFile {
    private String name;
    private final long size;

    public void setName(String name) {
        this.name = name;
    }
}

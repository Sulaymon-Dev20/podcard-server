package com.example.podcastserver.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileService {
    private final String path = System.getProperty("user.dir");

    @SneakyThrows
    public String saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        new File(path + fileName).delete();
        Path pathf = Paths.get(path + fileName);
        Files.copy(file.getInputStream(), pathf, StandardCopyOption.REPLACE_EXISTING);
        return file.getName();
    }
}

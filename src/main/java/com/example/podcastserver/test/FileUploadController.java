package com.example.podcastserver.test;

import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
public class FileUploadController {

    private final String path = System.getProperty("user.dir");

    @GetMapping(path = "/download/{id}")
    public ResponseEntity<?> download(@PathVariable String id) throws IOException {
        File file2Upload = new File(path + id);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file2Upload.getAbsolutePath())));
        return ResponseEntity.ok()
                .contentLength(file2Upload.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/upload")
    public HttpEntity<?> uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        String s = this.saveFile(file);
        return ResponseEntity.ok(s);
    }

    @SneakyThrows
    public String saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path pathf = Paths.get(path + fileName);
        Files.copy(file.getInputStream(), pathf, StandardCopyOption.REPLACE_EXISTING);
        return file.getName();
    }
}

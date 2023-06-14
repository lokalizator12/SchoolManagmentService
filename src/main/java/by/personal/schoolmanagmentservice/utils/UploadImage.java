package com.syberry.school.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class UploadImage {

    public void upload(MultipartFile file, String path) throws IOException {
        log.info("Start upload image with name:{} and path:{}", file.getOriginalFilename(), path);
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(path, file.getOriginalFilename());
        Path directory = Paths.get(path);
        Files.createDirectories(directory);
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

    }
}

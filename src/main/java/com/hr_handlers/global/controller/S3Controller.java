package com.hr_handlers.global.controller;

import com.hr_handlers.global.service.S3Service;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @RequestParam("path") String path,
            @RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3Service.uploadFile(path, file);
            return ResponseEntity.ok().body(Map.of(
                    "uploaded", true,
                    "url", fileUrl
            ));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of(
                    "uploaded", false,
                    "error", Map.of("message", "파일 업로드 실패: " + e.getMessage())
            ));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFile(@RequestParam("fileUrl") String fileUrl) {
        try {
            s3Service.deleteFile(fileUrl);
            return ResponseEntity.ok().body(Map.of("message", "파일 삭제 성공"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "message", "파일 삭제 실패",
                    "error", e.getMessage()
            ));
        }
    }
}
package com.example.legalplatform.controller;

import com.example.legalplatform.common.Result;
import com.example.legalplatform.entity.Document;
import com.example.legalplatform.service.DocumentService;
import com.example.legalplatform.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Value("${file.upload-path}")
    private String uploadDir;

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public Result<Document> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long caseId,
            @RequestParam String title,
            @RequestParam String type) throws IOException {

        String fileName = file.getOriginalFilename();
        String newFileName = FileUtil.upload(file, uploadDir);

        Document doc = new Document();
        doc.setCaseId(caseId);
        doc.setTitle(title);
        doc.setType(type);
        doc.setFileName(fileName);
        doc.setFilePath(newFileName);
        doc.setFileType(file.getContentType());
        doc.setFileSize(file.getSize());
        doc.setCreateTime(LocalDateTime.now());
        doc.setUpdateTime(LocalDateTime.now());

        documentService.save(doc);
        return Result.success(doc);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Document doc = documentService.getById(id);
        File file = new File(uploadDir + File.separator + doc.getFilePath());

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = new FileSystemResource(file);
        String encodedName = URLEncoder.encode(doc.getFileName(), "UTF-8");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + encodedName + "\"")
                .body(resource);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<Resource> preview(@PathVariable Long id) {
        Document doc = documentService.getById(id);
        File file = new File(uploadDir + File.separator + doc.getFilePath());

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, doc.getFileType())
                .body(resource);
    }

    @GetMapping("/list/{caseId}")
    public Result<List<Document>> list(@PathVariable Long caseId) {
        return Result.success(documentService.lambdaQuery()
                .eq(Document::getCaseId, caseId)
                .orderByDesc(Document::getCreateTime)
                .list());
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        documentService.removeById(id);
        return Result.success();
    }
}
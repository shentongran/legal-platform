package com.example.legalplatform.controller;

import com.example.legalplatform.common.Result;
import com.example.legalplatform.entity.FileInfo;
import com.example.legalplatform.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${file.upload-path}")
    private String baseDir;

    @GetMapping("/list")
    public Result<List<FileInfo>> list(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String role) {

        if ("ADMIN".equals(role)) {
            return Result.success(fileService.list());
        } else {
            return Result.success(fileService.listByUserId(userId));
        }
    }

    @GetMapping("/list/case/{caseId}")
    public Result<List<FileInfo>> listByCaseId(@PathVariable Long caseId) {
        return Result.success(fileService.listByCaseId(caseId));
    }

    @PostMapping("/upload")
    public Result<FileInfo> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) Long caseId,
            @RequestParam(required = false) String caseName,
            @RequestParam(required = false) Long uploadUserId,
            @RequestParam(required = false) String uploadUser) throws Exception {

        File dir = new File(baseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uuidName = UUID.randomUUID() + suffix;

        File dest = new File(dir, uuidName);
        file.transferTo(dest);

        FileInfo info = new FileInfo();
        info.setCaseId(caseId);
        info.setCaseName(caseName);
        info.setFileName(originalFilename);
        info.setFilePath(uuidName);
        info.setFileType(suffix.replace(".", ""));
        info.setFileSize(file.getSize());
        info.setUploadUserId(uploadUserId);
        info.setUploadUser(uploadUser);
        info.setCreateTime(LocalDateTime.now());

        fileService.save(info);
        return Result.success(info);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<Resource> preview(@PathVariable Long id) {
        FileInfo info = fileService.getById(id);
        if (info == null) {
            return ResponseEntity.notFound().build();
        }

        File file = new File(baseDir + File.separator + info.getFilePath());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);
        String contentType = info.getFileType() != null ? getContentType(info.getFileType()) : MediaType.APPLICATION_OCTET_STREAM_VALUE;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws Exception {
        FileInfo info = fileService.getById(id);
        if (info == null) {
            return ResponseEntity.notFound().build();
        }

        File file = new File(baseDir + File.separator + info.getFilePath());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);
        String encodedName = URLEncoder.encode(info.getFileName(), "UTF-8");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + encodedName + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        FileInfo info = fileService.getById(id);
        if (info != null) {
            File file = new File(baseDir + File.separator + info.getFilePath());
            if (file.exists()) {
                file.delete();
            }
            fileService.removeById(id);
        }
        return Result.success();
    }

    private String getContentType(String fileType) {
        String type = fileType.toLowerCase();
        switch (type) {
            case "pdf":
                return "application/pdf";
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            case "doc":
            case "docx":
                return "application/msword";
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}

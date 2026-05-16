package com.example.legalplatform.controller;

import com.example.legalplatform.entity.FileInfo;
import com.example.legalplatform.entity.Result;
import com.example.legalplatform.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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

    // 获取文件列表（带权限过滤）
    @GetMapping("/list")
    public Result<List<FileInfo>> list(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String role) {

        // 管理员：查看全部
        if ("ADMIN".equals(role)) {
            return Result.success(fileService.list());
        }
        // 普通用户 / 法官 / 游客：只看自己的（游客userId为空，返回空）
        else {
            return Result.success(fileService.listByUserId(userId));
        }
    }

    // 文件上传
    @PostMapping("/upload")
    public Result<String> upload(
            @RequestParam("caseName") String caseName,
            @RequestParam("uploadUser") String uploadUser,
            @RequestParam("file") MultipartFile file) throws Exception {

        File dir = new File(baseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String suffix = null;
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uuidName = UUID.randomUUID() + suffix;

        File dest = new File(dir, uuidName);
        file.transferTo(dest);

        FileInfo info = new FileInfo();
        info.setCaseName(caseName);
        info.setFileName(originalFilename);
        info.setFilePath(uuidName);
        if (suffix != null) {
            info.setFileType(suffix.replace(".", ""));
        }
        info.setUploadUser(uploadUser);
        info.setCreateTime(LocalDateTime.now());

        fileService.save(info);
        return Result.success("上传成功");
    }

    // 文件预览（PDF / 图片）
    @GetMapping("/preview/{id}")
    public void preview(@PathVariable Long id, HttpServletResponse response) throws Exception {
        FileInfo info = fileService.getById(id);
        if (info == null) {
            response.setStatus(404);
            return;
        }

        File file = new File(info.getFilePath());

        if (!file.exists()) {
            response.setStatus(404);
            return;
        }

        String type = info.getFileType().toLowerCase();
        if (type.equals("pdf")) {
            response.setContentType("application/pdf");
        } else if (type.equals("png")) {
            response.setContentType("image/png");
        } else if (type.equals("jpg") || type.equals("jpeg")) {
            response.setContentType("image/jpeg");
        } else {
            response.setContentType("application/octet-stream");
        }

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        }
    }

    // 文件下载
    @GetMapping("/download/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response) throws Exception {
        FileInfo info = fileService.getById(id);
        if (info == null) {
            response.setStatus(404);
            return;
        }

        File file = new File(info.getFilePath());

        if (!file.exists()) {
            response.setStatus(404);
            return;
        }

        response.setHeader("Content-Disposition",
                "attachment;filename*=UTF-8''" + URLEncoder.encode(info.getFileName(), "UTF-8"));
        response.setContentType("application/octet-stream");

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        }
    }

    // 文件删除（数据库+磁盘）
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id) {
        FileInfo info = fileService.getById(id);
        if (info != null) {
            File file = new File(info.getFilePath());
            if (file.exists()) {
                file.delete();
            }
            fileService.removeById(id);
        }
        return Result.success("删除成功");
    }
}
package com.example.legalplatform.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    // 上传文件
    public static String upload(MultipartFile file, String uploadDir) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String original = file.getOriginalFilename();
        String suffix = original.substring(original.lastIndexOf("."));
        String newName = UUID.randomUUID() + suffix;
        File dest = new File(dir, newName);
        file.transferTo(dest);
        return newName;
    }
}
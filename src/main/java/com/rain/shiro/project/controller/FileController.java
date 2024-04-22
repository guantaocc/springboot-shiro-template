package com.rain.shiro.project.controller;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.uuid.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        String originFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originFileName;
        String projectPath = System.getProperty("user.dir");
        String realPath = projectPath + "\\"  + "file" + "\\" + fileName;
        File saveFile = new File(realPath);
        file.transferTo(saveFile);
        return Result.success("成功", "/" + fileName);
    }
}

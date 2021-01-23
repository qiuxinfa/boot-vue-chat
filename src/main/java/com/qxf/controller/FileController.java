package com.qxf.controller;

import com.qxf.util.FileConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @ClassName FileController
 * @Description 保存文件，返回文件url
 * @Author qiuxinfa
 * @Date 2021/1/22 0:03
 **/
@RestController
public class FileController {
    @PostMapping("/file")
    public String uploadFile(MultipartFile file) throws IOException{
        String filename = file.getOriginalFilename();  // 文件名
        String suffix = filename.substring(filename.lastIndexOf("."));  // 文件后缀
        LocalDate now = LocalDate.now();
        String filePath = ""+now.getYear()+ File.separator + now.getMonthValue() + File.separator
                + now.getDayOfMonth() + UUID.randomUUID().toString().replaceAll("-","") + suffix;
        File toSaveFile = new File(FileConstant.ROOT_PATH + filePath);
        toSaveFile.getParentFile().mkdirs();
        file.transferTo(toSaveFile);
        return FileConstant.BASE_URL + FileConstant.VIRTUAL_PATH + filePath;
    }
}

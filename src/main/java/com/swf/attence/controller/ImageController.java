package com.swf.attence.controller;

import org.apache.shiro.session.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ImageController {

    private static  final  String PATH="F:\\Attence相关\\userpic\\";
    @PostMapping("/userMsg/uploadImg")
    @ResponseBody
    public String uploadImsg(@RequestParam(value = "userpic1",required = false) MultipartFile multipartFile, Model model){
        String originalFilename = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()){
            try {
                Files.copy(multipartFile.getInputStream(), Paths.get(PATH, originalFilename));
                model.addAttribute("ImgUploadMsg","图片保存成功");
                System.out.println("图片保存成功");
                return "true";
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("ImgUploadMsg","上传错误，请重新上传");
            }
        }else {
            model.addAttribute("ImgUploadMsg","图片已存在");
        }
        return "false";
    }

}
package com.dr.framework.sys.controller;

import com.dr.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * 编辑器内的图片上传
 *
 * @Author: dr
 */
@RestController
@RequestMapping(value = "/api/uploadImage")
public class UploadImageController {


    //获取项目IP和端口号，编辑器用
    @Autowired
    private ServerConfig serverConfig;

    @RequestMapping(value = "/uplaodWangEditorImage")
    public Map<String, Object> uplaodWangEditorImage(@RequestParam("files") List<MultipartFile> files) {
        String realPath = "e://uploadFiles";
        File rootFilePath = new File(realPath);
        if (!rootFilePath.isDirectory()) {
            //递归生成文件夹
            rootFilePath.mkdirs();
        }
        int errno = 0;
        //返回值。wangeditor上传图片要求图片格式固定
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        String oldName;
        String newName;
        File newFile;
        for (MultipartFile uploadFile : files) {
            //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
            oldName = uploadFile.getOriginalFilename();
            newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            try {
                String a = rootFilePath.getAbsolutePath() + File.separator + newName;
                //构建真实的文件路径
                newFile = new File(a);
                //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
                uploadFile.transferTo(newFile);
                //图片路径，返回给前台
                list.add(serverConfig.getUrl() + "/img/editorimg/" + newName);
            } catch (Exception e) {
                e.printStackTrace();
                errno = 1;
            }
        }
        map.put("errno", errno);
        map.put("data", list);
        return map;
    }

}

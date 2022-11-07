package com.YongChang.controller.common;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.YongChang.config.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
@Controller
@RequestMapping("fileNoCos")
public class ImgControllerNoCos {
    public static final String FILE_PATH = "F:\\immersive_shopping\\";
    static {
        File file = new File(FILE_PATH);
        file.mkdirs();
    }


    @PostMapping("/upload")
    @ResponseBody
    public Result uplaod(@RequestParam("file") MultipartFile file)throws Exception {
        String fileName =  IdWorker.get32UUID() +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            File file1 = new File(FILE_PATH+"\\"+fileName);
            file1.createNewFile();
            OutputStream os=new FileOutputStream(file1.getPath());
            InputStream is=file.getInputStream();
            int temp;
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();
            InputStream inputStream = new FileInputStream(file1.getPath());
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.error("上传文件失败");
        }
        return Result.success("/file/pic?pictureName="+fileName,"文件上传成功");
    }
    @RequestMapping("/pic")
    public void ShowImg(String pictureName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileInputStream fileIs=null;
        OutputStream outStream = null;
        try {
            fileIs = new FileInputStream(FILE_PATH+"/"+pictureName);
            int i=fileIs.available();
            byte data[]=new byte[i];
            fileIs.read(data);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outStream=response.getOutputStream();
            outStream.write(data);
            outStream.flush();
        } catch (Exception e) {
        }finally {
            if(outStream!=null){
                outStream.close();
                fileIs.close();
            }
        }
    }
}

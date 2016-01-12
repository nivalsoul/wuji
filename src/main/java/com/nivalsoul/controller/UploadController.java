package com.nivalsoul.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller  
@RequestMapping("/data") 
public class UploadController {
	
	@RequestMapping(value ="uploadImg", method = RequestMethod.GET)
    public void test(
    		HttpServletRequest request, HttpServletResponse response) throws Exception{
		String url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		System.out.println(url);
		
	}

	@RequestMapping(value ="uploadImg", method = RequestMethod.POST)
    public void uploadImg(@RequestParam(value ="wangEditor_uploadImg") MultipartFile[] files,
    		HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path = request.getServletContext().getRealPath("/uploadImages");
		System.out.println(request.getContextPath());
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        String fileName = "";// 文件名称

        /**上传文件处理内容**/
        try {  
            for (MultipartFile mf : files) {  
                if(!mf.isEmpty()){  
                	String ofilename=mf.getOriginalFilename();
                	fileName = UUID.randomUUID()+"."+ofilename.split("[.]")[1];
                	mf.transferTo(new File(path, fileName));
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        /**********************/

        //组织http响应数据，返回html内容
        //src="页面地址#ok|图片路径"
        String url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        String assitUrl = url+"/wangEditor_uploadImg_assist.html";
        String html = "<iframe src='"+assitUrl+"#ok|"+url+"/uploadImages/"+fileName+"'></iframe>";
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(html);
        out.flush();
        out.close();
    }


}

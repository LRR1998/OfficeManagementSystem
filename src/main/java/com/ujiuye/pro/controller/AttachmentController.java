package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(String path) throws IOException {
        FileInputStream fis = new FileInputStream(new File(path));
        byte[] b = new byte[fis.available()];
        fis.read(b);
        MultiValueMap<String,String> headers = new HttpHeaders();
        String[] split = path.split("/");
        String attname = split[split.length-1];
        attname = new String(attname.getBytes("gbk"),"iso8859-1");
        headers.add("Content-Disposition","attachment;filename="+attname);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(b,headers,status);
        return result;
    }
    //保存附件信息，包含文件上传v
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Attachment attachment,MultipartFile attFile, HttpServletRequest request) throws IOException {
        String realPath = request.getRealPath("/upload");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String fileName = UUID.randomUUID().toString()+attFile.getOriginalFilename();
        attFile.transferTo(new File(realPath+"/"+fileName));
        attachment.setPath(realPath+"/"+fileName);
        attachmentService.saveInfo(attachment);
        return "redirect:getAttachments";
    }
    //获取所有附件信息列表
    @RequestMapping(value = "/getAttachments",method = RequestMethod.GET)
    public ModelAndView getAttachments(){
        ModelAndView mv = new ModelAndView("project-file");
        List<Attachment> list = attachmentService.getAttachments();
        mv.addObject("list",list);
        return mv;
    }
}

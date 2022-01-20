package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.File;
import com.my.pojo.FileExample;
import com.my.service.DepartService;
import com.my.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    Gson gson = new Gson();
    private FileService fileService;
    private DepartService departService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    @ResponseBody
    @RequestMapping(value = "/getFilePage", produces = "text/plain;charset=UTF-8")
    public String getFilePage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<com.my.pojo.File> fileList = new ArrayList<>();
        FileExample fileExample = new FileExample();
        FileExample.Criteria criteria = fileExample.createCriteria();
        criteria.andSenderLike("%" + sousuo + "%");
        fileExample.setOrderByClause("file_id DESC");
        PageHelper.startPage(currentPage, perPageCount);
        fileList = fileService.selectByExample(fileExample);

        if (fileList != null) {
            map.put("message", "success");
            PageInfo<com.my.pojo.File> pageInfo = new PageInfo<com.my.pojo.File>(fileList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("fileList", gson.toJson(fileList));

        } else {
            map.put("message", "文件列表为空");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyFilePage", produces = "text/plain;charset=UTF-8")
    public String getMyFilePage(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<File> fileList = new ArrayList<>();
        if (sousuo.equals("")) {
            FileExample fileExample = new FileExample();
            FileExample.Criteria criteria = fileExample.createCriteria();
            criteria.andSenderEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
            fileExample.setOrderByClause("file_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            fileList = fileService.selectByExample(fileExample);
        } else {
            //设置分页条件
            FileExample fileExample = new FileExample();
            FileExample.Criteria criteria = fileExample.createCriteria();
            criteria.andSenderEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
            fileExample.setOrderByClause("file_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            fileList = fileService.selectByExample(fileExample);
        }
        if (fileList != null) {
            map.put("message", "success");
            PageInfo<File> pageInfo = new PageInfo<File>(fileList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("fileList", gson.toJson(fileList));
        } else {
            map.put("message", "文件列表为空");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyFilePage1", produces = "text/plain;charset=UTF-8")
    public String getMyFilePage1(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<File> fileList1 = new ArrayList<>();
        if (sousuo.equals("")) {
            FileExample fileExample = new FileExample();
            FileExample.Criteria criteria = fileExample.createCriteria();
            criteria.andRecriverEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
            fileExample.setOrderByClause("file_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            fileList1 = fileService.selectByExample(fileExample);
        } else {
            //设置分页条件
            FileExample fileExample = new FileExample();
            FileExample.Criteria criteria = fileExample.createCriteria();
            criteria.andRecriverEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
            fileExample.setOrderByClause("file_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            fileList1 = fileService.selectByExample(fileExample);
        }
        if (fileList1 != null) {
            map.put("message", "success");
            PageInfo<File> pageInfo = new PageInfo<File>(fileList1);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("fileList1", gson.toJson(fileList1));
        } else {
            map.put("message", "文件列表为空");
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/uploadFileByEmp", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String uploadFileByEmp(HttpServletRequest request,
                                  @RequestParam("file") MultipartFile file)
            throws IOException {
        String result = saveFile(request, file, "files");
        if (result != null) {
            return result;
        }
        return "false";
    }

    public String saveFile(HttpServletRequest request,
                           MultipartFile file, String path) throws IOException {
        if (!file.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            System.out.println("文件非空");
            //获取上传文件的保存位置
            String savepath = request.getSession().getServletContext().
                    getRealPath("/" + path + "/");
            System.out.println(savepath);
            //判断该路径是否存在
            java.io.File filePath = new java.io.File(savepath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String oldName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            long size = file.getSize();
            System.out.println("文件名称：" + oldName);
            System.out.println("文件大小：" + size);
            //文件传输
            file.transferTo(new java.io.File(filePath, oldName));
            Gson gson = new Gson();
            map.put("code", "000000");
            map.put("fileUrl", oldName);
            return gson.toJson(map);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/addFileByEmp", produces = "text/plain;charset=UTF-8")
    public String addGoods(@RequestParam("workId") String workId,
                           @RequestParam("departId") String departId,
                           @RequestParam("select") String select,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("fileUrl") String fileUrl) {
        Map<String, Object> map = new HashMap<>();
        if (select.equals("经理")) {
            select = departService.selectByPrimaryKey
                    (Integer.parseInt(departId)).getWorkId();
            if (select.equals("1")) {
                map.put("message", "该部门还未设置经理，请联系管理员");
                return new Gson().toJson(map);
            } else {
                File file = new File();
                file.setSender(workId);
                file.setRecriver(select);
                file.setTitle(title);
                file.setContent(content);
                file.setFileUrl(fileUrl);
                file.setTime(new Date());

                if (fileService.insertSelective(file) == 1) {
                    map.put("message", "success");
                    return new Gson().toJson(map);
                } else {
                    map.put("message", "error");
                    return new Gson().toJson(map);
                }
            }
        } else {
            File file = new File();
            file.setSender(workId);
            file.setRecriver(select);
            file.setTitle(title);
            file.setContent(content);
            file.setFileUrl(fileUrl);
            file.setTime(new Date());
            if (fileService.insertSelective(file) == 1) {
                map.put("message", "success");
                return new Gson().toJson(map);
            } else {
                map.put("message", "error");
                return new Gson().toJson(map);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addFileByAdmin", produces = "text/plain;charset=UTF-8")
    public String addGoods(@RequestParam("select") String select,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("fileUrl") String fileUrl) {
        Map<String, Object> map = new HashMap<>();

        File file = new File();
        file.setSender("00000");
        file.setRecriver(select);
        file.setTitle(title);
        file.setContent(content);
        file.setFileUrl(fileUrl);
        file.setTime(new Date());

        if (fileService.insertSelective(file) == 1) {
            map.put("message", "success");
            return new Gson().toJson(map);
        } else {
            map.put("message", "error");
            return new Gson().toJson(map);
        }

    }

    @RequestMapping(value = "/downloadFile", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
                                               String filename) throws Exception {
        System.out.println("filename" + filename);
        //String filename=fileService.selectByPrimaryKey(
        //Integer.parseInt(fileId)).getFileUrl();
        //指定要下载的文件所在路径
        String path = request.getServletContext().getRealPath("/files/");
        System.out.println("下载路径：" + path);
        //创建该文件对象
        java.io.File file = new java.io.File(path
                + java.io.File.separator + filename);
        System.out.println("该文件对象：" + file);
        //对文件名编码，防止中文文件乱码
        filename = this.getFilename(request, filename);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment", filename);
        //定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用 Sring MVC 框架的 ResponseEntity 对象封装返回下载数据
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }
    //根据浏览器的不同进行编码设置，返回编码后的文件名
    public String getFilename(HttpServletRequest request, String filename)
            throws Exception {
        //IE不同版本 User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        //获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //火狐等其他浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }
}

package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.mapper.StudentMapper;
import cn.edu.usst.spm.req.StudentLoginReq;
import cn.edu.usst.spm.req.StudentSaveReq;
import cn.edu.usst.spm.req.TeamReq;
import cn.edu.usst.spm.resp.CommonResp;
import cn.edu.usst.spm.resp.StudentLoginResp;
import cn.edu.usst.spm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentrmapper;

    @PostMapping("/login")
    public CommonResp login(@RequestBody StudentLoginReq req, HttpSession session){
        CommonResp<Object> resp = new CommonResp<>();
        StudentLoginResp studentLoginResp =  studentService.login(req);
        if (studentLoginResp == null) {
            resp.setSuccess(false);
            return resp;
        }
        session.setAttribute("username",studentLoginResp.getUsername());
        session.setAttribute("isTeacher",false);
        return resp;
    }
    @PostMapping("/register")
    public CommonResp register(@RequestBody StudentSaveReq req){
        //@Requestbody 简单的说就是，把客户的json对象转换为javaBean对象
        CommonResp resp = new CommonResp<>();
        Boolean studentLoginResp =  studentService.register(req);
//        resp.setContent(studentLoginResp);
        if(studentLoginResp){
            return resp;
        }else {
            resp.setSuccess(false);
            return resp;
        }
    }

    @PostMapping("/team")
    public CommonResp Teamup(@RequestBody TeamReq req){
        CommonResp resp = new CommonResp<>();
        Boolean teamresp = studentService.saveteam(req);
//        resp.setContent(studentLoginResp);
        if (!teamresp) {
            resp.setSuccess(false);
        }
        return resp;
    }

    @GetMapping("/findAll")
    public List studentquery(){
//        List<User>list= usermapper.find();
        List<StudentPO>list = studentrmapper.selectList(null);//查询全部
        System.out.println(list);
        return list;
    }


}

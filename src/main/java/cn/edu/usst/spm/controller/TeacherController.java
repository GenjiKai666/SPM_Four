package cn.edu.usst.spm.controller;


import cn.edu.usst.spm.req.TeacherLoginReq;
import cn.edu.usst.spm.req.TeacherSaveReq;
import cn.edu.usst.spm.resp.CommonResp;
import cn.edu.usst.spm.resp.TeacherLoginResp;
import cn.edu.usst.spm.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
//    @Autowired
//    private Teachermapper teachermapper;

    @PostMapping("/login")
    public CommonResp login(@RequestBody TeacherLoginReq req, HttpSession session){
        CommonResp<Object> resp = new CommonResp<>();
        TeacherLoginResp teacherLoginResp =  teacherService.login(req);
        if (teacherLoginResp == null) {
            resp.setSuccess(false);
            return resp;
        }
        session.setAttribute("username",teacherLoginResp.getUsername());
        session.setAttribute("isTeacher",true);
        return resp;
    }
    @PostMapping("/register")
    public CommonResp register(@RequestBody TeacherSaveReq req){
        //@Requestbody 简单的说就是，把客户的json对象转换为javaBean对象
        CommonResp resp = new CommonResp<>();
        Boolean teacherLogin = teacherService.register(req);
//        resp.setContent(studentLoginResp);
        if (!teacherLogin) {
            resp.setSuccess(false);
        }
        return resp;

    }

//    @GetMapping("/findAll")
//    public List studentquery(){
////        List<User>list= usermapper.find();
//        List<Teacher>list = teachermapper.selectList(null);//查询全部
//        System.out.println(list);
//        return list;
//    }

}

package cn.edu.usst.spm.controller;


import cn.edu.usst.spm.bean.LoginUserImpl;
import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.mapper.TeacherMapper;
import cn.edu.usst.spm.req.TeacherLoginReq;
import cn.edu.usst.spm.req.TeacherSaveReq;
import cn.edu.usst.spm.resp.CommonResp;
import cn.edu.usst.spm.resp.TeacherLoginResp;
import cn.edu.usst.spm.service.TeacherService;
import cn.edu.usst.spm.util.Constant;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;

    @PostMapping("/login")
    public CommonResp login(@RequestBody TeacherLoginReq req, HttpSession session){
        CommonResp<Object> resp = new CommonResp<>();
        TeacherLoginResp teacherLoginResp =  teacherService.login(req);
        if (teacherLoginResp == null) {
            resp.setSuccess(false);
            return resp;
        }
        session.setAttribute("username", teacherLoginResp.getUsername());
        session.setAttribute("isTeacher", true);
        // 补充之前定义的接口的登陆状态记录，不移除上面的记录，防止不兼容问题发生
        TeacherPO teacherPO = teacherMapper.selectOne(Wrappers.lambdaQuery(TeacherPO.class)
                .eq(TeacherPO::getUserName, teacherLoginResp.getUsername()));
        session.setAttribute(Constant.USER, new LoginUserImpl(teacherPO.getId(), false));
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

package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.LoginUserImpl;
import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.mapper.StudentTeacherMapper;
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
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentTeacherMapper studentTeacherMapper;

    //查询所有数据接口
    @GetMapping("/findall")
    public List<TeacherPO> index() {
        return teacherMapper.findAll();
    }


    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return teacherMapper.deleteById(id);
    }

    @GetMapping("/findweek") //接口路径 :/teacher/findweek
    public List<TeacherPO> findWeek(@RequestParam Integer weeknum){
        if(weeknum == 1){
            return teacherMapper.selectWeekone(weeknum);
        }else if(weeknum == 2){
            return teacherMapper.selectWeektwo(weeknum);
        }else if(weeknum == 3){
            return teacherMapper.selectWeekthree(weeknum);
        }else if(weeknum == 4){
            return teacherMapper.selectWeekfour(weeknum);
        }else{
            return teacherMapper.selectWeekfive(weeknum);
        }

    }

    @PostMapping(value="/connect")
    public void connect(@RequestParam Integer studentId,@RequestParam Integer teacherId) {
        if(studentTeacherMapper.findStudentId(studentId,teacherId)==null){
            studentTeacherMapper.connect(studentId,teacherId);
        }else{
            studentTeacherMapper.deleteConnect(studentId,teacherId);
        }

    }


    @PostMapping("/login")
    public CommonResp login(@RequestBody TeacherLoginReq req, HttpSession session){
        CommonResp<Object> resp = new CommonResp<>();
        TeacherLoginResp teacherLoginResp =  teacherService.login(req);
        teacherLoginResp.setUsername(req.getUsername());
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


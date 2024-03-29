package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.LoginUser;
import cn.edu.usst.spm.bean.LoginUserImpl;
import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.bean.vo.GradeInfoVO;
import cn.edu.usst.spm.mapper.StudentMapper;
import cn.edu.usst.spm.mapper.StudentTeacherMapper;
import cn.edu.usst.spm.mapper.TeacherMapper;
import cn.edu.usst.spm.req.GroupReq;
import cn.edu.usst.spm.req.StudentLoginReq;
import cn.edu.usst.spm.req.StudentSaveReq;
import cn.edu.usst.spm.req.TeamReq;
import cn.edu.usst.spm.resp.CommonResp;
import cn.edu.usst.spm.resp.StudentLoginResp;
import cn.edu.usst.spm.service.StudentService;
import cn.edu.usst.spm.service.TeacherService;
import cn.edu.usst.spm.util.Constant;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentTeacherMapper studentTeacherMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;


    @GetMapping("/findstudents")
    List<StudentPO> findStudents(@RequestParam Integer teacherId) {
        return studentTeacherMapper.findStudentByTeacherId(teacherId);
    };

    @GetMapping("/loginTeacher")
    public ResponseEntity<TeacherPO> showTeacher(HttpSession session) {
        LoginUser user = (LoginUser) session.getAttribute(Constant.USER);

        // 不是老师，返回403拒绝访问
        if (!user.isTeacher()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        TeacherPO teacher = teacherMapper.getTeacher(user.getId());
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/loginStudent")
    public ResponseEntity<StudentPO> showStudent(HttpSession session) {
        LoginUser user = (LoginUser) session.getAttribute(Constant.USER);

        // 不是学生，返回403拒绝访问
        if (!user.isTeacher()) {
            StudentPO student = studentMapper.getStudnet(user.getId());
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentrmapper;

    @PostMapping("/login")
    public CommonResp login(@RequestBody StudentLoginReq req, HttpSession session) {
        CommonResp<Object> resp = new CommonResp<>();
        StudentLoginResp studentLoginResp = studentService.login(req);
        if (studentLoginResp == null) {
            resp.setSuccess(false);
            return resp;
        }
        session.setAttribute("username", studentLoginResp.getUsername());
        session.setAttribute("isTeacher", false);
        // 补充之前定义的接口的登陆状态记录，不移除上面的记录，防止不兼容问题发生
        StudentPO studentPO = studentrmapper.selectOne(Wrappers.lambdaQuery(StudentPO.class)
                .eq(StudentPO::getUsername, studentLoginResp.getUsername()));
        session.setAttribute(Constant.USER, new LoginUserImpl(studentPO.getId(), false));
        return resp;
    }

    @PostMapping("/register")
    public CommonResp register(@RequestBody StudentSaveReq req) {
        //@Requestbody 简单的说就是，把客户的json对象转换为javaBean对象
        CommonResp resp = new CommonResp<>();
        Boolean studentLoginResp = studentService.register(req);
//        resp.setContent(studentLoginResp);
        if (studentLoginResp) {
            return resp;
        } else {
            resp.setSuccess(false);
            return resp;
        }
    }

    @PostMapping("/team")
    public CommonResp Teamup(@RequestBody GroupReq req) {
        CommonResp resp = new CommonResp<>();
        Boolean teamresp = studentService.saveteam(req);
//        resp.setContent(studentLoginResp);
        if (!teamresp) {
            resp.setSuccess(false);
        }
        return resp;
    }

    @GetMapping("/findAll")
    public List studentquery() {
//        List<User>list= usermapper.find();
        List<StudentPO> list = studentrmapper.selectList(null);//查询全部
        System.out.println(list);
        return list;
    }
}

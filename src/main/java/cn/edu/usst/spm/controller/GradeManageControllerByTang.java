package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.LoginUser;
import cn.edu.usst.spm.bean.vo.GradeInfoVO;
import cn.edu.usst.spm.service.GradeManageServiceByTang;
import cn.edu.usst.spm.util.Constant;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Log4j2
public class GradeManageControllerByTang {

    @Autowired
    GradeManageServiceByTang gradeManageServiceByTang;

    @GetMapping("/grade-manage/grades")
    public ResponseEntity<List<GradeInfoVO>> showGrades(HttpSession session) {
        LoginUser user = (LoginUser) session.getAttribute(Constant.USER);

        // 不是老师，返回403拒绝访问
        if (user == null || !user.isTeacher()) {
            log.warn("无权限的访问");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<GradeInfoVO> grades = gradeManageServiceByTang.getGrades(1);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/grade-manage/show-grade")
    public ResponseEntity<GradeInfoVO> showGrade(HttpSession session) {
        LoginUser user = (LoginUser) session.getAttribute(Constant.USER);

        // 不是学生，返回403拒绝访问
        if (user == null || user.isTeacher()) {
            log.warn("无权限的访问");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        GradeInfoVO grade = gradeManageServiceByTang.getGrade(user.getId());
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @PostMapping("/grade-manage/send-grade-info")
    public ResponseEntity<Integer> sendText(@RequestBody List<Integer> receivers, HttpSession session) {
        LoginUser user = (LoginUser) session.getAttribute(Constant.USER);
        // 不是老师，403拒绝
        if (user == null || !user.isTeacher()) {
            log.warn("无权限的访问");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (receivers != null && receivers.size() > 0) {
            gradeManageServiceByTang.sendGradeInfo(receivers);
        }
        return new ResponseEntity<>(0, HttpStatus.OK);
    }

    @PostMapping("/grade-manage/send-grade-warning")
    public ResponseEntity<Integer> sendWarning(@RequestBody List<Integer> receivers, HttpSession session) {
        LoginUser user = (LoginUser) session.getAttribute(Constant.USER);
        // 不是老师，403拒绝
        if (user == null || !user.isTeacher()) {
            log.warn("无权限的访问");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (receivers != null && receivers.size() > 0) {
            gradeManageServiceByTang.sendGradeWarning(receivers);
        }
        return new ResponseEntity<>(0, HttpStatus.OK);
    }
}

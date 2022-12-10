package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.mapper.StudentTeacherMapper;
import cn.edu.usst.spm.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student_teacher")
public class StudentTeacherController {

    @Autowired
    private StudentTeacherMapper studentTeacherMapper;

    @PostMapping("/confirmed")
    public void confirmedStudents(@RequestParam int teacherId){
      studentTeacherMapper.confirmed(teacherId);
    };
}

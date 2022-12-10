package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.mapper.StudentTeacherMapper;
import cn.edu.usst.spm.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherMapper teacherMapper;

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
        studentTeacherMapper.connect(studentId,teacherId);
    }

}
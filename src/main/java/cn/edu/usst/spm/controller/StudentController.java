package cn.edu.usst.spm.controller;

        import cn.edu.usst.spm.bean.po.StudentPO;
        import cn.edu.usst.spm.bean.po.TeacherPO;
        import cn.edu.usst.spm.mapper.StudentTeacherMapper;
        import cn.edu.usst.spm.mapper.TeacherMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentTeacherMapper studentTeacherMapper;


    @GetMapping("/findstudents")
    List<StudentPO> findStudents(@RequestParam Integer teacherId){
        return studentTeacherMapper.findStudentByTeacherId(teacherId);
    };

}

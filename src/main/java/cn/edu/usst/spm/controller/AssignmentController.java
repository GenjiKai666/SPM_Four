package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.LoginUserImpl;
import cn.edu.usst.spm.bean.po.AssignmentPO;
import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.bean.vo.AssignmentVO;
import cn.edu.usst.spm.bean.vo.LoginCheck;
import cn.edu.usst.spm.bean.vo.StudentComittedAnswerVO;
import cn.edu.usst.spm.mapper.StudentMapper;
import cn.edu.usst.spm.mapper.TeacherMapper;
import cn.edu.usst.spm.service.AssignmentService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 处理发布新题目的请求
     *
     * @param question 题干
     * @param date     截止时间
     * @return 成功返回1，失败返回0
     */
    @RequestMapping(value = "/assignment/insert", method = RequestMethod.POST)
    public int insertAssignment(@NotNull @RequestParam("question") String question,
                                @NotNull @RequestParam("date") long date,
                                @NotNull @RequestParam("bygroup") boolean byGroup) {
        return assignmentService.insertAssignment(new AssignmentPO(null, question, new Date(date),byGroup));
    }

    /**
     * 处理搜索题目的请求
     *
     * @param id 题目ID
     * @return 返回类
     */
    @RequestMapping(value = "/assignment/select", method = RequestMethod.GET)
    public AssignmentVO selectAssignmentById(@NotNull @RequestParam("id") Integer id) {
        AssignmentPO assignmentPO = assignmentService.selectAssignmentById(id);
        if (assignmentPO == null) {
            return new AssignmentVO(null, null,null,null);
        }
        return new AssignmentVO(assignmentPO.getId(),assignmentPO.getQuestion(), assignmentPO.getDeadline().toString(),null);
    }

    /**
     * 处理删除某次作业的请求
     *
     * @param id 题目ID
     * @return 返回类
     */
    @RequestMapping(value = "/assignment/delete", method = RequestMethod.POST)
    public int deleteAssignmentById(@NotNull @RequestParam("id") Integer id) {
        return assignmentService.deleteAssignmentById(id);
    }
    @RequestMapping(value = "/assignment/student_submit",method = RequestMethod.GET)
    public int studentSubmitAnswer(@NotNull @RequestParam("assignmentid") Integer assignmentId,
                                   @NotNull @RequestParam("studentid") Integer studentId,
                                   @RequestParam("answer") String answer){
        return  assignmentService.studentSubmitAnswer(assignmentId,studentId,answer);
    }
    @RequestMapping(value = "/assignment/teacher_check",method = RequestMethod.GET)
    public int teacherCheckById(@NotNull @RequestParam("id") Integer id,
                                @NotNull @RequestParam("score") Double score){
        return assignmentService.teacherCheckById(id,score);
    }
    @RequestMapping(value = "/assignment/getassignmentbystudentid",method = RequestMethod.GET)
    public List<AssignmentVO> getassignmentbystudentid(@NotNull @RequestParam("studentid") Integer studentid){
        List<AssignmentVO> assignmentVOS = assignmentService.getAssignmentByStudentId(studentid);
        return  assignmentVOS;
    }
    @RequestMapping(value = "/assignment/getuserinfo",method = RequestMethod.GET)
    public LoginCheck getUserInfo(HttpSession session){
        String username = (String)session.getAttribute("username");
        Boolean isTeacher = (Boolean) session.getAttribute("isTeacher");
        Integer teacher;
        if(isTeacher == null || username == null){
            return null;
        }
        Integer id = null;
        if(isTeacher){
            id = teacherMapper.selectOne(Wrappers
                    .lambdaQuery(TeacherPO.class)
                    .eq(TeacherPO::getUserName,username))
                    .getId();
            teacher = 1;
        }
        else{
            id = studentMapper.selectOne(Wrappers
                            .lambdaQuery(StudentPO.class)
                            .eq(StudentPO::getUsername,username))
                    .getId();
            teacher = 0;
        }
        return new LoginCheck(id,teacher);
    }
    @RequestMapping(value = "/assignment/getcommittedanswerbyteacherid",method = RequestMethod.GET)
    public List<StudentComittedAnswerVO> getAnswers(@RequestParam("teacherid") @NotNull Integer teacherid){
        return assignmentService.getCommittedAnswerByTeacherId(teacherid);
    }
}

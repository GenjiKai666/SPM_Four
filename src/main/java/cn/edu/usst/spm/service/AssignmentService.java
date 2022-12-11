package cn.edu.usst.spm.service;

import cn.edu.usst.spm.bean.po.AssignmentPO;
import cn.edu.usst.spm.bean.po.StudentTeacherAssignmentPO;
import cn.edu.usst.spm.bean.po.StudentTeacherPO;
import cn.edu.usst.spm.bean.vo.AssignmentVO;
import cn.edu.usst.spm.bean.vo.StudentComittedAnswerVO;
import cn.edu.usst.spm.mapper.AssignmentMapper;
import cn.edu.usst.spm.mapper.StudentMapper;
import cn.edu.usst.spm.mapper.StudentTeacherAssignmentMapper;
import cn.edu.usst.spm.mapper.StudentTeacherMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AssignmentService {
    @Autowired
    AssignmentMapper assignmentMapper;
    @Autowired
    StudentTeacherAssignmentMapper studentTeacherAssignmentMapper;
    @Autowired
    StudentTeacherMapper studentTeacherMapper;
    @Autowired
    StudentMapper studentMapper;

    /**
     * 向数据库中插入新的作业
     *
     * @param assignment 作业
     * @return 1为成功，0为失败
     */
    public int insertAssignment(AssignmentPO assignment) {
        if (assignmentMapper.insert(assignment) == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * 通过ID查找某次作业
     *
     * @param id 作业ID
     * @return 返回类
     */
    public AssignmentPO selectAssignmentById(int id) {
        return assignmentMapper.selectById(id);
    }

    /**
     * 通过ID删除某次作业
     *
     * @param id 作业ID
     * @return 返回类
     */
    public int deleteAssignmentById(int id) {
        if (assignmentMapper.deleteById(id) == 1) {
            return 1;
        }
        return 0;
    }

    public int studentSubmitAnswer(Integer assignmentId, Integer studentId, String answer) {
        Integer student_teacher_id = studentTeacherMapper.selectOne(Wrappers
                .lambdaQuery(StudentTeacherPO.class)
                .eq(StudentTeacherPO::getStudentId,studentId))
                .getId();

        StudentTeacherAssignmentPO po = studentTeacherAssignmentMapper.selectOne(Wrappers
                .lambdaQuery(StudentTeacherAssignmentPO.class)
                .eq(StudentTeacherAssignmentPO::getStudentTeacherId,student_teacher_id)
                .eq(StudentTeacherAssignmentPO::getAssignmentId,assignmentId));
        po.setAnswer(answer);
        studentTeacherAssignmentMapper.updateById(po);

        return 1;
    }

    public int teacherCheckById(Integer id, Double score) {
        studentTeacherAssignmentMapper.updateById(new StudentTeacherAssignmentPO(id,
                null,
                null,
                null,
                score));
        return 1;
    }

    public List<AssignmentVO> getAssignmentByStudentId(Integer studentId) {
        List<AssignmentVO> assignments = new ArrayList<>();
        Integer student_teacher_id = studentTeacherMapper.selectOne(Wrappers.lambdaQuery(StudentTeacherPO.class)
                .eq(StudentTeacherPO::getStudentId, studentId)).getId();
        List<StudentTeacherAssignmentPO> studentTeacherAssignmentList = studentTeacherAssignmentMapper
                .selectList(Wrappers
                        .lambdaQuery(StudentTeacherAssignmentPO.class)
                        .eq(StudentTeacherAssignmentPO::getStudentTeacherId, student_teacher_id)
                        .last("order by ASSIGNMENT_ID"));
        for(StudentTeacherAssignmentPO po:studentTeacherAssignmentList){
            AssignmentPO temp = assignmentMapper.selectById(po.getAssignmentId());
            if(temp.getByGroup() == false){
                assignments.add(new AssignmentVO(temp.getId(), temp.getQuestion(),temp.getDeadline().toString(),po.getScore()));
            }
        }
        return assignments;
    }
    public List<StudentComittedAnswerVO> getCommittedAnswerByTeacherId(Integer teacherid){
        List<StudentComittedAnswerVO> studentComittedAnswerVOS = new ArrayList<>();
        List<StudentTeacherPO> studentTeacherPOS =
                studentTeacherMapper.selectList(Wrappers
                        .lambdaQuery(StudentTeacherPO.class)
                        .eq(StudentTeacherPO::getTeacherId,teacherid));
        for(StudentTeacherPO studentTeacherPO:studentTeacherPOS){
            List<StudentTeacherAssignmentPO> studentTeacherAssignmentPOS =
                    studentTeacherAssignmentMapper.selectList(Wrappers
                            .lambdaQuery(StudentTeacherAssignmentPO.class)
                            .eq(StudentTeacherAssignmentPO::getStudentTeacherId,studentTeacherPO.getId()));
            for(StudentTeacherAssignmentPO studentTeacherAssignmentPO:studentTeacherAssignmentPOS){
                String question = assignmentMapper.selectById(studentTeacherAssignmentPO.getId()).getQuestion();
                String studentName = studentMapper.selectById(studentTeacherPO.getStudentId()).getUsername();
                studentComittedAnswerVOS.add(new StudentComittedAnswerVO(studentTeacherAssignmentPO.getId(),
                        question,
                        studentTeacherAssignmentPO.getAssignmentId(),
                        studentName,
                        studentTeacherAssignmentPO.getStudentTeacherId(),
                        studentTeacherAssignmentPO.getAnswer(),
                        studentTeacherAssignmentPO.getScore()));

            }
        }
        return studentComittedAnswerVOS;
    }
}

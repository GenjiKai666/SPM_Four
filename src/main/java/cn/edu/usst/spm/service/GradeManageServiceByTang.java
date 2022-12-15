package cn.edu.usst.spm.service;

import cn.edu.usst.spm.bean.po.ScorePO;
import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.StudentTeacherPO;
import cn.edu.usst.spm.bean.vo.GradeInfoVO;
import cn.edu.usst.spm.mapper.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Validated
public class GradeManageServiceByTang {

    StudentMapper studentMapper;
    TeacherMapper teacherMapper;
    StudentTeacherMapper studentTeacherMapper;
    StudentTeacherAssignmentMapper studentTeacherAssignmentMapper;
    ScoreMapper scoreMapper;
    MailService mailService;

    @Autowired
    public GradeManageServiceByTang(StudentMapper studentMapper,
                                    TeacherMapper teacherMapper,
                                    StudentTeacherMapper studentTeacherMapper,
                                    StudentTeacherAssignmentMapper studentTeacherAssignmentMapper,
                                    ScoreMapper scoreMapper,
                                    MailService mailService) {
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
        this.studentTeacherMapper = studentTeacherMapper;
        this.studentTeacherAssignmentMapper = studentTeacherAssignmentMapper;
        this.scoreMapper = scoreMapper;
        this.mailService = mailService;
    }

    public GradeInfoVO getGrade(int studentId) {
        StudentPO studentPO = studentMapper.selectById(studentId);
        StudentTeacherPO studentTeacherPO = studentTeacherMapper
                .selectOne(Wrappers
                        .lambdaQuery(StudentTeacherPO.class)
                        .eq(StudentTeacherPO::getStudentId, studentId));
        ScorePO scorePO = null;
        if (studentTeacherPO != null) {
            scorePO = scoreMapper.selectOne(Wrappers.lambdaQuery(ScorePO.class)
                    .eq(ScorePO::getStudentTeacherId, studentTeacherPO.getId()));
        }

        return getGrade(studentPO, scorePO);
    }

    public GradeInfoVO getGrade(int studentId, int studentTeacherId) {
        StudentPO studentPO = studentMapper.selectById(studentId);
        ScorePO scorePO = scoreMapper.selectOne(Wrappers.lambdaQuery(ScorePO.class)
                .eq(ScorePO::getStudentTeacherId, studentTeacherId));

        return getGrade(studentPO, scorePO);
    }

    public GradeInfoVO getGrade(StudentPO studentPO, ScorePO scorePO) {
        if (scorePO == null) {
            return new GradeInfoVO(studentPO.getId(),
                    studentPO.getUsername(),
                    studentPO.getClassName(),
                    null,
                    null,
                    null,
                    null,
                    null);
        } else {
            return new GradeInfoVO(studentPO.getId(),
                    studentPO.getUsername(),
                    studentPO.getClassName(),
                    scorePO.getUsualGrade(),
                    scorePO.getMidExamGrade(),
                    scorePO.getFinalExamGrade(),
                    scorePO.getExperimentGrade(),
                    getAllGrade(scorePO));
        }

    }

    private double getAllGrade(ScorePO scorePO) {
        return scorePO.getUsualGrade() * 0.1 +
                scorePO.getMidExamGrade() * 0.1 +
                scorePO.getExperimentGrade() * 0.2 +
                scorePO.getFinalExamGrade() * 0.6;
    }

    /**
     * 根据搜索字符串，查找成绩
     *
     * @param id 老师的id
     * @return 找到的成绩信息数组
     */
    public List<GradeInfoVO> getGrades(int id) {
        List<GradeInfoVO> result = new ArrayList<>();
        List<StudentTeacherPO> studentTeacherPOS = studentTeacherMapper
                .selectList(Wrappers.lambdaQuery(StudentTeacherPO.class)
                        .eq(StudentTeacherPO::getTeacherId, id)
                        .eq(StudentTeacherPO::getIsConfirmed, true));
        for (StudentTeacherPO studentTeacherPO : studentTeacherPOS) {
            result.add(getGrade(studentTeacherPO.getStudentId(), studentTeacherPO.getId()));
        }
        return result;
    }

    private String formatGradeInfo(GradeInfoVO gradeInfoVO) {
        String message = """
                姓名：{0}
                班级：{1}
                平时成绩：{2}
                期中成绩：{3}
                期末成绩：{4}
                总成绩：{5}
                """;
        return MessageFormat.format(message,
                gradeInfoVO.getName(),
                gradeInfoVO.getClassName(),
                gradeInfoVO.getUsualGrade(),
                gradeInfoVO.getMidExamGrade(),
                gradeInfoVO.getFinalExamGrade(),
                gradeInfoVO.getAllGrade());
    }

    public void sendGradeInfo(@NotNull List<Integer> studentIds) {
        for (Integer studentId : studentIds) {
            StudentPO studentPO = studentMapper.selectOne(Wrappers.lambdaQuery(StudentPO.class)
                    .eq(StudentPO::getId, studentId));
            GradeInfoVO grade = getGrade(studentPO.getId());
            mailService.sendSimple(studentPO.getEmail(), "成绩通知", formatGradeInfo(grade));
        }
    }

    public void sendGradeWarning(@NotNull List<Integer> studentIds) {
        for (Integer studentId : studentIds) {
            StudentPO studentPO = studentMapper.selectOne(Wrappers.lambdaQuery(StudentPO.class)
                    .eq(StudentPO::getId, studentId));
            GradeInfoVO grade = getGrade(studentPO.getId());
            mailService.sendSimple(studentPO.getEmail(),
                    "成绩预警",
                    formatGradeInfo(grade) + "\n请准备补考！！！");
        }
    }

}

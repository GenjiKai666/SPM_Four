package cn.edu.usst.spm.service;

import cn.edu.usst.spm.bean.po.AssignmentPO;
import cn.edu.usst.spm.bean.po.StudentTeacherAssignmentPO;
import cn.edu.usst.spm.mapper.AssignmentMapper;
import cn.edu.usst.spm.mapper.StudentTeacherAssignmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssignmentService {
    @Autowired
    AssignmentMapper assignmentMapper;
    @Autowired
    StudentTeacherAssignmentMapper studentTeacherAssignmentMapper;

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
    public int studentSubmitAnswer(Integer assignmentId, Integer studentTeacherId, String answer){
        studentTeacherAssignmentMapper.insert(new StudentTeacherAssignmentPO(null,
                assignmentId,studentTeacherId,answer,null));
        return 1;
    }
    public int teacherCheckById(Integer id,Double score){
        studentTeacherAssignmentMapper.updateById(new StudentTeacherAssignmentPO(id,
                null,
                null,
                null,
                score));
        return 1;
    }
}

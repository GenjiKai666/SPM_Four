package cn.edu.usst.spm.mapper;

import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.StudentTeacherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentTeacherMapper extends BaseMapper<StudentTeacherPO> {


    @Insert("INSERT INTO STUDENT_TEACHER(STUDENT_ID,TEACHER_ID,IS_CONFIRMED) VALUES (#{studentId},#{teacherId},0)")
    void connect(Integer studentId, Integer teacherId);


    @Select("SELECT * FROM STUDENT WHERE ID = ANY (SELECT STUDENT_ID FROM STUDENT_TEACHER WHERE TEACHER_ID = #{teacherId})")
    List<StudentPO> findStudentByTeacherId(Integer teacherId);

    @Update("UPDATE STUDENT_TEACHER SET IS_CONFIRMED = 1 WHERE TEACHER_ID=#{teacherId}" )
    void confirmed(Integer teacherId);
    @Insert("INSERT INTO  score(STUDENT_TEACHER_ID) select ID FROM student_teacher")
    void insert();
    @Select("SELECT STUDENT_ID FROM STUDENT_TEACHER WHERE STUDENT_ID = #{studentId} and TEACHER_ID=#{teacherId}")
    Integer findStudentId(Integer studentId, Integer teacherId);

    @Delete("DELETE FROM STUDENT_TEACHER WHERE STUDENT_ID = #{studentId} and TEACHER_ID=#{teacherId}")
    void deleteConnect(Integer studentId, Integer teacherId);
}

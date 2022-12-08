package cn.edu.usst.spm.mapper;

import cn.edu.usst.spm.bean.po.StudentTeacherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentTeacherMapper extends BaseMapper<StudentTeacherPO> {


    @Insert("INSERT INTO STUDENT_TEACHER(STUDENT_ID,TEACHER_ID,IS_CONFIRMED) VALUES (#{studentId},#{teacherId},0)")
    void connect(Integer studentId, Integer teacherId);
}

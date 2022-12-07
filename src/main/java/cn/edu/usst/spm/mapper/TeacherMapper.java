package cn.edu.usst.spm.mapper;


import cn.edu.usst.spm.bean.po.TeacherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TeacherMapper extends BaseMapper<TeacherPO> {

    @Select("SELECT * from teacher")
    List<TeacherPO> findAll();



    @Delete("Delete from teacher where id=#{id}" )
    Integer deleteById(@Param("id") Integer id);

    @Select("SELECT * from teacher where COURSE_TIME LIKE '%周一%' ")
    List<TeacherPO> selectWeekone(Integer weeknum);

    @Select("SELECT * from teacher where COURSE_TIME LIKE '%周二%' ")
    List<TeacherPO> selectWeektwo(Integer weeknum);

    @Select("SELECT * from teacher where COURSE_TIME LIKE '%周三%' ")
    List<TeacherPO> selectWeekthree(Integer weeknum);

    @Select("SELECT * from teacher where COURSE_TIME LIKE '%周四%' ")
    List<TeacherPO> selectWeekfour(Integer weeknum);

    @Select("SELECT * from teacher where COURSE_TIME LIKE '%周五%' ")
    List<TeacherPO> selectWeekfive(Integer weeknum);
}

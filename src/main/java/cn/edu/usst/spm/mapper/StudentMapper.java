package cn.edu.usst.spm.mapper;

import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.TeacherPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper extends BaseMapper<StudentPO> {

    @Select("SELECT * from student where ID =#{id}")
    StudentPO getStudnet(Integer id);
}

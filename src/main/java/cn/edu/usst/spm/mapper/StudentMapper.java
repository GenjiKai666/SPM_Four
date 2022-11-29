package cn.edu.usst.spm.mapper;

import cn.edu.usst.spm.bean.po.StudentPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<StudentPO> {
}

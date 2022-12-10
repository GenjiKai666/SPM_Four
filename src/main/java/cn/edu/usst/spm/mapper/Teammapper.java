package cn.edu.usst.spm.mapper;

import cn.edu.usst.spm.bean.po.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Teammapper extends BaseMapper<Team> {

    @Select("select * from team where headman =#{member} or member01 =#{member} or member02 =#{member} or member03=#{member} or member04 =#{member}")
    public List<Team> selectmember(String member);

}

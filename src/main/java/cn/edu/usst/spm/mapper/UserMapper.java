package cn.edu.usst.spm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springvue.entity.Score;
import org.apache.ibatis.annotations.Param;

//@Mapper 使用了@MapperScan代替，遵从配置要放在配置类上面的原则
public interface UserMapper extends BaseMapper<Score>  {
    Page<Score> findPage(Page<Score> page, @Param("name") String name);


//    @Select("SELECT * FROM userinformation")
//    List<User> findAll();
//
//    @Select("SELECT * FROM userinformation where username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum,Integer pageSize,String username);
//    @Select("SELECT count(*) FROM userinformation where username like concat('%',#{username},'%')")
//    Integer selectTotal(String username);
}

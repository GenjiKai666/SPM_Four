package cn.edu.usst.spm.service.imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springvue.entity.Score;
import com.example.springvue.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScoreServise extends ServiceImpl<UserMapper, Score>  {

    @Resource
    private UserMapper userMapper;
    public boolean saveUser(Score score) {
//        if (user.getId() == null) {
//            return save(user);
//        }else {
//            return updateById(user);
//        }
        return saveOrUpdate(score);

    }

    public Page<Score> findPage(Page<Score> page, String name) {
        return userMapper.findPage(page , name);
    }
}

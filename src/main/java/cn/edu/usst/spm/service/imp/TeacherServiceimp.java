package cn.edu.usst.spm.service.imp;

import cn.edu.usst.spm.bean.po.TeacherPO;
import cn.edu.usst.spm.mapper.TeacherMapper;
import cn.edu.usst.spm.req.TeacherLoginReq;
import cn.edu.usst.spm.req.TeacherSaveReq;
import cn.edu.usst.spm.resp.TeacherLoginResp;
import cn.edu.usst.spm.service.TeacherService;
import cn.edu.usst.spm.service.utils.CopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TeacherServiceimp extends ServiceImpl<TeacherMapper, TeacherPO> implements TeacherService {
    @Autowired
    TeacherMapper teachermapper;
    @Override
    public boolean register(TeacherSaveReq req) {
        TeacherPO teacher = CopyUtil.copy(req, TeacherPO.class);
        if(req.getUsername() != null ){
            TeacherPO teacherDB = selectByusername(req.getUsername());
            if(ObjectUtils.isEmpty(teacherDB)){
                //为空则数据库中没有这一条，则插入新用户
                int i = teachermapper.insert(teacher);
                if(i>0){
                    return true;
                }else
                    return false;
            }
        }
        return false;
    }

    @Override
    public TeacherLoginResp login(TeacherLoginReq req) {
        TeacherPO teacherDB = selectByusername(req.getUsername());
        if(teacherDB == null || !teacherDB.getPassword().equals(req.getPassword())){
            //数据库中没有这条或者密码不为所记录的密码
            return null;
        }else {
            TeacherLoginResp copy = CopyUtil.copy(teacherDB, TeacherLoginResp.class);
            return copy;
        }
    }

    public TeacherPO selectByusername(String username){
        QueryWrapper<TeacherPO> wrapper = new QueryWrapper<>();
        //eq("数据库字段名字","值")
        wrapper.lambda().eq(TeacherPO::getUserName,username);
        List<TeacherPO> teachers = teachermapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(teachers)){
            return null;
        }else {
            return teachers.get(0);
        }
    }

}

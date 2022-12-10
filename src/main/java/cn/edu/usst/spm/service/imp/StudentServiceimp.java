package cn.edu.usst.spm.service.imp;

import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.Team;
import cn.edu.usst.spm.mapper.StudentMapper;
import cn.edu.usst.spm.mapper.Teammapper;
import cn.edu.usst.spm.req.StudentLoginReq;
import cn.edu.usst.spm.req.StudentSaveReq;
import cn.edu.usst.spm.req.TeamReq;
import cn.edu.usst.spm.resp.StudentLoginResp;
import cn.edu.usst.spm.service.StudentService;
import cn.edu.usst.spm.service.utils.CopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class StudentServiceimp extends ServiceImpl<StudentMapper, StudentPO> implements StudentService {

    @Autowired
    StudentMapper studentrmapper;
    @Autowired
    Teammapper teammapper;
    @Override
    public boolean register(StudentSaveReq req) {
        StudentPO student = CopyUtil.copy(req, StudentPO.class);
        if(req.getUsername() != null ){
            StudentPO studentDb = selectByusername(req.getUsername());
            if(ObjectUtils.isEmpty(studentDb)){
                //为空则数据库中没有这一条，则插入新用户
                int i = studentrmapper.insert(student);
                if(i>0){
                    return true;
                }else
                    return false;
            }
        }
        return false;
    }

    @Override
    public StudentLoginResp login(StudentLoginReq req) {
        StudentPO studentDb = selectByusername(req.getUsername());
        if(studentDb == null || !studentDb.getPassword().equals(req.getPassword())){
            //数据库中没有这条或者密码不为所记录的密码
            return null;
        }else {
            StudentLoginResp copy = CopyUtil.copy(studentDb, StudentLoginResp.class);
            return copy;
        }
    }


    @Override
    public boolean saveteam(TeamReq req) {
        //每一位都要查
        Team team = CopyUtil.copy(req, Team.class);
        List<Team> teamDb01;
        List<Team> teamDb02 = null;
        List<Team> teamDb03 = null;
        List<Team> teamDb04 = null;
        List<Team> teamDb05 = null;
        if(req.getHeadman() != null ){
            //组长不为空
            teamDb01 = selectByheadman(req.getHeadman());
            if(ObjectUtils.isEmpty(teamDb01)){
                //为组长为空，可能此组长为其他组的组员，下面从组员开始找
                teamDb01 = teammapper.selectmember(req.getHeadman());
                if(req.getMember01().equals("")){
                    //member01存在，则检查在数据库中是否存在
                     teamDb02 = teammapper.selectmember(req.getMember01());
                }
                if(req.getMember02().equals("")){
                    //member02存在，则检查在数据库中是否存在
                    teamDb03 = teammapper.selectmember(req.getMember02());
                }
                if(req.getMember03().equals("")){
                    //member03存在，则检查在数据库中是否存在
                    teamDb04 = teammapper.selectmember(req.getMember03());
                }
                if(req.getMember04().equals("")){
                    //member04存在，则检查在数据库中是否存在
                    teamDb05 =teammapper.selectmember(req.getMember04());
                }
                if (ObjectUtils.isEmpty(teamDb01)&& ObjectUtils.isEmpty(teamDb02)&& ObjectUtils.isEmpty(teamDb03)&& ObjectUtils.isEmpty(teamDb04)&& ObjectUtils.isEmpty(teamDb05)){
                    //数据库中没有存储新Team中任何人的数据，可以插入数据库
                    int i = teammapper.insert(team);
                    if(i > 0)
                        return true;
                    else
                        return false;
                }
            }//组长存在，拒绝插入
        }//主键组长为空，拒接插入
        return false;
    }

    public StudentPO selectByusername(String username){
        QueryWrapper<StudentPO> wrapper = new QueryWrapper<>();
        //eq("数据库字段名字","值")
        wrapper.lambda().eq(StudentPO::getUsername,username);
        List<StudentPO> studentList = studentrmapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(studentList)){
            return null;
        }else {
            return studentList.get(0);
        }
    }

    public List<Team> selectByheadman(String headman){
        QueryWrapper<Team> wrapper = new QueryWrapper<>();
        //eq("数据库字段名字","值")
        wrapper.lambda().eq(Team::getHeadman,headman);
        List<Team> teamList = teammapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(teamList)){
            return null;
        }else {
            return teamList;
        }
    }

}

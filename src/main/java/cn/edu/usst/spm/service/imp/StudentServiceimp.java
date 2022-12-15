package cn.edu.usst.spm.service.imp;

import cn.edu.usst.spm.bean.po.GroupMemberPO;
import cn.edu.usst.spm.bean.po.GroupPO;
import cn.edu.usst.spm.bean.po.StudentPO;
import cn.edu.usst.spm.bean.po.Team;
import cn.edu.usst.spm.mapper.GroupMapper;
import cn.edu.usst.spm.mapper.GroupMemberMapper;
import cn.edu.usst.spm.mapper.StudentMapper;
import cn.edu.usst.spm.mapper.Teammapper;
import cn.edu.usst.spm.req.GroupReq;
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
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GroupMemberMapper groupMemberMapper;

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
    public boolean saveteam(GroupReq req) {
        int i = -1;
        int j = -1;
        GroupMemberPO groupMemberPO01 = new GroupMemberPO();
        GroupMemberPO groupMemberPO02 = new GroupMemberPO();
        GroupMemberPO groupMemberPO03 = new GroupMemberPO();
        GroupMemberPO groupMemberPO04 = new GroupMemberPO();

        GroupPO groupPO = new GroupPO();
        //小组id
        groupPO.setId(req.getGroupid());
        //组名
        groupPO.setName(req.getName());
        //组长id
        groupPO.setStudentTeacherId(req.getHeadmanid());

        i = groupMapper.insert(groupPO);
//        i = groupMapper.insertgroup(req.getGroupid(),req.getName(),req.getHeadmanid());
        //先把组长插入进去
        //小组id
        groupMemberPO01.setGroupId(req.getGroupid());
        //学生id
        groupMemberPO01.setStudentTeacherId(req.getHeadmanid());
        j = groupMemberMapper.insert(groupMemberPO01);

        //查看组员
        if (req.getMember01id() != 0) {
            groupMemberPO02.setGroupId(req.getGroupid());
            //学生id
            groupMemberPO02.setStudentTeacherId(req.getMember01id());
            j = groupMemberMapper.insert(groupMemberPO02);
        }
        if (req.getMember02id() != 0) {
            groupMemberPO03.setGroupId(req.getGroupid());
            //学生id
            groupMemberPO03.setStudentTeacherId(req.getMember02id());
            j = groupMemberMapper.insert(groupMemberPO03);
        }
        if (req.getMember03id() != 0) {
            groupMemberPO04.setGroupId(req.getGroupid());
            //学生id
            groupMemberPO04.setStudentTeacherId(req.getMember03id());
            j = groupMemberMapper.insert(groupMemberPO04);
        }
        if (i > 0 && j > 0) {
            return true;
        } else
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

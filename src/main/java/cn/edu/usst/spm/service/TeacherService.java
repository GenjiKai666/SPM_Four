package cn.edu.usst.spm.service;


import cn.edu.usst.spm.req.TeacherLoginReq;
import cn.edu.usst.spm.req.TeacherSaveReq;
import cn.edu.usst.spm.resp.TeacherLoginResp;

public interface TeacherService {

    public boolean register(TeacherSaveReq req);

    TeacherLoginResp login(TeacherLoginReq req);
}

package cn.edu.usst.spm.service;


import cn.edu.usst.spm.req.StudentLoginReq;
import cn.edu.usst.spm.req.StudentSaveReq;
import cn.edu.usst.spm.req.TeamReq;
import cn.edu.usst.spm.resp.StudentLoginResp;

public interface StudentService {
    public boolean register(StudentSaveReq req);

    StudentLoginResp login(StudentLoginReq req);

    boolean saveteam(TeamReq req);

}

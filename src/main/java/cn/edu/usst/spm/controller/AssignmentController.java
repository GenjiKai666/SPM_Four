package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.po.AssignmentPO;
import cn.edu.usst.spm.bean.vo.AssignmentVO;
import cn.edu.usst.spm.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    /**
     * 处理发布新题目的请求
     *
     * @param question 题干
     * @param date     截止时间
     * @return 成功返回1，失败返回0
     */
    @RequestMapping(value = "/assignment/insert", method = RequestMethod.POST)
    public int insertAssignment(@NotNull @RequestParam("question") String question,
                                @NotNull @RequestParam("date") long date,
                                @NotNull @RequestParam("bygroup") boolean byGroup) {
        return assignmentService.insertAssignment(new AssignmentPO(null, question, new Date(date),byGroup));
    }

    /**
     * 处理搜索题目的请求
     *
     * @param id 题目ID
     * @return 返回类
     */
    @RequestMapping(value = "/assignment/select", method = RequestMethod.GET)
    public AssignmentVO selectAssignmentById(@NotNull @RequestParam("id") Integer id) {
        AssignmentPO assignmentPO = assignmentService.selectAssignmentById(id);
        if (assignmentPO == null) {
            return new AssignmentVO(null, null,null);
        }
        return new AssignmentVO(assignmentPO.getQuestion(), assignmentPO.getDeadline(),assignmentPO.getByGroup());
    }

    /**
     * 处理删除某次作业的请求
     *
     * @param id 题目ID
     * @return 返回类
     */
    @RequestMapping(value = "/assignment/delete", method = RequestMethod.POST)
    public int deleteAssignmentById(@NotNull @RequestParam("id") Integer id) {
        return assignmentService.deleteAssignmentById(id);
    }
}

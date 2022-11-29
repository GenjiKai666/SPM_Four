package cn.edu.usst.spm.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 作业得分关系表对应的类，和数据库的作业回答打分的表相对应。
 * 可以使用 {@link cn.edu.usst.spm.mapper.StudentTeacherAssignmentMapper} 实现数据库操作作业回答和打分。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("STUDENT_TEACHER_ASSIGNMENT")
public class StudentTeacherAssignmentPO {
    @TableId(type = IdType.AUTO)
    private Integer id;                 // 逻辑主键，自增
    @TableField
    private Integer AssignmentId;       // 作业题目，外键
    @TableField
    private Integer StudentTeacherId;   // 选课的关系表的外键
    @TableField
    private String answer;              // 学生的回答
    @TableField
    private Double score;               // 老师的打分
}

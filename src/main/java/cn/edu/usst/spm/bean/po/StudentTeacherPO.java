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
 * 学生和老师选课的关系表对应的实体类。
 * 可以使用 {@link cn.edu.usst.spm.mapper.StudentTeacherMapper} 实现数据库操表。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("STUDENT_TEACHER")
public class StudentTeacherPO {
    @TableId(type = IdType.AUTO)
    private Integer id;         // 逻辑主键，自增
    @TableField
    private Integer studentId;  //学生ID，外键
    @TableField
    private Integer teacherId;  //老师ID，外键
    @TableField
    private Boolean isConfirmed;  // 选课关系的确认，如果没有确认，则为false,老师确认后，则为ture
}

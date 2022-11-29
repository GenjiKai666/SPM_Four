package cn.edu.usst.spm.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 作业的实体类，和数据库的作业表相对应。
 * 可以使用 {@link cn.edu.usst.spm.mapper.AssignmentMapper} 实现数据库操作作业实体。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("ASSIGNMENT")
public class AssignmentPO {
    @TableId(type = IdType.AUTO)
    private Integer id;          //作业编号,自增
    @TableField
    private String question;    //题干
    @TableField
    private Date deadLine;      //结束时间
}

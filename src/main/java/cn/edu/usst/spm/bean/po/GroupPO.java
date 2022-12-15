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
 * id：逻辑主键：自动生成，无意义
 * name：小组名：长度不要超过25
 * studentTeacherId：小组组长：不可重复,是StudentTeacher表的外键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
/**
 * group是mysql关键字，插入和查询的时候会报语法错误，改为非关键字01或其他都可以运行成功
 */
@TableName("GROUP01")
public class GroupPO {
    @TableId(type = IdType.AUTO)
    private Integer id;                 // 逻辑主键，id
    @TableField
    private String name;                // 小组名字，不可重复，长度不超过25
    @TableField("LEADER_ID")
    private Integer studentTeacherId;   // 小组组长，不可重复
}

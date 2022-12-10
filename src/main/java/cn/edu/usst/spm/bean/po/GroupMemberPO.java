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
 * <p>id：逻辑主键：自动生成，无意义</p>
 * <p>groupId：小组id：外键</p>
 * <p>
 * studentTacherId：小组成员：不可重复，成员可以包括也可以不包括组长。考虑一个人不能在两个组，
 * 可以把组长包括在成员表中，利用sql的约束条件，去防止一个人既是A的组长，又是B的成员
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("GROUP_MEMBER")
public class GroupMemberPO {
    //自增，插入时不需要赋值
    @TableId(type = IdType.AUTO)
    private Integer id;                 // 逻辑主键，id
    @TableField
    private Integer groupId;            // 小组的id
    @TableField
    private Integer studentTeacherId;   // 小组组员的学生老师选课关系的id，不可重复
}

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
 * 老师的实体类，和数据库的老师表相对应。
 * 可以使用 {@link cn.edu.usst.spm.mapper.TeacherMapper} 实现数据库操作老师表。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("TEACHER")
public class TeacherPO {
    @TableId(type = IdType.AUTO)
    Integer id;   //老师工号 自增
    @TableField
    String username;    //老师姓名，并且用此来登陆
    @TableField
    String password;    //登陆密码
}

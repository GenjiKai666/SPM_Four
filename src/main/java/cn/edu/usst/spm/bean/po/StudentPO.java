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
 * 学生的实体类，和数据库的学生表相对应。
 * 可以使用 {@link cn.edu.usst.spm.mapper.StudentMapper} 实现数据库操作学生表。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("STUDENT")
public class StudentPO {
    @TableId(type = IdType.AUTO)
    Integer id;         //学号,自增
    @TableField
    String className;   //班级名
    @TableField
    String username;    //学生真实姓名以及登陆用户名
    @TableField
    String password;    //登陆密码
    @TableField
    String email;       //电子邮箱
    @TableField
    String phone;       //电话号码
}

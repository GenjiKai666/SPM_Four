package cn.edu.usst.spm.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @TableId(value="ID" ,type = IdType.AUTO)
    Integer id;         //学号,自增
    @TableField("CLASS_NAME")
    String className;   //班级名
    @TableField("USERNAME")
    String username;    //学生真实姓名以及登陆用户名
    @JsonIgnore
    @TableField("PASSWORD")
    String password;    //登陆密码
    @TableField("EMAIL")
    String email;       //电子邮箱
    @TableField("PHONE")
    String phone;       //电话号码
}

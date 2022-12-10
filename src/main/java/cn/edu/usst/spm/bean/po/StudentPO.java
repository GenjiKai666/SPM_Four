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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

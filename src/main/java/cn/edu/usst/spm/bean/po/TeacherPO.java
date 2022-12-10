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

import java.util.Date;

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

    @TableId(value="ID",type = IdType.AUTO)
    private Integer id;

    @TableField("USERNAME")
    private String userName;

    //忽略password，不将其进行展示
    @JsonIgnore
    @TableField("PASSWORD")
    private String password;

    @TableField("COURSE_TIME")
    private String courseTime;

    @TableField("COURSE_LOCATION")
    private String courseLocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }
}

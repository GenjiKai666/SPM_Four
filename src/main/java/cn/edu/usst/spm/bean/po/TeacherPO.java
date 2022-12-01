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
    private Integer id;   //老师工号 自增
    @TableField
    private String username;    //老师姓名，并且用此来登陆
    @TableField
    private String password;    //登陆密码
    @TableField
    private Date courseTime;  // 上课开始时间
    @TableField
    private String courseLocation;  // 上课地点，长度不要超过15
}

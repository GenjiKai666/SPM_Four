package cn.edu.usst.spm.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentVO {
    Integer id;
    String username;    //学生真实姓名以及登陆用户名
    String className;   //班级名
    String email;       //电子邮箱
    String phone;       //电话号码
}

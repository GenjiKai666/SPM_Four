package cn.edu.usst.spm.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "score")
public class Score {
    @TableId(type = IdType.AUTO)
    private Integer id;     // 逻辑主键，自增
//    @TableField
//    private int STUDENT_TEACHER_ID;   //学生和老师关系的表外键id
//    @TableField
//    private double USUAL_GRADE;      //平时成绩
//    @TableField
//    private double MID_EXAM_GRADE;    //期中成绩
//    @TableField
//    private double FINAL_EXAM_GRADE;  //期末成绩
//    @TableField
//    private double EXPERIMENT_GRADE; //实验成绩
//    @TableField
//    private double FINAL_SCORE;      //最终成绩
    @TableField(value ="STUDENT_TEACHER_ID")
    private int studentTeacherId;   //学生和老师关系的表外键id
    @TableField(value ="USUAL_GRADE")
    private double usualGrade;      //平时成绩
    @TableField(value ="MID_EXAM_GRADE")
    private double midExamGrade;    //期中成绩
    @TableField(value ="FINAL_EXAM_GRADE")
    private double finalExamGrade;  //期末成绩
    @TableField(value ="EXPERIMENT_GRADE")
    private double experimentGrade; //实验成绩
    @TableField(value ="FINAL_SCORE")
    private double finalscore;
    @TableField(exist = false)
    private String student;         //学生姓名
//    @TableId(value = "id", type = IdType.ASSIGN_ID)//规范用的，如果你下面的命名不是id可以使用该注解强制将他与ID绑定
//    private Integer ID;
//    private String username;
//    @JsonIgnore
//    private String password;
//    @TableField(value = "asd") 可以指定下面的属性变成数据库asd断，尽管命名和asd不一样
//    private Date time;

}

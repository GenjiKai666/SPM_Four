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


}

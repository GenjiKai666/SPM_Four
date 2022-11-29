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
 * 成绩的实体类，和数据库的成绩表相对应。
 * 可以使用 {@link cn.edu.usst.spm.mapper.ScoreMapper} 实现数据库操作成绩表。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("SCORE")
public class ScorePO {
    @TableId(type = IdType.AUTO)
    private Integer id;     // 逻辑主键，自增
    @TableField
    private int studentTeacherId;   //学生和老师关系的表外键id
    @TableField
    private double usualGrade;      //平时成绩
    @TableField
    private double midExamGrade;    //期中成绩
    @TableField
    private double finalExamGrade;  //期末成绩
    @TableField
    private double experimentGrade; //实验成绩
}

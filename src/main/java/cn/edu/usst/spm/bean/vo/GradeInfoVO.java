package cn.edu.usst.spm.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GradeInfoVO {
    private Integer id;
    private String name;
    private String className;
    private Double usualGrade;
    private Double midExamGrade;
    private Double finalExamGrade;
    private Double experimentGrade;
    private Double allGrade;
}

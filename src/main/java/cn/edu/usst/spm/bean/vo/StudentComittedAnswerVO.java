package cn.edu.usst.spm.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudentComittedAnswerVO {
    Integer id;     //答卷ID
    String question;//题干
    Integer assignmentID;
    String commit_student; //答题用户
    Integer student_teacher_id;
    String answer;
    Double score;//分数
}

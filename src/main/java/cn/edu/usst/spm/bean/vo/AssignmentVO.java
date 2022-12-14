package cn.edu.usst.spm.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AssignmentVO {
    Integer id;
    String question;
    String deadline;
    Long deadline_time;
    Double score;
}

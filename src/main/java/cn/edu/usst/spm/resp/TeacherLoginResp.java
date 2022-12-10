package cn.edu.usst.spm.resp;

import java.util.Date;

public class TeacherLoginResp {

    private String username;


    private String password;


    private Date course_time;  // 上课开始时间


    private String course_tocation;  // 上课地点，长度不要超过15

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Date getCourse_time() {
        return course_time;
    }

    public void setCourse_time(Date course_time) {
        this.course_time = course_time;
    }

    public String getCourse_tocation() {
        return course_tocation;
    }

    public void setCourse_tocation(String course_tocation) {
        this.course_tocation = course_tocation;
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
}

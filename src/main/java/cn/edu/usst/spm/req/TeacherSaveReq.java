package cn.edu.usst.spm.req;

import java.util.Date;

public class TeacherSaveReq {

    private Integer id;
    private String username;
    private String password;


    private Date courseTime;  // 上课开始时间


    private String courseLocation;  // 上课地点，长度不要超过15


    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
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

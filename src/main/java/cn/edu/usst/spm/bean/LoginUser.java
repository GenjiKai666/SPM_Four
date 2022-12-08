package cn.edu.usst.spm.bean;

public interface LoginUser {

    /**
     * @return 返回用户在数据库的id
     */
    Integer getId();

    /**
     * 是老师，返回ture,不是老师返回false
     * @return 是否老师
     */
    boolean isTeacher();

}

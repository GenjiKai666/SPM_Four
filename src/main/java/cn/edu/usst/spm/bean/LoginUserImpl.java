package cn.edu.usst.spm.bean;

public class LoginUserImpl implements LoginUser {
    private final int id;
    private final boolean isTeacher;

    public LoginUserImpl(int id, boolean isTeacher) {
        this.id = id;
        this.isTeacher = isTeacher;
    }

    /**
     * @return 返回用户在数据库的id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * 是老师，返回ture,不是老师返回false
     *
     * @return 是否老师
     */
    @Override
    public boolean isTeacher() {
        return isTeacher;
    }
}

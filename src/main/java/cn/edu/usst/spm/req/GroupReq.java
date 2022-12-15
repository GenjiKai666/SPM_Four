package cn.edu.usst.spm.req;

public class GroupReq {
    String name;
    Integer headmanid;
    Integer member01id;
    Integer member02id;
    Integer member03id;
    Integer groupid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadmanid() {
        return headmanid;
    }

    public void setHeadmanid(int headmanid) {
        this.headmanid = headmanid;
    }

    public int getMember01id() {
        return member01id;
    }

    public void setMember01id(int member01id) {
        this.member01id = member01id;
    }

    public int getMember02id() {
        return member02id;
    }

    public void setMember02id(int member02id) {
        this.member02id = member02id;
    }

    public int getMember03id() {
        return member03id;
    }

    public void setMember03id(int member03id) {
        this.member03id = member03id;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}

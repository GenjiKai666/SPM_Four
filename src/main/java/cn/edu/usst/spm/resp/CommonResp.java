package cn.edu.usst.spm.resp;

public class CommonResp <T>{
    private boolean success = true;

    @Override
    public String toString() {
        return "CommonResp{" +
                "success=" + success +
                ", massage='" + massage + '\'' +
                ", content=" + content +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    private String massage;

    private T content;


}

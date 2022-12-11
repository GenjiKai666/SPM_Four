package cn.edu.usst.spm.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一接口返回包装类
 * */
@Data
@NoArgsConstructor  //无参构造注解
@AllArgsConstructor  //有参构造
public class Result {
    private String code;  //返回的状态码
    private String msg;   //信息
    private Object data;  //发送的数据

    public static  Result success(){
        return new Result("200","",null);
    }
    public static  Result success(Object data){
        return new Result("200","",data);
    }
    public static  Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    public static  Result error(){
        return new Result("500","代码错误",null);
    }

}

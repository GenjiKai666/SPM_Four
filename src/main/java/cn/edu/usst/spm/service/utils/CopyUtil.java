package cn.edu.usst.spm.service.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    //单体复制
    public static <T> T copy(Object source,Class<T> clazz){
        if(source==null)
            return null ;
        T obj = null;
        try{
            obj = clazz.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source,obj);
        return obj;
    }
    public static <T> List<T> copyList(List souce,Class<T> clazz){
        List <T> target=new  ArrayList<>();
        if(!CollectionUtils.isEmpty(souce)){
            for (Object c :souce) {
                T obj = copy(c,clazz);
                target.add(obj);
            }
        }
        return target;
    }
}

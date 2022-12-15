package cn.edu.usst.spm.controller;

import cn.edu.usst.spm.bean.LoginUser;
import cn.edu.usst.spm.bean.po.Score;
import cn.edu.usst.spm.common.Result;
import cn.edu.usst.spm.service.imp.ScoreServise;
import cn.edu.usst.spm.util.Constant;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Echarts")
public class Echartscontroller {

    @Autowired
    ScoreServise scoreServise;

    @GetMapping("/score")                     //当有多个方法返回多种数据时，前端就靠定义的路径进行请求区分
    public Result get(HttpSession session){

        List<Score> scoreList = scoreServise.list();
        double score = 0;
        int fail = 0;                //不及格
        int pass = 0;                //60-70
        int good = 0;                //70-80
        int excellent = 0;           //80-90
        int best = 0;                //90-100
        int sum = 0,passnum =0;
        for (Score user : scoreList) {
            score = user.getFinalscore();
            if (score<60) {
                fail+=1;
            }else if(score >= 60 && score < 70){
                pass+=1;
            }else if(score >= 70 && score < 80){
                good+=1;
            }else if(score >= 80 && score < 90){
                excellent+=1;
            }else if(score >= 90 && score < 100){
                best+=1;
            }
        }
        sum = scoreList.size();
        passnum = sum-fail;
        return Result.success(CollUtil.newArrayList(fail,pass,good,excellent,best,passnum,sum));
    }
    @GetMapping("/All")
    public Result getAll(){
        double fail,pass,excellent;
        Integer all;
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Score> ScoreQueryWrapper= new QueryWrapper<>();
        ScoreQueryWrapper.select("count(*)");
        scoreServise.getMap(ScoreQueryWrapper);
        ScoreQueryWrapper.lt("FINAL_SCORE",60);
        ScoreQueryWrapper.ge("FINAL_SCORE",60);
        ScoreQueryWrapper.ge("FINAL_SCORE",90);
//       map = scoreServise.getMap(ScoreQueryWrapper);
        return  Result.success(map);
    }
}

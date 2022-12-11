package cn.edu.usst.spm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springvue.entity.Score;
import com.example.springvue.service.ScoreServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class Scorecontroller {
//    @Resource
//    private UserMapper userMapper;


    //mybatis-puls需要的service类,通过调用其继承的父类里的方法进行增删查改
    @Autowired
    private ScoreServise scoreServise;


    //mybatis-puls新增或者修改
    @PostMapping
    public boolean save(@RequestBody Score score){
        return scoreServise.saveUser(score);
    }


    //mybatis-puls查询所有数据
    @GetMapping("/all")
    public List<Score> findAll() {
        return scoreServise.list();
    }


    //mybatis-puls删除数据
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return scoreServise.removeById(id);
    }


    //mybatis-puls批量删除数据
    @PostMapping("/del/batch")
    public boolean delete(@RequestBody List<Integer> ids){
        return scoreServise.removeBatchByIds(ids);
    }



    @GetMapping("/page")
    public IPage<Score> findPag(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name
//                               @RequestParam(defaultValue = "")String addtress
    ){
//        IPage<Score> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<Score> queryWrap = new QueryWrapper<>();
//        if(!"".equals(STUDENT_TEACHER_ID)) {
//            queryWrap.like("STUDENT_TEACHER_ID", STUDENT_TEACHER_ID);
//        }
//        queryWrap.orderByDesc("ID"); //倒叙显示
        Page<Score> page = scoreServise.findPage(new Page<>(pageNum, pageSize),name);
        return page;

    }
//    //mybatis查询
//    @GetMapping("/all")
//    public List<User> getUser(){
//       List<User> all = userMapper.findAll();
//        return all;
//    }
    //mybatis-puls分页查询


//    //mybatis分页查询
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username){
//           pageNum = (pageNum - 1) * pageSize;
//           List<User> data = userMapper.selectPage(pageNum, pageSize,username);
//           Integer total = userMapper.selectTotal(username);
//           Map<String, Object> res = new HashMap<>();
//           res.put("data",data);
//           res.put("total",total);
//           return res;
//
//    }
}
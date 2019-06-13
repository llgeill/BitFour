package cn.stylefeng.guns.modular.spark.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.spark.model.Enroll;
import cn.stylefeng.guns.modular.spark.service.IEnrollService;

/**
 * 用户报名控制器
 *
 * @author fengshuonan
 * @Date 2018-12-23 10:46:35
 */
@Controller
@RequestMapping("/spark/enroll")
public class EnrollController extends BaseController {

    private String PREFIX = "/spark/enroll/";

    @Autowired
    private IEnrollService enrollService;

    /**
     * 跳转到用户报名首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "enroll.html";
    }

    /**
     * 跳转到添加用户报名
     */
    @RequestMapping("/enroll_add")
    public String enrollAdd() {
        return PREFIX + "enroll_add.html";
    }

    /**
     * 跳转到修改用户报名
     */
    @RequestMapping("/enroll_update/{enrollId}")
    public String enrollUpdate(@PathVariable Integer enrollId, Model model) {
        Enroll enroll = enrollService.selectById(enrollId);
        model.addAttribute("item",enroll);
        LogObjectHolder.me().set(enroll);
        return PREFIX + "enroll_edit.html";
    }

    /**
     * 获取用户报名列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return enrollService.selectList(null);
    }

    /**
     * 新增用户报名
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Enroll enroll) {
        enrollService.insert(enroll);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户报名
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer enrollId) {
        enrollService.deleteById(enrollId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户报名
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Enroll enroll) {
        enrollService.updateById(enroll);
        return SUCCESS_TIP;
    }

    /**
     * 用户报名详情
     */
    @RequestMapping(value = "/detail/{enrollId}")
    @ResponseBody
    public Object detail(@PathVariable("enrollId") Integer enrollId) {
        return enrollService.selectById(enrollId);
    }
}

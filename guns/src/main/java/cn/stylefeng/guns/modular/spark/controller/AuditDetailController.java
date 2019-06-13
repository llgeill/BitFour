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
import cn.stylefeng.guns.modular.spark.model.AuditDetail;
import cn.stylefeng.guns.modular.spark.service.IAuditDetailService;

/**
 * 兼职审核细节控制器
 *
 * @author fengshuonan
 * @Date 2018-12-18 19:09:56
 */
@Controller
@RequestMapping("/spark/auditDetail")
public class AuditDetailController extends BaseController {

    private String PREFIX = "/spark/auditDetail/";

    @Autowired
    private IAuditDetailService auditDetailService;

    /**
     * 跳转到兼职审核细节首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "auditDetail.html";
    }

    /**
     * 跳转到添加兼职审核细节
     */
    @RequestMapping("/auditDetail_add")
    public String auditDetailAdd() {
        return PREFIX + "auditDetail_add.html";
    }

    /**
     * 跳转到修改兼职审核细节
     */
    @RequestMapping("/auditDetail_update/{auditDetailId}")
    public String auditDetailUpdate(@PathVariable Integer auditDetailId, Model model) {
        AuditDetail auditDetail = auditDetailService.selectById(auditDetailId);
        model.addAttribute("item",auditDetail);
        LogObjectHolder.me().set(auditDetail);
        return PREFIX + "auditDetail_edit.html";
    }

    /**
     * 获取兼职审核细节列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return auditDetailService.selectList(null);
    }

    /**
     * 新增兼职审核细节
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AuditDetail auditDetail) {
        auditDetailService.insert(auditDetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除兼职审核细节
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer auditDetailId) {
        auditDetailService.deleteById(auditDetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改兼职审核细节
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AuditDetail auditDetail) {
        auditDetailService.updateById(auditDetail);
        return SUCCESS_TIP;
    }

    /**
     * 兼职审核细节详情
     */
    @RequestMapping(value = "/detail/{auditDetailId}")
    @ResponseBody
    public Object detail(@PathVariable("auditDetailId") Integer auditDetailId) {
        return auditDetailService.selectById(auditDetailId);
    }
}

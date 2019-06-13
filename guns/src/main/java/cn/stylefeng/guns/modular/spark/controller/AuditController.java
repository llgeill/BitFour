package cn.stylefeng.guns.modular.spark.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.modular.spark.model.AuditDetail;
import cn.stylefeng.guns.modular.spark.model.PartTime;
import cn.stylefeng.guns.modular.spark.model.ReviewJob;
import cn.stylefeng.guns.modular.spark.service.IAuditDetailService;
import cn.stylefeng.guns.modular.spark.service.IPartTimeService;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.spark.model.Audit;
import cn.stylefeng.guns.modular.spark.service.IAuditService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 兼职审核控制器
 *
 * @author fengshuonan
 * @Date 2018-12-20 15:00:56
 */
@Controller
@RequestMapping("/spark/audit")
public class AuditController extends BaseController {

    private String PREFIX = "/spark/audit/";

    @Autowired
    private IAuditService auditService;
    @Autowired
    private IPartTimeService  partTimeService;
    @Autowired
    private IAuditDetailService auditDetailService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到兼职审核首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "audit.html";
    }

    /**
     * 跳转到添加兼职审核
     */
    @RequestMapping("/audit_add")
    public String auditAdd() {
        return PREFIX + "audit_add.html";
    }

    /**
     * 跳转到修改兼职审核
     */
    @RequestMapping("/audit_update/{auditId}")
    public String auditUpdate(@PathVariable Integer auditId, Model model) {
        Audit audit = auditService.selectById(auditId);
        model.addAttribute("item",audit);
        LogObjectHolder.me().set(audit);
        return PREFIX + "audit_edit.html";
    }

    /**
     * 获取兼职审核列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return auditService.selectList(null);
    }

    /**
     * 新增兼职审核
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Audit audit) {
        auditService.insert(audit);
        return SUCCESS_TIP;
    }

    /**
     * 删除兼职审核
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer auditId) {
        auditService.deleteById(auditId);
        return SUCCESS_TIP;
    }

    /**
     * 修改兼职审核
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Audit audit) {
        auditService.updateById(audit);
        return SUCCESS_TIP;
    }

    /**
     * 兼职审核详情
     */
    @RequestMapping(value = "/detail/{auditId}")
    @ResponseBody
    public Object detail(@PathVariable("auditId") Integer auditId) {
        return auditService.selectById(auditId);
    }



    @RequestMapping(value="/review_job")
    @Permission("school")
    @ResponseBody
    public Object findList(){
        List<ReviewJob> reviewJobs=new LinkedList<>();
        List<Audit> audits=  auditService.selectList(null);
        for(Audit audit:audits){
            PartTime partTime= partTimeService.selectById(audit.getPartTimeId());
            ReviewJob reviewJob=new ReviewJob();
            reviewJob.setAudit(audit);
            reviewJob.setPartTime(partTime);
            User user=userService.selectById(partTime.getPubishId());
            reviewJob.setUser(user);
            Map map=new HashMap();
            map.put("spark_audit_id",audit.getId());
            List<AuditDetail> list=auditDetailService.selectByMap(map);
            reviewJob.setAuditDetails(list);
            reviewJobs.add(reviewJob);
        }
        return reviewJobs;

    }

    @RequestMapping(value="/modify_status")
    @ResponseBody
    public String ModifyStatus(int index,String status,String content){
        Audit audit=auditService.selectList(null).get(index);
        AuditDetail detail=new AuditDetail();

        detail.setSparkAuditId(audit.getId());

        detail.setAuditorId(userService.selectById(partTimeService.selectById(audit.getPartTimeId()).getPubishId()).getId());
        auditDetailService.insert(detail);

        if(status.equals("未通过")){
            detail.setContent(content);
            audit.setStatus(4);
            auditService.updateById(audit);
            return "4";
        }else if (status.equals("已通过")){
            auditService.updateById(audit);
            audit.setStatus(3);
            auditService.updateById(audit);
            return "3";
        }

        return "0";

    }




}

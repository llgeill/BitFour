package cn.stylefeng.guns.modular.spark.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeptDict;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.MenuNode;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.ApiMenuFilter;
import cn.stylefeng.guns.modular.spark.model.Audit;
import cn.stylefeng.guns.modular.spark.service.IAuditService;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.spark.model.PartTime;
import cn.stylefeng.guns.modular.spark.service.IPartTimeService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 兼职详情数据操作控制器
 *
 * @author fengshuonan
 * @Date 2018-12-13 17:30:19
 */
@Controller
@RequestMapping("/spark/partTime")
public class PartTimeController extends BaseController {

    private String PREFIX = "/spark/partTime/";

    @Autowired
    private IPartTimeService partTimeService;

    @Autowired
    private IAuditService iAuditService;

    @RequestMapping("/partTimeDetail/{id}")
    public String getDetailsPartTimeId(Model model,@PathVariable String id) {
        Date date = null;
        PartTime partTime = partTimeService.selectById(id);
        model.addAttribute("partTime",partTime);//key-value

        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = partTime.getWorkStartTime();
        String wst = dFormat.format(date);
        model.addAttribute("workStartTime",wst);
        date = partTime.getWorkEndTime();
        String wet = dFormat.format(date);
        model.addAttribute("workEndTime",wet);
        date = partTime.getGmtCreate();
        String gc = dFormat.format(date);
        model.addAttribute("gmtCreate",gc);

        int wt = partTime.getWorkType();
        if(wt == 0){
            model.addAttribute("workType","短招");
        }else if(wt == 1){
            model.addAttribute("workType","长招");
        }

        int gr = partTime.getGenderRequirement();
        if(gr == 1){
            model.addAttribute("genderRequirement","男生优先");
        }else if(gr == 2){
            model.addAttribute("genderRequirement","女生优先");
        }else if(gr == 3){
            model.addAttribute("genderRequirement","男、女都招");
        }

        int sc = partTime.getSettlementCycle();
        if(sc == 1){
            model.addAttribute("settlementCycle","日结");
        }else if(sc == 2){
            model.addAttribute("settlementCycle","周结");
        }else if(sc == 3){
            model.addAttribute("settlementCycle","月结");
        }else if(sc == 4){
            model.addAttribute("settlementCycle","完工结算");
        }
        return "/spark/details_of_job.html";
    }


    /**
     * 跳转到兼职详情数据操作首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "partTime.html";
    }

    /**
     * 跳转到添加兼职详情数据操作
     */
    @RequestMapping("/partTime_add")
    public String partTimeAdd() {
        return PREFIX + "partTime_add.html";
    }

    /**
     * 跳转到修改兼职详情数据操作
     */
    @RequestMapping("/partTime_update/{partTimeId}")
    public String partTimeUpdate(@PathVariable Integer partTimeId, Model model) {
        PartTime partTime = partTimeService.selectById(partTimeId);
        model.addAttribute("item",partTime);
        LogObjectHolder.me().set(partTime);
        return PREFIX + "partTime_edit.html";
    }

    /**
     * 获取（和查找）兼职详情数据操作列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isNotEmpty(condition)){
            EntityWrapper<PartTime> partTimeDetailEntityWrapper = new EntityWrapper<>();
            Wrapper<PartTime> type = partTimeDetailEntityWrapper.like("part_time_type","%"+condition+"%");
            List<PartTime> partTime = partTimeService.selectList(type);


            return partTime;
        }else {
            List<PartTime> partTimeDetails = partTimeService.selectList(null);
            return partTimeDetails;
        }
    }

    /**
     * 分页获取（和查找）兼职详情数据操作列表
     */
    @RequestMapping(value = "/page")
    @ResponseBody
    public Object page() {
        Page<PartTime> page = new PageFactory<PartTime>().defaultPage();
        return page.setRecords(partTimeService.getAuditPartTime(page));
    }

    /**
     * 新增兼职详情数据操作
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PartTime partTime) {
        if(partTime != null){
            partTime.setGmtCreate(new Date());
        }
        partTimeService.insert(partTime);
        return SUCCESS_TIP;
    }

    /**
     * 新增兼职详情数据操作
     */
    @RequestMapping(value = "/publish")
    @Transactional
    @ResponseBody
    public String publish(PartTime partTime,String[] workWelfares) {
        //填充剩余数据
        StringBuffer workWelf=new StringBuffer();
        for(int i=0;i<workWelfares.length;i++){
            workWelf.append(workWelfares[i]);
            if(i<workWelfares.length-1) workWelf.append(",");
        }
        partTime.setWorkWelfare(workWelf.toString());
        partTime.setGmtCreate(new Date());
        partTime.setGmtModified(new Date());
        partTime.setPubishId(ShiroKit.getUser().id);
        partTimeService.insert(partTime);
        Audit audit=new Audit();
        audit.setStatus(1);
        audit.setPartTimeId(partTime.getId());
        audit.setGmtCreate(new Date());
        audit.setGmtModified(new Date());
        iAuditService.insert(audit);
        return "/spark/index.html";
    }

    /**
     * 删除兼职详情数据操作
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer partTimeId) {
        partTimeService.deleteById(partTimeId.longValue());
        return SUCCESS_TIP;
    }

    /**
     * 修改兼职详情数据操作
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PartTime partTime) {
        partTimeService.updateById(partTime);
        return SUCCESS_TIP;
    }

    /**
     * 兼职详情数据操作详情
     */
    @RequestMapping(value = "/detail/{partTimeId}")
    @ResponseBody
    public Object detail(@PathVariable("partTimeId") Integer partTimeId) {
        return partTimeService.selectById(partTimeId);
    }
}

package cn.stylefeng.guns.modular.spark.controller;

import cn.stylefeng.guns.modular.spark.model.PartTime;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.spark.model.Complaint;
import cn.stylefeng.guns.modular.spark.service.IComplaintService;

import java.util.Date;
import java.util.List;

/**
 * 申诉控制器
 *
 * @author fengshuonan
 * @Date 2018-12-23 00:55:28
 */
@Controller
@RequestMapping("/spark/complaint")
public class ComplaintController extends BaseController {

    private String PREFIX = "/spark/complaint/";

    @Autowired
    private IComplaintService complaintService;

    /**
     * 跳转到申诉首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "complaint.html";
    }

    /**
     * 跳转到添加申诉
     */
    @RequestMapping("/complaint_add")
    public String complaintAdd() {
        return PREFIX + "complaint_add.html";
    }

    /**
     * 跳转到修改申诉
     */
    @RequestMapping("/complaint_update/{complaintId}")
    public String complaintUpdate(@PathVariable Integer complaintId, Model model) {
        Complaint complaint = complaintService.selectById(complaintId);
        model.addAttribute("item",complaint);
        LogObjectHolder.me().set(complaint);
        return PREFIX + "complaint_edit.html";
    }

    /**
     * 查看(获取)申诉列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isNotEmpty(condition)){
            EntityWrapper<Complaint> complaintEntityWrapper = new EntityWrapper<>();
            Wrapper<Complaint> type = complaintEntityWrapper.like("status","%"+condition+"%");
            List<Complaint> complaints = complaintService.selectList(type);
            return complaints;
        }else {
            List<Complaint> complaints = complaintService.selectList(null);
            return complaints;
        }
//        return complaintService.selectList(null);
    }

    /**
     * 新增申诉
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Complaint complaint) {
        if(complaint != null){
            complaint.setGmtCreate(new Date());
        }
        complaintService.insert(complaint);
        return SUCCESS_TIP;
    }

    /**
     * 删除申诉
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer complaintId) {
        complaintService.deleteById(complaintId.longValue());
        return SUCCESS_TIP;
    }

    /**
     * 修改申诉
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Complaint complaint) {
        complaintService.updateById(complaint);
        return SUCCESS_TIP;
    }

    /**
     * 申诉详情
     */
    @RequestMapping(value = "/detail/{complaintId}")
    @ResponseBody
    public Object detail(@PathVariable("complaintId") Integer complaintId) {
        return complaintService.selectById(complaintId);
    }
}

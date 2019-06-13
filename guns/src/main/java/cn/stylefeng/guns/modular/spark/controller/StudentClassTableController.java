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
import cn.stylefeng.guns.modular.spark.model.StudentClassTable;
import cn.stylefeng.guns.modular.spark.service.IStudentClassTableService;

/**
 * 课表控制器
 *
 * @author fengshuonan
 * @Date 2018-12-21 23:42:02
 */
@Controller
@RequestMapping("/spark/studentClassTable")
public class StudentClassTableController extends BaseController {

    private String PREFIX = "/spark/studentClassTable/";

    @Autowired
    private IStudentClassTableService studentClassTableService;

    /**
     * 跳转到课表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "studentClassTable.html";
    }

    /**
     * 跳转到添加课表
     */
    @RequestMapping("/studentClassTable_add")
    public String studentClassTableAdd() {
        return PREFIX + "studentClassTable_add.html";
    }

    /**
     * 跳转到修改课表
     */
    @RequestMapping("/studentClassTable_update/{studentClassTableId}")
    public String studentClassTableUpdate(@PathVariable Integer studentClassTableId, Model model) {
        StudentClassTable studentClassTable = studentClassTableService.selectById(studentClassTableId);
        model.addAttribute("item",studentClassTable);
        LogObjectHolder.me().set(studentClassTable);
        return PREFIX + "studentClassTable_edit.html";
    }

    /**
     * 获取课表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return studentClassTableService.selectList(null);
    }

    /**
     * 新增课表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(StudentClassTable studentClassTable) {
        studentClassTableService.insert(studentClassTable);
        return SUCCESS_TIP;
    }

    /**
     * 删除课表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer studentClassTableId) {
        studentClassTableService.deleteById(studentClassTableId);
        return SUCCESS_TIP;
    }

    /**
     * 修改课表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(StudentClassTable studentClassTable) {
        studentClassTableService.updateById(studentClassTable);
        return SUCCESS_TIP;
    }

    /**
     * 课表详情
     */
    @RequestMapping(value = "/detail/{studentClassTableId}")
    @ResponseBody
    public Object detail(@PathVariable("studentClassTableId") Integer studentClassTableId) {
        return studentClassTableService.selectById(studentClassTableId);
    }
}

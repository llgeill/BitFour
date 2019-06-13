/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.spark.controller;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.aop.PermissionAop;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import cn.stylefeng.guns.core.common.constant.dictmap.UserDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.controller.KaptchaController;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.model.Role;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.guns.modular.system.transfer.UserDto;
import cn.stylefeng.guns.modular.system.warpper.UserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;

/**
 * 系统管理员控制器
 *
 * @author 李利光
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/spark/mgr")
public class SparkUserMgrController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 添加平台用户
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
//    @ApiOperation(value = "注册兼职平台新用户", httpMethod = "POST", notes = "注册兼职平台新用户", response = ResponseData.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "form",name = "sparkRole",value = "平台用户角色",dataType = "Integer" ),
//    })
    public String add(@Valid @ModelAttribute UserDto user, BindingResult result) {

        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //赋予平台角色
        if(user.getSparkRole()==1){
            user.setRoleid(Integer.toString(8));
        }else if(user.getSparkRole()==2){
            user.setRoleid(Integer.toString(9));
        }else if(user.getSparkRole()==3){
            user.setRoleid(Integer.toString(10));
        }
        //保存登陆所需的用户名和密码
        String username=user.getAccount();
        String password=user.getPassword();
        // 完善账号信息
        user.setName(username);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatetime(new Date());
        this.userService.insert(UserFactory.createUser(user));
        //完成登陆操作
        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        currentUser.login(token);
        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
        ShiroKit.getSession().setAttribute("sessionFlag", true);

        return REDIRECT+"/spark/index";
    }


    @RequestMapping("/repeatAccount")
    @ResponseBody
    public boolean repeatAccount(String username){
        // 判断账号是否重复（account）
        User theUser = userService.getByAccount(username);
        if (theUser != null) {
//            throw new ServiceException(BizExceptionEnum.USER_ALREADY_REG);
            return true;
        }
        return false;
    }

    /**
     * 跳转到查看用户详情页面
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return  "spark/user_view.html";
    }

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public ResponseData edit(@Valid UserDto user, BindingResult result) throws NoPermissionException {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        User oldUser = userService.selectById(user.getId());
        ShiroUser shiroUser = ShiroKit.getUser();
        if (shiroUser.getId().equals(user.getId())) {
                this.userService.updateById(UserFactory.editUser(user, oldUser));
                if(user.getName()!=null)shiroUser.setName(user.getName());
                return SUCCESS_TIP;
        } else {
                throw new ServiceException(BizExceptionEnum.NO_PERMITION);
        }

    }



    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return "spark/user_chpwd.html";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new ServiceException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }else{
            Integer userId = ShiroKit.getUser().getId();
            User user = userService.selectById(userId);
            String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
            if (!user.getPassword().equals(oldMd5)) {
                throw new ServiceException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
            } else {
                String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
                user.setPassword(newMd5);
                user.updateById();
                return SUCCESS_TIP;
            }
        }

    }




}

package cn.stylefeng.guns.modular.spark.service.impl;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.spark.model.StudentClassTable;
import cn.stylefeng.guns.modular.spark.model.StudentUserDetail;
import cn.stylefeng.guns.modular.spark.dao.StudentUserDetailMapper;
import cn.stylefeng.guns.modular.spark.service.IStudentClassTableService;
import cn.stylefeng.guns.modular.spark.service.IStudentUserDetailService;
import cn.stylefeng.guns.modular.spark.util.StudentAuthentication;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.CalendarData;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2018-12-21
 */
@Service
public class StudentUserDetailServiceImpl extends ServiceImpl<StudentUserDetailMapper, StudentUserDetail> implements IStudentUserDetailService {



    @Autowired
    IStudentClassTableService iStudentClassTableService;

    @Autowired
    private GunsProperties gunsProperties;

    @Override
    @Transactional
    public StudentUserDetail createByAccount(String studentID,String studentPassword,String code,StudentAuthentication studentAuthentication) {
        try {
            String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix("jpg");
            studentAuthentication.save_head_img_url=gunsProperties.getFileUploadPath()+pictureName;
            studentAuthentication.studentID=studentID;
            studentAuthentication.studentPassword=studentPassword;
            studentAuthentication.check=code;
            studentAuthentication.load();
            studentAuthentication.getNameByUrl();
            studentAuthentication.getKB();
            studentAuthentication.getPersonInfo();
            StudentClassTable studentClassTable=new StudentClassTable();
            String [][] classTableArray=studentAuthentication.classTable;
            for(int i=1;i<classTableArray.length;i++){
                StringBuffer temp=new StringBuffer();
                for(int j=1;j<classTableArray[i].length;j++){
                    temp.append(classTableArray[i][j]);
                    if(j!=classTableArray[i].length-1)temp.append("#");
                }
                studentClassTable.setData(i,temp.toString());

            }
            iStudentClassTableService.insert(studentClassTable);
            StudentUserDetail studentUserDetail;
            Map map=new HashMap<String,Object>();
            map.put("sys_user_id",ShiroKit.getUser().id);
            List<StudentUserDetail> list=selectByMap(map);
            if(list!=null&&list.size()!=0)studentUserDetail=list.get(0);
            else  studentUserDetail=new StudentUserDetail();
            studentUserDetail.setStudentClassId(studentClassTable.getId());
            studentUserDetail.setStudentId(studentID);
            studentUserDetail.setStudentPassword(studentPassword);
            String year=studentAuthentication.schoolDate.substring(0,4);
            String month=studentAuthentication.schoolDate.substring(4,6);
            String day=studentAuthentication.schoolDate.substring(6,8);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date date=  simpleDateFormat.parse(year+"-"+month+"-"+day);
            studentUserDetail.setSysUserId(ShiroKit.getUser().id);
            studentUserDetail.setEnrollmentDate(date);
            studentUserDetail.setEmail(studentAuthentication.email);
            studentUserDetail.setPhone(studentAuthentication.phone);
            studentUserDetail.setName(studentAuthentication.name);
            studentUserDetail.setAvatar(pictureName);
            if(list!=null&&list.size()!=0)updateById(studentUserDetail);
            else  insert(studentUserDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

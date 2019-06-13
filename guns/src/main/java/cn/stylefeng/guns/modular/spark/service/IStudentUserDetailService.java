package cn.stylefeng.guns.modular.spark.service;

import cn.stylefeng.guns.modular.spark.model.StudentUserDetail;
import cn.stylefeng.guns.modular.spark.util.StudentAuthentication;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李利光
 * @since 2018-12-21
 */
public interface IStudentUserDetailService extends IService<StudentUserDetail> {

    public StudentUserDetail createByAccount(String studentID,String studentPassword,String code,StudentAuthentication studentAuthentication);

}

package cn.stylefeng.guns.modular.spark.service;

import cn.stylefeng.guns.modular.spark.model.PartTime;
import cn.stylefeng.guns.modular.system.model.LoginLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周志勇
 * @since 2018-12-13
 */
public interface IPartTimeService extends IService<PartTime> {
    List<PartTime> getAuditPartTime(Page<PartTime> page);

}

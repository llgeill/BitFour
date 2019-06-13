package cn.stylefeng.guns.modular.spark.dao;

import cn.stylefeng.guns.modular.spark.model.PartTime;
import cn.stylefeng.guns.modular.system.model.LoginLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周志勇
 * @since 2018-12-13
 */
public interface PartTimeMapper extends BaseMapper<PartTime> {
    List<PartTime>  getAuditPartTime(@Param("page") Page<PartTime> page);
}

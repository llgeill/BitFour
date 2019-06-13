package cn.stylefeng.guns.modular.spark.service.impl;

import cn.stylefeng.guns.modular.spark.model.PartTime;
import cn.stylefeng.guns.modular.spark.dao.PartTimeMapper;
import cn.stylefeng.guns.modular.spark.service.IPartTimeService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周志勇
 * @since 2018-12-13
 */
@Service
public class PartTimeServiceImpl extends ServiceImpl<PartTimeMapper, PartTime> implements IPartTimeService {
    @Autowired
    private PartTimeMapper partTimeMapper;


    @Override
    public List<PartTime> getAuditPartTime(Page<PartTime> page) {

        return partTimeMapper.getAuditPartTime(page);
    }
}

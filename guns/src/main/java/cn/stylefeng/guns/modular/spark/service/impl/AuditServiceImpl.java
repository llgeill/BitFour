package cn.stylefeng.guns.modular.spark.service.impl;

import cn.stylefeng.guns.modular.spark.dao.AuditDetailMapper;
import cn.stylefeng.guns.modular.spark.dao.PartTimeMapper;
import cn.stylefeng.guns.modular.spark.model.Audit;
import cn.stylefeng.guns.modular.spark.dao.AuditMapper;
import cn.stylefeng.guns.modular.spark.model.ReviewJob;
import cn.stylefeng.guns.modular.spark.service.IAuditService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志成
 * @since 2018-12-20
 */
@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, Audit> implements IAuditService {

}

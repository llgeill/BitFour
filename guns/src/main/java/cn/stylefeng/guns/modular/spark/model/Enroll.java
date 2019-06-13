package cn.stylefeng.guns.modular.spark.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李利光
 * @since 2018-12-23
 */
@TableName("spark_enroll")
public class Enroll extends Model<Enroll> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 报名状态（1正在报名2报名成功3报名失败）
     */
    private Integer status;
    /**
     * 报名者id
     */
    @TableField("sys_user_id")
    private Integer sysUserId;
    /**
     * 兼职信息id
     */
    @TableField("part_time_id")
    private Long partTimeId;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getPartTimeId() {
        return partTimeId;
    }

    public void setPartTimeId(Long partTimeId) {
        this.partTimeId = partTimeId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Enroll{" +
        ", id=" + id +
        ", status=" + status +
        ", sysUserId=" + sysUserId +
        ", partTimeId=" + partTimeId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}

package cn.stylefeng.guns.modular.spark.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-12-22
 */
@TableName("spark_audit")
public class Audit extends Model<Audit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 审核状态(1：待审核 2：再审核  3：已通过 4：未通过）
     */
    private Integer status;
    /**
     * 兼职信息id
     */
    @TableField("part_time_id")
    private Long partTimeId;
    /**
     * 数据创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 数据修改时间
     */
    @TableField("gmt_modified")
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
        return this.id;
    }

    @Override
    public String toString() {
        return "Audit{" +
        ", id=" + id +
        ", status=" + status +
        ", partTimeId=" + partTimeId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}

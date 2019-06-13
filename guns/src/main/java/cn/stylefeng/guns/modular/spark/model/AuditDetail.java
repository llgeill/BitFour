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
 * @author 李志成
 * @since 2018-12-18
 */
@TableName("spark_audit_detail")
public class AuditDetail extends Model<AuditDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 审核人员id，也就是sys_user表的id
     */
    @TableField("auditor_id")//
    private Integer auditorId;
    /**
     * 审核回复内容
     */
    private String content;
    /**
     * spark_audit表的id，为父id
     */
    @TableField("spark_audit_id")
    private Long sparkAuditId;
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

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSparkAuditId() {
        return sparkAuditId;
    }

    public void setSparkAuditId(Long sparkAuditId) {
        this.sparkAuditId = sparkAuditId;
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
        return "AuditDetail{" +
        ", id=" + id +
        ", auditorId=" + auditorId +
        ", content=" + content +
        ", sparkAuditId=" + sparkAuditId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}

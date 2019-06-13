package cn.stylefeng.guns.modular.spark.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @author 周志勇
 * @since 2018-12-23
 */
@TableName("spark_complaint")
public class Complaint extends Model<Complaint> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 申诉标题
     */
    @TableField("title")
    private String title;
    /**
     * 申诉内容
     */
    @TableField("content")
    private String content;
    /**
     * 申诉凭证（图片），最多9张，保存的地址用逗号隔开
     */
    @TableField("voucher")
    private String voucher;
    /**
     * 1 未处理 2 正在处理 3已处理
     */
    @TableField("status")
    private Integer status;
    /**
     * 申诉发起者的id
     */
    @TableField("complainants_id")
    private Integer complainantsId;
    /**
     * 数据创建时间
     */
    @TableField("gmt_create")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;
    /**
     * 数据修改时间
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getComplainantsId() {
        return complainantsId;
    }

    public void setComplainantsId(Integer complainantsId) {
        this.complainantsId = complainantsId;
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
        return "Complaint{" +
        ", id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", voucher=" + voucher +
        ", status=" + status +
        ", complainantsId=" + complainantsId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}

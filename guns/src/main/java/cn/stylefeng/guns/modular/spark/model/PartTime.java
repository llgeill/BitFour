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
 * @since 2018-12-13
 */
@TableName("spark_part_time")
public class PartTime extends Model<PartTime> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 兼职标题
     */
    @TableField("part_time_title")
    private String partTimeTitle;
    /**
     * 兼职内容
     */
    @TableField("part_time_content")
    private String partTimeContent;
    /**
     * 兼职类型
     */
    @TableField("part_time_type")
    private String partTimeType;
    /**
     * 兼职招聘人数
     */
    @TableField("recruiter_number")
    private Integer recruiterNumber;
    /**
     * 工作种类 0 短招 1长招
     */
    @TableField("work_type")
    private Integer workType;
    /**
     * 工作开始时间
     */
    @TableField("work_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workStartTime;
    /**
     * 工作结束时间
     */
    @TableField("work_end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workEndTime;
    /**
     * 工作时间范围，一天可能有多个非连续时间段，用‘ ，’分割
     */
    @TableField("work_time_range")
    private String workTimeRange;
    /**
     * 性别要求（1：男 2：女 3: 男+女）
     */
    @TableField("gender_requirement")
    private Integer genderRequirement;
    /**
     * 工资
     */
    @TableField("work_salary")
    private String workSalary;
    /**
     * 工作福利，每项福利用逗号分隔
     */
    @TableField("work_welfare")
    private String workWelfare;
    /**
     * 工作地点
     */
    @TableField("work_place")
    private String workPlace;
    /**
     * 结算周期 1.日结 2.周结 3.月结 4.完工结
     */
    @TableField("settlement_cycle")
    private Integer settlementCycle;
    /**
     * 发布的学校对象
     */
    @TableField("publish_school")
    private String publishSchool;
    /**
     * 发布者id
     */
    @TableField("pubish_id")
    private Integer pubishId;
    /**
     * 发布兼职者的个人邮箱
     */
    @TableField("publish_email")
    private String publishEmail;
    /**
     * 发布兼职者的电话号码
     */
    @TableField("publish_phone")
    private String publishPhone;
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

    public String getPartTimeTitle() {
        return partTimeTitle;
    }

    public void setPartTimeTitle(String partTimeTitle) {
        this.partTimeTitle = partTimeTitle;
    }

    public String getPartTimeContent() {
        return partTimeContent;
    }

    public void setPartTimeContent(String partTimeContent) {
        this.partTimeContent = partTimeContent;
    }

    public String getPartTimeType() {
        return partTimeType;
    }

    public void setPartTimeType(String partTimeType) {
        this.partTimeType = partTimeType;
    }

    public Integer getRecruiterNumber() {
        return recruiterNumber;
    }

    public void setRecruiterNumber(Integer recruiterNumber) {
        this.recruiterNumber = recruiterNumber;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Date getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Date getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(Date workEndTime) {
        this.workEndTime = workEndTime;
    }

    public String getWorkTimeRange() {
        return workTimeRange;
    }

    public void setWorkTimeRange(String workTimeRange) {
        this.workTimeRange = workTimeRange;
    }

    public Integer getGenderRequirement() {
        return genderRequirement;
    }

    public void setGenderRequirement(Integer genderRequirement) {
        this.genderRequirement = genderRequirement;
    }

    public String getWorkSalary() {
        return workSalary;
    }

    public void setWorkSalary(String workSalary) {
        this.workSalary = workSalary;
    }

    public String getWorkWelfare() {
        return workWelfare;
    }

    public void setWorkWelfare(String workWelfare) {
        this.workWelfare = workWelfare;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Integer getSettlementCycle() {
        return settlementCycle;
    }

    public void setSettlementCycle(Integer settlementCycle) {
        this.settlementCycle = settlementCycle;
    }

    public String getPublishSchool() {
        return publishSchool;
    }

    public void setPublishSchool(String publishSchool) {
        this.publishSchool = publishSchool;
    }

    public Integer getPubishId() {
        return pubishId;
    }

    public void setPubishId(Integer pubishId) {
        this.pubishId = pubishId;
    }

    public String getPublishEmail() {
        return publishEmail;
    }

    public void setPublishEmail(String publishEmail) {
        this.publishEmail = publishEmail;
    }

    public String getPublishPhone() {
        return publishPhone;
    }

    public void setPublishPhone(String publishPhone) {
        this.publishPhone = publishPhone;
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
        return "PartTime{" +
        ", id=" + id +
        ", partTimeTitle=" + partTimeTitle +
        ", partTimeContent=" + partTimeContent +
        ", partTimeType=" + partTimeType +
        ", recruiterNumber=" + recruiterNumber +
        ", workType=" + workType +
        ", workStartTime=" + workStartTime +
        ", workEndTime=" + workEndTime +
        ", workTimeRange=" + workTimeRange +
        ", genderRequirement=" + genderRequirement +
        ", workSalary=" + workSalary +
        ", workWelfare=" + workWelfare +
        ", workPlace=" + workPlace +
        ", settlementCycle=" + settlementCycle +
        ", publishSchool=" + publishSchool +
        ", pubishId=" + pubishId +
        ", publishEmail=" + publishEmail +
        ", publishPhone=" + publishPhone +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}

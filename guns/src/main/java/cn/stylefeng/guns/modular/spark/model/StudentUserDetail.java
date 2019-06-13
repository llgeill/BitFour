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
 * @author 李利光
 * @since 2018-12-21
 */
@TableName("spark_student_user_detail")
public class StudentUserDetail extends Model<StudentUserDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 学号
     */
    @TableField("student_id")
    private String studentId;
    /**
     * 密码
     */
    @TableField("student_password")
    private String studentPassword;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 真实头像
     */
    private String avatar;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 入学日期
     */
    @TableField("enrollment_date")
    private Date enrollmentDate;
    /**
     * 学生课表
     */
    @TableField("student_class_id")
    private Long studentClassId;
    /**
     * sys_user表的用户id
     */
    @TableField("sys_user_id")
    private Integer sysUserId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Long getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
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
        return "StudentUserDetail{" +
        ", id=" + id +
        ", studentId=" + studentId +
        ", studentPassword=" + studentPassword +
        ", name=" + name +
        ", avatar=" + avatar +
        ", phone=" + phone +
        ", email=" + email +
        ", enrollmentDate=" + enrollmentDate +
        ", studentClassId=" + studentClassId +
        ", sysUserId=" + sysUserId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}

package cn.stylefeng.guns.modular.spark.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("spark_part_time")
public class PartTimeSimple {
    /**
     * 兼职标题
     */
    @TableField("part_time_title")
    private String partTimeTitle;

    /**
     * 兼职类型
     */
    @TableField("part_time_type")
    private String partTimeType;

    /**
     * 数据创建时间
     */
    @TableField("gmt_create")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;

    /**
     * 工作地点
     */
    @TableField("work_place")
    private String workPlace;


    public String getPartTimeTitle() {
        return partTimeTitle;
    }

    public void setPartTimeTitle(String partTimeTitle) {
        this.partTimeTitle = partTimeTitle;
    }

    public String getPartTimeType() {
        return partTimeType;
    }

    public void setPartTimeType(String partTimeType) {
        this.partTimeType = partTimeType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}

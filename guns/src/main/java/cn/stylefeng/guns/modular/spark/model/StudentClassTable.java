package cn.stylefeng.guns.modular.spark.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李利光
 * @since 2018-12-22
 */
@TableName("spark_student_class_table")
public class StudentClassTable extends Model<StudentClassTable> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 星期一
     */
    private String monday;
    /**
     * 星期二
     */
    private String tuesday;
    /**
     * 星期三
     */
    private String wednesday;
    /**
     * 星期四
     */
    private String thursday;
    /**
     * 星期五
     */
    private String friday;
    /**
     * 星期六
     */
    private String saturday;
    /**
     * 星期日
     */
    private String sunday;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public void setData(int key,String value){
        switch (key){
            case 1:
                setMonday(value);
                break;
            case 2:
                setTuesday(value);
                break;
            case 3:
                setWednesday(value);
                break;
            case 4:
                setThursday(value);
                break;
            case 5:
                setFriday(value);
                break;
            case 6:
                setSaturday(value);
                break;
            case 7:
                setSunday(value);
                break;

        }
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StudentClassTable{" +
        ", id=" + id +
        ", monday=" + monday +
        ", tuesday=" + tuesday +
        ", wednesday=" + wednesday +
        ", thursday=" + thursday +
        ", friday=" + friday +
        ", saturday=" + saturday +
        ", sunday=" + sunday +
        "}";
    }
}

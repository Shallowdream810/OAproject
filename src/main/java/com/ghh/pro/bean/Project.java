package com.ghh.pro.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.print.DocFlavor;
import java.util.Date;

public class Project {
    private Integer pid;//项目id

    private String pname;//项目名

    private Integer comname;//客户公司id

    private String conmaneByid;       //自己添加公司名称

    private String empNamebyId;         //自己添加员工姓名

    private String comper;//客户公司的联系人

    private Integer empFk1;//员工级别

    private Integer empcount;//员工人数

    private Date starttime;//

    private Date buildtime;//

    private Integer cost;//

    private String level;//

    private Date endtime;//

    private String remark;//

    private Integer empFk;//员工id

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getComname() {
        return comname;
    }

    public void setComname(Integer comname) {
        this.comname = comname;
    }

    public String getComper() {
        return comper;
    }

    public void setComper(String comper) {
        this.comper = comper == null ? null : comper.trim();
    }

    public Integer getEmpFk1() {
        return empFk1;
    }

    public void setEmpFk1(Integer empFk1) {
        this.empFk1 = empFk1;
    }

    public Integer getEmpcount() {
        return empcount;
    }

    public void setEmpcount(Integer empcount) {
        this.empcount = empcount;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Date buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }

    public String getConmaneByid() {
        return conmaneByid;
    }

    public void setConmaneByid(String conmaneByid) {
        this.conmaneByid = conmaneByid;
    }

    public String getEmpNamebyId() {
        return empNamebyId;
    }

    public void setEmpNamebyId(String empNamebyId) {
        this.empNamebyId = empNamebyId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", comname=" + comname +
                ", comper='" + comper + '\'' +
                ", empFk1=" + empFk1 +
                ", empcount=" + empcount +
                ", starttime=" + starttime +
                ", buildtime=" + buildtime +
                ", cost=" + cost +
                ", level='" + level + '\'' +
                ", endtime=" + endtime +
                ", remark='" + remark + '\'' +
                ", empFk=" + empFk +
                '}';
    }
}
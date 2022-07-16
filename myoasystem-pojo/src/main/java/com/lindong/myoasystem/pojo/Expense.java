package com.lindong.myoasystem.pojo;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private Integer expid;

    private String empid;

    private Double totalamount;

    private Date exptime;

    private String expdesc;

    private String nextauditor;

    private String lastresult;

    private String status;

    private static final long serialVersionUID = 1L;

    public Integer getExpid() {
        return expid;
    }

    public void setExpid(Integer expid) {
        this.expid = expid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public Date getExptime() {
        return exptime;
    }

    public void setExptime(Date exptime) {
        this.exptime = exptime;
    }

    public String getExpdesc() {
        return expdesc;
    }

    public void setExpdesc(String expdesc) {
        this.expdesc = expdesc == null ? null : expdesc.trim();
    }

    public String getNextauditor() {
        return nextauditor;
    }

    public void setNextauditor(String nextauditor) {
        this.nextauditor = nextauditor == null ? null : nextauditor.trim();
    }

    public String getLastresult() {
        return lastresult;
    }

    public void setLastresult(String lastresult) {
        this.lastresult = lastresult == null ? null : lastresult.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", expid=").append(expid);
        sb.append(", empid=").append(empid);
        sb.append(", totalamount=").append(totalamount);
        sb.append(", exptime=").append(exptime);
        sb.append(", expdesc=").append(expdesc);
        sb.append(", nextauditor=").append(nextauditor);
        sb.append(", lastresult=").append(lastresult);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
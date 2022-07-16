package com.lindong.myoasystem.pojo;

import java.io.Serializable;
import java.util.Date;

public class Duty implements Serializable {
    private Integer dtid;

    private String emprid;

    private Date dtdate;

    private String signintime;

    private String signouttime;

    private static final long serialVersionUID = 1L;

    public Integer getDtid() {
        return dtid;
    }

    public void setDtid(Integer dtid) {
        this.dtid = dtid;
    }

    public String getEmprid() {
        return emprid;
    }

    public void setEmprid(String emprid) {
        this.emprid = emprid == null ? null : emprid.trim();
    }

    public Date getDtdate() {
        return dtdate;
    }

    public void setDtdate(Date dtdate) {
        this.dtdate = dtdate;
    }

    public String getSignintime() {
        return signintime;
    }

    public void setSignintime(String signintime) {
        this.signintime = signintime == null ? null : signintime.trim();
    }

    public String getSignouttime() {
        return signouttime;
    }

    public void setSignouttime(String signouttime) {
        this.signouttime = signouttime == null ? null : signouttime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dtid=").append(dtid);
        sb.append(", emprid=").append(emprid);
        sb.append(", dtdate=").append(dtdate);
        sb.append(", signintime=").append(signintime);
        sb.append(", signouttime=").append(signouttime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.example.test.model;

import java.sql.Date;

public class Pendaftaran {
    private Integer memberId ;
    private String memberName ;
    private Date memberBirthday ;
    private String memberParent ;
    private Integer memberPhone ;
    private String memberEmail ;
    private boolean memberSkill ;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(Date memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

    public String getMemberParent() {
        return memberParent;
    }

    public void setMemberParent(String memberParent) {
        this.memberParent = memberParent;
    }

    public Integer getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(Integer memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public boolean isMemberSkill() {
        return memberSkill;
    }

    public void setMemberSkill(boolean memberSkill) {
        this.memberSkill = memberSkill;
    }
}

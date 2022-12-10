package com.example.seisd_pro;

public class Admit_card_TableModel {
    String subname, subcode, subcredit;

    public Admit_card_TableModel(String subcode, String subname, String subcredit){
        this.subcode = subcode;
        this.subname = subname;
        this.subcredit = subcredit;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getSubcredit() {
        return subcredit;
    }

    public void setSubcredit(String subcredit) {
        this.subcredit = subcredit;
    }

}
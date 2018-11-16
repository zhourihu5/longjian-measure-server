package com.longfor.longjian.basic.po;

public class TeamWithBLOBs extends Team {
    private String setting;

    private String customExtra;

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
    }

    public String getCustomExtra() {
        return customExtra;
    }

    public void setCustomExtra(String customExtra) {
        this.customExtra = customExtra == null ? null : customExtra.trim();
    }
}
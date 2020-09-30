package com.robotao.app.scisample.db.entity;

import androidx.room.ColumnInfo;

// TODO: 2020-09-27
// 等解析百度等api的再做扩展
public class Address {

    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}
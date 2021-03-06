package com.simon.generator.bean;

import com.simon.generator.bean.enums.ColumnIndexType;

import java.io.Serializable;

/**
 * 数据库列对象
 * Created by guoshuai on 2018/8/29 0029.
 */
public class ColumnBean implements Serializable{

    /** 列名称 */
    private String name;

    /** 列数据类型 */
    private String dataType;

    /** 列注释 */
    private String comment;

    /** 索引类型 */
    private ColumnIndexType indexType;

    /** 关联一个数据库表对象 */
    private TableBean tableBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ColumnIndexType getIndexType() {
        return indexType;
    }

    public void setIndexType(ColumnIndexType indexType) {
        this.indexType = indexType;
    }

    public TableBean getTableBean() {
        return tableBean;
    }

    public void setTableBean(TableBean tableBean) {
        this.tableBean = tableBean;
    }

    @Override
    public String toString() {
        return "ColumnBean{" +
                "name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", comment='" + comment + '\'' +
                ", indexType=" + indexType +
                ", tableBean=" + tableBean +
                '}';
    }
}

package com.xgw.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private Object id;

    private Object rolecode;

    private String rolename;

    private Object departAgId;

    private Object roleType;

    private Object updateName;

    private Date updateDate;

    private Object updateBy;

    private Object createName;

    private Date createDate;

    private Object createBy;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getRolecode() {
        return rolecode;
    }

    public void setRolecode(Object rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Object getDepartAgId() {
        return departAgId;
    }

    public void setDepartAgId(Object departAgId) {
        this.departAgId = departAgId;
    }

    public Object getRoleType() {
        return roleType;
    }

    public void setRoleType(Object roleType) {
        this.roleType = roleType;
    }

    public Object getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Object updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getCreateName() {
        return createName;
    }

    public void setCreateName(Object createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }
}
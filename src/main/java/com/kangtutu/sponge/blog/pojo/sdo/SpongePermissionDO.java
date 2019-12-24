package com.kangtutu.sponge.blog.pojo.sdo;

import java.io.Serializable;

public class SpongePermissionDO implements Serializable {

    private Integer id;
    private String parmName;
    private String parmTarg;

    @Override
    public String toString() {
        return "SpongePermissionDO{" +
                "id=" + id +
                ", parmName='" + parmName + '\'' +
                ", parmTarg='" + parmTarg + '\'' +
                '}';
    }

    public String getParmName() {
        return parmName;
    }

    public void setParmName(String parmName) {
        this.parmName = parmName;
    }

    public String getParmTarg() {
        return parmTarg;
    }

    public void setParmTarg(String parmTarg) {
        this.parmTarg = parmTarg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

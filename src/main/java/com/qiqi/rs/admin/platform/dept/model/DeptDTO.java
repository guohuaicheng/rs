package com.qiqi.rs.admin.platform.dept.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeptDTO extends Dept {

    public int[] getDeptPathArr() {
        if (this.getDeptPath() != null) {
            String[] p = this.getDeptPath().substring(1).split("\\.");
            int[] pr = new int[p.length];
            for (int i = 0, l = p.length; i < l; i++) {
                pr[i] = Integer.valueOf(p[i]);
            }
            return pr;
        }
        return null;
    }

    private List<DeptDTO> children;

    public List<DeptDTO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptDTO> children) {
        this.children = children;
    }
}

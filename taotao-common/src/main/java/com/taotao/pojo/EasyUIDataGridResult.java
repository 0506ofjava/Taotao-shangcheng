package com.taotao.pojo;

import java.util.List;

/**
 * @author yq
 * @description
 * @create 2019-15-22-26
 **/
public class EasyUIDataGridResult {

    private long total;

    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
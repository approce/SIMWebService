package com.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 02.03.2015.
 */
public class PaginatedResult {
    private long total;
    private List<Map<String, Object>> rows = new LinkedList<>();

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}

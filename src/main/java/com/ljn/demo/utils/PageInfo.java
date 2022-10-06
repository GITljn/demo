package com.ljn.demo.utils;

public class PageInfo {
    private int current = 1;
    private int size = 10;
    private int totalRows;
    // 首页、末页、上一页、下一页都需要路径，如果全都在前端写，后面路径一旦需要修改，维护起来很麻烦
    private String path;
//    private int totalPages;
//    private int from;
//    private int to;
//    private boolean hasPre;
//    private boolean hasNext;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current > 0) {
            this.current = current;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        // 数据太多，浏览器可能会卡死
        if (size > 0 && size < 100) {
            this.size = size;
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        if (totalRows >= 0) {
            this.totalRows = totalRows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTotalPages() {
        if (totalRows % size == 0) {
            return totalRows / size;
        } else {
            return totalRows / size + 1;
        }
    }

    public int getFrom() {
        return Math.max(current - 2, 1);
    }

    public int getTo() {
        return Math.min(current + 2, getTotalPages());
    }

    public boolean getHasPre() {
        return current > 1;
    }

    public boolean getHasNext() {
        return current < getTotalPages();
    }

    public int getOffset() {
        return (current - 1) * size;
    }
}

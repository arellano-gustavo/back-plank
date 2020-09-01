package mx.qbits.plank.util;

import java.io.Serializable;

public class PageBoundaries implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page;
    private int size;
    private String sortColumn;
    private boolean ascending;

    public PageBoundaries() {
    }

    public PageBoundaries(int page, int size, String sortColumn, boolean ascending) {
        this.page = page;
        this.size = size;
        this.sortColumn = sortColumn;
        this.ascending = ascending;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

}

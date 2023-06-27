package com.ua.rosella.util;

public class Pagination{
    final Integer curPage;
    final Integer itemsCount;
    final Integer itemsPerPage;
    final Integer numberOfPages;



    public Pagination(int curPage, int itemsCount, int itemsPerPage) {
        this.curPage = curPage;
        this.itemsCount = itemsCount;
        this.itemsPerPage = itemsPerPage;
        this.numberOfPages = (int) Math.ceil((double) itemsCount / (double) itemsPerPage);
    }

    public Integer getCurPage() {
        return curPage;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

}
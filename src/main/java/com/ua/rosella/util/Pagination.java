package com.ua.rosella.util;

public class Pagination{
    final Integer curPage;
    final Integer itemsCount;
    final Integer itemsPerPage;
    final Integer pagesBefore;
    final Integer pagesAfter;
    final Integer numberOfPages;
    final Integer startPage;
    final Integer endPage;


    public Pagination(int curPage, int itemsCount, int itemsPerPage, int pagesBefore, int pagesAfter) {
        this.curPage = curPage;
        this.itemsCount = itemsCount;
        this.itemsPerPage = itemsPerPage;
        this.pagesBefore = pagesBefore;
        this.pagesAfter = pagesAfter;
        this.numberOfPages = (int) Math.ceil((double) itemsCount / (double) itemsPerPage);
        this.startPage = Math.max(1, curPage - pagesBefore);
        this.endPage = Math.min(numberOfPages, curPage + pagesAfter);
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

    public Integer getPagesBefore() {
        return pagesBefore;
    }

    public Integer getPagesAfter() {
        return pagesAfter;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }
}
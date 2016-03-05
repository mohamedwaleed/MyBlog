package my.blog.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageViewModel<D> {
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private List<D> pageContent;

    public PageViewModel(){}

    public PageViewModel(Page<D> page) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.numberOfElements = page.getNumberOfElements();
        this.pageContent = page.getContent();
        this.totalPages = (int) ((totalElements + size - 1) / size);
    }

    public int getNumber() {
        return this.number;
    }

    public int getSize() {
        return this.size;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public List<D> getPageContent() {
        return this.pageContent;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setPageContent(List<D> pageContent) {
        this.pageContent = pageContent;
    }



}

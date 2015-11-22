package com.nivalsoul.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class ResultPage<T> {
	List<T> content;
	int totalPages;
	long totalElements;
	boolean last;
	boolean first;
	int size;
	int number;
	Sort sort;
	int numberOfElements;
	
	public ResultPage(Page<T> page) {
		this.content = new ArrayList<T>(page.getContent());
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.last = page.isLast();
		this.first = page.isFirst();
		this.size = page.getSize();
		this.number = page.getNumber();
		this.sort = page.getSort();
		this.numberOfElements = page.getNumberOfElements();
	}
	

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	
}

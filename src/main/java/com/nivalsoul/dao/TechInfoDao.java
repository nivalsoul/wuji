package com.nivalsoul.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nivalsoul.model.TechInfo;

@Transactional
public interface TechInfoDao extends PagingAndSortingRepository<TechInfo, Long> {

	Page<TechInfo> findBySource(String source, Pageable pageable);
}

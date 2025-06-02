package com.luv2code.doan.repository;

import com.luv2code.doan.entity.SearchHistory;
import com.luv2code.doan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
    List<SearchHistory> findByUserOrderBySearchedAtDesc(User user);
}

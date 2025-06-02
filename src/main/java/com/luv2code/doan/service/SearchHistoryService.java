package com.luv2code.doan.service;

import com.luv2code.doan.entity.SearchHistory;
import com.luv2code.doan.entity.User;
import com.luv2code.doan.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    public void saveSearch(User user, String keyword) {
        if (user != null && keyword != null && !keyword.trim().isEmpty()) {
            SearchHistory history = new SearchHistory();
            history.setUser(user);
            history.setKeyword(keyword.trim());
            history.setSearchedAt(new Date());
            searchHistoryRepository.save(history);
        }
    }
}

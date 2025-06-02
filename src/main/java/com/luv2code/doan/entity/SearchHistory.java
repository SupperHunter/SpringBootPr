package com.luv2code.doan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "search_history")
public class SearchHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "keyword", nullable = false, length = 255)
    private String keyword;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "searched_at", nullable = false)
    private Date searchedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // giả sử bạn có entity User

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(Date searchedAt) {
        this.searchedAt = searchedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

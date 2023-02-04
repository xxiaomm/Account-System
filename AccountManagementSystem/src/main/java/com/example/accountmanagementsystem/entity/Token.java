package com.example.accountmanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Token")
public class Token {
    @Id
    @Column(name="content")
    private String content;

    @Column(name="expired_date")
    private LocalDate expiredDate;

    @JsonIgnore
    @OneToOne(mappedBy = "token")
    private Account account;

//    @JsonIgnore
//    @OneToOne(mappedBy = "token")
//    private Post_Status post_status;


    // default expired date is after 30 days
    public Token(String content) {
        this.content = content;
        this.expiredDate = LocalDate.now().plusDays(30);
    }

    public Token() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "Token{" +
                "content='" + content + '\'' +
                ", expiredDate=" + expiredDate +
                ", account=" + account +
                '}';
    }
}

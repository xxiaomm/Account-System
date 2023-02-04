package com.example.accountmanagementsystem.entity;


import com.example.accountmanagementsystem.entity.Enum.Status;
import jakarta.persistence.*;


@Entity
@Table(name="Post_Status")
public class Post_Status {
//    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn(name="token_content", referencedColumnName = "content")
    @Id
    @Column(name="token_content")
    private String tokenContent;

    @Column(name="post_token_status")
    private Status post_token_status;

    public Post_Status(String tokenContent, Status post_token_status) {
        this.tokenContent = tokenContent;
        this.post_token_status = post_token_status;
    }

    public Post_Status() {

    }

    public String getTokenContent() {
        return tokenContent;
    }

    public void setTokenContent(String tokenContent) {
        this.tokenContent = tokenContent;
    }

    public Status getPost_token_status() {
        return post_token_status;
    }

    public void setPost_token_status(Status post_token_status) {
        this.post_token_status = post_token_status;
    }

    @Override
    public String toString() {
        return "Post_Status{" +
                "tokenContent='" + tokenContent + '\'' +
                ", post_token_status=" + post_token_status +
                '}';
    }
}
package com.example.accountmanagementsystem.entity;

import com.example.accountmanagementsystem.entity.Enum.Status;
import jakarta.persistence.*;

@Entity
@Table(name="Account")
public class Account {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    // name is the name in current table Account Token,
    // referencedColumnName is the name of that in the table Token
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="token", referencedColumnName = "content")
    private Token token;

    @Column(name="status")
    private Status status;  // token and account status


    public Account(String id, String name, Token token, Status status) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.status = status;
    }

    public Account() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", token=" + token +
                ", status=" + status +
                '}';
    }
}

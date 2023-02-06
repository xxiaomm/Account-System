package com.example.accountmanagementsystem.entity;

import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
import jakarta.persistence.*;

import java.util.UUID;

/** reference
 * https://www.youtube.com/watch?v=o6tJM7jW69s
 * https://www.youtube.com/watch?v=bFdMrNwBFo4
 * https://www.youtube.com/watch?v=DpgI1xialn0
 * https://www.youtube.com/watch?v=eXyqVLSAP6Q
 * https://blog.csdn.net/careful_thebrave/article/details/124027847
 */

@Entity
@Table(name="Account")
public class Account {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique = true, nullable = false)
    private String id;

    @Column(name="name")
    private String name;

    // name is the name in current table Account Token,
    // referencedColumnName is the name of that in the table Token
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="token", referencedColumnName = "content")
//    private Token token;

    @Column(name="token")
    private String token;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private EnumStatus status;  // token and account status


    public Account(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.token = null;
        this.status = EnumStatus.ACTIVE;
    }

    public Account(String name, EnumStatus status) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.status = status;
    }

    public Account (String name, String token, EnumStatus status) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.token = token;
        this.status = status;
    }

    public Account() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
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

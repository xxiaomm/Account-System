package com.example.accountmanagementsystem.entity;


import com.example.accountmanagementsystem.entity.Enum.EnumPosStatus;
import jakarta.persistence.*;


@Entity
@Table(name="Pos_Status")
public class Pos_Status {
//    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn(name="token_content", referencedColumnName = "content")
    @Id
    @Column(name="token_content", unique = true, nullable = false)
    private String tokenContent;

    @Column(name="pos_token_status")
    private EnumPosStatus pos_token_status;

    public Pos_Status(String tokenContent, EnumPosStatus pos_token_status) {
        this.tokenContent = tokenContent;
        this.pos_token_status = pos_token_status;
    }

    public Pos_Status() {

    }

    public EnumPosStatus getPos_token_status() {
        return pos_token_status;
    }


    @Override
    public String toString() {
        return "Pos_Status{" +
                "tokenContent='" + tokenContent + '\'' +
                ", pos_token_status=" + pos_token_status +
                '}';
    }
}

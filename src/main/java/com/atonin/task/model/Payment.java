package com.atonin.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "supplier_id")
    private Long supplier_id;

    @Column(name = "account", unique = true)
    private Long account;

    @Column(name = "amount")
    private Double amount;

    @Transient
    private String command;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}

package com.accenture.budgetapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 200, name = "name")
    private String name;
    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_transaction_fk", referencedColumnName = "id")
    private List<Transaction> transactions;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

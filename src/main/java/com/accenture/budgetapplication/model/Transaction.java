//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.accenture.budgetapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "person_transactions")
@NoArgsConstructor
@AllArgsConstructor
@ValidTransaction
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, name = "product")
    String product;
    @Column(nullable = false, name = "creation_date")
    private LocalDateTime createdAt;
    @Column(nullable = false, name = "transaction_confirmation")
    private Boolean confirmed;
    @Column(nullable = false, name = "user_id")
    private Integer userId;
    @Column(nullable = false, name = "action_type")
    private ActionType type;
    @Column(nullable = false)
    private Double amount;

    public Integer getId() {
        return this.id;
    }

    public String getProduct() {
        return this.product;
    }

    public ActionType getType() {
        return this.type;
    }

    public double getAmount() {
        return this.amount;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String toString() {
        return "Transaction{" +
                "id=" + this.id + ", " +
                "product='" + this.product +
                "', createdAt=" + this.createdAt +
                ", confirmed=" + this.confirmed +
                ", userId=" + this.userId +
                ", type=" + this.type +
                ", amount=" + this.amount + "}";
    }
}

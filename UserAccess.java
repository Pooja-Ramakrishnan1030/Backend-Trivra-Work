package com.tr.trivra.trivraEngine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "user_access")
public class UserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "transaction_log_id", referencedColumnName = "id")
    private TransactionLog transactionLog;

    public UserAccess() {}

    public static UserAccess create(User user, TransactionLog transactionLog) {
        UserAccess userAccess = new UserAccess();
        userAccess.setUser(user);
        userAccess.setTransactionLog(transactionLog);
        return userAccess;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionLog getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Long getTransactionLogId() {
        return transactionLog != null ? transactionLog.getId() : null;
    }
}
package com.tr.trivra.trivraEngine.entity;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_logs")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "access_code", nullable = false)
    private String accessCode;

    @Column(name = "expiry_date", nullable = false)
    private OffsetDateTime expiryDate;

    @Column(name = "use_count", nullable = false)
    private Integer useCount;

    @Column(name = "status")
    private String status;

    @Column(name = "ts_created", nullable = false)
    private OffsetDateTime tsCreated;

    public TransactionLog() {}

    public static TransactionLog create(
            User user,
            Long paymentId,
            String transactionId,
            String accessCode,
            Integer useCount,
            OffsetDateTime expiryDate,
            String status) {

        TransactionLog t = new TransactionLog();

        t.user = user;
        t.paymentId = paymentId;
        t.transactionId = transactionId;
        t.accessCode = accessCode;
        t.useCount = useCount;
        t.expiryDate = expiryDate;
        t.status = status;
        t.tsCreated = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        return t;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Long getPaymentId() { return paymentId; }
    public String getTransactionId() { return transactionId; }
    public String getAccessCode() { return accessCode; }
    public OffsetDateTime getExpiryDate() { return expiryDate; }
    public Integer getUseCount() { return useCount; }
    public String getStatus() { return status; }
    public OffsetDateTime getTsCreated() { return tsCreated; }
}
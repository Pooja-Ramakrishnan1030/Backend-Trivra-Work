package com.tr.trivra.trivraEngine.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.trivra.trivraEngine.entity.TransactionLog;
import com.tr.trivra.trivraEngine.entity.User;
import com.tr.trivra.trivraEngine.exception.TransactionLogException;
import com.tr.trivra.trivraEngine.repository.TransactionLogRepository;
import com.tr.trivra.trivraEngine.repository.UserRepository;

@Service
public class TransactionLogService {
    @Autowired
    private TransactionLogRepository transactionLogRepository;
    @Autowired
    private UserRepository userRepository;

    public List<TransactionLog> getAllTransactionLog() {
        return transactionLogRepository.findAll();
    }

    public TransactionLog getById(Long id) {
        Optional<TransactionLog> transactionLog = transactionLogRepository.findById(id);
        if (!transactionLog.isPresent()) {
            throw new TransactionLogException(404, "TransactionLog with id " + id + " cannot be found");
        }
        return transactionLog.get();
    }

    public List<TransactionLog> getTractionByUserIdAfterDate(Long userId, OffsetDateTime date) {

        return transactionLogRepository.findTractionByUserIdAfterDate(userId, date);
    }

    public TransactionLog create(Long userId, Long paymentId, String transactionId,
                             String accessCode, Integer useCount,
                             OffsetDateTime expiryDate, String status) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new TransactionLogException(404, "User not found"));

    if (accessCode == null || accessCode.isBlank()) {
        throw new TransactionLogException(400, "accessCode is required");
    }

    if (expiryDate == null) {
        throw new TransactionLogException(400, "expiryDate is required");
    }

    if (useCount == null) useCount = 0;
    if (status == null) status = "ACTIVE";

    TransactionLog transactionLog = TransactionLog.create(
        user,
        paymentId,
        transactionId,
        accessCode,
        useCount,
        expiryDate,
        status
    );

    return transactionLogRepository.save(transactionLog);
}
}

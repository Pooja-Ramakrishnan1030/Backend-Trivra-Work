package com.tr.trivra.trivraEngine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.trivra.trivraEngine.entity.UserAccess;
import com.tr.trivra.trivraEngine.entity.TransactionLog;
import com.tr.trivra.trivraEngine.entity.User;
import com.tr.trivra.trivraEngine.exception.UserAccessException;
import com.tr.trivra.trivraEngine.repository.UserAccessRepository;
import com.tr.trivra.trivraEngine.repository.TransactionLogRepository;
import com.tr.trivra.trivraEngine.repository.UserRepository;

@Service
public class UserAccessService {

    @Autowired
    private UserAccessRepository userAccessRepository;

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserAccess> getAllUserAccess() {
        return userAccessRepository.findAll();
    }

    public UserAccess getById(Long id) {
        return userAccessRepository.findById(id)
            .orElseThrow(() -> UserAccessException.notFound(
                "UserAccess with id " + id + " cannot be found"
            ));
    }

    public List<UserAccess> getByUserIdAndTransactionLogId(Long userId, Long transactionLogId) {
        return userAccessRepository.findByUserIdAndTransactionLogId(userId, transactionLogId);
    }

    public Boolean remove(Long userId, Long transactionLogId) {

        userRepository.findById(userId)
            .orElseThrow(() -> UserAccessException.notFound("User not found"));

        transactionLogRepository.findById(transactionLogId)
            .orElseThrow(() -> UserAccessException.notFound("TransactionLog not found"));

        List<UserAccess> accesses =
            userAccessRepository.findByUserIdAndTransactionLogId(userId, transactionLogId);

        if (accesses.isEmpty()) {
            throw UserAccessException.notFound("UserAccess not found");
        }

        userAccessRepository.deleteAll(accesses);

        return true;
    }
}
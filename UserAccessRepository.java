package com.tr.trivra.trivraEngine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tr.trivra.trivraEngine.entity.UserAccess;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
    @Query("SELECT t FROM UserAccess t WHERE t.user.id = ?1 AND t.transactionLog.id = ?2")
List<UserAccess> findByUserIdAndTransactionLogId(Long userId, Long transactionLogId);
}
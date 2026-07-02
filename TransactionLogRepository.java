package com.tr.trivra.trivraEngine.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tr.trivra.trivraEngine.entity.TransactionLog;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
    @Query("select t from TransactionLog t where t.user.id = ?1 and t.tsCreated > ?2")
    List<TransactionLog> findTractionByUserIdAfterDate(Long userId, OffsetDateTime date);
}
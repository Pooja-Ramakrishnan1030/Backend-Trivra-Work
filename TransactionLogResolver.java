package com.tr.trivra.trivraEngine.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tr.trivra.trivraEngine.entity.TransactionLog;
import com.tr.trivra.trivraEngine.service.TransactionLogService;

@Controller
public class TransactionLogResolver {
	
    @Autowired
    private TransactionLogService transactionLogService;
    
    @QueryMapping
    public TransactionLog transactionLogById(@Argument Long id) { 
    	return transactionLogService.getById(id);
    	}

    @QueryMapping
    public List<TransactionLog> transactionLogByUserIdAndDate(
    		@Argument Long userId, 
    		@Argument OffsetDateTime date) {
        return transactionLogService.getTractionByUserIdAfterDate(userId, date);
    }

    @MutationMapping
    public TransactionLog createTransactionLog(@Argument Long userId,  
    @Argument Long paymentId, 
    @Argument String transactionId,
    @Argument String accessCode, 
    @Argument OffsetDateTime expiryDate,
    @Argument Integer useCount,
    @Argument String status
) {
    return transactionLogService.create(
        userId, paymentId, transactionId, accessCode, useCount, expiryDate, status
    );
}

}

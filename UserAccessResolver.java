package com.tr.trivra.trivraEngine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tr.trivra.trivraEngine.entity.UserAccess;
import com.tr.trivra.trivraEngine.service.UserAccessService;

@Controller
public class UserAccessResolver {
    
    @Autowired
    private UserAccessService userAccessService;
    
    @QueryMapping
    public UserAccess userAccessById(@Argument Long id) {
        return userAccessService.getById(id);
    }

    @QueryMapping
    public List<UserAccess> userAccessByUserIdAndTransactionLogId(
        @Argument Long userId,
        @Argument Long transactionLogId
    ) {
        return userAccessService.getByUserIdAndTransactionLogId(userId, transactionLogId);
    }

    @MutationMapping
    public Boolean removeUserAccess(
        @Argument Long userId,
        @Argument Long transactionLogId
    ) {
        return userAccessService.remove(userId, transactionLogId);
    }
}
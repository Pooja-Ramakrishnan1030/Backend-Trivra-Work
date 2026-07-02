package com.tr.trivra.trivraEngine.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import org.springframework.graphql.execution.ErrorType;

public class UserAccessException extends AbstractGraphqlException {

    private static final long serialVersionUID = 1L;

    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;

    private final int errorCode;

    public UserAccessException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static UserAccessException notFound(String message) {
        return new UserAccessException(NOT_FOUND, message);
    }

    public static UserAccessException badRequest(String message) {
        return new UserAccessException(BAD_REQUEST, message);
    }

    public GraphQLError toGraphQLError() {
        return GraphqlErrorBuilder.newError()
                .message(getMessage())
                .errorType(mapErrorType())
                .build();
    }

    private ErrorType mapErrorType() {
        return switch (errorCode) {
            case NOT_FOUND -> ErrorType.NOT_FOUND;
            case BAD_REQUEST -> ErrorType.BAD_REQUEST;
            default -> ErrorType.INTERNAL_ERROR;
        };
    }
}
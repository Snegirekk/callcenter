package com.snegirekk.callcenter.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.stream.Collectors;

public class BadRequestException extends ApiException {

    protected BadRequestException(String message) {
        super(message);
        httpStatus = HttpStatus.BAD_REQUEST;
    }

    public static ApiException onNonExistingEntity(String entityType, Map<String, String> criteria) {

        String criteriaString = convertMapToString(criteria);
        String message = String.format("Couldn't find resource of type '%s' by [%s].", entityType, criteriaString);

        return new BadRequestException(message);
    }

    public static ApiException onIllegalDuplicate(String entityType, Map<String, String> criteria) {

        String criteriaString = convertMapToString(criteria);
        String message = String.format("Resource of type '%s' with [%s] is already exists.", entityType, criteriaString);

        return new BadRequestException(message);
    }

    public static ApiException onInvalidQueryParams(String message) {

        return new BadRequestException(message);
    }

    private static String convertMapToString(Map<String, String> map) {
        return map.entrySet().stream()
                .map(entry -> String.format("'%s' => '%s'", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
    }
}

package org.example.javawebkpicourse.web;

import org.example.javawebkpicourse.domain.payment.PaymentTransaction;
import org.example.javawebkpicourse.service.exception.CatTravellerNotFoundException;
import org.example.javawebkpicourse.service.exception.PaymentTransactionFailed;
import org.example.javawebkpicourse.web.exception.ParamsViolationDetails;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.example.javawebkpicourse.util.PaymentDetailsUtils.getValidationErrorsProblemDetail;
import static java.net.URI.create;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@ControllerAdvice
@Slf4j
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CatTravellerNotFoundException.class)
    ProblemDetail handleStoreConfigurationNotFoundException(CatTravellerNotFoundException ex) {
        log.info("Traveller Not Found exception raised");
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, ex.getMessage());
        problemDetail.setType(create("traveller-not-found"));
        problemDetail.setTitle("Traveller Not Found");
        return problemDetail;
    }

    @ExceptionHandler(PaymentTransactionFailed.class)
    ProblemDetail handlePaymentTransactionFailedException(PaymentTransactionFailed ex) {
        log.info("Payment Transaction Failed");
        ProblemDetail problemDetail = forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        problemDetail.setType(create("payment-refused"));
        problemDetail.setTitle("Payment Transaction Failed to process");
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ParamsViolationDetails> validationResponse =
                errors.stream().map(err -> ParamsViolationDetails.builder().reason(err.getDefaultMessage()).fieldName(err.getField()).build()).toList();
        log.info("Input params validation failed");
        return ResponseEntity.status(BAD_REQUEST).body(getValidationErrorsProblemDetail(validationResponse));
    }

}

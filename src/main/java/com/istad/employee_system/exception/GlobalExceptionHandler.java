package com.istad.employee_system.exception;

import com.istad.employee_system.payload.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * پوری ایپلی کیشن کے ایررز کو ہینڈل کرنے والا چوکیدار (Global Handler)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. جب یوزر کوئی ایسی آئی ڈی مانگے جو ڈیٹا بیس میں نہ ہو (مثلاً ID: 999)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        // Builder کے بجائے براہ راست کنسٹرکٹر کا استعمال
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.toString(), // 404 NOT FOUND
                LocalDateTime.now(), // اب یہ ApiResponse کے اسٹائل سے میچ کرے گا
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    // 2. جب یوزر ویلیڈیشن کی خلاف ورزی کرے (جیسے نام خالی چھوڑ دے یا ای میل غلط لکھے)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        // جو میسج ہم نے DTO میں @NotBlank میں لکھا تھا، یہ اسے نکال لے گا
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.toString(), // 400 BAD REQUEST
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    // 3. اگر کوئی ایسا ایرر آ جائے جو ہمیں پتہ ہی نہ ہو (Fallback / Generic Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception exception) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), // 500 INTERNAL SERVER ERROR
                LocalDateTime.now(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
    }
}
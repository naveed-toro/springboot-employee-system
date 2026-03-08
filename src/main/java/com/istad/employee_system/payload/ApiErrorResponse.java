package com.istad.employee_system.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * ایرر کا رسپانس دکھانے کے لیے نیا ریکارڈ
 */
public record ApiErrorResponse(
        String status,
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        
        String errorMessage
) {
}
package com.istad.employee_system.payload;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

// یہ T ایک "Genric Type" ہے، اس کا مطلب ہے اس ڈبے میں ہم 
// Employee، Department یا کوئی بھی ڈیٹا ڈال سکتے ہیں
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
    
    // اس ایک لائن سے ہمارا ٹائم سٹیمپ بہت خوبصورت اور ریڈ ایبل ہو جائے گا
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    // ہم نے یہ کنسٹرکٹر بنایا ہے تاکہ اسے آسانی سے استعمال کر سکیں
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now(); // یہ خود بخود موجودہ وقت لے لے گا
    }

    // --- Getters and Setters ---
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
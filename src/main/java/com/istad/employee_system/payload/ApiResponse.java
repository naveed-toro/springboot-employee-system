package com.istad.employee_system.payload;

import java.time.LocalDateTime;

// یہ T ایک "Genric Type" ہے، اس کا مطلب ہے اس ڈبے میں ہم 
// Employee، Department یا کوئی بھی ڈیٹا ڈال سکتے ہیں
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
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
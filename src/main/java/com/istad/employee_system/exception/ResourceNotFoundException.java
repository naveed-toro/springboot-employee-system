package com.istad.employee_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// یہ لائن بتاتی ہے کہ جب یہ ایرر آئے تو 404 (Not Found) کا اسٹیٹس دکھانا ہے
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // یہ وہ میسج ہے جو ہم گاہک کو دکھائیں گے
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
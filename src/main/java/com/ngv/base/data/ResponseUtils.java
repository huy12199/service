package com.ngv.base.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> ResponseEntity<ResponseData<T>> success() {
        return ResponseEntity.ok(new ResponseData<>());
    }

    public static <T> ResponseEntity<ResponseData<T>> success(T o) {
        return ResponseEntity.ok(new ResponseData<T>().success(o));
    }

    public static <T> ResponseEntity<ResponseData<T>> success(T o, int code, String message) {
        return ResponseEntity.ok(new ResponseData<T>().success(o, code, message));
    }

    public static <T> ResponseEntity<ResponseData<T>> created() {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>());
    }

    public static <T> ResponseEntity<ResponseData<T>> created(T o) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<T>().success(o));
    }

    public static <T> ResponseEntity<ResponseData<T>> created(T o, int code, String message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<T>().success(o,code,message));
    }

    public static <T> ResponseEntity<ResponseData<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseData<>());
    }

    public static <T> ResponseEntity<ResponseData<T>> error(
            int code, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(getResponseDataError(code, message, null));
    }

    public static <T> ResponseEntity<ResponseData<T>> error(
            int code, String message, T data, HttpStatus status) {
        return ResponseEntity.status(status).body(getResponseDataError(code, message, data));
    }

    public static <T> ResponseEntity<ResponseData<T>> error(HttpStatus status) {
        return ResponseEntity.status(status).body(getResponseDataError(status.value(), status.getReasonPhrase(), null));
    }

    public static <T> ResponseData<T> getResponseDataError(
            int code, String message, T data) {
        return new ResponseData<T>().error(code, message, data);
    }


}

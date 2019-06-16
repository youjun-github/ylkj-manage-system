package com.ylkj.mgt.core.common;

import com.ylkj.mgt.core.lang.HttpCode;
import com.ylkj.mgt.core.lang.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 基Controller,便于扩展
 * @author youjun
 * @create 2019-06-16 21:45
 */
public class BaseController implements HttpCode {

    public <T> Result<T> ok(T data) {
        return Result.ok(data);
    }

    public <T> Result<T> error(String code, String msg) {
        return Result.error(code, msg);
    }

    public <T> Result<T> error(String msg) {
        return Result.error(DEFAULT_ERROR_CODE, msg);
    }

    public ResponseEntity<byte[]> download(byte[] body, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String filename = URLEncoder.encode(fileName, "UTF-8");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        } catch (UnsupportedEncodingException e) {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        }
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
    }

    public ResponseEntity<File> download(File body, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment;filename=" + new String(fileName.getBytes("ISO8859-1"), "UTF-8"));
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        } catch (UnsupportedEncodingException e) {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        }
        return new ResponseEntity<File>(body, headers, HttpStatus.OK);
    }
}

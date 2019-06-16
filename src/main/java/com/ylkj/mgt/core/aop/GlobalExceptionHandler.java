package com.ylkj.mgt.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ylkj.mgt.core.lang.HttpCode;
import com.ylkj.mgt.core.lang.MessageException;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.utils.CommonUtils;

/**
 * @Description:
 * @Author: youjun
 * @Date: 2019-06-16 21:01:01
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public <T> Result<T> handleBindException(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(x -> errorMsg.append(x.getDefaultMessage()));
        return Result.error(HttpCode.PARAM_INVALID, errorMsg.toString());
    }

    @ExceptionHandler({MessageException.class})
    @ResponseBody
    public Result messageException(MessageException e) {
        String msg = e.getMessage();
        if (CommonUtils.isEmpty(msg)) msg = "操作失败";
        String status = e.getCode();
        if (CommonUtils.isNotEmpty(e.getData())) {
            return new Result(status, msg, e.getData());
        }
        return Result.error(status, msg);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public <T> Result<T> handleException(Exception e) {
        if (e.getCause() instanceof MessageException) {
            return messageException((MessageException) e.getCause());
        }
        LOGGER.error(e.getMessage(), e);
        return Result.error(HttpCode.Message.ERROR_SYSTEM.code, HttpCode.Message.ERROR_SYSTEM.msg);
    }

    /**
     * @description: 针对Throwable 异常做处理
     * @auturn: youjun
     * @date: 2019-06-16 21:01:01
     * @param: e
     */
    public <T> Result<T> handleThrowable(Throwable e) {
        if (e instanceof MessageException) {
            return messageException((MessageException) e);
        }
        LOGGER.error(e.getMessage(), e);
        return Result.error(HttpCode.Message.ERROR_SYSTEM.code, HttpCode.Message.ERROR_SYSTEM.msg);
    }

}

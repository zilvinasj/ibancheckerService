/**
 * 
 */
package com.demo.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import java.util.Collections;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.demo.model.Infos;
import com.demo.model.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZJankunas
 *
 */
@Slf4j
@ControllerAdvice
public class IbanCheckerExceptionHandler
{

    /**
     * This method handles MethodArgumentTypeMismatchException exceptions.
     *
     *
     * @param e
     *            The exception
     * @return ServiceResponse Response
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    @ResponseBody
    public Response handle(MissingServletRequestParameterException e)
    {
        log.error(e.toString());
        Response response = new Response();
        Infos error = new Infos();
        error.setCode(IbanCheckerConstants.BAD_REQUEST.getMessage());
        error.setDescription(e.getMessage());
        response.setErrors(Collections.singletonList(error));
        return response;
    }

    /**
     * This method handles HttpRequestMethodNotSupportedException exceptions.
     *
     *
     * @param e
     *            The exception
     * @return ServiceResponse Response
     */
    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler
    @ResponseBody
    public Response handle(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.toString());

        Response response = new Response();
        Infos error = new Infos();
        error.setCode(IbanCheckerConstants.BAD_REQUEST.getMessage());
        error.setDescription(e.getMessage());
        response.setErrors(Collections.singletonList(error));
        return response;
    }

    /**
     * Catch all for exceptions not handled explicitly in other methods in this
     * class.
     * 
     * @param e
     *            Exception
     * @return response
     */
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    @ResponseBody
    public Response handle(Exception e)
    {
        log.error(e.toString());
        Response response = new Response();
        Infos error = new Infos();
        error.setCode(IbanCheckerConstants.INTERNAL_ERROR.getMessage());
        error.setDescription(e.getMessage());
        response.setErrors(Collections.singletonList(error));
        return response;
    }

    /**
     * This method handles MethodArgumentTypeMismatchException exceptions.
     *
     *
     * @param e
     *            The exception
     * @return ServiceResponse Response
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    @ResponseBody
    public Response handle(MethodArgumentTypeMismatchException e)
    {
        log.error(e.toString());

        Response response = new Response();
        Infos error = new Infos();
        error.setCode(IbanCheckerConstants.BAD_REQUEST.getMessage());
        error.setDescription(e.getMessage());
        response.setErrors(Collections.singletonList(error));
        return response;
    }
}

/**
 * 
 */
package com.demo.api;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.IbansResponse;
import com.demo.model.Request;
import com.demo.model.Response;
import com.demo.service.IbanCheckerService;

/**
 * @author ZJankunas
 *
 */
@RestController
public class Controller
{
    @Autowired
    private IbanCheckerService service;

    @PostMapping(value = "/ibanValidation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> validateIbans(@RequestBody @Valid Request ibans)
    {

        Response response = Response.builder().results(ibans.getIbans().stream()
                .map(e -> new IbansResponse(e, service.validateIBAN(e))).collect(Collectors.toList())).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

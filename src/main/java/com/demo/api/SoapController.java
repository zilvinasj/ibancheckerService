/**
 * 
 */
package com.demo.api;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.demo.service.IbanCheckerService;

import soapmodel.RequestIbanValidation;
import soapmodel.ResponseIbanValidation;

/**
 * @author ZJankunas
 *
 */
@Endpoint
public class SoapController
{
    @Autowired
    private IbanCheckerService service;

    @PayloadRoot(namespace = "soapModel", localPart = "RequestIbanValidation")
    @ResponsePayload
    public ResponseIbanValidation validate(@RequestPayload RequestIbanValidation request)
    {

        ResponseIbanValidation response = new ResponseIbanValidation();
        request.getRequestIbans().stream()
                .forEach(iban -> response.getResponseIbans().add(iban + ";" + service.validateIBAN(iban)));
        response.getResponseIbans().addAll(request.getRequestIbans().stream()
                .map(iban -> iban + ";" + service.validateIBAN(iban)).collect(Collectors.toList()));
        return response;
    }
}

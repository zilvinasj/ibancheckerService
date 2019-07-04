# ibancheckerService

exposes a REST Endpoint under:

localhost:8080/ibanValidation

Request:

{
    "ibans": [
        "AA051245445454552117989",
        "LT647044001231465456"
    ]
}


SOAP Endpoint under:
localhost:8080/ws/ibans

Sample request:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="soapModel">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:RequestIbanValidation>
         <gs:requestIbans>AA051245445454552117989</gs:requestIbans>
         <gs:requestIbans>AA051245445454552117989</gs:requestIbans>
      </gs:RequestIbanValidation>
   </soapenv:Body>
</soapenv:Envelope>



How to run:

mvn spring-boot:run

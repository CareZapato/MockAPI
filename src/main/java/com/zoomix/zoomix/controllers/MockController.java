package com.zoomix.zoomix.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin
@Configuration
@Log4j2
public class MockController {

    @Value("${project.info.name}")
    String name;

    @Value("${project.info.version}")
    String version;

    @Value("${project.info.ambiente}")
    String ambiente;

    @Value("${project.info.date}")
    String date;

    @GetMapping("/")
    public ResponseEntity<String> presentacion() {
        log.info("[RootController][presentacion]");
        return new ResponseEntity<String>(name + " " + version + " | " + ambiente + "@" + date, HttpStatus.OK);
    }

    @PostMapping("/transactions")
    public ResponseEntity<Map<String, Object>> processTransaction(@RequestBody Map<String, Object> requestBody) {
        log.info("[MockController][processTransaction] Request received: {}", requestBody);

        // Simulación de respuesta
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> meta = new HashMap<>();
        meta.put("_version", "1.0.0");
        meta.put("_rqDateTime", "2017-01-24T05:00:00.000Z");
        meta.put("_clientId", "899812212-1");

        Map<String, Object> data = new HashMap<>();
        data.put("token", "e074d38c628122c63e5c0986368ece22974d6fee1440617d85873b7b4efa48a3");
        data.put("status", "AUTHORIZED");
        data.put("buyOrder", "1234567890");
        data.put("amount", 40450);
        data.put("cardToken", "4895XXXX7001YYYY749");
        data.put("tokenExpirationDate", "2310");
        data.put("posEntryMode", "010");
        data.put("eci", "7");
        data.put("pmntInd", "R");
        data.put("recurPmnt", "F");
        data.put("deviceTypeTkn", "00");
        data.put("tknAssuranceLvl", "00");
        data.put("tknRqId", "9003E803750");
        data.put("authenticationValue", "");
        data.put("authenticationVersion", "1");
        data.put("dsTransId", "030000000900F1FA8AD90103A9003E8037501000");
        data.put("dsTrxId", "f38e6948-5388-41a6-bca4-b49723c19437");
        data.put("eciTrxRes", "0");
        data.put("cavvTrxRes", "");
        data.put("transactionDate", "2019-03-20T20:18:20Z");
        data.put("accountingDate", "2310");
        data.put("authorizationCode", "877550");
        data.put("responseCode", 0);
        data.put("paymentTypeCode", "VN");
        data.put("installmentsAmount", 2500);
        data.put("installmentsNumber", 2);
        data.put("recurrenceId", 1);

        Map<String, String> paymentMethod = new HashMap<>();
        paymentMethod.put("brand", "VISA");
        paymentMethod.put("name", "Débito");
        data.put("paymentMethod", paymentMethod);

        response.put("meta", meta);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/transactions/{token}")
    public ResponseEntity<Map<String, Object>> updateTransactionByToken(@PathVariable String token,
            @RequestBody Map<String, Object> requestBody) {
        log.info("[MockController][updateTransactionByToken] Token received: {}", token);
        log.info("[MockController][updateTransactionByToken] Request body: {}", requestBody);

        // Simulación de respuesta
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> meta = new HashMap<>();
        meta.put("_version", "1.0.0");
        meta.put("_rqDateTime", new Date().toString());
        meta.put("_clientId", "899812212-1");

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Transaction updated successfully");
        data.put("token", token);
        data.put("requestData", requestBody); // Devuelve el cuerpo recibido para verificación

        response.put("meta", meta);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
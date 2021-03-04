package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.Purchase;
import com.platzi.platzimarket.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getCliente(@PathVariable("clientId") String clientId){
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping()
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }

}

package com.calabrianshop.progettopsw.controllers;


import com.calabrianshop.progettopsw.entities.Prodotto;
import com.calabrianshop.progettopsw.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@PreAuthorize("hasAuthority('Admin')")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})


public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;


    @GetMapping
    public List<Prodotto> allProducts(){
        return prodottoService.showAllProducts();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        this.prodottoService.deleteProduct(id);
    }

    @PostMapping("/update")
    public void  updateProduct(@RequestBody Prodotto prodotto){
        prodottoService.updateProduct(prodotto);
    }

    @PostMapping(path = "/{id}")
    public void updateQuantita(@PathVariable("id") int prid) { this.prodottoService.updateQuantita(prid); }

    @PostMapping("/add")
    public void addProdotto(@RequestBody Prodotto prodotto){
        prodottoService.addProduct(prodotto);
    }

    @GetMapping(path = "/{id}")
    public Prodotto getProdotto(@PathVariable("id") int id){
        return prodottoService.getProdotto(id);
    }
}

package com.calabrianshop.progettopsw.controllers;

import com.calabrianshop.progettopsw.entities.Ordine;
import com.calabrianshop.progettopsw.entities.ProdottoInCarrello;
import com.calabrianshop.progettopsw.entities.Utente;
import com.calabrianshop.progettopsw.services.BollaService;
import com.calabrianshop.progettopsw.services.CarrelloService;
import com.calabrianshop.progettopsw.services.UtenteService;
import com.calabrianshop.progettopsw.support.Carrello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:4300")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private BollaService bollaService;


    @GetMapping("/orderreg")
    public ResponseEntity regOridne(@AuthenticationPrincipal HttpServletRequest user, @RequestParam("indirizzo") String indirizzo) {

        Ordine o = carrelloService.registraOrdine(user,indirizzo);
        return new ResponseEntity(o, HttpStatus.OK);
    }

    @GetMapping
    public Carrello getProdottiInCarr(@AuthenticationPrincipal HttpServletRequest user) {
        Utente u = utenteService.getUtente(user);
        Carrello carr= new Carrello(carrelloService.getProdottiCarrello(u.getEmail()));
        return carr;
    }

    @GetMapping("/empty")
    public ResponseEntity emptyCarrello(@AuthenticationPrincipal HttpServletRequest user){
        carrelloService.emptyCart(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity rimuoviProdotto(@AuthenticationPrincipal HttpServletRequest user, @RequestBody ProdottoInCarrello prodottoInCarrello){
        carrelloService.rimuoviProdottoInCarrello(user, prodottoInCarrello);
        return new ResponseEntity(prodottoInCarrello, HttpStatus.OK);
    }

    @GetMapping("/utente")
    public Utente getUtente(@AuthenticationPrincipal HttpServletRequest user){
        return utenteService.getUtente(user);
    }

    @GetMapping("/utentename")
    public String getUtenteName(@AuthenticationPrincipal HttpServletRequest user){
        Utente u=utenteService.getUtente(user);
        return  u.getNome();
    }

    @GetMapping("/utenteemail")
    public String getUtenteEmail(@AuthenticationPrincipal HttpServletRequest user){
        Utente u=utenteService.getUtente(user);
        System.out.println(u.getEmail());
        return  u.getNome();
    }
}
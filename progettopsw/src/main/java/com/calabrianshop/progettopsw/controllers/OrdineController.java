package com.calabrianshop.progettopsw.controllers;

import com.calabrianshop.progettopsw.entities.Bolla;
import com.calabrianshop.progettopsw.entities.Ordine;
import com.calabrianshop.progettopsw.entities.OrdineProdotto;
import com.calabrianshop.progettopsw.services.BollaService;
import com.calabrianshop.progettopsw.services.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4300"})
public class OrdineController {
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private BollaService bollaService;

    @GetMapping
    public List<Ordine> getOrdiniUtente(@AuthenticationPrincipal HttpServletRequest user){
        return ordineService.getOrdiniUtente(user);
    }

    @PostMapping("/ordered")
    public List<OrdineProdotto> getProdottiOrdinati(@AuthenticationPrincipal HttpServletRequest user, @RequestBody Ordine ordine){
        List<OrdineProdotto> l=ordineService.getProdottiOrdinati(user,ordine);
        for(OrdineProdotto op : l){
            System.out.println(op.getProdotto().getNome()+" "+op.getProdotto().getQuantita());
        }
        return l;
    }

    @PostMapping("/data")
    public String getData(@RequestBody Ordine ordine){
        return ordineService.getData(ordine);
    }

    @PostMapping("/bolla")
    public Bolla generaBolla(@AuthenticationPrincipal Principal user,@RequestBody Ordine ordine){
        return bollaService.generaBolla(user,ordine);
    }
}

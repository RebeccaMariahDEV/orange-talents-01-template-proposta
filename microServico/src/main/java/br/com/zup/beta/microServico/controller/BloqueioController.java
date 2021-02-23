package br.com.zup.beta.microServico.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("api/")
public class BloqueioController {


    @PostMapping("bloqueios")
    public ResponseEntity<?> ipUsuario(@RequestHeader(value = "User-Agent") String userAgent, RedirectAttributes redirectAttributes, Model model){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("User-Agent", "updated-Value");


        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("New order created.");
    }

}

package com.efs.server.controller;


import com.efs.server.JasperService;
import com.efs.server.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class JasperController {

    @Autowired
    private JasperService service;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/reports")
    public String abrir(){
        return "reports";
    }

    @GetMapping("/relatorio/pdf/jr1")
    public void exibirRelatorio01(@RequestParam ("code") String code,
                                 @RequestParam("acao") String acao,
                                 HttpServletResponse response) throws IOException {

        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        if(acao.equals("v")){
            response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf");
        }else{
            response.setHeader("Content-disposition", "attachment; filename=relatorio-"+ code + ".pdf");
        }
        response.getOutputStream().write(bytes);

    }

    //Relatório com parâmetro - Exemplo: nome
    @GetMapping("/relatorio/pdf/jr6/{code}")
    public void exibirRelatorio09(@PathVariable("code") String code,
                                  @RequestParam(name="nome", required = false) String nome,
                                  HttpServletResponse response) throws IOException {
        service.addParams("NIVEL_DESC", nome.isEmpty()? null : nome);


        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf");

        response.getOutputStream().write(bytes);

    }

    //RETORNA UMA LISTA COM OS NOMES
    @ModelAttribute("nomes")
    public List<String> getNomes(){
        return clienteRepository.findNomes();
    }

}

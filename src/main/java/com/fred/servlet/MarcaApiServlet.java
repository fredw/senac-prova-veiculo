package com.fred.servlet;

import com.fred.domain.MarcaDomain;
import com.fred.repository.MarcaRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/marcas")
public class MarcaApiServlet extends AbstractApiServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            MarcaRepository marcaRepository = new MarcaRepository();
            List<MarcaDomain> marcas = marcaRepository.findAll();
            response.getWriter().write(new Gson().toJson(marcas));
        } catch (Exception e) {
            response.getWriter().write(new Gson().toJson(e.toString()));
        }
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        HashMap<String,String> result = new HashMap<>();
        try {
            MarcaDomain marca = new Gson().fromJson(this.getBody(request), MarcaDomain.class);
            MarcaRepository marcaRepository = new MarcaRepository();
            marcaRepository.insert(marca);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        response.getWriter().write(new Gson().toJson(result));
    }
}

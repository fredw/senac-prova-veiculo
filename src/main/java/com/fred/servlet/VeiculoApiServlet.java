package com.fred.servlet;

import com.fred.domain.VeiculoDomain;
import com.fred.repository.VeiculoRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/veiculos")
public class VeiculoApiServlet extends AbstractApiServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            VeiculoRepository veiculoRepository = new VeiculoRepository();
            List<VeiculoDomain> veiculos;

            String modelo = request.getParameter("modelo");
            int marca = 0;
            String marcaString = request.getParameter("marca");
            if (marcaString != null && !marcaString.equals("null") && !marcaString.isEmpty()) {
                marca = Integer.valueOf(marcaString);
            }

            if (modelo != null && !modelo.equals("")) {
                veiculos = veiculoRepository.findByModelo(modelo);
            } else if (marca != 0) {
                veiculos = veiculoRepository.findByMarca(marca);
            } else {
                veiculos = veiculoRepository.findAll();
            }
            response.getWriter().write(new Gson().toJson(veiculos));
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
            VeiculoDomain veiculo = new Gson().fromJson(this.getBody(request), VeiculoDomain.class);
            VeiculoRepository veiculoRepository = new VeiculoRepository();
            veiculoRepository.insert(veiculo);
            result.put("status", "success");
        } catch (SQLException e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        response.getWriter().write(new Gson().toJson(result));
    }
}

package com.fred.repository;

import com.fred.database.ConnectionManager;
import com.fred.domain.VeiculoDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    public List<VeiculoDomain> findAll() throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from veiculo";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                List<VeiculoDomain> result = new ArrayList<>();
                while (rs.next()) {
                    VeiculoDomain veiculo = new VeiculoDomain();
                    this.setDomainFields(veiculo, rs);
                    result.add(veiculo);
                }
                return result;
            }
        }
    }

    public List<VeiculoDomain> findByModelo(String modelo) throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from veiculo where modelo like ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, "%" + modelo + "%");
                ResultSet rs = pst.executeQuery();
                List<VeiculoDomain> result = new ArrayList<>();
                while (rs.next()) {
                    VeiculoDomain veiculo = new VeiculoDomain();
                    this.setDomainFields(veiculo, rs);
                    result.add(veiculo);
                }
                return result;
            }
        }
    }

    public List<VeiculoDomain> findByMarca(int marca) throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from veiculo where id_marca = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, marca);
                ResultSet rs = pst.executeQuery();
                List<VeiculoDomain> result = new ArrayList<>();
                while (rs.next()) {
                    VeiculoDomain veiculo = new VeiculoDomain();
                    this.setDomainFields(veiculo, rs);
                    result.add(veiculo);
                }
                return result;
            }
        }
    }

    private void setDomainFields(VeiculoDomain veiculo, ResultSet rs) throws SQLException {
        MarcaRepository marcaRepository = new MarcaRepository();
        veiculo.setId(rs.getInt("id"));
        veiculo.setMarca(marcaRepository.get(rs.getInt("id_marca")));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setCategoria(rs.getString("categoria"));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setCor(rs.getString("cor"));
    }

    public void insert(VeiculoDomain veiculo) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "insert into veiculo (id_marca, modelo, categoria, ano, cor) values (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, veiculo.getMarca().getId());
                pst.setString(2, veiculo.getModelo());
                pst.setString(3, veiculo.getCategoria());
                pst.setInt(4, veiculo.getAno());
                pst.setString(5, veiculo.getCor());
                pst.executeUpdate();
            }
        }
    }
}

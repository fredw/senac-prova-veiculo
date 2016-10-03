package com.fred.repository;

import com.fred.database.ConnectionManager;
import com.fred.domain.MarcaDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaRepository {

    public List<MarcaDomain> findAll() throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from marca";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                List<MarcaDomain> result = new ArrayList<>();
                while (rs.next()) {
                    MarcaDomain marca = new MarcaDomain();
                    this.setDomainFields(marca, rs);
                    result.add(marca);
                }
                return result;
            }
        }
    }

    public MarcaDomain get(int id) throws SQLException {
        MarcaDomain marca = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from marca where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    marca = new MarcaDomain();
                    this.setDomainFields(marca, rs);
                }
            }
        }
        return marca;
    }

    private void setDomainFields(MarcaDomain marca, ResultSet rs) throws SQLException {
        marca.setId(rs.getInt("id"));
        marca.setNome(rs.getString("nome"));
    }

    public void insert(MarcaDomain marca) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "insert into marca (nome) values (?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, marca.getNome());
                pst.executeUpdate();
            }
        }
    }
}

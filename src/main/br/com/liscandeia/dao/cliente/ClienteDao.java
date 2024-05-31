package main.br.com.liscandeia.dao.cliente;

import main.br.com.liscandeia.dao.jdbc.ConnectionFactory;
import main.br.com.liscandeia.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements ICLienteDao {

    @Override
    public Integer cadastrar(Cliente entity) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO TB_CLIENTE (ID, CODIGO, NOME) ");
            sb.append("VALUES (nextval('SQ_CLIENTE'),?,?)");
            String sql =  sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1,entity.getCodigo());
            stm.setString(2,entity.getNome());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }

    }

    @Override
    public Integer excluir(Cliente entity) throws  Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM TB_CLIENTE ");
            sb.append("WHERE CODIGO = ?");
            String sql = sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getCodigo());
            return stm.executeUpdate();
        }catch (Exception e){
            throw  e;
        }finally {
            closeConnection(connection, stm, rs);
        }
    }

    @Override
    public Integer alterar(Cliente entity) throws  Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE TB_CLIENTE ");
            sb.append("SET NOME = ?, CODIGO = ? ");
            sb.append("WHERE ID = ?");
            String sql =  sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getNome());
            stm.setString(2, entity.getCodigo());
            stm.setLong(3, entity.getId());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }
    }

    @Override
    public Cliente buscarPorCodigo(String codigo) throws  Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM TB_CLIENTE ");
            sb.append("WHERE CODIGO = ?");
            String sql = sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarTodos() throws  Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> list = new ArrayList<>();
        Cliente cliente = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM TB_CLIENTE");
            String sql = sb.toString();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
                list.add(cliente);
            }
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }
        return list;
    }
    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}

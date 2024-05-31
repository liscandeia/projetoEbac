package main.br.com.liscandeia.dao.produto;

import main.br.com.liscandeia.dao.jdbc.ConnectionFactory;
import main.br.com.liscandeia.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao implements IProdutoDao {
    @Override
    public Integer cadastrar(Produto entity) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO TB_PRODUTO (ID, CODIGO, NOME, VALOR) ");
            sb.append("VALUES (nextval('SQ_PRODUTO'),?,?,?)");
            String sql =  sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1,entity.getCodigo());
            stm.setString(2,entity.getNome());
            stm.setDouble(3,entity.getValor());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }
    }

    @Override
    public Integer excluir(Produto entity) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM TB_PRODUTO ");
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
    public Integer alterar(Produto entity) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE TB_PRODUTO ");
            sb.append("SET NOME = ? , CODIGO = ? , VALOR = ? ");
            sb.append("WHERE ID = ?");
            String sql =  sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getNome());
            stm.setString(2, entity.getCodigo());
            stm.setDouble(3, entity.getValor());
            stm.setLong(4, entity.getId());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }
    }

    @Override
    public Produto buscarPorCodigo(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM TB_PRODUTO ");
            sb.append("WHERE CODIGO = ?");
            String sql = sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                Double valor = rs.getDouble("VALOR");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
                produto.setValor(valor);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, rs);
        }
        return produto;
    }

    @Override
    public List<Produto> listarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        Produto produto = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM TB_PRODUTO");
            String sql = sb.toString();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                Double valor = rs.getDouble("VALOR");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
                produto.setValor(valor);
                list.add(produto);
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

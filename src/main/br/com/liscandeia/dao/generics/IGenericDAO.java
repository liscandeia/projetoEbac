package main.br.com.liscandeia.dao.generics;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {
    public Integer cadastrar(T entity) throws  Exception;
    public Integer excluir(T entity) throws  Exception;
    public Integer alterar(T entity) throws  Exception;
    public T buscarPorCodigo(String codigo) throws  Exception;
    public List<T> listarTodos() throws  Exception;
}

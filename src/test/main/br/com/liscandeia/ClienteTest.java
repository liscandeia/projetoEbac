package test.main.br.com.liscandeia;
import org.junit.Test;
import main.br.com.liscandeia.dao.cliente.ClienteDao;
import main.br.com.liscandeia.dao.cliente.ICLienteDao;
import main.br.com.liscandeia.domain.Cliente;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ClienteTest {
    ICLienteDao clienteDao;
    @Test
    public void cadastraTest() throws Exception{
        clienteDao = new ClienteDao();
        Cliente cliente = cadastraCliente("Lis","28");
        buscarCliente(cliente);
        excluirCliente(cliente);
    }

    @Test
    public void buscarTest() throws Exception{
        clienteDao = new ClienteDao();
        Cliente cliente = cadastraCliente("Lis","28");
        buscarCliente(cliente);
        excluirCliente(cliente);
    }
    @Test
    public void excluirTest() throws Exception{
        clienteDao = new ClienteDao();
        Cliente cliente = cadastraCliente("Lis","28");
        buscarCliente(cliente);
        excluirCliente(cliente);
    }
    @Test
    public void alterarTest() throws Exception{
        clienteDao = new ClienteDao();
        Cliente cliente = cadastraCliente("Lis","28");
        buscarCliente(cliente);
        cliente.setNome("Leo");
        cliente.setCodigo("32");
        Integer qndUpdate = clienteDao.alterar(cliente);
        assertTrue(qndUpdate == 1);
        Cliente clienteAntigo = clienteDao.buscarPorCodigo("28");
        assertNull(clienteAntigo);
        Cliente clienteAlterado = clienteDao.buscarPorCodigo("32");
        buscarCliente(clienteAlterado);
        assertEquals(cliente.getId(), clienteAlterado.getId());
        excluirCliente(cliente);
    }
    @Test
    public void buscarTodosTest() throws Exception{
        clienteDao = new ClienteDao();
        Cliente cliente = cadastraCliente("Lis","28");
        Cliente cliente2 = cadastraCliente("Leo","32");
        List<Cliente> list = clienteDao.listarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());
        int qndExcluido = 0;
        for (Cliente c : list){
            clienteDao.excluir(c);
            qndExcluido++;
        }
        assertEquals(list.size(),qndExcluido);
        list = clienteDao.listarTodos();
        assertEquals(0,list.size());
    }
    //TODO:funções do teste
    private void buscarCliente(Cliente cliente) throws Exception {
        Cliente clienteVerificado = clienteDao.buscarPorCodigo(cliente.getCodigo());
        assertNotNull(clienteVerificado);
        assertEquals(clienteVerificado.getCodigo(), cliente.getCodigo());
        assertEquals(clienteVerificado.getNome(), cliente.getNome());
    }

    private Cliente cadastraCliente(String nome, String codigo) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCodigo(codigo);
        Integer qndCadastro = clienteDao.cadastrar(cliente);
        assertTrue(qndCadastro == 1);
        return clienteDao.buscarPorCodigo(codigo);
    }
    private void excluirCliente(Cliente cliente) throws Exception {
        Integer countDel = clienteDao.excluir(cliente);
        assertTrue(countDel == 1);
    }
}
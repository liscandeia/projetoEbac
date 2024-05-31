package test.main.br.com.liscandeia;

import main.br.com.liscandeia.dao.produto.IProdutoDao;
import main.br.com.liscandeia.dao.produto.ProdutoDao;
import main.br.com.liscandeia.domain.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class ProdutoTest {
    IProdutoDao produtoDao;
    @Test
    public void cadastrarTest() throws Exception {
        produtoDao = new ProdutoDao();
        Produto produto = cadastrarProduto("TV",1999.00,"28");
        buscarProduto(produto);
        excluirProduto(produto);
    }
    @Test
    public void buscarTest() throws Exception{
        produtoDao = new ProdutoDao();
        Produto produto = cadastrarProduto("TV",1999.00,"28");
        buscarProduto(produto);
        excluirProduto(produto);
    }
    @Test
    public void excluirTest() throws Exception{
        produtoDao = new ProdutoDao();
        Produto produto = cadastrarProduto("TV",1999.00,"28");
        buscarProduto(produto);
        excluirProduto(produto);
    }

    @Test
    public void alterarTest() throws Exception{
        produtoDao = new ProdutoDao();
        Produto produto = cadastrarProduto("TV",1999.99,"28");
        buscarProduto(produto);
        produto.setNome("DVD");
        produto.setValor(199.99);
        produto.setCodigo("32");
        Integer qndUpdate = produtoDao.alterar(produto);
        assertTrue(qndUpdate == 1);
        Produto produtoAntigo = produtoDao.buscarPorCodigo("28");
        assertNull(produtoAntigo);
        Produto produtoAlterado = produtoDao.buscarPorCodigo("32");
        buscarProduto(produtoAlterado);
        assertEquals(produto.getId(), produtoAlterado.getId());
        excluirProduto(produto);
    }
    @Test
    public void buscarTodosTest() throws Exception{
        produtoDao = new ProdutoDao();
        Produto produto = cadastrarProduto("TV",1999.99,"28");
        Produto produto2 = cadastrarProduto("DVD",199.99,"32");
        List<Produto> list = produtoDao.listarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());
        int qndExcluido = 0;
        for (Produto p : list){
            produtoDao.excluir(p);
            qndExcluido++;
        }
        assertEquals(list.size(),qndExcluido);
        list = produtoDao.listarTodos();
        assertEquals(0,list.size());
    }

    //TODO: Funções do teste

    private void excluirProduto(Produto produto) throws Exception {
        Integer countDel = produtoDao.excluir(produto);
        assertTrue(countDel == 1);
    }

    private void buscarProduto(Produto produto) throws Exception {
       Produto produtoVerificado = produtoDao.buscarPorCodigo(produto.getCodigo());
       assertNotNull(produtoVerificado);
       assertEquals(produtoVerificado.getNome(), produto.getNome());
       assertEquals(produtoVerificado.getValor(), produto.getValor());
       assertEquals(produtoVerificado.getCodigo(), produto.getCodigo());
    }

    private Produto cadastrarProduto(String nome, Double valor, String codigo)throws Exception {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setCodigo(codigo);
        Integer qntProduto = produtoDao.cadastrar(produto);
        assertTrue(qntProduto == 1);
        return produtoDao.buscarPorCodigo(codigo);
    }
}

package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable {

	private static final long serialVersionUID = -423846883924093712L;

	@Inject
	private NotaFiscalDao notaFiscalDao;

	@Inject
	private ProdutoDao produtoDao;
	
	private Long idProduto;
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	
	public void grava() {

		notaFiscalDao.adiciona(getNotaFiscal());
		cleanBean();
		notaFiscal = new NotaFiscal();
	}

	public void addItem() {
		
		Produto produto = produtoDao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		item.setNotaFiscal(notaFiscal);
		notaFiscal.getItens().add(item);
		cleanBean();
	}

	private void cleanBean() {
		
		item = new Item();
		idProduto = null;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}

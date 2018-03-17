package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@RequestScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -2099759752931142946L;

	@Inject
	private ProdutoDao dao;

	private Produto produto = new Produto();
	private List<Produto> produtos;

	public void grava() {

		if (produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		produto = new Produto();
		produtos = dao.listaTodos();
	}

	public void cancela() {
		produto = new Produto();
	}

	public void remove(Produto produto) {

		dao.remove(produto);
		produtos = dao.listaTodos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			produtos = dao.listaTodos();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}

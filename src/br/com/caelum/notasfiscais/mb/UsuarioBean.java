package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -3775700202796036186L;

	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;

	@Inject
	private UsuarioDao dao;

	private Usuario usuario = new Usuario();

	public String logar() {

		if (dao.existe(usuario)) {

			usuarioLogadoBean.logar(usuario);
			return "produto?faces-redirect=true";
		} else {

			usuarioLogadoBean.deslogar();
			usuario = new Usuario();
			return "login";
		}
	}

	public String deslogar() {

		usuarioLogadoBean.deslogar();
		usuario = new Usuario();
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

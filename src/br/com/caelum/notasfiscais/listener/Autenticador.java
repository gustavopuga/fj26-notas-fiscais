package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autenticador implements PhaseListener {

	private static final long serialVersionUID = -1018554329308368912L;

	@Inject
	private FacesContext context;
	
	@Inject
	private NavigationHandler handler;
	
	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
		if ("/index.xhtml".equals(context.getViewRoot().getViewId())){
			return;
		}
		
		if (!usuarioLogadoBean.isLogado()){
			handler.handleNavigation(context, null, "index?faces-redirect=true");
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}

package es.uma.g6.vistas;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import es.uma.g6.Usuario;
import es.uma.g6.ejb.gestionAdministrador;
import exceptions.AdministracionException; 


@Named(value = "login")
@RequestScoped
public class LoginBean {

    @Inject
    private gestionAdministrador ga;

    @Inject
    private InfoSesion sesion;
    @Inject
    private Usuario usuario;

    /**
     * Creates a new instance of login
     */
    public void Login() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String entrar() {
        try {
            ga.login(usuario.getNombre(), usuario.getContraseña());
            sesion.setUsuario(ga.refrescarUsuario(usuario));
            return "contactos.xhtml";

        } catch (AdministracionException e) {
            FacesMessage fm = new FacesMessage("ERROR");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } 
        return null;
    }

}


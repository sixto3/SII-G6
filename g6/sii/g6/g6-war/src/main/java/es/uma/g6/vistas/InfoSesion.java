/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.g6.vistas;

import es.uma.g6.*;
import es.uma.g6.ejb.gestionAdministrador;
import exceptions.AdministracionException;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author francis
 */
@Named(value = "infoSesion")
@SessionScoped
public class InfoSesion implements Serializable {

    @Inject
    private gestionAdministrador negocio;
    private Usuario usuario;
    
    /**
     * Creates a new instance of InfoSesion
     */
    public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
    public synchronized Cliente getContactos()
    {
        if (usuario != null)
        {
            return usuario.getCliente();
        }
        return null;
    }
    
    public synchronized String invalidarSesion()
    {
        if (usuario != null)
        {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "login.xhtml";
    }
    
    public synchronized void refrescarUsuario()
    {
        try {
        if (usuario != null)
        {
            usuario = negocio.refrescarUsuario(usuario);
            System.out.println(getContactos());
        } 
        }
        catch (AdministracionException e) {
            // TODO
        }
    }
    
}

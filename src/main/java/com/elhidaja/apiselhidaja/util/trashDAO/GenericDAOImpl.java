package com.elhidaja.apiselhidaja.util.trashDAO;

public abstract class GenericDAOImpl <T,K> implements GenericDAO<T,K>{
    protected String mensaje;
    protected boolean exito;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
          this.exito = exito;
    }
      
}

package com.example.chatpc;

public class Mensaje {

    private String mensaje;
    private String nombre;
    private String fotoperfil;
    private String tipomensaje;
    private String hora;
    private String urlfoto;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String nombre, String fotoperfil, String tipomensaje, String hora) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.fotoperfil = fotoperfil;
        this.tipomensaje = tipomensaje;
        this.hora = hora;
    }

    public Mensaje(String mensaje, String nombre, String fotoperfil, String tipomensaje, String hora, String urlfoto) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.fotoperfil = fotoperfil;
        this.tipomensaje = tipomensaje;
        this.hora = hora;
        this.urlfoto = urlfoto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoperfil() {
        return fotoperfil;
    }

    public void setFotoperfil(String fotoperfil) {
        this.fotoperfil = fotoperfil;
    }

    public String getTipomensaje() {
        return tipomensaje;
    }

    public void setTipomensaje(String tipomensaje) {
        this.tipomensaje = tipomensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}

/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * NRC: 6112
 *
 * Sistema: CHATP&C
 * Creado 16/07/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package com.example.chatpc;

/**
 * Clase que representa un mensaje del chat.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Mensaje {

    private String mensaje;
    private String nombre;
    private String fotoPerfil;
    private String tipoMensaje;
    private String hora;
    private String urlFoto;

    /**
     * Constructor vacío de la clase Mensaje.
     */
    public Mensaje() {
    }

    /**
     * Constructor con parámetros de la clase Mensaje para enviar texto.
     *
     * @param mensaje Contenido del mensaje
     * @param nombre Nombre del usuario que envia el mensaje
     * @param fotoPerfil Foto de perfil del usuario que envia el mensaje
     * @param tipoMensaje Tipo de mensaje enviado
     * @param hora Hora del envio del mensaje
     */
    public Mensaje(String mensaje, String nombre, String fotoPerfil, String tipoMensaje, String hora) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
        this.tipoMensaje = tipoMensaje;
        this.hora = hora;
    }

    /**
     * Constructor con parámetros de la clase Mensaje para enviar una imagen.
     *
     * @param mensaje Contenido del mensaje
     * @param nombre Nombre del usuario que envia el mensaje
     * @param fotoPerfil Foto de perfil del usuario que envia el mensaje
     * @param tipoMensaje Tipo de mensaje enviado
     * @param hora Hora del envio del mensaje
     * @param urlFoto Foro enviada
     */
    public Mensaje(String mensaje, String nombre, String fotoPerfil, String tipoMensaje, String hora, String urlFoto) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
        this.tipoMensaje = tipoMensaje;
        this.hora = hora;
        this.urlFoto = urlFoto;
    }

    /**
     * Método que obtiene el valor del atributo Mensaje de la clase Mensaje.
     *
     * @return Contenido del Mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Método que setea el valor del atributo Mensaje de la clase Mensaje.
     *
     * @param mensaje Contenido del mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Método que obtiene el valor del atributo Nombre de la clase Mensaje.
     *
     * @return Nombre del usuario que envia el mensaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que setea el valor del atributo Nombre de la clase Mensaje.
     *
     * @param nombre Nombre del usuario que envia el mensaje.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el valor del atributo fotoPerfil de la clase Mensaje.
     *
     * @return Foto de perfil del usuario que envia el mensaje.
     */
    public String getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * Método que setea el valor del atributo fotoPerfil de la clase Mensaje.
     *
     * @param fotoPerfil Foto de perfil del usuario que envia el mensaje.
     */
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    /**
     * Método que obtiene el valor del atributo tipoMensaje de la clase Mensaje.
     *
     * @return Tipo de mensaje que se envia.
     */
    public String getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * Método que setea el valor del atributo tipoMensaje de la clase Mensaje.
     *
     * @param tipoMensaje Tipo de mensaje que se envia.
     */
    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    /**
     * Método que obtiene el valor del atributo Hora de la clase Mensaje.
     *
     * @return Hora de envío del mensaje.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Método que setea el valor del atributo Hora de la clase Mensaje.
     *
     * @param hora Hora de envío del mensaje.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Método que obtiene el valor del atributo urlFoto de la clase Mensaje.
     *
     * @return Foto enviada.
     */
    public String getUrlFoto() {
        return urlFoto;
    }

    /**
     * Método que setea el valor del atributo urlFoto de la clase Mensaje.
     *
     * @param urlFoto Foto enviada por el chat.
     */
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}

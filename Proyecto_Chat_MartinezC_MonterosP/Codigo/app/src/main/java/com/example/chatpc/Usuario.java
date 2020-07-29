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
 * Clase que representa a un usuario del chat.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Usuario {
    String id;
    String foto;
    String nombre;
    String apellido;

    /**
     * Constructor vacío de la clase usuario.
     */
    public Usuario(){

    }

    /**
     * Constructor con parámetros de la clase Usuario.
     * @param id Id del usuario
     * @param foto Foto del Usuario
     * @param nombre Nombre del Usuario
     * @param apellido Apellido del Usuario
     */
    public Usuario(String id, String foto, String nombre, String apellido) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Método que obtiene el valor del atributo Id de la clase Usuario.
     *
     * @return id del Usuario.
     */
    public String getId() {
        return id;
    }

    /**
     * Método que setea el valor del atributo Id de la clase Usuario.
     *
     * @param id del Usuario
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método que obtiene el valor del atributo foto de la clase Usuario.
     *
     * @return foto del Usuario.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Método que setea el valor del atributo Foto de la clase Usuario.
     *
     * @param foto del Usuario
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Método que obtiene el valor del atributo Nombre de la clase Usuario.
     *
     * @return nombre del Usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que setea el valor del atributo Nombre de la clase Usuario.
     *
     * @param nombre del Usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el valor del atributo Apellido de la clase Usuario.
     *
     * @return apellido del Usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que setea el valor del atributo Apellido de la clase Usuario.
     *
     * @param apellido del Usuario
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}

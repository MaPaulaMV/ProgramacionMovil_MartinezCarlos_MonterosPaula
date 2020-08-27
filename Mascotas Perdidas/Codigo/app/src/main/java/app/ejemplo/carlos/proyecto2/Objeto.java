package app.ejemplo.carlos.proyecto2;

public class Objeto {

    private String nombre_mascota;
    private String mapa;
    private String desc;
    private  String nomPersona;
    private  String numCelular;
    private  String email;
    private  String url;
    private  String id_mascota;
    private String tipo;
    private String user_id;

    public Objeto(String nombre, String lugar, String desc, String nomPersona, String numCelular, String email, String id1, String url1, String tipo1) {
        this.nombre_mascota = nombre;
        this.mapa = lugar;
        this.desc = desc;
        this.nomPersona = nomPersona;
        this.numCelular = numCelular;
        this.email = email;
        this.id_mascota =id1;
        this.url=url1;
        this.tipo=tipo1;
    }
    public Objeto(String nombre, String lugar, String desc, String nomPersona, String numCelular, String email, String id1, String url1, String tipo1, String u_id) {
        this.nombre_mascota = nombre;
        this.mapa = lugar;
        this.desc = desc;
        this.nomPersona = nomPersona;
        this.numCelular = numCelular;
        this.email = email;
        this.id_mascota =id1;
        this.url=url1;
        this.tipo=tipo1;
        this.user_id = u_id;
    }

    public Objeto() {
    }
    public String getId_mascota() { return id_mascota;
    }

    public void setId_mascota(String id_mascota) {this.id_mascota = id_mascota;
    }
    public String getNomPersona() {
        return nomPersona;
    }

    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getTipo() {      return tipo;    }

    public void setTipo(String tipo) {        this.tipo = tipo;    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

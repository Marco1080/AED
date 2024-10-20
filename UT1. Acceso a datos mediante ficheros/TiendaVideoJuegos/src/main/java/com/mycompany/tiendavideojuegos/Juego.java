package com.mycompany.tiendavideojuegos;

public class Juego {

    private String nombre;
    private String plataforma;
    private String desarrollador;
    private String genero;
    private String numeroJugadores;
    private String clasificacionEdad;
    private String fechaLanzamiento;
    private int criticasPositivas;
    private int criticasNeutrales;
    private int criticasNegativas;
    private int resenasPositivas;
    private int resenasNeutrales;
    private int resenasNegativas;
    private double metaScore;
    private double userScore;

    public Juego(String nombre, String plataforma, String desarrollador, String genero, String numeroJugadores, String clasificacionEdad, String fechaLanzamiento, int criticasPositivas, int criticasNeutrales, int criticasNegativas, int resenasPositivas, int resenasNeutrales, int resenasNegativas, double metaScore, double userScore) {
        this.nombre = ajustarCadena(nombre);
        this.plataforma = ajustarCadena(plataforma);
        this.desarrollador = ajustarCadena(desarrollador);
        this.genero = ajustarCadena(genero);
        this.numeroJugadores = ajustarCadena(numeroJugadores);
        this.clasificacionEdad = ajustarCadena(clasificacionEdad);
        this.fechaLanzamiento = fechaLanzamiento;
        this.criticasPositivas = criticasPositivas;
        this.criticasNeutrales = criticasNeutrales;
        this.criticasNegativas = criticasNegativas;
        this.resenasPositivas = resenasPositivas;
        this.resenasNeutrales = resenasNeutrales;
        this.resenasNegativas = resenasNegativas;
        this.metaScore = metaScore;
        this.userScore = userScore;
    }

    private String ajustarCadena(String cadena) {
        if (cadena.length() > 15) {
            return cadena.substring(0, 15);
        } else {
            return String.format("%-15s", cadena);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = ajustarCadena(nombre);
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = ajustarCadena(plataforma);
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = ajustarCadena(desarrollador);
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = ajustarCadena(genero);
    }

    public String getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(String numeroJugadores) {
        this.numeroJugadores = ajustarCadena(numeroJugadores);
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = ajustarCadena(clasificacionEdad);
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getCriticasPositivas() {
        return criticasPositivas;
    }

    public void setCriticasPositivas(int criticasPositivas) {
        this.criticasPositivas = criticasPositivas;
    }

    public int getCriticasNeutrales() {
        return criticasNeutrales;
    }

    public void setCriticasNeutrales(int criticasNeutrales) {
        this.criticasNeutrales = criticasNeutrales;
    }

    public int getCriticasNegativas() {
        return criticasNegativas;
    }

    public void setCriticasNegativas(int criticasNegativas) {
        this.criticasNegativas = criticasNegativas;
    }

    public int getResenasPositivas() {
        return resenasPositivas;
    }

    public void setResenasPositivas(int resenasPositivas) {
        this.resenasPositivas = resenasPositivas;
    }

    public int getResenasNeutrales() {
        return resenasNeutrales;
    }

    public void setReseñasNeutrales(int resenasNeutrales) {
        this.resenasNeutrales = resenasNeutrales;
    }

    public int getResenasNegativas() {
        return resenasNegativas;
    }

    public void setReseñasNegativas(int resenasNegativas) {
        this.resenasNegativas = resenasNegativas;
    }

    public double getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(double metaScore) {
        this.metaScore = metaScore;
    }

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

    @Override
    public String toString() {
        return "Juego{"
                + "Nombre='" + nombre + '\''
                + ", Plataforma='" + plataforma + '\''
                + ", Desarrollador='" + desarrollador + '\''
                + ", Género='" + genero + '\''
                + ", Número de Jugadores='" + numeroJugadores + '\''
                + ", Clasificación de Edad='" + clasificacionEdad + '\''
                + ", Fecha de Lanzamiento='" + fechaLanzamiento + '\''
                + ", Críticas Positivas=" + criticasPositivas
                + ", Críticas Neutrales=" + criticasNeutrales
                + ", Críticas Negativas=" + criticasNegativas
                + ", Reseñas Positivas=" + resenasPositivas
                + ", Reseñas Neutrales=" + resenasNeutrales
                + ", Reseñas Negativas=" + resenasNegativas
                + ", Meta Score=" + metaScore
                + ", User Score=" + userScore
                + '}';
    }
}

package com.koolenwijkstra.siree.geoguessswipe;

public class Plaatje {
    private int foto;
    private boolean isInEuropa;

    public Plaatje(int foto, boolean isInEuropa){
        this.foto = foto;
        this.isInEuropa = isInEuropa;
    }

    public int getFoto() {
        return foto;
    }

    public boolean isInEuropa() {
        return isInEuropa;
    }
}

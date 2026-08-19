package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import java.util.List;

public class PretraziGostaSO extends ApstraktnaGenerickaOperacija{

    private List<ApstraktniDomenskiObjekat> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Gost g = (Gost) obj;
        String kriterijum = g.getIme();
        String uslov = " JOIN mesto ON gost.idMesto = mesto.idMesto"
                     + " WHERE gost.ime LIKE '%" + kriterijum + "%'"
                     + " OR gost.prezime LIKE '%" + kriterijum + "%'"
                     + " OR mesto.naziv LIKE '%" + kriterijum + "%'";
        lista = broker.getAll(g, uslov);
    }
    
    public List<ApstraktniDomenskiObjekat> getLista(){
        return lista;
    }    
}

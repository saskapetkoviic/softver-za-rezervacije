package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import java.util.List;

public class UcitajGosteSO extends ApstraktnaGenerickaOperacija{
    
    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Gost g = (Gost)obj;
        String uslov = " JOIN mesto ON gost.idMesto = mesto.idMesto";
        lista = broker.getAll(g, uslov);
    }
    
    public List<ApstraktniDomenskiObjekat> getLista(){
        return lista;
    }
    
}

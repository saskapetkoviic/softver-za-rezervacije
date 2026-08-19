package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Mesto;
import java.util.List;

public class UcitajMestaSO extends ApstraktnaGenerickaOperacija{

    private List<ApstraktniDomenskiObjekat> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        lista = broker.getAll((Mesto)obj, null);
    }
    
    public List<ApstraktniDomenskiObjekat> getLista(){
        return lista;
    }
    
}

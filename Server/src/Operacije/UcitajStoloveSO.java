package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Sto;
import java.util.List;

public class UcitajStoloveSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        lista = broker.getAll((Sto)obj, null);
    }
    
    public List<ApstraktniDomenskiObjekat> getLista(){
        return lista;
    }   
}

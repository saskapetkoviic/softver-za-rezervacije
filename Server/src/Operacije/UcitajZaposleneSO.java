package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Zaposleni;
import java.util.List;

public class UcitajZaposleneSO extends ApstraktnaGenerickaOperacija{

    private List<ApstraktniDomenskiObjekat> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
      lista = broker.getAll((Zaposleni)obj, null);
    }
    
    public List<ApstraktniDomenskiObjekat> getLista(){
        return lista;
    }
    
}

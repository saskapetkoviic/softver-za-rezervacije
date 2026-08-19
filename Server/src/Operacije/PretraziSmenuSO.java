package Operacije;

import Model.ApstraktniDomenskiObjekat;
import Model.Smena;
import java.util.List;

public class PretraziSmenuSO extends ApstraktnaGenerickaOperacija {
    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        lista = broker.getAll((Smena) obj, null);
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }
}
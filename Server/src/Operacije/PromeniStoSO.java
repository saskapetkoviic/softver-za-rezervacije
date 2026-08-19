package Operacije;

import Model.Sto;

public class PromeniStoSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Sto)){
            throw new Exception("Objekat nije tipa sto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Sto sto = (Sto)obj;
        broker.edit(sto);
    }
    
}

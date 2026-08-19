package Operacije;

import Model.Mesto;

public class KreirajMestoSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Mesto)) {
            throw new Exception("Objekat nije tipa Mesto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Mesto mesto = (Mesto)obj;
        broker.add(mesto);
    }
    
    
}

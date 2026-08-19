package Operacije;

import Model.Smena;

public class UbaciSmenuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Smena)){
            throw new Exception("Objekat nije tipa Smena.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Smena smena = (Smena)obj;
        broker.add(smena);
    }
    
}

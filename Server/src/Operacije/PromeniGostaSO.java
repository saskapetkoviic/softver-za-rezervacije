package Operacije;

import Model.Gost;

public class PromeniGostaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Gost)){
            throw new Exception("Objekat nije tipa Gost.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Gost gost = (Gost)obj;
        broker.edit(gost);
    }
}

package Operacije;

import Model.Rezervacija;
import Model.StavkaRezervacije;

public class KreirajRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Rezervacija)){
            throw new Exception("Objekat nije tipa Rezervacija.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Rezervacija rezervacija = (Rezervacija)obj;
        
        int id = broker.addAndReturnId(rezervacija);
        rezervacija.setIdRezervacija(id);
        
        for(StavkaRezervacije stavka: rezervacija.getStavke()){
            stavka.setIdRezervacija(id);
            broker.add(stavka);
        }
    }
}

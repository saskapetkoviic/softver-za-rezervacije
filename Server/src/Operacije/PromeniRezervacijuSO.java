package Operacije;

import Model.Rezervacija;
import Model.StavkaRezervacije;

public class PromeniRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(!(obj instanceof Rezervacija)){
            throw new Exception("Objekat nije tipa Rezervacija.");
        }
    }

@Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Rezervacija rezervacija = (Rezervacija) obj;

        broker.edit(rezervacija);

        StavkaRezervacije pomocna = new StavkaRezervacije();
        pomocna.setIdRezervacija(rezervacija.getIdRezervacija());
        obrisiStareStavke(rezervacija.getIdRezervacija());

        for (StavkaRezervacije stavka : rezervacija.getStavke()) {
            stavka.setIdRezervacija(rezervacija.getIdRezervacija());
            broker.add(stavka);
        }
    }

    private void obrisiStareStavke(int idRezervacija) throws Exception {
        java.sql.Connection conn = Repository.DB.DbConnectionFactory.getInstance().getConnection();
        String upit = "DELETE FROM stavkarezervacije WHERE idRezervacija=" + idRezervacija;
        try (java.sql.Statement s = conn.createStatement()) {
            s.executeUpdate(upit);
        }
    }
    
}

package Model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ZaposleniSmena implements ApstraktniDomenskiObjekat{
    private Zaposleni zaposleni;
    private Smena smena;
    private LocalDateTime datum;
    private String prisutnost;
    private double ocena;
    
    public ZaposleniSmena(){
        
    }
    
    public ZaposleniSmena(Zaposleni zaposleni, Smena smena, LocalDateTime datum, String prisutnost, double ocena){
        this.zaposleni = zaposleni;
        this.smena = smena;
        this.datum = datum;
        this.prisutnost = prisutnost;
        this.ocena = ocena;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getPrisutnost() {
        return prisutnost;
    }

    public void setPrisutnost(String prisutnost) {
        this.prisutnost = prisutnost;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return zaposleni + " - " + smena;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.zaposleni);
        hash = 79 * hash + Objects.hashCode(this.smena);
        hash = 79 * hash + Objects.hashCode(this.datum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZaposleniSmena other = (ZaposleniSmena) obj;
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }
    
    @Override
    public String vratiNazivTabele() {
        return "zaposlenismena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List <ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            LocalDateTime datum = rs.getTimestamp("datum").toLocalDateTime();
            String prisutnost = rs.getString("prisutnost");
            double ocena = rs.getDouble("ocena");
            
            int idZaposleni = rs.getInt("idZaposleni");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            String sifra = rs.getString("sifra");
            Zaposleni z = new Zaposleni(idZaposleni, ime,prezime,korisnickoIme,sifra);
            
            int idSmena = rs.getInt("idSmena");
            LocalDateTime pocetak = rs.getTimestamp("vremePocetka").toLocalDateTime();
            LocalDateTime zavrsetak = rs.getTimestamp("vremeZavrsetka").toLocalDateTime();
            Smena s = new Smena(idSmena,pocetak,zavrsetak);
            
            ZaposleniSmena zs = new ZaposleniSmena(z,s, datum, prisutnost, ocena);
            lista.add(zs);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idZaposleni, idSmena, datum, prisutnost, ocena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return zaposleni.getIdZaposleni() + ", " + smena.getIdSmena() + ", '" + datum + "', '" + prisutnost + "', " + ocena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idZaposleni=" + zaposleni.getIdZaposleni() + " AND idSmena=" + smena.getIdSmena() + " AND datum='" + datum + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       return "idZaposleni=" + zaposleni.getIdZaposleni() + ", idSmena=" + smena.getIdSmena() + ", datum='" + datum + "', prisutnost='" + prisutnost + "', ocena=" + ocena;
    }
}

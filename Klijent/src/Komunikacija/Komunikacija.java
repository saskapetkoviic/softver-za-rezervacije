package Komunikacija;

import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import Model.Rezervacija;
import Model.Smena;
import Model.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Komunikacija {
    private Socket soket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    private Komunikacija(){
        
    }
    
    public static Komunikacija getInstance(){
        if(instance==null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void konekcija(){
        try{
            soket = new Socket("localhost",9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        }catch(IOException ex){
            ex.printStackTrace();
            System.out.println("Server nije pokrenut!"); 
        }
    }
    
    public Zaposleni login(String korisnickoIme, String sifra) throws Exception{
        Zaposleni z = new Zaposleni();
        z.setKorisnickoIme(korisnickoIme);
        z.setSifra(sifra);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor)primalac.primi();
        
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
        return (Zaposleni) odgovor.getOdgovor();
    }
    
    public List<ApstraktniDomenskiObjekat> ucitajMesta() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor)primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
        return (List<ApstraktniDomenskiObjekat>)odgovor.getOdgovor();
    }
    
    public List<ApstraktniDomenskiObjekat> ucitajStolove() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STOLOVE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
        return (List<ApstraktniDomenskiObjekat>) odgovor.getOdgovor();
    }
    
    public List<ApstraktniDomenskiObjekat> ucitajZaposlene() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
        return (List<ApstraktniDomenskiObjekat>) odgovor.getOdgovor();
    }
    
    public List<ApstraktniDomenskiObjekat> ucitajGoste() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_GOSTE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
        return (List<ApstraktniDomenskiObjekat>) odgovor.getOdgovor();
    }
    
    public void kreirajGosta(Gost g) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_GOSTA, g);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
    }
    
    public void promeniGosta(Gost g) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_GOSTA, g);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
    }
    
     public void obrisiGosta(Gost g) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_GOSTA, g);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
    }
     
     public void ubaciSmenu(Smena s) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UBACI_SMENU, s);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
    }
     
     public List<ApstraktniDomenskiObjekat> pretraziSmenu() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_SMENU, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() != null) throw odgovor.getGreska();
        return (List<ApstraktniDomenskiObjekat>) odgovor.getOdgovor();
    }
     
     public void kreirajRezervaciju(Rezervacija r) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_REZERVACIJU, r);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
    }
     
     public void promeniRezervaciju(Rezervacija r) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_REZERVACIJU, r);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getGreska() != null){
            throw odgovor.getGreska();
        }
    }
    
    public List<ApstraktniDomenskiObjekat> pretraziGosta(Gost g) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_GOSTA, g);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() != null) throw odgovor.getGreska();
        return (List<ApstraktniDomenskiObjekat>) odgovor.getOdgovor();
    }

    public List<ApstraktniDomenskiObjekat> pretraziRezervaciju(Rezervacija r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_REZERVACIJU, r);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() != null) throw odgovor.getGreska();
        return (List<ApstraktniDomenskiObjekat>) odgovor.getOdgovor();
    }
}

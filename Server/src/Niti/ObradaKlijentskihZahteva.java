package Niti;

import Komunikacija.Odgovor;
import Komunikacija.Posiljalac;
import Komunikacija.Primalac;
import Komunikacija.Zahtev;
import Model.Gost;
import Model.Rezervacija;
import Model.Smena;
import Model.Zaposleni;
import Controller.Controller;
import java.net.Socket;

public class ObradaKlijentskihZahteva extends Thread {
    private Socket socket;
    private Primalac primalac;
    private Posiljalac posiljalac;
    private boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        this.primalac = new Primalac(socket);
        this.posiljalac = new Posiljalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();

                if (zahtev == null) {
                    prekini();
                    return;
                }

                try {
                    switch (zahtev.getOperacija()) {
                        case LOGIN:
                            Zaposleni z = (Zaposleni) zahtev.getParametar();
                            odgovor.setOdgovor(Controller.getInstance().prijaviZaposleni(z));
                            break;
                        case UCITAJ_MESTA:
                            odgovor.setOdgovor(Controller.getInstance().ucitajMesta());
                            break;
                        case UCITAJ_STOLOVE:
                            odgovor.setOdgovor(Controller.getInstance().ucitajStolove());
                            break;
                        case UCITAJ_ZAPOSLENE:
                            odgovor.setOdgovor(Controller.getInstance().ucitajZaposlene());
                            break;
                        case KREIRAJ_GOSTA:
                            Controller.getInstance().kreirajGosta((Gost) zahtev.getParametar());
                            break;
                        case PROMENI_GOSTA:
                            Controller.getInstance().promeniGosta((Gost) zahtev.getParametar());
                            break;
                        case OBRISI_GOSTA:
                            Controller.getInstance().obrisiGosta((Gost) zahtev.getParametar());
                            break;
                        case UCITAJ_GOSTE:
                            odgovor.setOdgovor(Controller.getInstance().ucitajGoste());
                            break;
                        case PRETRAZI_GOSTA:
                            odgovor.setOdgovor(Controller.getInstance().pretraziGosta((Gost) zahtev.getParametar()));
                            break;
                        case UBACI_SMENU:
                            Controller.getInstance().ubaciSmenu((Smena) zahtev.getParametar());
                            break;
                        case PRETRAZI_SMENU:
                            odgovor.setOdgovor(Controller.getInstance().pretraziSmenu());
                            break;
                        case KREIRAJ_REZERVACIJU:
                            Controller.getInstance().kreirajRezervaciju((Rezervacija) zahtev.getParametar());
                            break;
                        case PROMENI_REZERVACIJU:
                            Controller.getInstance().promeniRezervaciju((Rezervacija) zahtev.getParametar());
                            break;
                        case PRETRAZI_REZERVACIJU:
                            odgovor.setOdgovor(Controller.getInstance().pretraziRezervaciju((Rezervacija) zahtev.getParametar()));
                            break;
                    }
                } catch (Exception ex) {
                    odgovor.setGreska(ex);
                }

                posiljalac.posalji(odgovor);

            } catch (Exception ex) {
                ex.printStackTrace();
                prekini();
                return;
            }
        }
    }

    public void prekini() {
        kraj = true;
    }
}
package Kontroleri;

import Forme.IzmeniRezervacijuForma;
import Forme.modeli.ModelTabeleStavke;
import Komunikacija.Komunikacija;
import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import Model.StavkaRezervacije;
import Model.Sto;
import Model.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class KreirajRezervacijuController {
    private IzmeniRezervacijuForma forma;
    private ModelTabeleStavke modelStavke;

    public KreirajRezervacijuController(IzmeniRezervacijuForma forma) {
        this.forma = forma;
        this.modelStavke = new ModelTabeleStavke();
        forma.getTblStavke().setModel(modelStavke);
        addActionsListener();
    }

    public void otvoriFormu() {
        napuniComboBoxove();
        forma.setVisible(true);
    }

    private void napuniComboBoxove() {
        try {
            List<ApstraktniDomenskiObjekat> zaposleni = Komunikacija.getInstance().ucitajZaposlene();
            forma.getCmbZaposleni().setModel(new DefaultComboBoxModel(zaposleni.toArray()));

            List<ApstraktniDomenskiObjekat> gosti = Komunikacija.getInstance().ucitajGoste();
            forma.getCmbGost().setModel(new DefaultComboBoxModel(gosti.toArray()));

            List<ApstraktniDomenskiObjekat> stolovi = Komunikacija.getInstance().ucitajStolove();
            forma.getCmbSto().setModel(new DefaultComboBoxModel(stolovi.toArray()));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
        }
    }

    private void addActionsListener() {
        forma.getBtnDodajStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }
        });

        forma.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }
        });
    }

    private void dodajStavku() {
        try {
            Sto sto = (Sto) forma.getCmbSto().getSelectedItem();
            double popust = Double.parseDouble(forma.getTxtPopust().getText());

            double cena = sto.getCenaRezervacije();
            double iznos = cena * (100 - popust) / 100;

            int rb = modelStavke.getStavke().size() + 1;

            StavkaRezervacije stavka = new StavkaRezervacije(0, rb, cena, popust, iznos, "", sto);
            modelStavke.dodajStavku(stavka);

            forma.getTxtUkupanIznos().setText(String.valueOf(modelStavke.getUkupanIznos()));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Proverite podatke o stavci.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
        }
    }

   private void sacuvaj() {
        try {
            Zaposleni zaposleni = (Zaposleni) forma.getCmbZaposleni().getSelectedItem();
            Gost gost = (Gost) forma.getCmbGost().getSelectedItem();
            String status = forma.getTxtStatus().getText();

            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            java.time.LocalDateTime datumDolaska = java.time.LocalDateTime.parse(forma.getTxtDatumDolaska().getText(), formatter);
            java.time.LocalDateTime datumRezervacije = java.time.LocalDateTime.now();

            List<StavkaRezervacije> stavke = modelStavke.getStavke();

            if (stavke.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Dodajte bar jedan sto.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double ukupanIznos = modelStavke.getUkupanIznos();

            Model.Rezervacija rezervacija = new Model.Rezervacija(0, datumRezervacije, datumDolaska, status, ukupanIznos, zaposleni, gost);
            rezervacija.setStavke(stavke);

            Komunikacija.getInstance().kreirajRezervaciju(rezervacija);

            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio rezervaciju.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti rezervaciju.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
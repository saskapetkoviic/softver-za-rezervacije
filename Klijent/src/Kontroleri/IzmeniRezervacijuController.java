package Kontroleri;

import Forme.IzmeniRezervacijuForma;
import Forme.modeli.ModelTabeleStavke;
import Komunikacija.Komunikacija;
import Model.ApstraktniDomenskiObjekat;
import Model.Gost;
import Model.Rezervacija;
import Model.StavkaRezervacije;
import Model.Sto;
import Model.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class IzmeniRezervacijuController {
    private IzmeniRezervacijuForma forma;
    private Rezervacija rezervacija;
    private ModelTabeleStavke modelStavke;

    public IzmeniRezervacijuController(IzmeniRezervacijuForma forma, Rezervacija rezervacija) {
        this.forma = forma;
        this.rezervacija = rezervacija;
        this.modelStavke = new ModelTabeleStavke();
        forma.getTblStavke().setModel(modelStavke);
        addActionsListener();
    }

    public void otvoriFormu() {
        napuniComboBoxove();
        popuniPolja();
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

    private void popuniPolja() {
        forma.getCmbZaposleni().setSelectedItem(rezervacija.getZaposleni());
        forma.getCmbGost().setSelectedItem(rezervacija.getGost());
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        forma.getTxtDatumDolaska().setText(rezervacija.getDatumVremeDolaska().format(formatter));
        forma.getTxtStatus().setText(rezervacija.getStatus());
        forma.getTxtUkupanIznos().setText(String.valueOf(rezervacija.getUkupanIznos()));
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

            List<StavkaRezervacije> stavke = modelStavke.getStavke();
            if (stavke.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Dodajte bar jedan sto.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double ukupanIznos = modelStavke.getUkupanIznos();

            rezervacija.setZaposleni(zaposleni);
            rezervacija.setGost(gost);
            rezervacija.setStatus(status);
            rezervacija.setDatumVremeDolaska(datumDolaska);
            rezervacija.setUkupanIznos(ukupanIznos);
            rezervacija.setStavke(stavke);

            Komunikacija.getInstance().promeniRezervaciju(rezervacija);

            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio rezervaciju.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            Koordinator.Koordinator.getInstance().osveziPrikazRezervacija();
            forma.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti rezervaciju.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
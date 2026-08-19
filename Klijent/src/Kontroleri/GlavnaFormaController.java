package Kontroleri;

import Forme.GlavnaForma;
import Koordinator.Koordinator;
import Model.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavnaFormaController {
    private GlavnaForma glavnaForma;

    public GlavnaFormaController(GlavnaForma glavnaForma) {
        this.glavnaForma = glavnaForma;
        postaviUlogovanog();
        addActionsListener();
    }

    public void otvoriFormu() {
        glavnaForma.setVisible(true);
    }

    private void postaviUlogovanog() {
        Zaposleni z = Koordinator.getInstance().getUlogovani();
        if (z != null) {
            glavnaForma.getLblUlogovani().setText("Zdravo, " + z.getIme() + " " + z.getPrezime());
        }
    }

    private void addActionsListener() {
        glavnaForma.getMiPrikazGostiju().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriPrikazGostiju();
            }
        });

        glavnaForma.getMiKreirajRezervaciju().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriKreirajRezervaciju();
            }
        });

        glavnaForma.getMiPrikazRezervacija().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriPrikazRezervacija();
            }
        });

        glavnaForma.getMiUbaciSmenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriUbaciSmenu();
            }
        });
        
        glavnaForma.getMiPretraziSmenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriPrikazSmene();
            }
        });
    }
}
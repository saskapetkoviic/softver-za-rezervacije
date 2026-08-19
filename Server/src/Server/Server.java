package Server;

import Niti.ObradaKlijentskihZahteva;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private boolean kraj = false;
    private ServerSocket serverskiSoket;
    private List<ObradaKlijentskihZahteva> povezaniKlijenti;

    public Server() {
        povezaniKlijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverskiSoket = new ServerSocket(9000);
            while (!kraj) {
                Socket socket = serverskiSoket.accept();
                System.out.println("Klijent je povezan");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(socket);
                povezaniKlijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            if (kraj == true) return;
            ex.printStackTrace();
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva klijent : povezaniKlijenti) {
                klijent.prekini();
            }
            if (serverskiSoket != null) {
                serverskiSoket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
package Komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Primalac {
    Socket s;
    ObjectInputStream ois;

    public Primalac(Socket s) {
        this.s = s;
        try {
            ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object primi() {
        try {
            return ois.readObject();
        } catch (SocketException ex) {
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
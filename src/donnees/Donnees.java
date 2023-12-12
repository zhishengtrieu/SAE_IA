package donnees;

import java.util.ArrayList;

public class Donnees {
    public ArrayList<Imagette> imagettes;

    public Donnees(){
        this.imagettes = new ArrayList<>();
    }

    public void add(Imagette img) {
        this.imagettes.add(img);
    }

    public ArrayList<Imagette> getImg() {
        return this.imagettes;
    }
}

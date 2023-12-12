package donnees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChargementData {
    public int type;
    public int nbImages;
    public int nbLignes;
    public int nbColonnes;
    public DataInputStream stream;
    public Donnees donnees;
    public int limit;

    public ChargementData(String imgPath, String labelPath, int limit) throws IOException {

        File file = new File(imgPath);
        FileInputStream f = new FileInputStream(file);
        this.limit = limit;
        this.stream = new DataInputStream(f);
        this.type = stream.readInt();
        this.nbImages = stream.readInt();
        this.nbLignes = stream.readInt();
        this.nbColonnes = stream.readInt();
        this.donnees = new Donnees();
        loadData();
        LabelsLoad labels = new LabelsLoad(labelPath, limit);
        for (int i = 0; i < limit; i++) {
            this.donnees.getImg().get(i).setLabel(labels.labels.get(i));
        }

    }

    public void loadData() throws IOException {

        for (int i = 0; i < limit; i++) {
            int[][] data = new int[this.nbColonnes][this.nbLignes];
            for (int j = 0; j < this.nbLignes; j++) {
                for (int k = 0; k < this.nbColonnes; k++) {
                    data[j][k] = this.stream.readUnsignedByte();
                }
            }
            Imagette img = new Imagette(data);
            this.donnees.add(img);
        }
    }

    public void saveImagettes() throws IOException {
        int n=0;
        for (Imagette imgACreer : this.donnees.getImg()) {
            int[][] niveauDeGris = imgACreer.getData();
            BufferedImage image = new BufferedImage(this.nbColonnes, this.nbLignes, BufferedImage.TYPE_3BYTE_BGR);
            for (int i = 0; i < this.nbLignes; i++) {
                for (int j = 0; j < this.nbColonnes; j++) {
                    int couleur = niveauDeGris[i][j];
                    Color c = new Color(couleur, couleur, couleur);
                    image.setRGB(j, i, c.getRGB());
                }
            }
            System.out.println(imgACreer.label);
            File outputfile = new File("img/image"+n+".png");
            ImageIO.write(image, "png", outputfile);
            n++;
        }
    }



    public static void main(String[] args) throws IOException {
        ChargementData dataLoaded = new ChargementData("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte", 1000);
        dataLoaded.saveImagettes();
    }

}

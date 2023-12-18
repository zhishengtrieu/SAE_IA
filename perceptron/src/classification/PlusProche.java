package classification;

import donnees.Imagette;

import java.io.IOException;

public class PlusProche extends AlgoClassification{
    public PlusProche() throws IOException {
    }

    @Override
    public int predict(Imagette img) {
        int res = this.trainData.getImg().get(0).getLabel();
        int dist = this.trainData.getImg().get(0).dist(img);
        for (Imagette imagette : this.trainData.getImg()) {
            int tmp = imagette.dist(img);
            if (tmp < dist) {
                dist = tmp;
                res = imagette.getLabel();
            }
        }
        return res;
    }
}

import smile.Network;

import java.util.Map;

public class Bayes {

    private Network network;

    public Bayes(){
        enableLicense();
        network = new Network();
        network.readFile("projekt.xdsl");
    }

//    public static void main(String[] args) {
//        Network network = new Network();
//        network.readFile("projekt.xdsl");
//        network.setEvidence("banjo", "tak");
//        network.setEvidence("harmonijka", "tak");
//        network.updateBeliefs();
//        double[] genres = network.getNodeValue("gatunek");
//        for(int i=0; i < genres.length; i++) {
//            System.out.println(network.getOutcomeId("gatunek", i) + ": " + genres[i]);
//        }
//
//        //network.clearAllEvidence();
//    }

    public String getResult(Map<String, String> nodes) {
        network.clearAllEvidence();
        nodes.forEach((option, evidence) -> network.setEvidence(option, evidence));
        network.updateBeliefs();
        double[] genres = network.getNodeValue("gatunek");
        StringBuilder result = new StringBuilder();
        for(int i=0; i < genres.length; i++) {
            result.append(network.getOutcomeId("gatunek", i) + ": " + genres[i] + "<br/>");
        }
        return result.toString();
    }

    private void enableLicense() {

    }
}

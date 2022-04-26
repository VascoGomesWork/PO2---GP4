import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Vasco Gomes 19921
 * @date 20/04/2022
 */
public class Horoscope {

    public static final String[] SIGN_NAMES = {
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius",
            "Capricorn",
            "Aquarius",
            "Pisces"};

    private List<String> sentences;


    public Horoscope(String path) {
        try {
            this.sentences = Files.readAllLines(Paths.get(path));
            //generatePredictions("sagitario");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> generatePredictions(String sing){
        List<String> sentencesCopy = new ArrayList<>(this.sentences);
        List<String> predictionList = new ArrayList<>();
        int max = 14;
        int min = 0;
        int randomNumber;
        // TODO - Gerar 3 a 6 previsões aleatórioas e distinta substituindo o place horder SIGNO pelo SIGN
        /*Collections.shuffle(this.sentences);
        predictionList = this.sentences.subList(0, (int)(Math.floor(Math.random()*(max-min+1)+min)));*/

        for (int i = 0; i < (int)(Math.floor(Math.random()*(6-3+1)+3)); i++) {
            randomNumber = (int)(Math.floor(Math.random()*(max-min+1)+min));
            //System.out.println("Random Number = " + randomNumber);
            //Gets the sentence to the list
            //method remove returns the removed element from the list
            String changedSentenced = this.sentences.remove(randomNumber);
            changedSentenced = changedSentenced.replace("SIGNO", sing);
            predictionList.add(changedSentenced);
        }

        //System.out.println("Prediction List = " + predictionList);
        return predictionList;
    }
}

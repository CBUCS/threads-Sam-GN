import Classes.Country;
import Classes.Sighting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import threads.StateMapper;

import java.util.ArrayList;

import static Functions.Functions.CountrySighting;
import static Functions.Functions.createUfoList;

public class Main {



    static ArrayList<Sighting> ufoList;

    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        logger.info("Hello, again");
        ufoList = new ArrayList<Sighting>();
        Country countrySighting = new Country();

        createUfoList(ufoList);
        //System.out.println(ufoList.get(0).getMin());
        System.out.println(ufoList.size());
        CountrySighting(ufoList,countrySighting);
        System.out.println("USA: "+countrySighting.getUSA());
        System.out.println("Canada: "+countrySighting.getCanada());
        System.out.println("UK: "+countrySighting.getUK());
        System.out.println("Unknown: "+countrySighting.getUnknown());

        StateMapper states = new StateMapper(ufoList);
        states.print();
    }


}

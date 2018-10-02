import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    static FileInputStream fis;
    static BufferedReader bis;
    static ArrayList<Sighting> ufoList;

    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        logger.info("Hello, again");
        ufoList = new ArrayList<Sighting>();
        Country countrySighting = new Country();
        File inputFile = new File("src/main/resources/scrubbed.txt");
        try {
            fis = new FileInputStream(inputFile);
            bis = new BufferedReader(new InputStreamReader(fis));

            String line = bis.readLine();


            int counter = 0;
            while (line != null) {
                if (!StringUtils.isEmpty(line)) {
                   /* if (!this.intMap.containsKey(counter % this.numThreads)) {
                        this.intMap.put(counter % this.numThreads, new ArrayList<Integer>());
                    }*/
                    //this.intMap.get(counter % this.numThreads).add(Integer.parseInt(StringUtils.strip(line)));
                    List<String> split = Arrays.asList(line.split(","));
                    Sighting ufo = new Sighting(split);
                    ufoList.add(ufo);
                    counter++;
                }
                line = bis.readLine();
            }



            bis.close();
        } catch(IOException e) {
            logger.fatal(e.toString());
            e.printStackTrace();
        }
        //System.out.println(ufoList.get(0).getMin());
        System.out.println(ufoList.size());
        CounrtySighting(ufoList,countrySighting);
        System.out.println("USA: "+countrySighting.getUSA());
        System.out.println("Canada: "+countrySighting.getCanada());
        System.out.println("UK: "+countrySighting.getUK());
        System.out.println("Unknown: "+countrySighting.getUnknown());
    }
    public static void CounrtySighting (ArrayList<Sighting> pUFO,Country pCountry) {

        for(Sighting ufo : pUFO){
            if(ufo.getCountry().equals("us"))
              pCountry.setUSA(pCountry.getUSA()+1);
            if(ufo.getCountry().equals("ca"))
                pCountry.setCanada(pCountry.getCanada()+1);
            if(ufo.getCountry().equals("gb"))
                pCountry.setUK(pCountry.getUK()+1);
            if(ufo.getCountry().equals(""))
                pCountry.setUnknown(pCountry.getUnknown()+1);
        }

    }

}

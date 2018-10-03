package Functions;

import Classes.Country;
import Classes.Sighting;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {


    public static void createUfoList (ArrayList<Sighting> pUfoList){
        FileInputStream fis;
        BufferedReader bis;
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
                    pUfoList.add(ufo);
                    counter++;
                }
                line = bis.readLine();
            }



            bis.close();
        } catch(IOException e) {
            //logger.fatal(e.toString());
            e.printStackTrace();
        }
    }

    public static void CountrySighting(ArrayList<Sighting> pUFO, Country pCountry) {

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

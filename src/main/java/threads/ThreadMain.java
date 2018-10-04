package threads;

import Classes.Sighting;
import administrative.AnotherMain;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

public class ThreadMain implements AnotherMain {

    protected int numThreads = 5;

    // Hold all our threads.
    private List<Thread> threads = new ArrayList<Thread>();

    private Map<String, List<Sighting>> StringMap = new HashMap<String, List<Sighting>>();

    public static final Logger logger = LogManager.getLogger(ThreadMain.class);

    private File inputFile = new File("src/main/resources/scrubbed.txt");

    public ThreadMain(int threads) {
        this.numThreads = threads;
    }

    static ArrayList<Sighting> ufoList;

    public void main() {

        logger.info("Hello!");
        ufoList = new ArrayList<Sighting>();
        FileInputStream fis;
        BufferedReader bis;

        try {
            fis = new FileInputStream(inputFile);
            bis = new BufferedReader(new InputStreamReader(fis));

            String line = bis.readLine();

            int counter = 0;
            while (line != null) {
                if (!StringUtils.isEmpty(line)) {
                    List<String> split = Arrays.asList(line.split(","));
                    Sighting ufo = new Sighting(split);
                    ufoList.add(ufo);
                    counter++;
                }
                line = bis.readLine();
            }

            bis.close();
        } catch(IOException e) {
            //logger.fatal(e.toString());
            e.printStackTrace();
        }

        //createUfoList(ufoList);

        CountryMapper country = new CountryMapper(ufoList,1);
        StateMapper states = new StateMapper(ufoList,2);
        YearMapper years = new YearMapper(ufoList,3);
        MonthMapper months = new MonthMapper(ufoList,4);
        PeriodMapper periods = new PeriodMapper(ufoList,5);

        Thread thread = new Thread(country);
        Thread thread2 = new Thread(states);
        Thread thread3 = new Thread(years);
        Thread thread4 = new Thread(months);
        Thread thread5 = new Thread(periods);

        this.threads.add(thread);
        this.threads.add(thread2);
        this.threads.add(thread3);
        this.threads.add(thread4);
        this.threads.add(thread5);

        for(Thread t:threads)
            t.start();

        try {
            for(Thread t:threads)
                t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        country.print();
        states.print();
        years.print();
        months.print();
        periods.print();


       // logger.info(NumberMapper.mapper);



    }

}

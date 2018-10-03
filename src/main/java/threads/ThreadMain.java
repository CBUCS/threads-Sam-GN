package threads;

import Classes.Country;
import Classes.Sighting;
import administrative.AnotherMain;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Functions.Functions.CountrySighting;
import static Functions.Functions.createUfoList;

public class ThreadMain implements AnotherMain {

    protected int numThreads = 4;

    // Hold all our threads.
    private List<Thread> threads = new ArrayList<Thread>();
    private List<StateMapper> stateMappers = new ArrayList<StateMapper>();
    private List<CountryMapper> countryMapper = new ArrayList<CountryMapper>();
    private List<YearMapper> yearMapper = new ArrayList<YearMapper>();
    private List<MonthMapper> monthMapper = new ArrayList<MonthMapper>();
    private List<PeriodMapper> periodMappers = new ArrayList<PeriodMapper>();

    private Map<String, List<Sighting>> StringMap = new HashMap<String, List<Sighting>>();

    public static final Logger logger = LogManager.getLogger(ThreadMain.class);

    private File inputFile = new File("src/main/resources/scrubbed.txt");

    public ThreadMain(int threads) {
        this.numThreads = threads;
    }

    static ArrayList<Sighting> ufoList;

    public void main() {

        logger.info("Hello, Thread main");
        ufoList = new ArrayList<Sighting>();
        Country countrySighting = new Country();

        createUfoList(ufoList);
        //System.out.println(ufoList.get(0).getMin());
        // System.out.println(ufoList.size());

        //CountrySighting(ufoList,countrySighting);

        // System.out.println("USA: "+countrySighting.getUSA());
        // System.out.println("Canada: "+countrySighting.getCanada());
        // System.out.println("UK: "+countrySighting.getUK());
        // System.out.println("Unknown: "+countrySighting.getUnknown());

        StateMapper states = new StateMapper(ufoList);
        CountryMapper country = new CountryMapper(ufoList);
        YearMapper years = new YearMapper(ufoList,3);
        MonthMapper months = new MonthMapper(ufoList,4);
        PeriodMapper periods = new PeriodMapper(ufoList,5);
        this.stateMappers.add(states);
        this.countryMapper.add(country);
        this.yearMapper.add(years);
        this.monthMapper.add(months);
        this.periodMappers.add(periods);
        Thread thread = new Thread(states);
        Thread thread2 = new Thread(country);
        Thread thread3 = new Thread(years);
        Thread thread4 = new Thread(months);
        Thread thread5 = new Thread(periods);
        this.threads.add(thread);
        this.threads.add(thread2);
        this.threads.add(thread3);
        this.threads.add(thread4);
        this.threads.add(thread5);
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();



        //states.print();

        /*for (int x = 0; x < this.numThreads; x++) {
            StateMapper sm = new StateMapper(this.StringMap.get(x));
            this.stateMappers.add(sm);
            Thread thread = new Thread(sm);
            this.threads.add(thread);
        }

        int x = 1;
        for (Thread thread : this.threads) {
           // logger.debug("Running thread: " + x);
            thread.start();
            x++;
        }*/

        int isDone = numThreads;

        while(isDone > 0) {
            for (StateMapper t : this.stateMappers) {
                if (t.isDone()) {
                    isDone--;
                    //logger.info("t2 done");
                }
            }
        }


        try {
            thread.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        states.print();
        country.print();
        years.print();
        months.print();
        periods.print();


        try {
           // TimeUnit.SECONDS.sleep(2);
        } catch(Exception e){}

       // logger.info(NumberMapper.mapper);



    }

}

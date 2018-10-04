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
            //Here I create each ufo sighting and add them to my ufoList
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

        //each thread for calculating different sightings are created and the list is passed to them.

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
            //main thread waits for the other threads to finish
            for(Thread t:threads)
                t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The requested data is printed
        country.print();
        states.print();
        years.print();
        months.print();
        periods.print();


                 //results:

             /* [INFO ] threads.ThreadMain - Hello!
                [INFO ] threads.CountryMapper - Hello, from thread: 1
                [INFO ] threads.StateMapper - Hello, from thread: 2
                [INFO ] threads.PeriodMapper - Hello, from thread: 5
                [INFO ] threads.YearMapper - Hello, from thread: 3
                [INFO ] threads.MonthMapper - Hello, from thread: 4
                [INFO ] threads.StateMapper - Goodbye, from thread: 2
                [INFO ] threads.CountryMapper - Goodbye, from thread: 1
                [INFO ] threads.MonthMapper - Goodbye, from thread: 4
                [INFO ] threads.YearMapper - Goodbye, from thread: 3
                [INFO ] threads.PeriodMapper - Goodbye, from thread: 5
                [INFO ] threads.CountryMapper - Unknown Countries: 9670
                [INFO ] threads.CountryMapper - de: 105
                [INFO ] threads.CountryMapper - au: 538
                [INFO ] threads.CountryMapper - uk: 1905
                [INFO ] threads.CountryMapper - us: 65114
                [INFO ] threads.CountryMapper - ca: 3000
                [INFO ] threads.StateMapper - Not A State: 5797
                [INFO ] threads.StateMapper - hi: 353
                [INFO ] threads.StateMapper - pq: 90
                [INFO ] threads.StateMapper - de: 183
                [INFO ] threads.StateMapper - pr: 33
                [INFO ] threads.StateMapper - tx: 3677
                [INFO ] threads.StateMapper - qc: 178
                [INFO ] threads.StateMapper - yk: 7
                [INFO ] threads.StateMapper - ma: 1358
                [INFO ] threads.StateMapper - mb: 155
                [INFO ] threads.StateMapper - md: 911
                [INFO ] threads.StateMapper - me: 633
                [INFO ] threads.StateMapper - ia: 707
                [INFO ] threads.StateMapper - id: 554
                [INFO ] threads.StateMapper - yt: 13
                [INFO ] threads.StateMapper - mi: 2071
                [INFO ] threads.StateMapper - ut: 743
                [INFO ] threads.StateMapper - ab: 333
                [INFO ] threads.StateMapper - mn: 1081
                [INFO ] threads.StateMapper - mo: 1576
                [INFO ] threads.StateMapper - il: 2645
                [INFO ] threads.StateMapper - in: 1386
                [INFO ] threads.StateMapper - ms: 415
                [INFO ] threads.StateMapper - mt: 510
                [INFO ] threads.StateMapper - ak: 354
                [INFO ] threads.StateMapper - al: 691
                [INFO ] threads.StateMapper - va: 1393
                [INFO ] threads.StateMapper - ar: 666
                [INFO ] threads.StateMapper - nb: 116
                [INFO ] threads.StateMapper - nc: 1869
                [INFO ] threads.StateMapper - nd: 138
                [INFO ] threads.StateMapper - ne: 405
                [INFO ] threads.StateMapper - ri: 290
                [INFO ] threads.StateMapper - nf: 25
                [INFO ] threads.StateMapper - az: 2689
                [INFO ] threads.StateMapper - nh: 535
                [INFO ] threads.StateMapper - nj: 1512
                [INFO ] threads.StateMapper - vt: 307
                [INFO ] threads.StateMapper - nm: 815
                [INFO ] threads.StateMapper - bc: 788
                [INFO ] threads.StateMapper - ns: 143
                [INFO ] threads.StateMapper - fl: 4200
                [INFO ] threads.StateMapper - nt: 20
                [INFO ] threads.StateMapper - nv: 905
                [INFO ] threads.StateMapper - wa: 4268
                [INFO ] threads.StateMapper - ny: 3219
                [INFO ] threads.StateMapper - sa: 30
                [INFO ] threads.StateMapper - sc: 1076
                [INFO ] threads.StateMapper - sd: 196
                [INFO ] threads.StateMapper - wi: 1333
                [INFO ] threads.StateMapper - sk: 98
                [INFO ] threads.StateMapper - oh: 2425
                [INFO ] threads.StateMapper - ga: 1347
                [INFO ] threads.StateMapper - ok: 766
                [INFO ] threads.StateMapper - ca: 9655
                [INFO ] threads.StateMapper - on: 1584
                [INFO ] threads.StateMapper - wv: 486
                [INFO ] threads.StateMapper - wy: 205
                [INFO ] threads.StateMapper - or: 1845
                [INFO ] threads.StateMapper - ks: 653
                [INFO ] threads.StateMapper - co: 1505
                [INFO ] threads.StateMapper - ky: 914
                [INFO ] threads.StateMapper - pa: 2582
                [INFO ] threads.StateMapper - ct: 968
                [INFO ] threads.StateMapper - la: 598
                [INFO ] threads.StateMapper - pe: 17
                [INFO ] threads.StateMapper - tn: 1193
                [INFO ] threads.StateMapper - dc: 99
                [INFO ] threads.YearMapper - 1920: 1
                [INFO ] threads.YearMapper - 1925: 1
                [INFO ] threads.YearMapper - 1929: 1
                [INFO ] threads.YearMapper - 1930: 1
                [INFO ] threads.YearMapper - 1931: 2
                [INFO ] threads.YearMapper - 1933: 1
                [INFO ] threads.YearMapper - 1934: 1
                [INFO ] threads.YearMapper - 1936: 2
                [INFO ] threads.YearMapper - 1937: 2
                [INFO ] threads.YearMapper - 1939: 3
                [INFO ] threads.YearMapper - 1941: 1
                [INFO ] threads.YearMapper - 1942: 2
                [INFO ] threads.YearMapper - 1943: 9
                [INFO ] threads.YearMapper - 1944: 9
                [INFO ] threads.YearMapper - 1945: 9
                [INFO ] threads.YearMapper - 1946: 10
                [INFO ] threads.YearMapper - 1947: 37
                [INFO ] threads.YearMapper - 1948: 8
                [INFO ] threads.YearMapper - 1949: 16
                [INFO ] threads.YearMapper - 1950: 28
                [INFO ] threads.YearMapper - 1951: 20
                [INFO ] threads.YearMapper - 1952: 49
                [INFO ] threads.YearMapper - 1953: 33
                [INFO ] threads.YearMapper - 1954: 53
                [INFO ] threads.YearMapper - 1955: 32
                [INFO ] threads.YearMapper - 1956: 45
                [INFO ] threads.YearMapper - 1957: 72
                [INFO ] threads.YearMapper - 1958: 47
                [INFO ] threads.YearMapper - 1959: 50
                [INFO ] threads.YearMapper - 1960: 66
                [INFO ] threads.YearMapper - 1961: 48
                [INFO ] threads.YearMapper - 1962: 73
                [INFO ] threads.YearMapper - 1963: 86
                [INFO ] threads.YearMapper - 1964: 88
                [INFO ] threads.YearMapper - 1965: 188
                [INFO ] threads.YearMapper - 1966: 193
                [INFO ] threads.YearMapper - 1967: 188
                [INFO ] threads.YearMapper - 1968: 220
                [INFO ] threads.YearMapper - 1969: 155
                [INFO ] threads.YearMapper - 1970: 147
                [INFO ] threads.YearMapper - 1971: 130
                [INFO ] threads.YearMapper - 1972: 158
                [INFO ] threads.YearMapper - 1973: 226
                [INFO ] threads.YearMapper - 1974: 273
                [INFO ] threads.YearMapper - 1975: 319
                [INFO ] threads.YearMapper - 1976: 279
                [INFO ] threads.YearMapper - 1977: 268
                [INFO ] threads.YearMapper - 1978: 335
                [INFO ] threads.YearMapper - 1979: 245
                [INFO ] threads.YearMapper - 1980: 246
                [INFO ] threads.YearMapper - 1981: 164
                [INFO ] threads.YearMapper - 1982: 183
                [INFO ] threads.YearMapper - 1983: 153
                [INFO ] threads.YearMapper - 1984: 186
                [INFO ] threads.YearMapper - 1985: 218
                [INFO ] threads.YearMapper - 1986: 193
                [INFO ] threads.YearMapper - 1987: 223
                [INFO ] threads.YearMapper - 1988: 241
                [INFO ] threads.YearMapper - 1989: 251
                [INFO ] threads.YearMapper - 1990: 259
                [INFO ] threads.YearMapper - 1991: 234
                [INFO ] threads.YearMapper - 1992: 252
                [INFO ] threads.YearMapper - 1993: 309
                [INFO ] threads.YearMapper - 1994: 421
                [INFO ] threads.YearMapper - 1995: 1078
                [INFO ] threads.YearMapper - 1996: 834
                [INFO ] threads.YearMapper - 1997: 1255
                [INFO ] threads.YearMapper - 1998: 1767
                [INFO ] threads.YearMapper - 1999: 2805
                [INFO ] threads.YearMapper - 2000: 2772
                [INFO ] threads.YearMapper - 2001: 3122
                [INFO ] threads.YearMapper - 2002: 3235
                [INFO ] threads.YearMapper - 2003: 3962
                [INFO ] threads.YearMapper - 2004: 4257
                [INFO ] threads.YearMapper - 2005: 4083
                [INFO ] threads.YearMapper - 2006: 3721
                [INFO ] threads.YearMapper - 2007: 4269
                [INFO ] threads.YearMapper - 2008: 4820
                [INFO ] threads.YearMapper - 2009: 4541
                [INFO ] threads.YearMapper - 2010: 4283
                [INFO ] threads.YearMapper - 2011: 5107
                [INFO ] threads.YearMapper - 2012: 7357
                [INFO ] threads.YearMapper - 2013: 7037
                [INFO ] threads.YearMapper - 2014: 2260
                [INFO ] threads.YearMapper - 1906: 1
                [INFO ] threads.YearMapper - 1910: 2
                [INFO ] threads.YearMapper - 1916: 1
                [INFO ] threads.MonthMapper - 1: 5689
                [INFO ] threads.MonthMapper - 2: 4665
                [INFO ] threads.MonthMapper - 3: 5450
                [INFO ] threads.MonthMapper - 4: 5527
                [INFO ] threads.MonthMapper - 5: 5293
                [INFO ] threads.MonthMapper - 6: 8152
                [INFO ] threads.MonthMapper - 7: 9520
                [INFO ] threads.MonthMapper - 8: 8636
                [INFO ] threads.MonthMapper - 9: 7589
                [INFO ] threads.MonthMapper - 10: 7407
                [INFO ] threads.MonthMapper - 11: 6739
                [INFO ] threads.MonthMapper - 12: 5665
                [INFO ] threads.PeriodMapper - Sightings happened in 8:01-12:00 : 4500
                [INFO ] threads.PeriodMapper - Sightings happened in 12:01-16:00 : 5460
                [INFO ] threads.PeriodMapper - Sightings happened in 16:01-20:00 : 16804
                [INFO ] threads.PeriodMapper - Sightings happened in 20:01-00:00 : 38095
                [INFO ] threads.PeriodMapper - Sightings happened in 00:01-8:00 : 15473*/


       // logger.info(NumberMapper.mapper);



    }

}

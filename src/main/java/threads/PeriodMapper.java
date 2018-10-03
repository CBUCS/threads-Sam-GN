package threads;

import Classes.Sighting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PeriodMapper implements Runnable {

    public static final Logger logger = LogManager.getLogger(PeriodMapper.class);

    public static int id = 1;

    private int threadId = id;

    private boolean isDone = false;

    private List<Sighting> integerList = new ArrayList<Sighting>();

    public static ConcurrentHashMap<Integer, Integer> mapper = new ConcurrentHashMap<Integer, Integer>();

    public PeriodMapper(List<Sighting> input, int pID) {
        this.threadId = pID;
        //  logger.info("Creating thread: " + this.threadId);
        integerList.addAll(input);
        // run();
    }

    public int getId() {
        return this.threadId;
    }

    public void run() {
        int offset = 1;

        logger.info("Hello, from thread: " + this.threadId);

        for (Sighting x : this.integerList) {
            if (offset % 10000 == 0) {
//                logger.info(String.format("Thread: %d\toffset: %d\tdata: %d", this.threadId, offset, x));

            }

            this.incrementAt(x);

            offset++;
        }
        this.isDone = true;
        logger.info("Goodbye, from thread: " + this.threadId);
    }

    public void incrementAt(Sighting pUFO) {
        int value;
        int key = 0;
        if((pUFO.getHour()>8&&pUFO.getHour()<12)||(pUFO.getHour()==8&&pUFO.getMin()>=1)||(pUFO.getHour()==12&&pUFO.getMin()==0)) {
            key = 1;
        } else if((pUFO.getHour()>12&&pUFO.getHour()<16)||(pUFO.getHour()==12&&pUFO.getMin()>=1)||(pUFO.getHour()==16&&pUFO.getMin()==0)){
            key = 2;
        } else if((pUFO.getHour()>16&&pUFO.getHour()<20)||(pUFO.getHour()==16&&pUFO.getMin()>=1)||(pUFO.getHour()==20&&pUFO.getMin()==0)){
            key = 3;
        } else if((pUFO.getHour()>20&&pUFO.getHour()<=23)||(pUFO.getHour()==20&&pUFO.getMin()>=1)||(pUFO.getHour()==0&&pUFO.getMin()==0)||(pUFO.getHour()==24&&pUFO.getMin()==0)){
            key = 4;
        } else if((pUFO.getHour()>0&&pUFO.getHour()<8)||(pUFO.getHour()==0&&pUFO.getMin()>=1)||(pUFO.getHour()==8&&pUFO.getMin()==0)){
            key = 5;
        }
        if(key==0)
            logger.error(pUFO.getDateTime());

            if (mapper.containsKey(key)) {
            value = mapper.get(key) + 1;
        } else {
            mapper.put(key, 0);
            value = 1;
        }
        mapper.put(key, value);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void print (){
        for (Integer key:mapper.keySet()){
            /*if(key.equals(""))
                logger.info("Unknown Countries"+": "+mapper.get(key));
                //System.out.println("Not A State"+": "+mapper.get(key));
            else if(key.equals("gb"))
                logger.info("uk"+": "+mapper.get(key));
            else
                //System.out.println(key+": "+mapper.get(key));
                logger.info(key+": "+mapper.get(key));*/
            switch (key) {
                case 0: logger.info("Period Not calculated correctly: "+mapper.get(key));
                    break;
                case 1: logger.info("Sightings happened in 8:01-12:00 : "+mapper.get(key));
                    break;
                case 2: logger.info("Sightings happened in 12:01-16:00 : "+mapper.get(key));
                    break;
                case 3: logger.info("Sightings happened in 16:01-20:00 : "+mapper.get(key));
                    break;
                case 4: logger.info("Sightings happened in 20:01-00:00 : "+mapper.get(key));
                    break;
                case 5: logger.info("Sightings happened in 00:01-8:00 : "+mapper.get(key));
                    break;
            }
            //logger.info(key+": "+mapper.get(key));
        }
    }


}

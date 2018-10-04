package threads;

import Classes.Sighting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MonthMapper implements Runnable {

    public static final Logger logger = LogManager.getLogger(MonthMapper.class);

    private int threadId = 1;

    private boolean isDone = false;

    private List<Sighting> sightingList = new ArrayList<Sighting>();

    public static ConcurrentHashMap<Integer, Integer> mapper = new ConcurrentHashMap<Integer, Integer>();

    public MonthMapper(List<Sighting> input, int pID) {
        this.threadId = pID;
        //  logger.info("Creating thread: " + this.threadId);
        sightingList.addAll(input);
    }

    public int getId() {
        return this.threadId;
    }

    public void run() {

        logger.info("Hello, from thread: " + this.threadId);
        for (Sighting x : this.sightingList) {

            this.incrementAt(x);

        }
        this.isDone = true;
        logger.info("Goodbye, from thread: " + this.threadId);

    }

    public void incrementAt(Sighting pUFO) {
        int value;
        if (mapper.containsKey(pUFO.getMonth())) {
            value = mapper.get(pUFO.getMonth()) + 1;
        } else {
            mapper.put(pUFO.getMonth(), 0);
            value = 1;
        }
        mapper.put(pUFO.getMonth(), value);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void print (){
        for (Integer key:mapper.keySet()){
            logger.info(key+": "+mapper.get(key));
        }
    }

}

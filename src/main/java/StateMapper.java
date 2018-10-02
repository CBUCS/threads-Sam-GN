import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StateMapper implements Runnable{

    public static final Logger logger = LogManager.getLogger(StateMapper.class);

    public static int id = 1;

    private int threadId = id;

    private boolean isDone = false;

    private List<Sighting> integerList = new ArrayList<Sighting>();

    public static ConcurrentHashMap<String, Integer> mapper = new ConcurrentHashMap<String, Integer>();

    public StateMapper(List<Sighting> input) {
        this.threadId = StateMapper.id++;
      //  logger.info("Creating thread: " + this.threadId);
        integerList.addAll(input);
        run();
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
     //   logger.info("Goodbye, from thread: " + this.threadId);
    }

    public void incrementAt(Sighting pUFO) {
        int value;
        if (mapper.containsKey(pUFO.getState())) {
            value = mapper.get(pUFO.getState()) + 1;
        } else {
            mapper.put(pUFO.getState(), 0);
            value = 1;
        }
        mapper.put(pUFO.getState(), value);
    }

    public boolean isDone() {
        return this.isDone;
    }
    public void print (){
        for (String key:mapper.keySet()){
            if(key.equals(""))
                System.out.println("Not A State"+": "+mapper.get(key));
            else
                System.out.println(key+": "+mapper.get(key));
        }
    }

}

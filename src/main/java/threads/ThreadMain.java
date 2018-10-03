package threads;

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

public class ThreadMain implements AnotherMain {

    protected int numThreads = 4;

    // Hold all our threads.
    private List<Thread> threads = new ArrayList<Thread>();
    private List<StateMapper> stateMappers = new ArrayList<StateMapper>();

    private Map<String, List<Sighting>> StringMap = new HashMap<String, List<Sighting>>();

    public static final Logger logger = LogManager.getLogger(ThreadMain.class);

    private File inputFile = new File("src/main/resources/scrubbed.txt");

    public ThreadMain(int threads) {
        this.numThreads = threads;
    }

    public void main() {

       /* FileInputStream fis;
        BufferedReader bis;

        try {
            fis = new FileInputStream(this.inputFile);
            bis = new BufferedReader(new InputStreamReader(fis));

            String line = bis.readLine();

            int counter = 0;
            while (line != null) {
                if (!StringUtils.isEmpty(line)) {
                    if (!this.StringMap.containsKey(counter % this.numThreads)) {
                        this.StringMap.put(counter % this.numThreads, new ArrayList<Sighting>());
                    }
                    this.StringMap.get(counter % this.numThreads).add(Integer.parseInt(StringUtils.strip(line)));
                    counter++;
                }
                line = bis.readLine();
            }
            bis.close();
        } catch(IOException e) {
            //logger.fatal(e.toString());
            e.printStackTrace();
        }*/

        for (int x = 0; x < this.numThreads; x++) {
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
        }

        int isDone = numThreads;

        while(isDone > 0) {
            for (StateMapper thread : this.stateMappers) {
                if (thread.isDone()) {
                    isDone--;
                }
            }
        }

        try {
           // TimeUnit.SECONDS.sleep(2);
        } catch(Exception e){}

       // logger.info(NumberMapper.mapper);



    }

}

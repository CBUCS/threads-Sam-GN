import administrative.AnotherMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import threads.ThreadMain;

public class Main {


    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //calling the main thread
        AnotherMain main = new ThreadMain(5);
        main.main();
    }


}

package org.openjfx.bioreactor;

import java.util.Timer;
import java.util.TimerTask;

public class MainReactor {
    public static void main(String[] args) {
        int i;
        long delay=5000;
        long period=100;
        BioReactor myreac=new BioReactor();
        String my_string=myreac.toString();
        System.out.println(my_string);

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                myreac.readingFile();
                System.out.println(myreac.toString());
            }
        };

        Timer timer=new Timer();
        timer.schedule(timerTask,delay, period);
    }
}

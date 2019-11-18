package roomParty;

import java.util.concurrent.Semaphore;

public class dean implements Runnable {
    private Integer students;
    private String dean;
    private Semaphore mutex;
    private Semaphore turn;
    private Semaphore clear;
    private Semaphore lieIn;

    public dean(Integer students, String dean, Semaphore mutex, Semaphore turn, Semaphore clear, Semaphore lieIn){
        this.students = students;
        this.dean = dean;
        this.mutex = mutex;
        this.turn = turn;
        this.clear = clear;
        this.lieIn = lieIn;
    }

    @Override
    public void run(){
        while (true) {
            try{
                synchronized (mutex){
                    mutex.wait();
                    if (students > 0 && students < 10) {
                        dean = "waiting";
                        mutex.notify();
                        lieIn.wait();
                    }

                    if (students >= 50){
                        dean = "in the room";
                        System.out.println("The party is over!!!!");
                        turn.wait();
                        mutex.notify();
                        clear.wait();
                        turn.notify();
                    }

                    else {
                        System.out.println("Searching...");
                    }
                    dean = "not here";
                    mutex.notify();
                }
            } catch (InterruptedException error){
                error.printStackTrace();
            }
        }
    }
}

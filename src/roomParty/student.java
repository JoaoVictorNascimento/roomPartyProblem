package roomParty;

import java.util.concurrent.Semaphore;

public class student implements Runnable{
    private Integer students;
    private String dean;
    private Semaphore mutex;
    private Semaphore turn;
    private Semaphore clear;
    private Semaphore lieIn;

    public student(Integer students, String dean, Semaphore mutex, Semaphore turn, Semaphore clear, Semaphore lieIn){
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
                synchronized (mutex) {
                    if (dean == "in the room") {
                        mutex.notify();
                        turn.wait();
                        turn.notify();
                        mutex.wait();
                    }

                    students += 1;

                    if (students == 50 && dean == "waiting") {
                        lieIn.notify();
                    }

                    else {
                        mutex.notify();
                    }

                    System.out.println("Time to hit the ship");

                    mutex.wait();
                    students -= 1;

                    if (students == 0 && dean == "waiting") {
                        lieIn.notify();
                    } else if (students == 0 && dean == "in the room") {
                        clear.notify();
                    } else {
                        mutex.notify();
                    }
                }
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
        }
    }
}

package roomParty;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class student implements Runnable {
    private roomParty room;
    private String dean;
    private Semaphore mutex;
    private Semaphore turn;
    private Semaphore clear;
    private Semaphore lieIn;

    public student(roomParty room, String dean, Semaphore mutex, Semaphore turn, Semaphore clear, Semaphore lieIn) {
        this.room = room;
        this.dean = dean;
        this.mutex = mutex;
        this.turn = turn;
        this.clear = clear;
        this.lieIn = lieIn;
    }

    @Override
    public void run() {
            while (true) {
                try {
                    mutex.acquire();
                    if (dean == "in the room") {
                        mutex.release();
                        turn.acquire();
                        turn.release();
                        mutex.acquire();
                    }
                    room.goParty();

                    if (room.getStudentsParty() == 50 && dean == "waiting") {
                        lieIn.release();
                    } else {
                        mutex.release();
                    }
                    System.out.println("Time to hit the ship");


                    mutex.acquire();
                    room.goOutParty();
                    if (room.getStudentsParty() == 0 && dean == "waiting") {
                        lieIn.release();
                    } else if (room.getStudentsParty() == 0 && dean == "in the room") {
                        clear.release();
                    } else {
                        mutex.release();
                    }

                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
            }
    }
}
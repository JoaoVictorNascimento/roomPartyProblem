package roomParty;

import java.util.concurrent.Semaphore;

public class dean implements Runnable {
    private roomParty room;
    private String dean;
    private Semaphore mutex;
    private Semaphore turn;
    private Semaphore clear;
    private Semaphore lieIn;

    public dean(roomParty room, String dean, Semaphore mutex, Semaphore turn, Semaphore clear, Semaphore lieIn){
        this.room = room;
        this.dean = dean;
        this.mutex = mutex;
        this.turn = turn;
        this.clear = clear;
        this.lieIn = lieIn;
    }

    @Override
    public void run(){
            while (true) {
                try {
                    mutex.acquire();
                    if (room.getStudentsParty() > 0 && room.getStudentsParty() < 50) {
                        dean = "waiting";
                        mutex.release();
                        lieIn.acquire();
                    }
                    System.out.println(room.getStudentsParty());

                    if (room.getStudentsParty() >= 50) {
                        dean = "in the room";
                        System.out.println("The party is over!!!!");
                        turn.acquire();
                        mutex.release();
                        clear.acquire();
                        turn.release();
                    } else {
                        System.out.println("Searching...");
                    }
                    dean = "not here";
                    mutex.release();

                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
            }
    }
}

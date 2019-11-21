package roomParty;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger students = new AtomicInteger(0);
        roomParty room = new roomParty(students);
        String dean = "not here";

        Semaphore mutex = new Semaphore(1);
        Semaphore turn = new Semaphore(1);
        Semaphore clear = new Semaphore(0);
        Semaphore lieIn = new Semaphore(0);


        new Thread(new dean(room, dean, mutex, turn, clear, lieIn)).start();
        for(Integer i=0; i<41; i++) {
            new Thread(new student(room, dean, mutex, turn, clear, lieIn)).start();
        }
    }
}

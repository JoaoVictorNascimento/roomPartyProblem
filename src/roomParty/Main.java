package roomParty;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Integer students = 0;
        String dean = "not here";
        Semaphore mutex = new Semaphore(1);
        Semaphore turn = new Semaphore(1);
        Semaphore clear = new Semaphore(0);
        Semaphore lieIn = new Semaphore(0);


        new Thread(new dean(students, dean, mutex, turn, clear, lieIn)).start();
        for(Integer i=0; i<80; i++) {
            new Thread(new student(students, dean, mutex, turn, clear, lieIn)).start();
        }
    }
}

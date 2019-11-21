package roomParty;

import java.util.concurrent.atomic.AtomicInteger;

public class roomParty {
    private AtomicInteger students;

    public roomParty(AtomicInteger students){
        this.students = students;
    }
    public synchronized int goParty(){
        return students.incrementAndGet();
    }

    public synchronized int goOutParty() {
        return students.decrementAndGet();
    }

    public synchronized int getStudentsParty(){ return students.get();}

}

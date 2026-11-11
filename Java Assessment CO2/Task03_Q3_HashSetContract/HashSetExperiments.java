import java.util.HashSet;
import java.util.Set;

public class HashSetExperiments {
    // Deliberately broken: equality is by registration number, hash is by object identity.
    static class BrokenStudent {
        final String regNo;
        BrokenStudent(String regNo) { this.regNo = regNo; }
        @Override public boolean equals(Object o) {
            return o instanceof BrokenStudent
                    && regNo.equals(((BrokenStudent) o).regNo);
        }
        // No hashCode override: violates the equals/hashCode contract.
    }

    // Mutable-key demonstration: changing the hash field after insertion is unsafe.
    static class MutableStudent {
        String regNo;
        MutableStudent(String regNo) { this.regNo = regNo; }
        @Override public boolean equals(Object o) {
            return o instanceof MutableStudent
                    && regNo.equals(((MutableStudent) o).regNo);
        }
        @Override public int hashCode() { return regNo.hashCode(); }
    }

    public static void main(String[] args) {
        System.out.println("TEST 1: broken class with same reference");
        BrokenStudent same = new BrokenStudent("R1");
        Set<BrokenStudent> test1 = new HashSet<>();
        test1.add(same);
        test1.add(same);
        System.out.println("Expected 1 because it is the same object; observed " + test1.size());

        System.out.println("TEST 2: broken class with two equal but distinct objects");
        Set<BrokenStudent> test2 = new HashSet<>();
        test2.add(new BrokenStudent("R2"));
        test2.add(new BrokenStudent("R2"));
        System.out.println("Expected logically 1; observed " + test2.size()
                + " (may appear as 2 because identity hash codes differ)");

        System.out.println("TEST 3: mutable key");
        MutableStudent student = new MutableStudent("BEFORE");
        Set<MutableStudent> test3 = new HashSet<>();
        test3.add(student);
        student.regNo = "AFTER";
        System.out.println("Contains after mutation: " + test3.contains(student)
                + " (typically false; bucket was selected using old hash)");

        System.out.println("Corrected class result is shown by running Attendance.");
    }
}

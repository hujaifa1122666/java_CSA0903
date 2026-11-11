import java.io.IOException;

class ExamException extends Exception {
    ExamException(String message) { super(message); }
    ExamException(String message, Throwable cause) { super(message, cause); }
}

class ExamWindowClosedException extends ExamException {
    ExamWindowClosedException(String message) { super(message); }
}
class StudentNotRegisteredException extends ExamException {
    StudentNotRegisteredException(String message) { super(message); }
}
class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String message, Throwable cause) { super(message, cause); }
}

class Storage {
    void save(String fileName, long sizeKb) throws SubmissionStorageException {
        try {
            if (sizeKb > 500) throw new IOException("Disk quota exceeded");
            System.out.println("Saved " + fileName);
        } catch (IOException e) {
            // TODO 1 completed: preserve the original IOException as the cause.
            throw new SubmissionStorageException("Could not store " + fileName, e);
        }
    }
}

class ExamService {
    private final Storage storage = new Storage();

    void submit(String studentId, boolean registered, boolean examOpen,
                String fileName, long sizeKb) throws ExamException {
        if (!examOpen) throw new ExamWindowClosedException("Exam window has closed.");
        if (!registered) throw new StudentNotRegisteredException("Student is not registered.");
        storage.save(fileName, sizeKb);
    }
}

class ExamController {
    private final ExamService service = new ExamService();

    void handleSubmission(String studentId, boolean registered, boolean examOpen,
                          String fileName, long sizeKb) {
        try {
            service.submit(studentId, registered, examOpen, fileName, sizeKb);
            System.out.println("Submission accepted.");
        } catch (ExamException e) {
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Cause: " + e.getCause().getMessage());
                System.out.println("Chained stack trace:");
                e.printStackTrace(System.out);
            }
        }
    }
}

public class StorageDemo {
    public static void main(String[] args) {
        ExamController controller = new ExamController();
        controller.handleSubmission("S101", true, true, "answer01.pdf", 120);
        controller.handleSubmission("S102", true, true, "answer02.pdf", 800);
        controller.handleSubmission("S103", true, false, "answer03.pdf", 100);
        controller.handleSubmission("S104", false, true, "answer04.pdf", 100);
    }
}

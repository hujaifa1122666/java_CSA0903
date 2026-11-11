import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidWithdrawalException extends Exception {
    InvalidWithdrawalException(String message) { super(message); }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) { super(message); }
}

public class ATM {
    private static final double BALANCE = 5000.00;

    static void checkCardReader(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Card-reader file is missing: " + path);
        }
    }

    static void withdraw(double amount) throws InvalidWithdrawalException,
            InsufficientFundsException {
        if (amount <= 0) throw new InvalidWithdrawalException("Amount must be positive.");
        if (amount > BALANCE) throw new InsufficientFundsException("Insufficient account balance.");
        System.out.printf("Withdrawal successful: Rs %.2f%n", amount);
    }

    public static void main(String[] args) {
        System.out.println("ATM SIMULATION");
        try (Scanner input = new Scanner(System.in)) {
            // Change this path to an existing file to test success, or a missing path to test IOException.
            checkCardReader(Path.of("card-reader.txt"));

            System.out.print("Enter account number: ");
            String account = input.nextLine().trim();
            if (account.isEmpty()) throw new IllegalArgumentException("Account number cannot be empty.");

            System.out.print("Enter withdrawal amount: ");
            double amount = input.nextDouble();
            withdraw(amount);
        } catch (InputMismatchException e) {
            System.out.println("Input error: Enter a numeric withdrawal amount.");
        } catch (InvalidWithdrawalException | InsufficientFundsException e) {
            System.out.println("Transaction rejected: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Card reader error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        } finally {
            System.out.println("ATM session closed (finally executed).");
        }
    }
}

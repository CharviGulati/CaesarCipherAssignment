package ui;

// Main class runs the entire program. Communicates with all other classes

public class Main {

    public static void main(String[] args) {

        GetUserInput.introductionToProgram();
        GetUserInput.handleUserEncryptionRequest();
        while (true) {
            GetUserInput.menuOperations();
        }
    }
}

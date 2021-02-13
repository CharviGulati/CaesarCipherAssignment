package ui;

public class Main {

    public static void main(String[] args) {

        GetUserInput.introductionToProgram();
        GetUserInput.handleUserEncryptionRequest();
        while (true) {
            GetUserInput.menuOperations();
        }
    }
}

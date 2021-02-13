package ui;

public class Main {

    public static void main(String[] args) {

        while (true) {
            GetUserInput.introductionToProgram();
            GetUserInput.handleUserEncryptionRequest();
            GetUserInput.getMenuInput();
            GetUserInput.menuOperations();
        }
    }
}

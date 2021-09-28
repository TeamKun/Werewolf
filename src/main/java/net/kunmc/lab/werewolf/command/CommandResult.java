package net.kunmc.lab.werewolf.command;

public class CommandResult {
    private boolean isSuccess;
    private String message;

    public CommandResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String message() {
        return message;
    }
}

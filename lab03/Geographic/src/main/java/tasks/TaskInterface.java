package tasks;

import java.io.IOException;
import java.util.HashMap;

public interface TaskInterface {
    String getNameOfTask();
    HashMap<String, String> getAllItems() throws IOException, InterruptedException;
    String getQuestion();
    String checkIsCorrectAnswer(String Continent, String answer ) throws IOException, InterruptedException;
}

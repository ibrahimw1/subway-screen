import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainSimulator {

    private static final List<String> STATIONS = List.of(
            "Station A", "Station B", "Station C", "Station D",
            "Station E", "Station F", "Station G", "Station H",
            "Station I", "Station J", "Station K", "Station L"
    );

    private Random random;
    private int currentTrainIndex;
    private String direction;

    public TrainSimulator() {
        this.random = new Random();
        selectRandomTrain();
    }

    private void selectRandomTrain() {
        currentTrainIndex = random.nextInt(STATIONS.size());
        direction = (random.nextBoolean()) ? "Northbound" : "Southbound";
    }

    public String getUpdatedStationInfo() {
        selectRandomTrain();  // Randomly select a train every time this method is called

        StringBuilder infoBuilder = new StringBuilder();

        // Display current station
        infoBuilder.append("Current: ").append(STATIONS.get(currentTrainIndex)).append("<br>");

        // Display 1 past station
        int pastIndex = (direction.equals("Northbound")) ? currentTrainIndex - 1 : currentTrainIndex + 1;
        if (isValidStationIndex(pastIndex)) {
            infoBuilder.append("Past: ").append(STATIONS.get(pastIndex)).append("<br>");
        }

        // Display 4 future stations
        infoBuilder.append("Upcoming: ");
        for (int i = 1; i <= 4; i++) {
            int futureIndex = (direction.equals("Northbound")) ? currentTrainIndex + i : currentTrainIndex - i;
            if (isValidStationIndex(futureIndex)) {
                infoBuilder.append(STATIONS.get(futureIndex)).append(", ");
            }
        }
        infoBuilder.setLength(infoBuilder.length() - 2); // Remove trailing comma and space

        // Display direction
        infoBuilder.append("<br>Direction: ").append(direction);

        return "<html>" + infoBuilder.toString() + "</html>"; // Wrap with HTML tags for proper line breaks in JLabel
    }

    private boolean isValidStationIndex(int index) {
        return index >= 0 && index < STATIONS.size();
    }
}

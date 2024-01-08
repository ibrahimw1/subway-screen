

package ca.ucalgary.ensf380;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code Simulator} class starts the subway simulator, continuously reads and displays its output in the console,
 * and then displays the train positions from the generated files in the 'out' folder.
 * @author group1
 * @version 1.0
 */
public class Simulator {
    private static final String OUTPUT_FOLDER_PATH = "./out";

    /**
     * Entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the subway simulator
        Process subwayProcess = null;
        try {
            String[] command = {"java", "-jar", "./exe/SubwaySimulator.jar", "--in", "./data/subway.csv", "--out", "./out"};
            subwayProcess = new ProcessBuilder(command).start();
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
        final Process finalSubwayProcess = subwayProcess;

        // Set up a shutdown hook to ensure the simulator process is terminated properly
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (finalSubwayProcess != null) {
                finalSubwayProcess.destroy();
            }
        }));

        // initial timer is 1 minute
        Timer clock = new Timer();
        clock.schedule(new TimerTask() {
            @Override
            public void run() {
                if (finalSubwayProcess != null) {
                    finalSubwayProcess.destroy();
                }
                clock.cancel();
            }
        }, 1 * 60 * 1000); // 1 minute in milliseconds

        // Process the subway simulator output and display train positions
        processSubwayOutput(subwayProcess);
    }

    /**
     * Process the output stream of the subway simulator and display the simulator output in the console.
     *
     * @param subwayProcess the Process representing the subway simulator
     */
    private static void processSubwayOutput(Process subwayProcess) {
        try {
            // Process the output stream of the subway simulator
            InputStream subwayInputStream = subwayProcess.getInputStream();
            BufferedReader subwayReader = new BufferedReader(new InputStreamReader(subwayInputStream));

            String subwayLine;
            while ((subwayLine = subwayReader.readLine()) != null) {
                System.out.println(subwayLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

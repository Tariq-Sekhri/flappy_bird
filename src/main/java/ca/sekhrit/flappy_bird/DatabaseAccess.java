package ca.sekhrit.flappy_bird;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The DatabaseAccess class is responsible for managing the scores of the game.
 * It uses serialization to store and retrieve scores from a file.
 *@author Tariq Sekhri
 */
@NoArgsConstructor
@Data
public class DatabaseAccess {
    /**
     * A list to hold Score objects.
     */
    List<Score> scoreList = new ArrayList<Score>();

    /**
     * This method adds a Score object to a file.
     *
     * @param score The Score object to be added.
     * @throws IOException If an I/O error occurs.
     */
    public void addScore(Score score) throws IOException {
        boolean append = new File("scores.dat").exists();
        try (ObjectOutputStream out = append ? new AppendableObjectOutputStream(new FileOutputStream("scores.dat", true)) : new ObjectOutputStream(new FileOutputStream("scores.dat"))) {
            out.writeObject(score);
        }
    }

    /**
     * This method generates random scores and adds them to the file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void setData() throws IOException {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Score score = new Score("Player" + (i + 1), random.nextInt(100));
            addScore(score);
        }
    }

    /**
     * This method loads scores from a file into the scoreList.
     *
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the Score class is not found.
     */
    public void loadScoreList() throws IOException, ClassNotFoundException {
        scoreList.clear();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            while (true) {
                Score score = (Score) in.readObject();
                scoreList.add(score);
            }
        } catch (EOFException e) {
            // This exception is expected when the end of the file is reached.
        }
        scoreList.sort(Score::compareTo);
    }

    /**
     * This method returns a string representation of the scoreList.
     *
     * @return A string representation of the scoreList.
     * @throws Exception If an error occurs.
     */
    @SneakyThrows
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Score score : scoreList) {
            stringBuilder.append(score.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

}

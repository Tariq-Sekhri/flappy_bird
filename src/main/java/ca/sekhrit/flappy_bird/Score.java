package ca.sekhrit.flappy_bird;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
/**
 * The Score class represents a player's score in the game.
 * It implements Comparable for sorting scores and Serializable for file I/O.
 */
@Data
@AllArgsConstructor
public class Score implements Comparable<Score>, Serializable {


    private String name;
    private  int score;
    /**
     * This method returns a string representation of the score.
     *
     * @return A string in the format "name score".
     */
    @Override

    public String toString() {
        return name + " " + score;
    }
    /**
     * This method compares this score with another score.
     *
     * @param other The other score to compare with.
     * @return A negative integer, zero, or a positive integer as this score is less than, equal to, or greater than the specified score.
     */
    @Override
    public int compareTo(Score other) {
        return Integer.compare(other.score,this.score);
    }
    /**
     * This method increases the score by one.
     */
    public void increaseScore(){
        this.score++;
    }
}

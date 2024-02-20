// Question3(a)
// You are developing a student score tracking system that keeps track of scores from different assignments. The
// ScoreTracker class will be used to calculate the median score from the stream of assignment scores. The class
// should have the following methods:
// • ScoreTracker() initializes a new ScoreTracker object.
// • void addScore(double score) adds a new assignment score to the data stream.
// • double getMedianScore() returns the median of all the assignment scores in the data stream. If the number
// of scores is even, the median should be the average of the two middle scores.
// Input:
// ScoreTracker scoreTracker = new ScoreTracker();
// scoreTracker.addScore(85.5); // Stream: [85.5]
// scoreTracker.addScore(92.3); // Stream: [85.5, 92.3]
// scoreTracker.addScore(77.8); // Stream: [85.5, 92.3, 77.8]
// scoreTracker.addScore(90.1); // Stream: [85.5, 92.3, 77.8, 90.1]
// double median1 = scoreTracker.getMedianScore(); // Output: 87.8 (average of 90.1 and 85.5)
// scoreTracker.addScore(81.2); // Stream: [85.5, 92.3, 77.8, 90.1, 81.2]
// scoreTracker.addScore(88.7); // Stream: [85.5, 92.3, 77.8, 90.1, 81.2, 88.7]
// double median2 = scoreTracker.getMedianScore(); // Output: 87.1 (average of 88.7 and 85.5)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreTrackerApp {
  private List<Double> scores; 
  public ScoreTrackerApp() {
    scores = new ArrayList<>();
  }

  
  public void addScore(double score) {
    scores.add(score); 
    Collections.sort(scores); 
  }

  
  public double getMedianScore() {
    int size = scores.size();
    if (size == 0) {
      throw new IllegalStateException("No scores available.");
    }

    int middleIndex = size / 2;
    if (size % 2 == 0) {
     
      double lowerMedian = scores.get(middleIndex - 1);
      double upperMedian = scores.get(middleIndex);
      return (lowerMedian + upperMedian) / 2.0;
    } else {
      
      return scores.get(middleIndex);
    }
  }

  public static void main(String[] args) {
   
    ScoreTrackerApp scoreTracker = new ScoreTrackerApp();
    scoreTracker.addScore(85.5);
    scoreTracker.addScore(92.3);
    scoreTracker.addScore(77.8);
    scoreTracker.addScore(90.1);
    double median1 = scoreTracker.getMedianScore();
    System.out.println("Median score 1: " + median1);

    scoreTracker.addScore(81.2);
    scoreTracker.addScore(88.7);
    double median2 = scoreTracker.getMedianScore();
    System.out.println("Median score 2: " + median2);
  }
}

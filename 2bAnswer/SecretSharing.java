//Question2(b)
//You are given an integer n representing the total number of individuals. Each individual is identified by a unique
//ID from 0 to n-1. The individuals have a unique secret that they can share with others.
//The secret-sharing process begins with person 0, who initially possesses the secret. Person 0 can share the secret
//with any number of individuals simultaneously during specific time intervals. Each time interval is represented by
//a tuple (start, end) where start and end are non-negative integers indicating the start and end times of the interval.
//You need to determine the set of individuals who will eventually know the secret after all the possible secret-
//sharing intervals have occurred.
//Example:
//Input: n = 5, intervals = [(0, 2), (1, 3), (2, 4)], firstPerson = 0
//Output: [0, 1, 2, 3, 4]
//Explanation:
//In this scenario, we have 5 individuals labeled from 0 to 4.
//The secret-sharing process starts with person 0, who has the secret at time 0. At time 0, person 0 can share the
//secret with any other person. Similarly, at time 1, person 0 can also share the secret. At time 2, person 0 shares the
//secret again, and so on.
//Given the intervals [(0, 2), (1, 3), (2, 4)], we can observe that during these intervals, person 0 shares the secret with
//every other individual at least once.
//Hence, after all the secret-sharing intervals, individuals 0, 1, 2, 3, and 4 will eventually know the secret.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecretSharing {
  public static List<Integer> findSecretRecipients(int totalIndividuals, int[][] sharingIntervals, int initialPerson) {
    
    Set<Integer> secretRecipients = new HashSet<>();
    
    secretRecipients.add(initialPerson);

    for (int[] interval : sharingIntervals) {
      int intervalStart = interval[0]; 
      int intervalEnd = interval[1]; 
      if (intervalStart <= initialPerson && initialPerson <= intervalEnd) {
       
        for (int i = 0; i < totalIndividuals; i++) {
          secretRecipients.add(i);
        }
      }
    }

    
    return new ArrayList<>(secretRecipients);
  }

  public static void main(String[] args) {
    
    int totalIndividuals = 5;
    
    int[][] sharingIntervals = { { 0, 2 }, { 1, 3 }, { 2, 4 } };
    
    int initialPerson = 0;

    
    List<Integer> secretRecipients = findSecretRecipients(totalIndividuals, sharingIntervals, initialPerson);

    System.out.println("Individuals who eventually know the secret:");
    for (int recipient : secretRecipients) {
      System.out.print(recipient + " ");
    }
  }
}


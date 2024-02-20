// Question 1(a)
// You are a planner working on organizing a series of events in a row of n venues. Each venue can be decorated with
// one of the k available themes. However, adjacent venues should not have the same theme. The cost of decorating
// each venue with a certain theme varies.
// The costs of decorating each venue with a specific theme are represented by an n x k cost matrix. For example,
// costs [0][0] represents the cost of decorating venue 0 with theme 0, and costs[1][2] represents the cost of
// decorating venue 1 with theme 2. Your task is to find the minimum cost to decorate all the venues while adhering
// to the adjacency constraint.
// For example, given the input costs = [[1, 5, 3], [2, 9, 4]], the minimum cost to decorate all the venues is 5. One
// possible arrangement is decorating venue 0 with theme 0 and venue 1 with theme 2, resulting in a minimum cost of
// 1 + 4 = 5. Alternatively, decorating venue 0 with theme 2 and venue 1 with theme 0 also yields a minimum cost of
// 3 + 2 = 5.
// Write a function that takes the cost matrix as input and returns the minimum cost to decorate all the venues while
// satisfying the adjacency constraint.
// Please note that the costs are positive integers.
// Example: Input: [[1, 3, 2], [4, 6, 8], [3, 1, 5]] Output: 7
// Explanation: Decorate venue 0 with theme 0, venue 1 with theme 1, and venue 2 with theme 0. Minimum cost: 1 +
// 6 + 1 = 7.

public class MinimumCostDecoration {

  public static int findMinimumCost(int[][] venueThemes) {
    if (venueThemes == null || venueThemes.length == 0 || venueThemes[0].length == 0) {
      return 0;
    }

    int numVenues = venueThemes.length;
    int numThemes = venueThemes[0].length;

    int[][] minCosts = new int[numVenues][numThemes];

    System.arraycopy(venueThemes[0], 0, minCosts[0], 0, numThemes);
    for (int venueIndex = 1; venueIndex < numVenues; venueIndex++) {
      for (int themeIndex = 0; themeIndex < numThemes; themeIndex++) {
        minCosts[venueIndex][themeIndex] = Integer.MAX_VALUE;
        for (int prevThemeIndex = 0; prevThemeIndex < numThemes; prevThemeIndex++) {
          if (prevThemeIndex != themeIndex) {
            minCosts[venueIndex][themeIndex] = Math.min(minCosts[venueIndex][themeIndex],
                minCosts[venueIndex - 1][prevThemeIndex]);
          }
        }
        minCosts[venueIndex][themeIndex] += venueThemes[venueIndex][themeIndex];
      }
    }

    int minCost = Integer.MAX_VALUE;
    for (int cost : minCosts[numVenues - 1]) {
      minCost = Math.min(minCost, cost);
    }

    return minCost;
  }

  public static void main(String[] args) {
    int[][] venueThemes = { { 1, 3, 2 }, { 4, 6, 8 }, { 3, 1, 5 } };
    int minCost = findMinimumCost(venueThemes);
    System.out.println("The minimum cost of venue decoration is: " + minCost);
  }
}

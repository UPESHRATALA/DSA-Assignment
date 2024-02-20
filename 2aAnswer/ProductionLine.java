//Question 2(a)
//You are the manager of a clothing manufacturing factory with a production line of super sewing machines. The
//production line consists of n super sewing machines placed in a line. Initially, each sewing machine has a certain
//number of dresses or is empty.
//For each move, you can select any m (1 <= m <= n) consecutive sewing machines on the production line and pass
//one dress from each selected sewing machine to its adjacent sewing machine simultaneously.
//Your goal is to equalize the number of dresses in all the sewing machines on the production line. You need to
//determine the minimum number of moves required to achieve this goal. If it is not possible to equalize the number
//of dresses, return -1.
//Input: [1,0,5]
//Output: 2
//Example 1:
//Imagine you have a production line with the following number of dresses in each sewing machine: [1,0,5]. The
//production line has 5 sewing machines.
//Here's how the process works:
//1. Initial state: [1,0,5]
//2. Move 1: Pass one dress from the third sewing machine to the first sewing machine, resulting in [1,1,4]
//3. Move 2: Pass one dress from the second sewing machine to the first sewing machine, and from third to
//first sewing Machine [2,1,3]
//4. Move 3: Pass one dress from the third sewing machine to the second sewing machine, resulting in [2,2,2]
//After these 3 moves, the number of dresses in each sewing machine is equalized to 2. Therefore, the minimum
//number of moves required to equalize the number of dresses is 3

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class ProductionLine {
  public static void main(String[] args) {
    ProductionLine obj = new ProductionLine();
    int n = 5;
    int[][] meetings = {
        { 0, 2, 1 },
        { 1, 3, 2 },
        { 2, 4, 3 }
    };
    int firstPerson = 0;

    List<Integer> result = obj.findAllPeople(n, meetings, firstPerson);
    System.out.println("People who know the secret: " + result);
  }

  public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

    Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

    Map<Integer, List<int[]>> sameTimeMeetings = new TreeMap<>();

    for (int[] meeting : meetings) {
      int x = meeting[0], y = meeting[1], t = meeting[2];
      sameTimeMeetings.computeIfAbsent(t, k -> new ArrayList<>()).add(new int[] { x, y });
    }

    UnionFind graph = new UnionFind(n);
    graph.unite(firstPerson, 0);

    for (int t : sameTimeMeetings.keySet()) {

      for (int[] meeting : sameTimeMeetings.get(t)) {
        int x = meeting[0], y = meeting[1];
        graph.unite(x, y);
      }

      for (int[] meeting : sameTimeMeetings.get(t)) {
        int x = meeting[0], y = meeting[1];
        if (!graph.connected(x, 0)) {

          graph.reset(x);
          graph.reset(y);
        }
      }
    }

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      if (graph.connected(i, 0)) {
        ans.add(i);
      }
    }
    return ans;
  }
}

class UnionFind {
  private int[] parent;
  private int[] rank;

  public UnionFind(int n) {
    parent = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; ++i) {
      parent[i] = i;
    }
  }

  public int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  public void unite(int x, int y) {
    int px = find(x);
    int py = find(y);
    if (px != py) {

      if (rank[px] > rank[py]) {
        parent[py] = px;
      } else if (rank[px] < rank[py]) {
        parent[px] = py;
      } else {
        parent[py] = px;
        rank[px] += 1;
      }
    }
  }

  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }

  public void reset(int x) {
    parent[x] = x;
    rank[x] = 0;
  }
}
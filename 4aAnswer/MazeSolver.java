//Question4(a)
//You are given a 2D grid representing a maze in a virtual game world. The grid is of size m x n and consists of
//different types of cells:
//'P' represents an empty path where you can move freely. 'W' represents a wall that you cannot pass through. 'S'
//represents the starting point. Lowercase letters represent hidden keys. Uppercase letters represent locked doors.
//You start at the starting point 'S' and can move in any of the four cardinal directions (up, down, left, right) to
//adjacent cells. However, you cannot walk through walls ('W').
//As you explore the maze, you may come across hidden keys represented by lowercase letters. To unlock a door
//represented by an uppercase letter, you need to collect the corresponding key first. Once you have a key, you can
//pass through the corresponding locked door.
//For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English
//alphabet in the maze. This means that there is exactly one key for each door, and one door for each key. The letters
//used to represent the keys and doors follow the English alphabet order.
//Your task is to find the minimum number of moves required to collect all the keys. If it is impossible to collect all
//the keys and reach the exit, return -1.
//Example:
//Input: grid = [ ["S","P","q","P","P"], ["W","W","W","P","W"], ["r","P","Q","P","R"]

import java.util.LinkedList;
import java.util.Queue;

public class MazeSolver {

   
    public static int findMinimumMovesToCollectAllKeys(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();

       
        int targetKeys = 0;

       
        int startX = 0, startY = 0;

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                } else if (cell == 'E') {
                    // Set the bit for the exit door
                    targetKeys |= (1 << ('f' - 'a'));
                } else if (cell >= 'a' && cell <= 'f') {
                    // Set the bit for the key
                    targetKeys |= (1 << (cell - 'a'));
                }
            }
        }

        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[rows][cols][1 << 6]; 
        queue.offer(new int[] { startX, startY, 0, 0 }); 

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int keys = current[2];
            int steps = current[3];

            
            if (keys == targetKeys) {
                return steps;
            }

            
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX].charAt(newY) != 'W') {
                    char cell = grid[newX].charAt(newY);

                    
                    if (cell == 'E' || cell == 'P' || (cell >= 'a' && cell <= 'f')
                            || (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) != 0)) {
                        int newKeys = keys;
                        
                        if (cell >= 'a' && cell <= 'f') {
                            newKeys |= (1 << (cell - 'a'));
                        }

                        
                        if (!visited[newX][newY][newKeys]) {
                            visited[newX][newY][newKeys] = true;
                            queue.offer(new int[] { newX, newY, newKeys, steps + 1 });
                        }
                    }
                }
            }
        }

        
        return -1;
    }

    public static void main(String[] args) {
        String[] grid = { "SPaPP", "WWWPW", "bPAPB" };
        int result = findMinimumMovesToCollectAllKeys(grid);
        System.out.println("Minimum number of moves: " + result); 
    }
}

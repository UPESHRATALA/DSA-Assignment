//Question5(b)
//Assume you were hired to create an application for an ISP, and there are n network devices, such as routers,
//that are linked together to provide internet access to users. You are given a 2D array that represents network
//connections between these network devices. write an algorithm to return impacted network devices, If there is
//a power outage on a certain device, these impacted device list assist you notify linked consumers that there is a
//power outage and it will take some time to rectify an issue.
//Input: edges= {{0,1},{0,2},{1,3},{1,6},{2,4},{4,6},{4,5},{5,7}}
//Target Device (On which power Failure occurred): 4

import java.util.*;

public class ImpactedNetworkDevices {

  
  public static List<Integer> findImpactedDevices(int[][] network, int targetDevice) {
    
    Map<Integer, Set<Integer>> networkMap = buildNetworkMap(network);

    
    Set<Integer> impactedDevices = new HashSet<>();

    
    depthFirstSearch(networkMap, targetDevice, impactedDevices);

    
    impactedDevices.remove(targetDevice);

    
    return new ArrayList<>(impactedDevices);
  }

  
  private static Map<Integer, Set<Integer>> buildNetworkMap(int[][] network) {
    Map<Integer, Set<Integer>> networkGraph = new HashMap<>();

    
    for (int[] connection : network) {
      int device1 = connection[0];
      int device2 = connection[1];

      networkGraph.computeIfAbsent(device1, k -> new HashSet<>()).add(device2);
      networkGraph.computeIfAbsent(device2, k -> new HashSet<>()).add(device1);
    }

    return networkGraph;
  }

  
  private static void depthFirstSearch(Map<Integer, Set<Integer>> networkMap, int currentDevice,
      Set<Integer> impactedDevices) {
    Stack<Integer> stack = new Stack<>();
    stack.push(currentDevice);

    while (!stack.isEmpty()) {
      int current = stack.pop();
      impactedDevices.add(current);

      
      Set<Integer> neighbors = networkMap.getOrDefault(current, Collections.emptySet());

      
      for (int neighbor : neighbors) {
        if (!impactedDevices.contains(neighbor)) {
          stack.push(neighbor);
        }
      }
    }
  }

  public static void main(String[] args) {
    
    int[][] network = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };
    int targetDevice = 4;

    
    List<Integer> impactedDevices = findImpactedDevices(network, targetDevice);
    System.out.println("Impacted Device List: " + impactedDevices);
  }
}

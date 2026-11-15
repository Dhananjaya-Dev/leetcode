import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build the adjacency list and compute in-degrees for each node
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            int course = p[0];
            int prereq = p[1];
            adj.get(prereq).add(course);
            inDegree[course]++;
        }
        
        // Add all courses with 0 in-degree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Process courses using BFS
        int processedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedCourses++;
            
            // Reduce in-degree for neighboring courses
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If we processed all courses, there are no cycles
        return processedCourses == numCourses;
    }
}
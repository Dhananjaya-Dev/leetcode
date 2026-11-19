import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Adjacency list to represent the graph: b_i -> a_i
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Array to track the number of prerequisites (in-degree) for each course
        int[] inDegree = new int[numCourses];
        
        // Build the graph
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            inDegree[course]++;
        }
        
        // Add all courses with no prerequisites to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] order = new int[numCourses];
        int index = 0;
        
        // Process courses in topological order
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current;
            
            // Reduce in-degree for dependent courses
            for (int nextCourse : adj.get(current)) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        // If we couldn't take all courses, there is a cycle
        if (index != numCourses) {
            return new int[0];
        }
        
        return order;
    }
}
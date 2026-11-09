import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int duration = load[i];

            // Free up servers whose tasks have finished by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                availableServers.add(busyServers.poll()[1]);
            }

            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }

            // Find the first available server >= (i % k)
            Integer serverId = availableServers.ceiling(i % k);
            if (serverId == null) {
                // Wrap around to the beginning if none found >= (i % k)
                serverId = availableServers.first();
            }

            // Assign the request to the chosen server
            availableServers.remove(serverId);
            busyServers.offer(new int[] { currTime + duration, serverId });
            requestCount[serverId]++;
            maxRequests = Math.max(maxRequests, requestCount[serverId]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
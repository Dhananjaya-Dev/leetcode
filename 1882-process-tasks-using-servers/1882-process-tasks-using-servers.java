class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Priority queue for free servers: sorted by weight first, then by index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Priority queue for busy servers: sorted by available time
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[0], b[0]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Free up servers that have completed their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] busy = busyServers.poll();
                int serverWeight = (int) busy[1];
                int serverIndex = (int) busy[2];
                freeServers.offer(new int[] { serverWeight, serverIndex });
            }

            // If no free servers are available, jump time to when the next server becomes free
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    long[] busy = busyServers.poll();
                    freeServers.offer(new int[] { (int) busy[1], (int) busy[2] });
                }
            }

            // Assign the task to the top server from the free queue
            int[] server = freeServers.poll();
            ans[j] = server[1];
            busyServers.offer(new long[] { currentTime + tasks[j], server[0], server[1] });
        }

        return ans;
    }
}
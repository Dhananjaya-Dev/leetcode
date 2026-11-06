class Solution {
public:
    vector<int> countServers(int n, vector<vector<int>>& logs, int x, vector<int>& queries) {
        int m = queries.size();
        vector<int> ans(m);
        
        // Store queries with their original indices so we can restore the original order
        vector<pair<int, int>> q(m);
        for (int i = 0; i < m; ++i) {
            q[i] = {queries[i], i};
        }
        
        // Sort queries by time
        sort(q.begin(), q.end());
        
        // Sort logs by time
        sort(logs.begin(), logs.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        
        int left = 0, right = 0;
        int active_servers = 0;
        vector<int> count(n + 1, 0);
        
        for (int i = 0; i < m; ++i) {
            int time_val = q[i].first;
            int original_idx = q[i].second;
            int window_start = time_val - x;
            
            // Add logs that fall within the right bound (<= time_val)
            while (right < logs.size() && logs[right][1] <= time_val) {
                int server_id = logs[right][0];
                if (count[server_id] == 0) {
                    active_servers++;
                }
                count[server_id]++;
                right++;
            }
            
            // Remove logs that fall out of the left bound (< window_start)
            while (left < logs.size() && logs[left][1] < window_start) {
                int server_id = logs[left][0];
                count[server_id]--;
                if (count[server_id] == 0) {
                    active_servers--;
                }
                left++;
            }
            
            // Total servers minus servers with at least one request
            ans[original_idx] = n - active_servers;
        }
        
        return ans;
    }
};
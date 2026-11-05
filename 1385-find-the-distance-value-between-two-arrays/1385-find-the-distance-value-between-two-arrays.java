class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        java.util.Arrays.sort(arr2);
        int count = 0;
        
        for (int x : arr1) {
            int low = 0, high = arr2.length - 1;
            boolean found = false;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (Math.abs(x - arr2[mid]) <= d) {
                    found = true;
                    break;
                }
                if (arr2[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            
            if (!found) {
                count++;
            }
        }
        
        return count;
    }
}
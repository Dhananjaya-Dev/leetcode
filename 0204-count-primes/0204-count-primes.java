class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        
        // isPrime[i] will be true if i is prime, false otherwise.
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        
        // Sieve of Eratosthenes
        for (int p = 2; p * p < n; p++) {
            if (isPrime[p]) {
                // Mark all multiples of p starting from p * p as false
                for (int i = p * p; i < n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        // Count how many numbers remain true
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        
        return count;
    }
}
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currentTank = 0;
        int startingStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int netGain = gas[i] - cost[i];
            totalTank += netGain;
            currentTank += netGain;

            // If the car runs out of gas, station i (and earlier) cannot be the starting point
            if (currentTank < 0) {
                startingStation = i + 1;
                currentTank = 0;
            }
        }

        // If the total gas available is less than total cost, completing the circuit is impossible
        return totalTank >= 0 ? startingStation : -1;
    }
}
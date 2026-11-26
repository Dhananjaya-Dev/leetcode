class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        boolean aliceTurn = true;
        
        while (n >= take) {
            n -= take;
            take--;
            aliceTurn = !aliceTurn;
        }
        
        // If it's Bob's turn when the loop ends, Bob couldn't move, so Alice wins
        return !aliceTurn;
    }
}
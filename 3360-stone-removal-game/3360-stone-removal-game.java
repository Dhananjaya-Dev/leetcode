class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean aliceTurn = true;
        
        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            aliceTurn = !aliceTurn;
        }
        
        // If it's Bob's turn and he can't make a move, Alice wins
        return !aliceTurn;
    }
}
package GUI;

public enum TurnType {
    PLAYER1_KILL_TURN, PLAYER1_RELIVE_TURN, PLAYER2_KILL_TURN, PLAYER2_RELIVE_TURN, NEXT_GENERATION;

    @Override
    public String toString(){
        String turn = "";
        switch (this){
            case PLAYER1_KILL_TURN:
                turn = "Player1 kill turn.";
                break;
            case PLAYER1_RELIVE_TURN:
                turn = "Player1 relive turn.";
                break;
            case PLAYER2_KILL_TURN:
                turn = "Player2 kill turn.";
                break;
            case PLAYER2_RELIVE_TURN:
                turn = "Player2 relive turn.";
                break;
            case NEXT_GENERATION:
                turn = "Click Next button.";
        }
        return turn;
    }
}

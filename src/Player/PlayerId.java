package Player;

public enum PlayerId {
    PLAYER_A,
    PLAYER_B;

    @Override
    public String toString(){
        String name = "";
        switch (this){
            case PLAYER_A -> name = "player1 ";
            case PLAYER_B -> name = "player2 ";
        }
        return name;
    }
}

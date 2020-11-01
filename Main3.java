/**
 * Not functionnal code
 */

import java.util.Scanner;
import java.util.Random;

abstract class Result {
    abstract int points();
}

class Tie extends Result {
    public Tie() {}
    public int points() {
        return 0;
    }

    public String toString() {
        return "Tie";
    }
}

class Win extends Result {
    public Win() {}
    public int points() {
        return 1;
    }

    public String toString() {
        return "Win";
    }
}

class Loss extends Result {
    public Loss() {}
    public int points() {
        return -1;
    }

    public String toString() {
        return "Loss";
    }
}

abstract class HandType {
    public static Result activate(HandRock hr, HandCisor hc) {
        return new Win();
    }

    public static Result activate(HandRock hr, HandPaper hc) {
        return new Loss();
    }

    public static Result activate(HandCisor hr, HandRock hc) {
        return new Loss();
    }

    public static Result activate(HandCisor hr, HandPaper hc) {
        return new Win();
    }

    public static Result activate(HandPaper hr, HandRock hc) {
        return new Win();
    }

    public static Result activate(HandPaper hr, HandCisor hc) {
        return new Loss();
    }

    public static Result activate(HandRock ht, HandRock ht2) {
        return new Tie();
    }

    public static Result activate(HandPaper ht, HandPaper ht2) {
        return new Tie();
    }

    public static Result activate(HandCisor ht, HandCisor ht2) {
        return new Tie();
    }
}

class HandRock extends HandType {}

class HandPaper extends HandType {}

class HandCisor extends HandType {}

class Main3 {
    public static HandType[] hands = {new HandRock(), new HandPaper(), new HandCisor()};
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Game start");
        game(0);
    }

    public static void game(int score) {
        try {
            System.out.println("Score is " + score);
            System.out.print("Choose (0 => Rock, 1 => Paper, 2 => Scisors) ");
            HandType player = hands[Integer.parseInt(sc.nextLine())];
            HandType other = hands[new Random().nextInt(3)];
            Result res = HandType.activate(player.getClass().cast(player), other.getClass().cast(other));
            System.out.println(res);
            game(score + res.points());
        }
        catch(Exception e){
            System.out.println("Game end");
        }
    }
}
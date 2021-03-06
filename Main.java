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
    abstract public Result activate(HandType other);
    abstract public Result withPaper();
    abstract public Result withCisors();
    abstract public Result withRock();
}

class HandRock extends HandType {
    public Result activate(HandType other) {
        return other.withRock();
    }

    public Result withPaper() {
        return new Loss();
    }

    public Result withCisors() {
        return new Win();
    }

    public Result withRock() {
        return new Tie();
    }
}

class HandPaper extends HandType {
    public Result activate(HandType other) {
        return other.withPaper();
    }

    public Result withPaper() {
        return new Tie();
    }

    public Result withCisors() {
        return new Loss();
    }

    public Result withRock() {
        return new Win();
    }
}

class HandCisor extends HandType {
    public Result activate(HandType other) {
        return other.withCisors();
    }

    public Result withPaper() {
        return new Win();
    }

    public Result withCisors() {
        return new Tie();
    }

    public Result withRock() {
        return new Loss();
    }
}

class Main {
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
            Result res = player.activate(other);
            System.out.println(res);
            game(score + res.points());
        }
        catch(Exception e){
            System.out.println("Game end");
        }
    }
}
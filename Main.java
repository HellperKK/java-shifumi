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
}

class Win extends Result {
    public Win() {}
    public int points() {
        return 1;
    }
}

class Loss extends Result {
    public Loss() {}
    public int points() {
        return -1;
    }
}

abstract class HandType {
    abstract public Result activate(HandType other);
    abstract public Result withPaper();
    abstract public Result withCisors();
    abstract public Result withRock();
}

class HandRock extends HandType {
    public HandRock() {

    }

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
    public HandPaper() {

    }

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
    public HandCisor() {

    }

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
    public static void main(String[] args) {
        System.out.println("Lancement de partie");
        game(0);
    }

    public static void game(int score) {
        try {
            System.out.println("Score is " + score);
            HandType[] hands = {new HandRock(), new HandPaper(), new HandCisor()};
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose (Rock, Paper; Scisors) ");
            HandType player = hands[Integer.parseInt(sc.nextLine())];
            HandType other = hands[new Random().nextInt(3)];
            Result res = player.activate(other);
            game(score + res.points());
        }
        catch(Exception e){
            System.out.println("Game end");
        }
    }
}
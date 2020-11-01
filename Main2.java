import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

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
    public static HashMap<HandType, Result> res;
    public static void init() {}

    public Result activate(HandType other) {
        return res.get(other);
    }

    public boolean equals(Object other) {
        return this.getClass().isInstance(other);
    }

    public int hashCode() {
        return 31 * 17 * this.getClass().getName().hashCode();
    }
}

class HandRock extends HandType {
    public static HashMap<HandType, Result> res;
    public static void init() {
        res = new HashMap<HandType, Result>();
        res.put(new HandRock(), new Tie());
        res.put(new HandPaper(), new Loss());
        res.put(new HandCisor(), new Win());
    }

    public Result activate(HandType other) {
        return res.get(other);
    }
}

class HandPaper extends HandType {
    public static HashMap<HandType, Result> res;
    public static void init() {
        res = new HashMap<HandType, Result>();
        res.put(new HandRock(), new Win());
        res.put(new HandPaper(), new Tie());
        res.put(new HandCisor(), new Loss());
    }

    public Result activate(HandType other) {
        return res.get(other);
    }
}

class HandCisor extends HandType {
    public static HashMap<HandType, Result> res;
    public static void init() {
        res = new HashMap<HandType, Result>();
        res.put(new HandRock(), new Loss());
        res.put(new HandPaper(), new Win());
        res.put(new HandCisor(), new Tie());
    }

    public Result activate(HandType other) {
        return res.get(other);
    }
}

class Main2 {
    public static HandType[] hands = {new HandRock(), new HandPaper(), new HandCisor()};
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        HandRock.init();
        HandPaper.init();
        HandCisor.init();

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
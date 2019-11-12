import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

abstract class ResultBis {
    abstract int points();
}

class TieBis extends ResultBis {
    public TieBis() {}
    public int points() {
        return 0;
    }
}

class WinBis extends ResultBis {
    public WinBis() {}
    public int points() {
        return 1;
    }
}

class LossBis extends ResultBis {
    public LossBis() {}
    public int points() {
        return -1;
    }
}

abstract class HandTypeBis {
    private HashMap<HandTypeBis, ResultBis> res;
    public ResultBis activate(HandTypeBis other) {
        return res.get(other);
    }
}

class HandRockBis extends HandTypeBis {
    private HashMap<HandTypeBis, ResultBis> res;
    public HandRockBis() {
        res = new HashMap<HandTypeBis, ResultBis>();
        res.put(new HandRockBis(), new TieBis());
        res.put(new HandPaperBis(), new LossBis());
        res.put(new HandCisorBis(), new WinBis());
    }
}

class HandPaperBis extends HandTypeBis {
    private HashMap<HandTypeBis, ResultBis> res;
    public HandPaperBis() {
        res = new HashMap<HandTypeBis, ResultBis>();
        res.put(new HandRockBis(), new WinBis());
        res.put(new HandPaperBis(), new TieBis());
        res.put(new HandCisorBis(), new LossBis());
    }
}

class HandCisorBis extends HandTypeBis {
    private HashMap<HandTypeBis, ResultBis> res;
    public HandCisorBis() {
        res = new HashMap<HandTypeBis, ResultBis>();
        res.put(new HandRockBis(), new LossBis());
        res.put(new HandPaperBis(), new WinBis());
        res.put(new HandCisorBis(), new TieBis());
    }
}

class Main2 {
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
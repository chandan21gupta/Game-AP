import java.io.*;
import java.util.*;

class Game {
    private static ArrayList<User> _users = new ArrayList<User>();
    Game() throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        this.startGame();
        int option = Integer.parseInt(buffer.readLine());
        while(option != 3) {
            switch (option) {
                case 1:
                    System.out.println("Enter Username");
                    String name = buffer.readLine();
                    System.out.println("Choose a Hero");
                    System.out.println("1) Warrior");
                    System.out.println("2) Thief");
                    System.out.println("3) Mage");
                    System.out.println("4) Healer");
                    int op = Integer.parseInt(buffer.readLine());
                    User u = new User(name, op);
                    _users.add(u);
                    break;
                case 2:
                    System.out.println("Enter Username");
                    String n = buffer.readLine();
                    User searched_user = this.searchUser(n);
                    System.out.println("User Found... logging in");
                    searched_user.welcomeUser();
                    break;
            }
            this.startGame();
            option = Integer.parseInt(buffer.readLine());
        }
    }

    private void startGame() {
        System.out.println("Welcome to ArchLegends");
        System.out.println("Choose your option");
        System.out.println("1) New User");
        System.out.println("2) Existing User");
        System.out.println("3) Exit");
    }

    private User searchUser(String name) {
        for(User user:_users) {
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }
}

class User {
    private Graph graph;
    final private String _name;
    private Hero _h;
    private int location = -1;
    User(String name,int option) {
        graph = new Graph();
        this._name = name;
        if(option == 1) {
            _h = new Warrior();
        }
        else if(option == 2) {
          _h = new Thief();
        }
        else if(option == 3) {
            _h = new Mage();
        }
        else if(option == 4) {
            _h = new Healer();
        }
        System.out.println("User Creation done. Username: "+this.getName()+". Hero type: "+this.getHero().getName()+". Log in to play the game. Exiting");
    }

    int getLocation() {
        return this.location;
    }

    String getName() {
        return this._name;
    }

    void setLocation(int location) {
        this.location = location;
    }

    Hero getHero() {
        return this._h;
    }

    void welcomeUser() throws java.io.IOException {
        System.out.println("Welcome "+this.getName());
        this.loadGame();
    }
    void loadGame() throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        if(this.getLocation() == -1) {
            System.out.println("You are at the starting location. Choose path");
        }
        else {
            System.out.println("You are at the "+this.getLocation()+" location. Choose path");
        }
        if(this.getLocation() == -1 || this.getLocation() == 0 || this.getLocation() == 1) {
            int l1 = this.getLocation()+1;
            int l2 = this.getLocation()+4;
            int l3 = this.getLocation()+7;
            System.out.println("1) Go to location "+l1);
            System.out.println("2) Go to location "+l2);
            System.out.println("3) Go to location "+l3);
            System.out.println("4) Enter -1 to exit");
            int option = Integer.parseInt(buffer.readLine());
            if(option == -1) {
                return;
            }
            else if(option == 1) {
                this.setLocation(this.getLocation()+1);
            }
            else if(option == 2) {
                this.setLocation(this.getLocation()+4);
            }
            else {
                this.setLocation(this.getLocation()+7);
            }

        }
        else if(this.getLocation() == 3 || this.getLocation() == 4) {
            int l1 = this.getLocation()-2;
            int l2 = this.getLocation()+1;
            int l3 = this.getLocation()+4;
            System.out.println("1) Go to location "+l1);
            System.out.println("2) Go to location "+l2);
            System.out.println("3) Go to location "+l3);
            System.out.println("4) Enter -1 to exit");
            System.out.println("5) Go back");
            int option = Integer.parseInt(buffer.readLine());
            if(option == -1) {
                return;
            }
            else if(option == 1) {
                this.setLocation(this.getLocation()-2);
            }
            else if(option == 2) {
                this.setLocation(this.getLocation()+1);
            }
            else {
                this.setLocation(this.getLocation()+4);
            }
        }
        else if(this.getLocation() == 2 || this.getLocation() == 5 || this.getLocation() == 8) {
            System.out.println("1) Go to location "+9);
            System.out.println("2) Go back");
        }
        if(this.getLocation() == 6 || this.getLocation() == 7) {
            int l1 = this.getLocation()-5;
            int l2 = this.getLocation()-2;
            int l3 = this.getLocation()+1;
            System.out.println("1) Go to location "+l1);
            System.out.println("2) Go to location "+l2);
            System.out.println("3) Go to location "+l3);
            System.out.println("4) Enter -1 to exit");
            System.out.println("5) Go back");
            int option = Integer.parseInt(buffer.readLine());
            if(option == -1) {
                return;
            }
            else if(option == 1) {
                this.setLocation(this.getLocation()-5);
            }
            else if(option == 2) {
                this.setLocation(this.getLocation()-2);
            }
            else {
                this.setLocation(this.getLocation()+1);
            }
        }
        this.moveLocation();
    }

    void moveLocation() throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int protection = 0;
        int moves = 0;
        this.graph.setMonster(this.getLocation());
        Monster m = this.graph.getMonster(this.getLocation());
        System.out.println("Fight Started. You are fighting a level "+m.getLevel()+" Monster.");
        while(m.gethp() != 0 && this.getHero().gethp() != 0){
            System.out.println("Choose move:");
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if(moves >= 4) {
                System.out.println("3) Special Attack");
            }
            int option = Integer.parseInt(buffer.readLine());
            switch(option) {
                case 1 :
                    System.out.println("You choose to attack");
                    this.getHero().attack(m);
                    moves++;
                    break;
                case 2 :
                    protection = this.getHero().defense(m);
                    moves++;
                    break;
                case 3 :
                    System.out.println("Special power activated");
                    System.out.println("Performing special power");
                    moves = 0;
                    this.getHero().special_power(m,moves);
                    break;
            }
            System.out.println("Your HP: "+this.getHero().gethp()+" Monsters HP: "+m.gethp());
            System.out.println("Monster attack!");
            m.monster_attack(this.getHero(),protection);
            System.out.println("Your HP: "+this.getHero().gethp()+" Monsters HP: "+m.gethp());
            protection = 0;
            if(moves == 3) {
                this.getHero().special_power(m, moves);
            }
        }
        if(m.gethp()==0) {
            System.out.println("Monster killed!");
            System.out.println(this.getHero().getxp()+" XP awarded.");
            System.out.println("Level Up: level:"+this.getHero().getlevel());
            System.out.println("Fight won proceed to the next location.");
        }
    }
}

class hero {

    public int level = 1;
    public int XP = 0;
    public double HP = 100;

    public void setLevel() {
        if(this.getLevel() == 1) {
            this.setHP(150);
            this.setXP(20);
            this.level++;
        }
        else if(this.getLevel() == 2) {
            this.setHP(200);
            this.setXP(40);
            this.level++;
        }
        else if(this.getLevel() == 3) {
            this.setHP(250);
            this.setXP(60);
            this.level++;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public int getXP() {
        return this.XP;
    }

    public void setHP(double HP) {
        if(HP < 0) {
            this.HP = HP;
            return;
        }
        this.HP = HP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public double getHP() {
        return this.HP;
    }
}

class Warrior extends hero implements Hero {

    public int damage = 10;
    public int protection = 3;
    public int moves = 0;
    private String _name = "Warrior";

    @Override
    public void attack(Monster m) {
        m.sethp(m.gethp()-this.damage);
        System.out.println("You inflicted "+this.damage+" to monster.");
    }

    @Override
    public int defense(Monster m) {
        return this.protection;
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void special_power(Monster m,int moves) {
        if(moves == 3) {

            this.damage = 10;
            this.protection = 3;
            return;
        }
        this.damage = 15;
        this.protection = 8;
    }

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public int getxp() {
        return getXP();
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public void levelupgrade() {
        setLevel();
    }
}

class Thief extends hero implements Hero {

    public int damage = 6;
    public int moves = 0;
    public int protection = 4;

    private String _name = "Thief";

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public int getlevel() {
        return getLevel();
    }


    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public void attack(Monster m) {
        m.sethp(m.gethp()-this.damage);
        System.out.println("You inflicted "+this.damage+" to monster.");
    }

    @Override
    public int defense(Monster m) {
        return this.protection;
    }

    @Override
    public void special_power(Monster m,int moves) {
        this.sethp(this.gethp()+0.3*m.gethp());
        m.sethp(0.7*m.gethp());
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public void levelupgrade() {
        setLevel();
    }

    @Override
    public int getxp() {
        return getXP();
    }
}

class Mage extends hero implements Hero {

    public int damage = 5;
    public int moves = 0;
    public int protection = 5;

    private String _name = "Mage";

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public void attack(Monster m) {
        m.sethp(m.gethp()-this.damage);
        System.out.println("You inflicted "+this.damage+" to monster.");
    }

    @Override
    public int defense(Monster m) {
        return this.protection;
    }

    @Override
    public void special_power(Monster m,int moves) {
        if(moves == 7) {
            m.sethp(m.gethp()+m.gethp()*0.05);
            return;
        }
        m.sethp(m.gethp()*0.95);
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public void levelupgrade() {
        setLevel();
    }
    @Override
    public int getxp() {
        return getXP();
    }

}

class Healer extends hero implements Hero {

    public int damage = 4;
    public int moves = 0;
    public int protection = 8;

    private String _name = "Healer";

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void attack(Monster m) {
        m.sethp(m.gethp()-this.damage);
        System.out.println("You inflicted "+this.damage+" to monster.");
    }

    @Override
    public int defense(Monster m) {
        return this.protection;
    }

    @Override
    public void special_power(Monster m,int moves) {
        if(moves == 3) {
            this.sethp(this.gethp()*0.95);
        }
        this.sethp(this.gethp()*1.05);
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public void levelupgrade() {
        setLevel();
    }
    @Override
    public int getxp() {
        return getXP();
    }
}

class monster {

    public double HP;

    monster(double HP) {
        this.HP = HP;
    }

    void setHP(double HP) {
        if(HP<=0) {
            this.HP = HP;
            return;
        }
        this.HP = HP;
    }

    double getHP() {
        return this.HP;
    }

    void attack(Hero h,int protection) {
        Random ran = new Random();
        double damage = ran.nextGaussian();
        h.sethp(h.gethp()+protection-damage);
    }

}

class Goblin extends monster implements Monster {

    private int level = 1;

    Goblin(int HP) {
        super(HP);
    }

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public void monster_attack(Hero h,int protection) {
        double prev = h.gethp();
        attack(h,protection);
        double next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(next-prev)+" damage to you.");
    }
}

class Zombie extends monster implements Monster {

    private int level = 2;

    Zombie(int HP) {
        super(HP);
    }

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public void monster_attack(Hero h,int protection) {
        double prev = h.gethp();
        attack(h,protection);
        double next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(next-prev)+" damage to you.");
    }
}

class Fiend extends monster implements Monster {
    private int level = 3;

    Fiend(int HP) {
        super(HP);
    }
    @Override
    public double gethp() {
        return getHP();
    }
    @Override
    public void sethp(double HP) {
        setHP(HP);
    }
    @Override
    public int getLevel() {
        return this.level;
    }
    @Override
    public void monster_attack(Hero h,int protection) {
        double prev = h.gethp();
        attack(h,protection);
        double next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(next-prev)+" damage to you.");
    }
}

class Lionfang extends monster implements Monster {
    private int level = 4;

    Lionfang(int HP) {
        super(HP);
    }

    @Override
    public double gethp() {
        return getHP();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void sethp(double HP) {
        setHP(HP);
    }

    @Override
    public void monster_attack(Hero h,int protection) {
        double prev = h.gethp();
        attack(h,protection);
        double next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(next-prev)+" damage to you.");
    }
}



class Graph {
    private Node[] locations = new Node[10];
    Graph() {
        for(int i=0;i<10;i++) {
            locations[i] = new Node(i);
        }
    }
    void setMonster(int l){
        this.locations[l].setMonster();
    }

    Monster getMonster(int l) {
        return this.locations[l].getMonster();
    }
}

class Node {
    int data;
    private Monster m;
    private int preference = 0;

    Node(int data) {
        this.data = data;
    }

    void setMonster() {
        if(this.preference == 0) {
            Random random = new Random();
            this.preference = random.nextInt(2);
        }
        if (preference == 0) {
            this.m = new Goblin(100);
        }
        else if (this.preference == 1) {
            this.m = new Zombie(100);
        }
        else if (this.preference == 2) {
            this.m = new Fiend(200);
        }
        else if (this.data == 9) {
            this.m = new Lionfang(250);
        }

    }

    Monster getMonster() {
        return this.m;
    }
}

class Main {
    public static void main(String[] args) throws java.io.IOException {
        Game game = new Game();
    }
}
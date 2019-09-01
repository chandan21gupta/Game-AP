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
    private Stack<Integer> levels_completed = new Stack<Integer>();
    private ArrayList<Sidekick> sidekicks = new ArrayList<Sidekick>();
    private Graph graph;
    final private String _name;
    private Hero _h;
    private int location = -1;
    private int flag_sidekick = 0;
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
        //this.levels_completed.push(-1);
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
            if(this.levels_completed.size()>0) {
                System.out.println("4) Go back");
            }
            System.out.println("Enter -1 to exit");
            int option = Integer.parseInt(buffer.readLine());
            if(option == -1) {
                return;
            }
            else if(option == 1) {
                this.levels_completed.push(option);
                this.setLocation(this.getLocation()+1);
            }
            else if(option == 2) {
                this.levels_completed.push(option);
                this.setLocation(this.getLocation()+4);
            }
            else if(option == 3){
                this.levels_completed.push(option);
                this.setLocation(this.getLocation()+7);
            }
            else if(option == 4) {
                this.setLocation(levels_completed.peek());
            }

        }
        else if(this.getLocation() == 3 || this.getLocation() == 4) {
            int l1 = this.getLocation()-2;
            int l2 = this.getLocation()+1;
            int l3 = this.getLocation()+4;
            System.out.println("1) Go to location "+l1);
            System.out.println("2) Go to location "+l2);
            System.out.println("3) Go to location "+l3);
            if(levels_completed.size()>0) {
                System.out.println("4) Go back");
            }
            System.out.println("Enter -1 to exit");
            int option = Integer.parseInt(buffer.readLine());
            if(option == -1) {
                return;
            }
            else if(option == 1) {
                levels_completed.push(option);
                this.setLocation(this.getLocation()-2);
            }
            else if(option == 2) {
                levels_completed.push(option);
                this.setLocation(this.getLocation()+1);
            }
            else if(option == 3) {
                levels_completed.push(option);
                this.setLocation(this.getLocation()+4);
            }
            else if(option == 4) {
                this.setLocation(levels_completed.peek());
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
            if(levels_completed.size()>0) {
                System.out.println("4) Go back");
            }
            System.out.println("Enter -1 to exit");
            int option = Integer.parseInt(buffer.readLine());
            if(option == -1) {
                return;
            }
            else if(option == 1) {
                levels_completed.push(option);
                this.setLocation(this.getLocation()-5);
            }
            else if(option == 2) {
                levels_completed.push(option);
                this.setLocation(this.getLocation()-2);
            }
            else if(option == 3){
                levels_completed.push(option);
                this.setLocation(this.getLocation()+1);
            }
            else if(option == 4) {
                this.setLocation(levels_completed.peek());
            }
        }
        this.moveLocation();
    }

    void moveLocation() throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int protection = 0;
        int moves = 0;
        Sidekick player = null;
        this.graph.setMonster(this.getLocation());
        Monster m = this.graph.getMonster(this.getLocation());
        System.out.println("Fight Started. You are fighting a level "+m.getLevel()+" Monster.");
        if(flag_sidekick == 1) {
            System.out.println("Type yes if you wish to use a sidekick, else type no");
            String o = buffer.readLine();
            if(o.equals("yes")) {
                //System.out.println("To execute here");
                Collections.sort(sidekicks,new MaxSidekick());
                player = sidekicks.get(sidekicks.size()-1);
                //player.activate();
                if(player.getName().equals("Minion")) {
                    System.out.println("You have a sidekick "+player.getName()+" with you. Attack of sidekick 2.");
                    System.out.println("Press c to use cloning ability. Else press f to move to the fight");
                    String clone = buffer.readLine();
                    if(clone.equals("c")) {
                        player.activate(this.getHero(),m);
                        System.out.println("Cloning done.");
                    }
                }
                else if(player.getName().equals("Knight")) {
                    System.out.println("You have a sidekick "+player.getName()+" with you. Attack of sidekick 2.");
                    System.out.println("Press c to use knight's ability. Else press f to move to the fight");
                    String clone = buffer.readLine();
                    if(clone.equals("c")) {
                        player.activate(this.getHero(),m);
                        System.out.println("Knight's power activated.");
                    }
                }
            }
        }
        while(m.gethp() != 0 && this.getHero().gethp() != 0){
            System.out.println("Choose move:");
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if(moves >= 3) {
                System.out.println("3) Special Attack");
            }
            int option = Integer.parseInt(buffer.readLine());
            switch(option) {
                case 1 :
                    System.out.println("You choose to attack");
                    this.getHero().attack(m);
                    if(player != null) {
                        player.attack(m);
                    }
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
            m.monster_attack(this.getHero(),player,protection);
            System.out.println("Your HP: "+this.getHero().gethp()+" Monsters HP: "+m.gethp());
            protection = 0;
            if(moves == 3) {
                this.getHero().special_power(m, moves);
            }
        }
        if(player!= null && player.gethp() == 0) {
            System.out.println("Sidekick dead!!");
            sidekicks.remove(player);
        }
        if(this.getHero().gethp() == 0) {
            System.out.println("You died!!");
            return;
        }
        if(m.gethp()==0) {
            System.out.println("Monster killed!");
            System.out.println(this.getHero().getxp()+" XP awarded.");
            this.getHero().setxp((int)(m.getLevel()*20));
            this.getHero().levelupgrade();
            if(player != null) {
                player.deactivate();
                player.sethp(100);
                player.setxp((int) (m.getLevel() * 2));
                player.checkxp();
            }
            System.out.println("Level Up: level:"+this.getHero().getlevel());
            this.getHero().setlevel();
            System.out.println("Fight won proceed to the next location.");
            System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level.");
            String n = buffer.readLine();
            if(n.equals("yes")) {
                System.out.println("Your current XP is "+this.getHero().getxp());
                System.out.println("If you want to buy a minion, press 1");
                System.out.println("If you want to buy a knight, press 2");
                int op = Integer.parseInt(buffer.readLine());
                System.out.print("XP to spend: ");
                int xp = Integer.parseInt(buffer.readLine());
                Sidekick s = null;
                if(op == 1) {
                    if(this.getHero().getxp() < 5) {
                        System.out.println("Sorry! Not enough XP");
                    }
                    else {
                        s = new Minion(xp,5);
                    }
                }
                else if(op == 2) {
                    if(this.getHero().getxp() < 7) {
                        System.out.println("Sorry! Not enough XP");
                    }
                    else{
                        s = new Knight(xp,7);
                    }
                }
                if(s != null) {
                    flag_sidekick = 1;
                    this.sidekicks.add(s);
                    this.getHero().buy(s,xp);
                    System.out.println("Attack of sidekick is :"+s.getdamage());
                }
            }
            loadGame();
        }
    }
}

class MaxSidekick implements Comparator<Sidekick> {
    @Override
    public int compare(Sidekick s1, Sidekick s2) {
        if(s1.getxp() > s2.getxp()) {
            return 1;
        }
        return -1;
    }
}

class hero {

    public int level = 1;
    public int XP = 0;
    public int HP = 100;

    public void setLevel() {
        if(this.getLevel() == 1 && this.getXP() == 20) {
            this.setXP(0);
            this.setHP(150);
            this.level++;
        }
        else if(this.getLevel() == 2 && this.getXP() == 40) {
            this.setXP(0);
            this.setHP(200);
            this.level++;
        }
        else if(this.getLevel() == 3 && this.getXP() == 60) {
            this.setXP(0);
            this.setHP(250);
            this.level++;
        }
    }

    public void Buy(Sidekick s, int xp) {
        s.setdamage(xp);
    }

    public int getLevel() {
        return this.level;
    }

    public int getXP() {
        return this.XP;
    }

    public void setHP(int HP) {
        if(HP < 0) {
            this.HP = HP;
            return;
        }
        this.HP = HP;
    }

    public void setXP(int XP) {
        this.XP += XP;
    }

    public int getHP() {
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
    public void setDefense(int protection) {
        this.protection = protection;
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void setlevel() {
        setLevel();
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
    public int gethp() {
        return getHP();
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    @Override
    public int getxp() {
        return getXP();
    }

    @Override
    public void setxp(int XP) {
        setXP(XP);
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
    public void buy(Sidekick s, int xp) {
        Buy(s,xp);
    }
}

class Thief extends hero implements Hero {

    public int damage = 6;
    public int moves = 0;
    public int protection = 4;

    private String _name = "Thief";

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void setlevel() {
        setLevel();
    }

    @Override
    public void sethp(int HP) {
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
    public void setDefense(int protection) {
        this.protection = protection;
    }

    @Override
    public void setxp(int XP) {
        setXP(XP);
    }

    @Override
    public void special_power(Monster m,int moves) {
        this.sethp((int)(this.gethp()+0.3*m.gethp()));
        m.sethp((int)(0.7*m.gethp()));
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

    @Override
    public void buy(Sidekick s, int xp) {
        Buy(s,xp);
    }
}


class Mage extends hero implements Hero {

    public int damage = 5;
    public int moves = 0;
    public int protection = 5;

    private String _name = "Mage";

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void setlevel() {
        setLevel();
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    @Override
    public void setxp(int XP) {
        setXP(XP);
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
    public void setDefense(int protection) {
        this.protection = protection;
    }

    @Override
    public void special_power(Monster m,int moves) {
        if(moves == 3) {
            m.sethp((int)(m.gethp()+m.gethp()*0.05));
            return;
        }
        m.sethp((int)(m.gethp()*0.95));
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

    @Override
    public void buy(Sidekick s, int xp) {
        Buy(s,xp);
    }

}

class Healer extends hero implements Hero {

    public int damage = 4;
    public int moves = 0;
    public int protection = 8;

    private String _name = "Healer";

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public void setxp(int XP) {
        setXP(XP);
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    @Override
    public int getlevel() {
        return getLevel();
    }

    @Override
    public void setlevel() {
        setLevel();
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
    public void setDefense(int protection) {
        this.protection = protection;
    }

    @Override
    public void special_power(Monster m,int moves) {
        if(moves == 3) {
            this.sethp((int)(this.gethp()*0.95));
        }
        this.sethp((int)(this.gethp()*1.05));
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

    @Override
    public void buy(Sidekick s, int xp) {
        Buy(s,xp);
    }
}

class sidekick {

    private int damage;
    private int XP = 0;
    private int HP = 100;
    private int price;

    sidekick(int damage, int price) {
        this.damage = damage - price;
        this.price = price;
    }

    int getXP() {
        return this.XP;
    }

    void setXP(int XP) {
        this.XP = XP;
    }

    int getHP() {
        return this.HP;
    }

    void setHP(int HP) {
        this.HP = HP;
    }

    int getDamage() {
        return this.damage;
    }

    void setDamage(int xp) {
        this.damage += (int)Math.round(0.5*(xp - this.price));
    }

    void Attack(Monster m) {
        m.sethp(m.gethp() - this.damage);
    }

    int getPrice() {
        return this.price;
    }

    void checkXP() {
        if(this.XP%5 == 0) {
            this.damage+=1;
        }
    }

}

class Minion extends sidekick implements Sidekick, Cloneable {
    private Sidekick s1;
    private Sidekick s2;
    private Sidekick s3;
    private int clone_flag = 0;
    private String name = "Minion";
    Minion(int damage, int price) {
        super(damage,price);
    }

    @Override
    public void setdamage(int xp) {
        setDamage(xp);
    }

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }
    @Override
    public void checkxp() {
        checkXP();
    }
    @Override
    public int getdamage() {
        return getDamage();
    }

    @Override
    public void attack(Monster m) {
        Attack(m);
            if (s1 != null && s2 != null && s3 != null) {
                s1.attack(m);
                System.out.println("The sidekick attacked and inflicted " + s1.getdamage() + " to the monster");
                s2.attack(m);
                System.out.println("The sidekick attacked and inflicted " + s1.getdamage() + " to the monster");
                s3.attack(m);
                System.out.println("The sidekick attacked and inflicted " + s1.getdamage() + " to the monster");
                System.out.println("Sidekick Hp:" + s1.gethp());
                System.out.println("Sidekick Hp:" + s1.gethp());
                System.out.println("Sidekick Hp:" + s1.gethp());
            }

    }

    @Override
    public void deactivate() {
        System.out.println("deactivating the clones");
        s1 = null;
        s2 = null;
        s3 = null;
        clone_flag = 1;
    }

    @Override
    public int getprice() {
        return getPrice();
    }

    @Override
    public int getxp() {
        return getXP();
    }

    @Override
    public void setxp(int xp) {
        setXP(xp);
    }

    @Override
    public void activate(Hero h,Monster m) {
        if(clone_flag == 0) {
            s1 = this.clone();
            s2 = this.clone();
            s3 = this.clone();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Sidekick clone() {
        try {
            Sidekick s = (Minion) super.clone();
            return s;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

class Knight extends sidekick implements Sidekick {

    private String name = "Knight";
    Knight(int damage, int price) {
        super(damage,price);
    }

    @Override
    public void setdamage(int xp) {
        setDamage(xp);
    }

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    @Override
    public int getdamage() {
        return getDamage();
    }

    @Override
    public void attack(Monster m) {
        Attack(m);
    }

    @Override
    public int getprice() {
        return getPrice();
    }

    @Override
    public int getxp() {
        return getXP();
    }

    @Override
    public void setxp(int xp) {
        setXP(xp);
    }

    @Override
    public void activate(Hero h,Monster m) {
        if(m instanceof Zombie) {
            h.setDefense(h.defense(m)+5);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void deactivate() {

    }

    @Override
    public void checkxp() {
        checkXP();
    }
}

class monster {

    public int HP;

    monster(int HP) {
        this.HP = HP;
    }

    void setHP(int HP) {
        if(HP<=0) {
            this.HP = 0;
            return;
        }
        this.HP = HP;
    }

    int getHP() {
        return this.HP;
    }

    void attack(Hero h,Sidekick s,int protection) {
        Random ran = new Random();
        int damage = (int)Math.round((this.getHP()/4)*(ran.nextGaussian()));
        int netDamage = Math.abs(protection-damage);
        h.sethp(h.gethp()+protection-netDamage);
        if(s!=null) {
            s.sethp(s.gethp() - (int) ((1.5) * damage));
        }
    }

}

class Goblin extends monster implements Monster {

    private int level = 1;

    Goblin(int HP) {
        super(HP);
    }

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    @Override
    public void monster_attack(Hero h,Sidekick s,int protection) {
        int prev = h.gethp();
        attack(h,s,protection);
        int next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(prev-next)+" damage to you.");
    }
}

class Zombie extends monster implements Monster {

    private int level = 2;

    Zombie(int HP) {
        super(HP);
    }

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    @Override
    public void monster_attack(Hero h,Sidekick s,int protection) {
        int prev = h.gethp();
        attack(h,s,protection);
        int next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(next-prev)+" damage to you.");
    }
}

class Fiend extends monster implements Monster {
    private int level = 3;

    Fiend(int HP) {
        super(HP);
    }
    @Override
    public int gethp() {
        return getHP();
    }
    @Override
    public void sethp(int HP) {
        setHP(HP);
    }
    @Override
    public int getLevel() {
        return this.level;
    }
    @Override
    public void monster_attack(Hero h,Sidekick s,int protection) {
        int prev = h.gethp();
        attack(h,s,protection);
        int next = h.gethp();
        System.out.println("The monster attacked and inflicted "+(next-prev)+" damage to you.");
    }
}

class Lionfang extends monster implements Monster {
    private int level = 4;

    Lionfang(int HP) {
        super(HP);
    }

    @Override
    public int gethp() {
        return getHP();
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void sethp(int HP) {
        setHP(HP);
    }

    void attack(Hero h,Sidekick s,int protection) {
        int damage = (int)Math.round(0.1*0.5*h.gethp());
        int netDamage = Math.abs(protection - damage);
        h.sethp(h.gethp()-netDamage);
        s.sethp(s.gethp()-(int)(1.5*damage));
    }

    @Override
    public void monster_attack(Hero h,Sidekick s,int protection) {
        int prev = h.gethp();
        this.attack(h,s,protection);
        int next = h.gethp();
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
public interface Hero {
    public int damage = 0;
    public int protection = 0;
    public int moves = 0;
    public int gethp();
    public void sethp(int HP);
    public void attack(Monster m);
    public int defense(Monster m);
    public void special_power(Monster m,int moves);
    public String getName();
    public void levelupgrade();
    public int getxp();
    public int getlevel();
    public void setxp(int XP);
    public void setlevel();
    public void buy(Sidekick s, int xp);
    public void setDefense(int protection);
}

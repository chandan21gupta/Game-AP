public interface Hero {
    public int damage = 0;
    public int protection = 0;
    public int moves = 0;
    public double gethp();
    public void sethp(double HP);
    public void attack(Monster m);
    public int defense(Monster m);
    public void special_power(Monster m,int moves);
    public String getName();
    public void levelupgrade();
    public int getxp();
    public int getlevel();
}

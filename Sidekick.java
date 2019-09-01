public interface Sidekick {
    public void setdamage(int xp);
    public int gethp();
    public void sethp(int HP);
    public int getdamage();
    public void attack(Monster m);
    public int getprice();
    public int getxp();
    public void setxp(int xp);
    public void activate(Hero h,Monster m);
    public String getName();
    public void deactivate();
    public void checkxp();
}

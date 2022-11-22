package entity;

public abstract class FantasyCharacter implements entity.Fighter {
    //Имя персонажа
    private String name;
    //Статы персонажа
    private int healthPoints;
    private int strength;
    private int dexterity;
    //Опыт и золото
    private int xp;
    private int gold;
    private int magic;
    //Конcтруктор
    public FantasyCharacter(String name, int healthPoints, int strength, int dexterity, int xp, int gold, int magic) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
        this.magic = magic;
    }
    //Мпетод для вдения боя
    @Override
    public int attack() {
        if (dexterity * 3 > getRandomValue()) return strength;
        else return  0;
    }

    public String getName() {
        return name;
    }
    //Геттеры и сеттеры
    public void setName(String name) {
        this.name = name;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = Integer.max(xp,0);;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int xp) {
        this.magic = Integer.max(magic,0);
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = Integer.max(gold,0);
    }
    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }
    //Переопределяем вывод в консоль, чтобы выводилось имя и очки здоровья
    @Override
    public String toString() {
        return String.format("%s здоровье:%d опыт:%d магии:%d", name, healthPoints, xp, magic);
    }
}
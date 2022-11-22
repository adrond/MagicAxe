package domain.npc;

public class Merchant implements Seller {
    private int mana = 0;
    private int potion = 0;

    // случайным образом генерим товары у торговца
    public Merchant() {
        this.mana = (int) (Math.random() * 10);;
        this.potion = (int) (Math.random() * 10);;
    }

    public int getMana() {
        return mana;
    }

    public int getPotion() {
        return potion;
    }

    //Метод для продажи
    @Override
    public String sell(Goods goods) {
        String result = "";
        if (goods == Goods.POTION) {
            result = "potion";
        } else if (goods == Goods.MANA) {
            result = "mana";
        }
        return result;
    }
    //Энам для товаров
    public enum Goods {
        POTION,
        MANA
    }
}
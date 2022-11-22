package domain;

import domain.monsters.lvl1.Goblin;
import domain.monsters.lvl1.Skeleton;
import domain.npc.Merchant;
import domain.player.Hero;
import entity.FantasyCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    //Класс для чтения введенных строк из консоли
    private static BufferedReader br;
    //Игрок должен хранится на протяжении всей игры
    private static FantasyCharacter player = null;
    //Класс для битвы можно не создавать каждый раз, а переиспользовать
    private static BattleScene battleScene = null;

    public static void main(String[] args) {
        //Инициализируем BufferedReader
        br = new BufferedReader(new InputStreamReader(System.in));
        //Инициализируем класс для боя
        battleScene = new BattleScene();
        //Первое, что нужно сделать при запуске игры это создать персонажа, поэтому мы предлагаем ввести его имя
        System.out.println("Введите имя персонажа:");
        //Далее ждем ввод от пользователя
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        //Если это первый запуск, то мы должны создать игрока, именем будет служить первая введенная строка из консоли
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0,
                    50
            );
            System.out.println(String.format("Спасти наш мир вызвался %s!", player.getName()));
            //Метод для вывода меню
        }
        printNavigation();
        //Варианты для команд
        switch (string) {
            case "1": {
                purchase();
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        //Снова ждем команды от пользователя
        command(br.readLine());
    }

    private static void purchase () throws IOException {
        // Создаем торговца
        Merchant merchant = new Merchant();
        if (merchant.getMana()* merchant.getPotion()==0){
            System.out.println("У торговца нет товара, зайдите в следующий раз");
        } else {
            System.out.print("У торговца есть MANA - " + merchant.getMana() + " POTION - " + merchant.getPotion() + ".");
            System.out.println("Что хотите купить? m - MANA, p - POTION, e - выход без покупок");
            System.out.println("Пример ввода m х. MANA в количестве х. Больше чем есть у торговца купить нельзя");
            boolean b = true;
            while (b){
                String s[] = br.readLine().split("\\s+");
                switch (s[0]) {
                    case "m": {
                        if (s.length > 1) {
                            int min = Integer.min(Integer.valueOf(s[1]), player.getMagic());
                            min = Integer.min(min, player.getGold());
                            player.setMagic(player.getMagic() + min);
                            player.setGold(player.getGold() - min);
                            System.out.print(player);
                            b = false;
                        } else {
                            System.out.println("Что хотите купить? m - MANA, p - POTION, e - выход без покупок");
                        }
                    }
                    break;
                    case "p": {
                        if (s.length > 1) {
                            int min = Integer.min(Integer.valueOf(s[1]), player.getHealthPoints());
                            min = Integer.min(min, player.getGold());
                            player.setHealthPoints(player.getHealthPoints() + min);
                            player.setGold(player.getGold() - min);
                            System.out.print(player);
                            b = false;
                        } else {
                            System.out.println("Что хотите купить? m - MANA, p - POTION, e - выход без покупок");
                        }
                    }
                    break;
                    case "e":
                        b=false;
                        break;
                    default:
                        System.out.print("Сделайте корректный выбор. ");
                        System.out.println("Что хотите купить? m - MANA, p - POTION, e - выход без покупок");
                    }
            }


        }
    }
    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static FantasyCharacter createMonster() {
        //Рандомайзер
        int random = (int) (Math.random() * 10);
        //С вероятностью 50% создается или скелет  или гоблин
        if (random % 2 == 0) return new Goblin(
                "Гоблин",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Скелет",
                25,
                20,
                20,
                100,
                10
        );
    }

    interface FightCallback {
        void fightWin();
        void fightLost();
    }
}
import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {290, 270, 250};
    public static int[] heroesDamage = {20, 15, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Piercing"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth [1]<=0 && heroesHealth [2] <=0) {
            System.out.println( "Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if( allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];

    }
    public static void playRound(){
        roundNumber++;
        chooseBossDefence();
        bossAttacks();
        heroesAttack();
        showStatistics();
    }
    public static void bossAttacks(){
        for( int i = 0; i < heroesHealth.length; i++){
            if(heroesHealth[i] > 0){
            heroesHealth[i] = heroesHealth[i] - bossDamage;
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }

            }
        }
    }
    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if( heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if(heroesAttackType[i]== bossDefence){
                    Random random = new Random();
                    int coeff = random.nextInt(9)+2;
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage:" + damage);
                }
                bossHealth = bossHealth - damage;
                if(bossHealth<0){
                    bossHealth = 0;
                }
            }
        }
    }
    public static void showStatistics() {
        System.out.println(" ROUND " + roundNumber + "____________");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health:" + bossHealth + "damage:" +
                bossDamage + "defence:" + (bossDefence == null ? "No defence" : bossDefence));
        for ( int i = 0;  i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage:" + heroesDamage[i]);
        }
    }
}

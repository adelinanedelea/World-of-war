package TemaPOO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    ArrayList<Account> accounts;
    HashMap<Integer, ArrayList<String>> dex;
    HashMap <String ,ArrayList<String>> povesti;
    ArrayList<String> username;
    static ArrayList < String > forest;
    static ArrayList < String > inamic;
    static ArrayList <String > finish;
    private static Game game = null;
     private Game() {
        this.accounts = new ArrayList<Account>();
        this.dex = new HashMap<Integer, ArrayList<String>>();
        this.username = new ArrayList<String>();
    }
    static public Game getInstance() {
         if ( game == null) {
             game = new Game();
         }
         return  game;
    }
    // in run are loc parsarea din json in account a conturilor
    public void run() {
        JSONParser jo = new JSONParser();
        try (FileReader reader = new FileReader("src/TemaPOO/accounts.json")) {
            Object obj = jo.parse(reader);
            JSONArray credentialsList = new JSONArray();
            credentialsList.add(obj);
            JSONObject credentials = null;
            for (Object o : credentialsList)
                credentials = (JSONObject) o;
            JSONArray userList = (JSONArray) credentials.get("accounts");
            for (Object o : userList)
                parseCredentialsObj((JSONObject) o, accounts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    //in story are loc parsarea din json in story a povestilor
     public void story () {
         JSONParser jo = new JSONParser();
         forest = new ArrayList<String>();
         inamic = new ArrayList <String>();
         finish = new ArrayList < String> ();

         try (FileReader reader = new FileReader("src/TemaPOO/stories.json")) {
             Object obj = jo.parse(reader);
             JSONArray storiesList = new JSONArray();
             JSONObject story  = null;
             storiesList.add(obj);
             for ( Object o : storiesList)
                 story = (JSONObject) o;
             JSONArray storyList = (JSONArray) story.get("stories");
             for ( Object o :storyList) {
                 String type = (String)((JSONObject)o).get("type");
                 String value = (String)((JSONObject)o).get("value");
                 if (type.equals("EMPTY")) {
                     forest.add(value);
                 }
                 else if ( type.equals("ENEMY")) {
                     inamic.add(value);
                 }
                 else if ( type.equals("FINISH")) {
                     finish.add(value);
                 }
             }
         }
         catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ParseException e) {
             e.printStackTrace();
         }
     }
     // functie ajutor pentru run
    public static void parseCredentialsObj(JSONObject o, ArrayList<Account> accounts) {
        String country = (String) o.get("country");
        JSONArray characters = (JSONArray) o.get("characters");
        JSONObject credentials = (JSONObject) o.get("credentials");
        JSONArray favorite_games = (JSONArray) o.get("favorite_games");
        String name = (String) o.get("name");
        String maps_completed = (String) o.get("maps_completed");
        JSONArray personaje = (JSONArray) o.get("characters");
        ArrayList<Character> caractere = new ArrayList<Character>();
        for (Object ob : personaje) {
            JSONObject personaj = (JSONObject) ob;
            String nume = (String) personaj.get("name");
            String profession = (String) personaj.get("profession");
            String level = (String) personaj.get("level");
            Long experience = (Long) personaj.get("experience");
            Character c = ChooseCharacter.factory(profession, nume,  Integer.valueOf(level), (Integer) experience.intValue());
            caractere.add(c);
        }
        Credentials crede = new Credentials();
        crede.setEmail((String) credentials.get("email"));
        crede.setPass((String) credentials.get("password"));
        Account a = new Account();
        Account.Information info = a.new Information();
        info = info.new Builder(crede).withName(name).withCountry(country).build();
        Account account = new Account(info, Integer.parseInt(maps_completed), caractere);
        accounts.add(account);
    }
    // jocul textt
    public void playtext() throws Exception {

        int i = 1;
        //se alege un cont
        System.out.println("Choose");
        for (Account account : accounts) {
            System.out.println(i + " " + account.information.name);
            ++i;
        }
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        System.out.println("Ai ales" + " " + accounts.get(choose - 1).information.name);
        //autentificarea
        while (true) {
            System.out.println("Email:");
            String email = sc.next();
            System.out.println("Password:");
            String password = sc.next();
            if (email.equals(accounts.get(choose - 1).information.credentials.getEmail())) {
                if (password.equals(accounts.get(choose - 1).information.credentials.getPass())) {
                    System.out.println("Welcome. Te-ai autentificat cu succes. Let's the game begin");
                    break;
                }
            }
            System.out.println(" Email sau Parola gresita");
        }
        //alegere personaj
        System.out.println("Alege persoanajul");
        i = 1;
        for (Character character : accounts.get(choose - 1).characters) {
            System.out.println(i + " " + character.name);
            ++i;
        }
        sc = new Scanner(System.in);
        int chooose = sc.nextInt();
        Character currentpersonaj = accounts.get(choose - 1).characters.get(chooose - 1);
        System.out.println("Ai ales personajul" + " " + currentpersonaj.name);
        Grid array = Grid.test();
        //jocul propriu zis cat timp casuta e diferita de final si caracterul mai are viata
        while(  array.current.celltype != 3 && currentpersonaj.current_life > 0) {
            //casuta este shop
            if (array.current.celltype == 2) {
                System.out.println("Choose your poison");
                i = 1;
                for (Potion p : ((Shop) array.current.cellelement).potions) {
                    System.out.println(i + " " +  p.getClass().getName().substring(8) + " " +  p.valueOfprice() + " " + p.valueOfweight() + " " + p.valueOfregeneration());
                    ++i;
                }
                System.out.println("Monede: " + currentpersonaj.inventory.money);
                int choosepotion = sc.nextInt();
                Potion potiune = ((Shop) array.current.cellelement).selectPotion(choosepotion - 1);
                System.out.println(currentpersonaj.buy_potion(potiune));
            }
            //casuta este inamic
            else if (array.current.celltype == 1) {
                int perand = 0;
                //lupta cu inamic cat timp viata este pozitiva
                while (((Enemy) array.current.cellelement).current_life > 0 && currentpersonaj.current_life > 0) {
                    if ( perand % 2 == 0) {
                        System.out.println("Choose");
                        System.out.println(1 + "Ataca inamic");
                        if ( currentpersonaj.spells.size() != 0) {
                            System.out.println(2 + "Foloseste abilitate");
                        }
                        if ( currentpersonaj.inventory.potions.size() != 0) {
                            System.out.println(3 + " Foloseste potiune");
                        }
                        int choose_enemy = sc.nextInt();
                        if (choose_enemy == 1) {
                            ((Enemy) array.current.cellelement).receiveDamage(currentpersonaj.getDamage());
                        }
                        else if (choose_enemy == 2) {
                            System.out.println("Choose  your spell");
                            i = 1;
                            for (Spell spell : currentpersonaj.spells) {
                                System.out.println(i + spell.getClass().getName().substring(8) + " " +  spell.cost_mana + "cost damage  " + spell.cost_damage);
                                ++i;
                            }
                            int choosespel = sc.nextInt();
                            currentpersonaj.use_abilities(currentpersonaj.spells.get(choosespel - 1), (Enemy) array.current.cellelement);
                        }
                        else {
                            System.out.println("Choose your poison");
                            i = 1;
                            for (Potion p : currentpersonaj.inventory.potions) {
                                System.out.println(i + " " + p.getClass().getName().substring(8)  + " " +  p.valueOfprice() + " " + p.valueOfweight() + " " + p.valueOfregeneration());
                                ++i;
                            }
                            int choosepotion = sc.nextInt();
                            Potion potiune = currentpersonaj.inventory.potions.get(choosepotion - 1);
                            if (potiune instanceof HealthPotion) {
                                currentpersonaj.regeneration_life(potiune.valueOfregeneration());
                            }
                            else {
                                currentpersonaj.regeneration_mana(potiune.valueOfregeneration());
                            }
                        }
                        perand++;
                        System.out.println("Viata  si mana inamic " + ((Enemy)array.current.cellelement).current_life +" " + ((Enemy) array.current.cellelement).current_mana);
                    }
                    else {
                        //atacul inamicului
                        Random rr = new Random();
                        Enemy e = (Enemy) array.current.cellelement;
                        int ataca = rr.nextInt(101);
                        if (((Enemy) array.current.cellelement).current_mana  < 0 ) {
                            currentpersonaj.receiveDamage(((Enemy) array.current.cellelement).getDamage());
                        }
                        else {
                            if (ataca < 75) {
                                currentpersonaj.receiveDamage(((Enemy) array.current.cellelement).getDamage());
                            } else {
                                e.use_abilities(e.spells.get(rr.nextInt(3)), currentpersonaj);
                            }
                        }
                        ++perand;
                        System.out.println("Viata  si mana personaj " + currentpersonaj.current_life +" " +currentpersonaj.current_mana);
                    }
                }
                if ( currentpersonaj.current_life <= 0 ) {
                    System.out.println("Game over! ");
                }
                else {
                    // daca personajul a castigat este o posibilitate de 80% sa primeasca moneda
                    array.current.cellelement = null;
                    array.current.celltype = 0;
                    Random random = new Random();
                    if ( random.nextInt(100) < 80 ) {
                        currentpersonaj.inventory.money ++;
                    }
                }
            }
            // exista o posibilitate de 20% sa gaseasca moneda pe o casuta goala
            else if(array.current.celltype == 0) {
                Random random = new Random();
                if ( random.nextInt(100) < 20 ) {
                    currentpersonaj.inventory.money ++;
                }
            }
            // afisare harta
            for (i = 0; i < 5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    if (array.current.Ox == i && array.current.Oy == j) {
                        System.out.print("P");
                        if (array.get(i).get(j).cellelement != null) {
                            System.out.print(array.get(i).get(j).cellelement.toCharacter() + " ");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                    else if (array.get(i).get(j).cellelement != null) {
                        System.out.print(array.get(i).get(j).cellelement.toCharacter() + " ");
                    }
                    else if (array.get(i).get(j).celltype == 3) {
                            System.out.print("F ");
                    }
                    else {
                        System.out.print("N ");
                    }
                }
                System.out.println("");
            }
            //alegere mutare
            System.out.println("What direction?");
            String d = sc.next();
            if (d.equals("N")) {
                array.goNorth();
            }
            else if (d.equals("S")) {
                array.goSouth();
            }
            else if (d.equals("E")) {
                array.goEast();
            }
            else if (d.equals("W")) {
                array.goWest();
            }
            else {
                throw new InvalidCommandException();
            }
        }
    }
}
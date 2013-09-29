package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.Users;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private Users myUser;
    
    public Application(EbeanServer server) {
        this.server = server;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }
        String username = loginView();
        mainMenu(username);
    }
    
    public void mainMenu(String username){
        System.out.println("Welcome " + username);

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                listBeers();
            } else if (command.equals("7")) {
                addBrewery();
            } else if (command.equals("8")) {
                deleteBrewery();
            } else if (command.equals("9")) {
                listUsers();
            } else if (command.equals("a")) {
                rateBeer();
            } else if (command.equals("b")) {
                listRatings();
            } else if (command.equals("c")) {
                listBeerWithRating();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("8   delete brewery");
        System.out.println("9   list users");
        System.out.println("a   rate beer");
        System.out.println("b   list ratings");
        System.out.println("c   list beers and avg rating");
        System.out.println("0   quit");
        System.out.println("");
    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);
        
        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());        
        brewery = server.find(Brewery.class, brewery.getId());        
        brewery.addBeer(b);
        server.save(brewery);
        
        server.save(new Brewery("Paulaner"));
    }

    private void findBeer() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println("found: " + foundBeer);
    }

    private void findBrewery() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }

    private void listBeers() {
        List<Beer> beers = server.find(Beer.class).findList();
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    private void addBrewery() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery != null) {
            System.out.println(name + " exists already");
            return;
        }
        Brewery brew = new Brewery();
        brew.setName(name);
        server.save(brew);
        System.out.println(name + " added to " + brew.getName());
    }

    private void deleteBrewery() {
        System.out.print("brewery to remove: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();
        if (brewery == null){
            System.out.println(name + " does not exist");
            return;
        }
        server.delete(brewery);
    }

    private String loginView() {
        String username ="";
        while (true) {
            System.out.println("Login (give ? to register a new user)");
            System.out.print("username: ");
            String name = scanner.nextLine();

            if (name.equals("?")) {
                newUser();
            } else {
                Users user = server.find(Users.class).where().like("name", name).findUnique();
                if (user!=null){
                    myUser = user;
                    break;
                } else {
                    System.out.println("Virheellinen käyttäjätunnus!");
                }
            } 
        }
        return username;
    }

    private void newUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String username = scanner.nextLine();
        Users user = server.find(Users.class).where().like("name", username).findUnique();
        if (user==null){
            user = new Users();
            user.setName(username);
            user.setPassword(username);
            server.save(user);
        } else {
            System.out.println("Käyttäjätunnus on jo olemassa!");
        }
    }

    private void listUsers() {
        List<Users> users = server.find(Users.class).findList();
        for (Users user : users) {
            System.out.println(user);
        }
    }

    private void rateBeer() {
        System.out.print("Which beer: ");
        String bname = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", bname).findUnique();
        if (beer == null){
            System.out.println("Beer not found");
            return;
        }
        System.out.print("Rate " + bname + ": ");
        int rating = scanner.nextInt();
        Rating rate = new Rating(beer, myUser, rating);
        server.save(rate);
    }

    private void listRatings() {
        List<Rating> ratings = server.find(Rating.class).where().like("user", myUser.getName()).findList();
        for(Rating rate: ratings){
            
        }
    }

    private void listBeerWithRating() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private void addBeerToPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", name).findUnique();

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        pub.addBeer(beer);
        server.save(pub);
    }

    private void showBeersInPub() {
        System.out.print("Which pub to list: ");
        String pubname = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", pubname).findUnique();
        List<Beer> beers = pub.getBeers();
        for(Beer beer: beers){
            System.out.println(beer.getName());
        }
    }

    private void listPubs() {
        List<Pub> pubs = server.find(Pub.class).findList();
        for(Pub pub: pubs){
            System.out.println(pub.getName());
        }
    }
    
    private void removePub(){
        System.out.print("Which pub: ");
        String pname = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", pname).findUnique();
        List<Beer> beers = pub.getBeers();
        for(int i = 0; i < beers.size(); i++){
            System.out.println(i + ") " + beers.get(i));
        }
        System.out.print("Which beer: ");
        int num = scanner.nextInt();
        Beer b = beers.get(num);
        pub.removeBeer(b);
        server.save(pub);
    }
}

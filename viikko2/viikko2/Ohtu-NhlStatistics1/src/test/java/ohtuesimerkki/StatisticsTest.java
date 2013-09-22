/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkortelainen
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String name = "Gretzky";
        Player expResult = new Player("Gretzky", "EDM", 35, 89);
        Player result = stats.search(name);
        assertEquals(expResult.toString(), result.toString());
        //assertTrue(expResult.equals(result));
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {
        ArrayList<Player> pl2 = new ArrayList<Player>();
        pl2.add(new Player("Semenko", "EDM", 4, 12));
        pl2.add(new Player("Kurri",   "EDM", 37, 53));
        pl2.add(new Player("Gretzky", "EDM", 35, 89));
        System.out.println("team");
        String teamName = "EDM";
        ArrayList<Player> expResult = pl2;
        ArrayList<Player> result = (ArrayList)stats.team(teamName);
        System.out.println(expResult);
        System.out.println(result);
        //assertEquals(expResult,result);
        comparePlayers(expResult, result);
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        System.out.println("topScorers");
        Player pl = new Player("Gretzky", "EDM", 35, 89);
        int howMany = 0;
        ArrayList<Player> expResult = new ArrayList<Player>();
        expResult.add(pl);
        ArrayList result = (ArrayList)stats.topScorers(howMany);
        //assertEquals(expResult, result);
        comparePlayers(expResult, result);
    }
    
    
    
    
    private void comparePlayers(ArrayList lst, ArrayList lst2) {
      Object[] array = lst.toArray();
      Object[] array2 = lst2.toArray();
      assertTrue("Arrays not the same length", array.length == array2.length);
      for (int i = 0; i < array.length; i++){
          Player pl1 = (Player)array[i];
          Player pl2 = (Player)array2[i];
          assertTrue(pl1.toString().equals(pl2.toString()));
      }
    }
}
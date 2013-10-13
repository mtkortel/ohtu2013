package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new HasFewerThan(21, "goals"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        Matcher m2 = new Not( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists")
        );
        
        for (Player player : stats.matches(m2)) {
            System.out.println( player );
        }
        Matcher m3 = new Or( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists")
        );
        
        for (Player player : stats.matches(m3)) {
            System.out.println( player );
        }
    }
}

package com.wpers.codewar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jcb on 20/3/17.
 */
public class BundesLiga {
    public LineMatch getInstance() {
        return new LineMatch();
    }

    public static String table(String[] results) {
        String resmatch = "";
        String separador = "  ";
        final ArrayList<LineMatch> lineMatches = new ArrayList<LineMatch>();
        BundesLiga bl = new BundesLiga();


        for (String s : results) {
            final LineMatch lineMatch1 = bl.getInstance();
            final LineMatch lineMatch2 = bl.getInstance();
            Pattern pt = Pattern.compile("(\\d+):(\\d+) (.*) - (.*)");
            Pattern pnojugado = Pattern.compile("(-):(-) (.*) - (.*)");

            Matcher mt = pt.matcher(s);
            if (mt.find()) {
//                System.out.println("gc1 " + mt.group(1));
//                System.out.println("gc2 " + mt.group(2));
//                System.out.println("nc1 " + mt.group(3));
//                System.out.println("nc2 " + mt.group(4));

                lineMatch1.setGolesafavor(Integer.parseInt(mt.group(1)));
                lineMatch2.setGolesafavor(Integer.parseInt(mt.group(2)));

                lineMatch1.setGolesencontra(Integer.parseInt(mt.group(2)));
                lineMatch2.setGolesencontra(Integer.parseInt(mt.group(1)));

                lineMatch1.setName(mt.group(3));
                lineMatch2.setName(mt.group(4));

                lineMatch1.setPj(1);
                lineMatch2.setPj(1);

            } else {
//                System.out.println("No reconoce patron");
            }

            mt = pnojugado.matcher(s);
            if (mt.find()) {
//                System.out.println("gc1 " + mt.group(1));
//                System.out.println("gc2 " + mt.group(2));
//                System.out.println("nc1 " + mt.group(3));
//                System.out.println("nc2 " + mt.group(4));

                lineMatch1.setGolesafavor(0);
                lineMatch2.setGolesafavor(0);

                lineMatch1.setGolesencontra(0);
                lineMatch2.setGolesencontra(0);

                lineMatch1.setName(mt.group(3));
                lineMatch2.setName(mt.group(4));

                lineMatch1.setPj(0);
                lineMatch2.setPj(0);

            } else {
//                System.out.println("No reconoce patron");
            }


//            System.out.println(lineMatch1.getGolesafavor());
//            System.out.println(lineMatch2.getGolesafavor());

            lineMatches.add(lineMatch1);
            lineMatches.add(lineMatch2);
        }

        lineMatches.forEach(element -> System.out.println(element));


        Collections.sort(lineMatches);

        int pos = 1;

        for (int i = 0; i < lineMatches.size(); i++) {

            if (i == 0) {
                pos = 1;
            } else if /*(lineMatches.get(i).getPuntos() < lineMatches.get(i - 1).getPuntos()) */
//                (lineMatches.get(i).compareTo(lineMatches.get(i-1)) == 1 /*&&
//                            (lineMatches.get(i).getPj() &&)*/)
                    (lineMatches.get(i).comparePos(lineMatches.get(i-1)) == 1) {
                pos = i + 1;
            }

            String nombre = String.format("%1$-28s", lineMatches.get(i).getName());

            resmatch = resmatch +
                    String.format("%1$2d", pos) + "." + " " +
                    nombre + separador +
                    lineMatches.get(i).getPj() + separador +
                    lineMatches.get(i).getPg() + separador +
                    lineMatches.get(i).getPe() + separador +
                    lineMatches.get(i).getPp() + separador +
                    lineMatches.get(i).getGolesafavor() + ":" + lineMatches.get(i).getGolesencontra() + separador +
                    lineMatches.get(i).getPuntos();

            if(lineMatches.size() > i+1)
                resmatch = resmatch + "\n";
        }

        System.out.print(resmatch);

        return resmatch;
    }

class LineMatch implements Comparable<LineMatch> {
    public LineMatch() {
    }

    String name;
        int pj;
//        int pg;
//        int pe;
//        int pp;
        int golesafavor;
        int golesencontra;
//        int puntos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj = pj;
    }

    public int getPg() {
        if(getPj() == 0) return 0;
        if(golesafavor  > golesencontra)
            return 1;
        else
            return 0;
    }
//
//    public void setPg(int pg) {
//        this.pg = pg;
//    }
//
    public int getPe() {
        if(getPj() == 0) return 0;
        if(golesafavor == golesencontra)
            return 1;
        else
            return 0;
    }
//
//    public void setPe(int pe) {
//        this.pe = pe;
//    }
//
//    public int getPp() {
//        return pp;
//    }
//
    public int getPp() {
        if(getPj() == 0) return 0;

        if(golesafavor  < golesencontra)
            return 1;
        else
            return 0;
    }

    public int getGolesafavor() {
        return golesafavor;
    }

    public void setGolesafavor(int golesafavor) {
        this.golesafavor = golesafavor;
    }

    public int getGolesencontra() {
        return golesencontra;
    }

    public void setGolesencontra(int golesencontra) {
        this.golesencontra = golesencontra;
    }

    public int getPuntos() {
        if(getPj() == 0) return 0;
        if(golesafavor == golesencontra)
            return 1;
        else if(golesafavor > golesencontra)
            return 3;
        else
            return 0;
    }

//    public void setPuntos(int puntos) {
//        this.puntos = puntos;
//    }

    @Override
    public String toString() {
        return "LineMatch{" +
                "name='" + name + '\'' +
                ", pj=" + getPj() +
                ", pg=" + getPg() +
                ", pe=" + getPe() +
                ", pp=" + getPp() +
                ", golesafavor=" + golesafavor +
                ", golesencontra=" + golesencontra +
                ", puntos=" + getPuntos() +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(LineMatch o) {
        if(this.getPuntos() < o.getPuntos()) return 1;
        else if (this.getPuntos() > o.getPuntos()) return -1;
        else {
            final int diferenciagoles1 = this.getGolesafavor() - this.getGolesencontra();
            final int diferenciagoles2 = o.getGolesafavor() - o.getGolesencontra();
            if (diferenciagoles1 < diferenciagoles2) return 1;
            else if(diferenciagoles1 > diferenciagoles2) return -1;
            else {
                if(this.getGolesafavor() < o.getGolesafavor()) return 1;
                else if(this.getGolesafavor() > o.getGolesafavor()) return -1;
                else {
                    return this.getName().toUpperCase().compareTo(o.getName().toUpperCase());
                }
            }
        }
    }

    public int comparePos(LineMatch o) {
        if(this.getPuntos() < o.getPuntos()) return 1;
        else if (this.getPuntos() > o.getPuntos()) return -1;
        else {
            final int diferenciagoles1 = this.getGolesafavor() - this.getGolesencontra();
            final int diferenciagoles2 = o.getGolesafavor() - o.getGolesencontra();
            if (diferenciagoles1 < diferenciagoles2) return 1;
            else if(diferenciagoles1 > diferenciagoles2) return -1;
            else {
                if(this.getGolesafavor() < o.getGolesafavor()) return 1;
                else if(this.getGolesafavor() > o.getGolesafavor()) return -1;
                else {
                    if(this.getPj() < o.getPj()) return 1;
                    else if(this.getPj() == o.getPj()) return 0;
                    else return -1;
                }
            }
        }
    }
}

}


//On 08/26/2016 (26.08.2016) the German Fussball-Bundesliga (national soccer league) started another new season!
//
//        In this kata you get an array with 9 strings, which contain the matches from the first match day.
//        Every string has this format, where x and y are the number of goals for the teams, if the match has already been played:
//
//        x:y [Team 1] - [Team 2]
//
//        Example:
//        6:0 FC Bayern München - Werder Bremen
//        -:- Eintracht Frankfurt - Schalke 04
//        The team, which has shot more goals than the other team, wins the match.
//
//        Your method should create and return the league table as one string.
//
//        Every line in the table follows these rules:
//
//        1. Tableplace with two chars and a dot (" 1.", "12.")
//        2. Space
//        3. Name of the team/club padded right up to 30 chars (filled up with spaces).
//        4. Number of played matches (in this kata always only one digit)
//        5. Two spaces
//        6. Number of won matches (in this kata always only one digit)
//        7. Two spaces
//        8. Number of tie matches (in this kata always only one digit)
//        9. Two spaces
//        10. Number of lost matches (in this kata always only one digit)
//        11. Two spaces
//        12. Differences of goals (shot vs. gotten)
//        13. Two spaces
//        14. Count of points
//        It is 3 points for a won match and 1 point for a tie.
//        The table has to be sorted by these criteria:
//
//        1. Points
//        2. If the points are the same: The difference of goals. (2:0 is better than 1:0)
//        3. If the difference of goals is the same: More goals are better! (2:1 is better than 1:0)
//        4. Otherwise: The teams share the same place, but ordered by the name of the team (case-insensitive!)!
//        Example with the two matches above, if the league WOULD only have 4 teams:
//
//        1. FC Bayern München             1  1  0  0  6:0  3
//        2. Eintrach Frankfurt            0  0  0  0  0:0  0
//        2. Schalke 04                    0  0  0  0  0:0  0
//        4. Werder Bremen                 1  0  0  1  0:6  0
//        You do not have to do a check for the input values. It will always be an array of 9 strings and all strings will be complete!
//
//        Have fun coding it and please don't forget to vote and rank this kata! :-)
//
//        I have created other katas. Have a look if you like coding and challenges.


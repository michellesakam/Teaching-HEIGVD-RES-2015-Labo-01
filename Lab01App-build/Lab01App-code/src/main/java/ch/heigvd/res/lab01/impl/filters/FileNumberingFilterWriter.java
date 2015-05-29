package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int compteur = 1;
    private int debut = 1;
    private int precedant = 'a';

    private Utils utile;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        //int compteur = 1;
        String resultat = "";
        if (compteur == 1) {
            resultat = "1\t";
        }
        int i = off;
        // if ((len + off)> str.length())
        int c = len + off;
        //resultat = compteur++ + "\t";
        while (i < c) {
            if (str.charAt(i) == '\r') {
                if (i + 1 < c && str.charAt(i + 1) == '\n') {
                    resultat = resultat + str.charAt(i);
                    resultat = resultat + str.charAt(++i) + ++compteur + "\t";

                } else {
                    resultat = resultat + str.charAt(i) + ++compteur + "\t";
                }

            } else if (str.charAt(i) == '\n') {
                resultat = resultat + str.charAt(i) + ++compteur + "\t";

            } else {
                resultat = resultat + str.charAt(i);
            }
            i++;
        }
        super.write(resultat, 0, resultat.length());
     //super.flush();
        //ception("The student has not implemented this method yet.");
    }

    //String resultat ="";
    int dernierVal = 1;

    @Override

    public void write(String str) throws IOException {

        //int compteur = 1;
        String resultat = "";

        if (compteur == 1 && dernierVal == 1) {
            resultat = "1\t";
        }

        int i = 0;
        //resultat = compteur++ + "\t";
        while (i < str.length()) {
            if (str.charAt(i) == '\r') {
                if (str.charAt(i + 1) == '\n') {
                    resultat = resultat + str.charAt(i);
                    resultat = resultat + str.charAt(++i) + ++compteur + "\t";
                    //i++;
                } else {
                    resultat = resultat + str.charAt(i) + ++compteur + "\t";
                }

            } else if (str.charAt(i) == '\n') {
                resultat = resultat + str.charAt(i) + ++compteur + "\t";

            } else {
                resultat = resultat + str.charAt(i);
            }
            i++;
        }

        if ((str.charAt(i - 1) != '\n') || (str.charAt(i - 1) != '\r')) {
            dernierVal = 0;
        }
        super.write(resultat, 0, resultat.length());

        super.flush();

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        String str = new String(cbuf);
        //int compteur = 1;
        String resultat = "";
        if (compteur == 1) {
            resultat = "1\t";
        }
        int i = off;
     //if ((len + off)> str.length())
        // return;
        //resultat = compteur++ + "\t";
        int c = len + off;
        while (i < c) {
            if (str.charAt(i) == '\r') {
                if (str.charAt(i + 1) == '\n') {
                    resultat = resultat + str.charAt(i);
                    resultat = resultat + str.charAt(++i) + ++compteur + "\t";
                } else {
                    resultat = resultat + str.charAt(i) + ++compteur + "\t";
                }

            } else if (str.charAt(i) == '\n') {
                resultat = resultat + str.charAt(i) + ++compteur + "\t";

            } else {
                resultat = resultat + str.charAt(i);
            }
            i++;
        }
        super.write(resultat, 0, resultat.length());
        super.flush();
        //
    }

    @Override

    public void write(int c) throws IOException {

        //char copie = Character.toTitleCase((char)c); 
        String resultat = "";

        if (debut == 1) {
            resultat = "1\t";
            super.write(resultat, 0, resultat.length());
            super.write(c);
        } else {
            if (Character.toTitleCase(c) == '\n'&& precedant != '\r') {
                super.write(c);
                resultat = resultat + ++compteur + "\t";
                super.write(resultat, 0, resultat.length());
            } else if (Character.toTitleCase(c) != '\n' && precedant == '\r') {
                resultat = resultat + ++compteur + "\t";
                super.write(resultat, 0, resultat.length());
                super.write(c);
                precedant = 'a';
            } else if (Character.toTitleCase(c) == '\n' && precedant == '\r') {
                super.write(c);
                resultat = resultat + ++compteur + "\t";
                super.write(resultat, 0, resultat.length());
                precedant = 'a';
            } else {
                super.write(c);
            }
            if (Character.toTitleCase(c) == '\r') {
                precedant = '\r';

            }
        }
        debut++;
    }

}

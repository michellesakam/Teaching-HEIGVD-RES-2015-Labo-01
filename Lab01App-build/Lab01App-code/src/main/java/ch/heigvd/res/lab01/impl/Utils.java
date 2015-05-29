package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
      String [] resultat = {null, null};
      String[] separateur = {"\n", "\r\n", "\r"};
      int indice ; 
      for (int i=0; i<separateur.length; i++){
          indice = lines.indexOf(separateur[i]);
          if (indice != -1){
              resultat[0] = lines.substring(0, indice + separateur[i].length() );
              resultat[1] = lines.substring(indice + separateur[i].length());
              
              return resultat;
          }
          
          
      }
      
      resultat[0] = "";
      resultat[1] = lines;
      return resultat;
  }

}

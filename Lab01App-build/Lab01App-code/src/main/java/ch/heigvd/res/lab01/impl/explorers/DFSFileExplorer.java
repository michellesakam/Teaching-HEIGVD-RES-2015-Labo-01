package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
       visitor.visit(rootDirectory);
      // declaration d'un tableau de fichiers du repertoire courant
      File List[] = rootDirectory.listFiles();
       // declaration d'un tableau de repertoires du repertoire courant
       LinkedList<File> repertories = new LinkedList<>();
       // si ce repertoire ne contient aucun fichier
        if ( List == null ) 
            return;// on sort
        // sinon
        else{
            // on parcours la liste des fichiers
             for ( int i = 0; i < List.length; i++) {
                 // si on a un repertoire
	             if(List[i].isDirectory())
                         // on l'ajoute à la liste des repretoire
                         repertories.add(List[i]);
                         
                     else{
                         // sinon on visite le fichier
                             visitor.visit(List[i]);
                             }
             }
             for(File f : repertories){
                 explore(f, visitor);
             }
                 
        }
        
  }

}

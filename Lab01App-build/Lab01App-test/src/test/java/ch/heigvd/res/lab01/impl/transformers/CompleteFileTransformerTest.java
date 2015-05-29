package ch.heigvd.res.lab01.impl.transformers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Olivier Liechti
 */
public class CompleteFileTransformerTest {
  
  @Test
  public void itShouldApplyTwoTransformationsOnAFile() throws IOException {
    FileUtils.deleteDirectory(new File("./tmp"));
    new File("./tmp").mkdir();
    CompleteFileTransformer transformer = new CompleteFileTransformer();
    File inputFile = new File("./tmp/test.2transformations.txt");
    File outputFile = new File("./tmp/test.2transformations.txt.out");
    File expectedFile = new File("./tmp/test.2transformations.txt.expected");
    
    OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream(inputFile), "UTF-8" );
    writer.write("This is line 1\nThis is line 2\r\nThis is line 3\rThis is line 4");
    writer.flush();
    writer.close();

    writer = new OutputStreamWriter( new FileOutputStream(expectedFile), "UTF-8" );
    writer.write("1\tTHIS IS LINE 1\n2\tTHIS IS LINE 2\r\n3\tTHIS IS LINE 3\r4\tTHIS IS LINE 4");
    writer.flush();
    writer.close();
    transformer.visit(inputFile);
    try{
      BufferedReader  buf = new BufferedReader(new FileReader(outputFile));
      try{
          String line;
          System.out.println("*************************");
          while((line = buf.readLine()) != null){
              System.out.println(line);
          }
          System.out.println("*************************");
      }finally{
          buf.close();
      }
    }catch(IOException  ioe){
           System.err.println("Erreur..." + ioe.toString());
              }
    
    
      try{
      BufferedReader  buf2 = new BufferedReader(new FileReader(expectedFile));
      try{
          String line2;
          System.out.println("*************************");
          while((line2 = buf2.readLine()) != null){
              System.out.println(line2);
          }
          System.out.println("*************************");
      }finally{
          buf2.close();
      }
    }catch(IOException  ioe){
           System.err.println("Erreur..." + ioe.toString());
              }
    assertTrue( FileUtils.contentEquals(expectedFile, outputFile) );
    FileUtils.deleteDirectory(new File("./tmp"));
  }

}

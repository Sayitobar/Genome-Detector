/*  Last edit date: 10.01.2022  */

package sayitobar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.xml.sax.SAXException;

public class GenomeDetector {
    public static final String version = "v0.4.9";

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // Get screen resolution
    public static int width  = (int) screenSize.getWidth();
    public static int height = (int) screenSize.getHeight();
    
    
    // intersect: Durchschnitt/kesişim -> intersect multiple arrays (without duplicates)
    public static List<Integer> intersect(List<List<Integer>> arrays) {
        Set<Integer> s1 = new HashSet<>(arrays.get(0));

        for (int i=1; i < arrays.size(); i++) {
            Set<Integer> s2 = new HashSet<>(arrays.get(i));
            s1.retainAll(s2);  // retain only those elements in the arraylist that are also present in the other array
        }

        return new ArrayList<>(s1);
    }
    // difference -> take only differences of multiple arrays (without duplicates)
    public static List<String> difference_str(List<List<String>> arrays) {
        Set<String> s1 = new HashSet<>(arrays.get(0));

        for (int i=1; i < arrays.size(); i++) {
            Set<String> s2 = new HashSet<>(arrays.get(i));
            s1.removeAll(s2);  // remove only those elements in the arraylist that are also present in the other array
        }

        return new ArrayList<>(s1);
    }
    
    public static List<String> intersect_str(List<List<String>> arrays) {
        Set<String> s1 = new HashSet<>(arrays.get(0));

        for (int i=1; i < arrays.size(); i++) {
            Set<String> s2 = new HashSet<>(arrays.get(i));
            s1.retainAll(s2);  // retain only those elements in the arraylist that are also present in the other array
        }

        return new ArrayList<>(s1);
    }


    public static String convert_XLSX_to_VCF(String XLSX_path, String newFilePath, boolean temporary) throws IOException, OpenXML4JException, SAXException {
        long startTime = System.nanoTime();

        // create new VCF file
        File f;
        if (temporary) {
            f = File.createTempFile(
                    "GDTEMP_" + Paths.get(newFilePath).getFileName().toString().split("\\.")[0] + "_",
                    "." + Paths.get(newFilePath).getFileName().toString().split("\\.")[1]
            );
            f.deleteOnExit();  // işi bitince otomatik silinir
        }
        else
            f = new File(newFilePath);

        
        // The package open is instantaneous, as it should be.
        OPCPackage  p  = OPCPackage.open(XLSX_path, PackageAccess.READ);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // read xlsx
        try (PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8.name())) {
            XLSX2VCF xlsx2vcf = new XLSX2VCF(p, ps);
            xlsx2vcf.process(0);
        } finally {
            p.revert();
        }

        // get string data
        String data = baos.toString(StandardCharsets.UTF_8.name());
        baos.close();
        data = data.replaceAll("\\s+$", "");  // := stripTrailing() - just remove redundant \n's

        // And then, save
        PrintStream ps = new PrintStream(f.getPath());
        ps.print(data);
        ps.close();

        ps.close();
        System.out.println("XLSX -> VCF conversion accomplished:\t" + XLSX_path + "    -->    " + f.getPath() + " \t(in " + (System.nanoTime()-startTime)/1000000000.0 + " secs)");

        return f.getPath();
    }

    
    
    public static int WinX = width/2, WinY = height/2;

    public static void main(String[] args) {
        MainMenu.wakeup();
    }
}

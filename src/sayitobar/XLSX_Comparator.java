package sayitobar;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

public class XLSX_Comparator {

    public static boolean isSplitChecked = false;
    public static boolean isOutputDiffs  = false;  // should we output differences instead of outputing common values?
    public static String splitters = "/|,|=|;";    // if wanted, split cell values by these (must be pure java REGEX form)
    
    
    public static List<List<String>> getCommonPositionsVCFs(String[] paths) throws IOException {  // same as VCF_Comparator.getCommonPositionsVCFs but this can split cells
        long startTime = System.nanoTime();
        System.out.println("Obtaining common positions of: " + Arrays.toString(paths));

        List<List<String>> commonPositions = new ArrayList<>();
        List<List<String>> positions = new ArrayList<>();  // [1st_files_positions, 2nd_files_positions, ...]

        // Iterate through all files (READ)
        for (int i = 0; i < paths.length; i++) {
            File file = new File(paths[i]);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            List<String> pos = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                // Get POS_INDEX
                if (VCF_Comparator.POS_INDEX < 0 && line.contains(VCF_Comparator.ROWID_firstCell[i]) && line.contains(VCF_Comparator.ROWID_aimedCell[i])){
                    VCF_Comparator.POS_INDEX = Arrays.asList(line.split("\t")).indexOf(VCF_Comparator.ROWID_aimedCell[i]);
                    continue;
                }

                // Check if this line is header part (#)
                if (line.startsWith("#")) continue;

                // else check if client wants to split cells & append it/them
                try {
                    if (isSplitChecked)
                        pos.addAll(Arrays.asList(line.split("\t")[VCF_Comparator.POS_INDEX].split(splitters)));
                    else
                        pos.add(line.split("\t")[VCF_Comparator.POS_INDEX]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IOException("Headers row could not be found! (Check FCHR & ACHR)");
                }
            }

            reader.close();
            positions.add(pos);
            VCF_Comparator.POS_INDEX = -1;
            System.out.println("\tCompleted " + (i+1) + "/" + paths.length);
        }
        
        
        for (int i = 0; i < paths.length; i++) {
            if (XLSX_Comparator.isOutputDiffs) {
                List<List<String>> temp = new ArrayList<>();
                
                temp.add(positions.get(i));  // for diff: first file's difference positions, seconds file's, ...
                for (int j = 0; j < paths.length; j++) {
                    if (i==j) continue;
                    temp.add(positions.get(j));
                }
                
                commonPositions.add(GenomeDetector.difference_str(temp));      // return different positions
            } else {
                commonPositions.add(GenomeDetector.intersect_str(positions));  // return common positions (regular)
            }
        }


        System.out.println("\tObtained common positions in " + (System.nanoTime()-startTime)/1000000000.0 + " seconds... (from .vcf)");
        
        return commonPositions;  // n commonPositions of n files
    }


    public static void start(String[] paths, String newFileLoc, String newFileName) throws IOException, OpenXML4JException, SAXException {
        // .xlsx to .vcf conversion
        for (int i=0; i < paths.length; i++) {
            String path = GenomeDetector.convert_XLSX_to_VCF(paths[i], paths[i].replace(".xlsx", ".vcf"), true);
            paths[i]    = path;
            GenomeDetector.progressBar.setValue((int) ((i+1.0)/(paths.length+2.0) * 100));
        }
        // XLSX_ComparatorMenu.progressBar_XLSX.setStringPainted(true);  // shows percentage as text on progressBar

        System.out.println("Started XLSX files comparison...");
        System.out.println("\tInput XLSX files:\t" + Arrays.toString(paths));
        System.out.println("\tOutput file:\t" + newFileLoc + newFileName);


        List<List<String>> commonPositions = XLSX_Comparator.getCommonPositionsVCFs(paths);  // if differences selected, diff of first file
        GenomeDetector.progressBar.setValue((int) ((paths.length+1.0)/(paths.length+2.0) * 100));

        VCF_Comparator.VCF_createNewXLSXwithCommonPositions(newFileLoc + newFileName, paths, commonPositions);
        GenomeDetector.progressBar.setValue(100);
    }
}

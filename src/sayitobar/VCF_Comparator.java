package sayitobar;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VCF_Comparator {

    public static int POS_INDEX = -1;        // Column index of position of mutation
    public static String[] ROWID_firstCell;  // First cell String of the categories row (for every file)
    public static String[] ROWID_aimedCell;  // Aimed cell String of the categories row (for every file)


    // Get positions of every variation
    // positions -> positions on whole genome as a number indicating that gene with alterations/mutations
    public static List<List<String>> getCommonPositionsVCFs(String[] paths) throws IOException {
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
                if (POS_INDEX < 0 && line.contains(ROWID_firstCell[i]) && line.contains(ROWID_aimedCell[i])){
                    POS_INDEX = Arrays.asList(line.split("\t")).indexOf(ROWID_aimedCell[i]);
                    continue;
                }

                // Check if this line is header part (#)
                if (line.startsWith("#")) continue;
                
                // else check if client wants to split cells & append it/them
                try {
                    if (XLSX_Comparator.isSplitChecked)
                        pos.addAll(Arrays.asList(line.split("\t")[POS_INDEX].split(XLSX_Comparator.splitters)));
                    else
                        pos.add(line.split("\t")[POS_INDEX]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IOException("Headers row could not be found! (Check FCHR & ACHR)");
                }
            }

            reader.close();
            positions.add(pos);
            POS_INDEX = -1;
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


    public static void VCF_createNewXLSXwithCommonPositions(String newFpath, String[] paths, List<List<String>> commonPositions) throws IOException {
        // n dosya karşılaştırılıyorsa n tane (o dosya adında) spreadsheet olacak, sadece occurrences kalacak

        long startTime = System.nanoTime();
        System.out.println("Creating new Excel with common intersected occurrences...");

        // Create new Excel file as OUTPUT
        SXSSFWorkbook wb_out = new SXSSFWorkbook();

        for (int j = 0; j < paths.length; j++) {
            // read original file to get occurrence-rows
            File file = new File(paths[j]);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String n = file.getName();
            if (file.getName().startsWith("GDTEMP_"))  // means we're dealing with an temp file
                n = n.substring(0, n.lastIndexOf("_")).replaceFirst("GDTEMP_", "") + ".xlsx";
            SXSSFSheet spreadsheet = wb_out.createSheet(n);


            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                // Don't write config part
                if (line.startsWith("##")) continue;

                if (POS_INDEX < 0 && line.contains(ROWID_firstCell[j]) && line.contains(ROWID_aimedCell[j])) {
                    POS_INDEX = Arrays.asList(line.split("\t")).indexOf(ROWID_aimedCell[j]);
                }

                // If we're in Categories/Headers row, create Headers row.
                if (line.contains(ROWID_firstCell[j]) && line.contains(ROWID_aimedCell[j])) {
                    spreadsheet.createRow(row);
                    for (int i = 0; i < line.split("\t").length; i++) {
                        spreadsheet.getRow(row).createCell(i).setCellValue(line.split("\t")[i]);
                    }
                    row++;
                }

                // If not, check if occurrence is in that original line. If so, insert it.
                else if (commonPositions.get(j).contains(line.split("\t")[POS_INDEX])) {
                    spreadsheet.createRow(row);

                    for (int i = 0; i < line.split("\t").length; i++) {     // cells
                        spreadsheet.getRow(row).createCell(i).setCellValue( // Import contents
                                line.split("\t")[i]
                        );
                    }
                    row++;
                }
            }

            reader.close();
            POS_INDEX = -1;
            System.out.println("\tFile " + paths[j] + " completed (" + (j+1) + "/" + paths.length + ")");
            
            GenomeDetector.progressBar.setValue((int) (GenomeDetector.progressBar.getValue() + (100-GenomeDetector.progressBar.getValue()) * (j+1.0)/paths.length));
        }


        // Writing the workbook into the file
        wb_out.write(new FileOutputStream(newFpath));
        wb_out.close();

        System.out.println("\tCreated in " + (System.nanoTime()-startTime)/1000000000.0 + " seconds...");
        System.out.println("\nFile saved as: " + newFpath);
    }


    

    
    public static void start(String[] paths, String newFileLoc, String newFileName) throws IOException {
        long startTime = System.nanoTime();

        System.out.println("Started VCF files comparison...");
        System.out.println("\tInput VCF files:\t" + Arrays.toString(paths));
        System.out.println("\tOutput file:\t" + newFileLoc + newFileName);


        List<List<String>> commonPositions = VCF_Comparator.getCommonPositionsVCFs(paths);
        VCF_Comparator.VCF_createNewXLSXwithCommonPositions(newFileLoc + newFileName, paths, commonPositions);

        System.out.println("\n\nTotal creation time: " + (System.nanoTime()-startTime)/1000000000.0 + " secs");
    }
}

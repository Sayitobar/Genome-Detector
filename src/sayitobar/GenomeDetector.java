/*  Last edit date: 05.09.2022  */

package sayitobar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.util.ZipSecureFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenomeDetector {
    public static final String version = "v0.4.1";
    
    private static int POS_INDEX;    // Index of position of mutation as column (vcf comparison)

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // Get screen resolution
    public static int width  = (int) screenSize.getWidth();
    public static int height = (int) screenSize.getHeight();
    

    public static ArrayList<String> getSearchedGenes(String file) throws IOException {
        ArrayList<String> geneNames = new ArrayList<>();

        // Creating workbook instance that refers to .xls file
        XSSFWorkbook wb      = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet    sheet_1 = wb.getSheetAt(0);  // get first sheet of Excel file

        // Find right column index
        int colIndex = -1;

        for (Cell cell: sheet_1.getRow(0)) {
            if (cell.getStringCellValue().toLowerCase().contains("gene")) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        // Get gene names (if name contains /,=; split it and store them separately)
        for (Row row : sheet_1) {
            if (row.getCell(colIndex) != null) {
                String gene = row.getCell(colIndex).getStringCellValue().replaceAll("\\s+","");

                if (!gene.equals(""))
                    geneNames.addAll(Arrays.asList(gene.split("[/,=;]")));  // /,=; olsa da olmasa da çalışıyor, array veriyor
            }
        }

        // Remove empty strings
        if (geneNames.contains(""))
            geneNames.removeAll(Arrays.asList("", null));

        // Remove duplicate gene names (via HashSet)
        Set<String> set = new HashSet<>(geneNames);
        geneNames.clear();
        geneNames.addAll(set);


        System.out.println(geneNames.size() + " genes: " + geneNames);
        wb.close();
        return geneNames;
    }

    public static ArrayList<Integer> searchForGenes(String file, ArrayList<String> genes) throws IOException {
        long startTime = System.nanoTime();

        // Create workbook of patient file
        XSSFWorkbook wb      = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet    sheet_1 = wb.getSheetAt(0);

        // Find right column index
        int colIndex = -1;

        for (Cell cell: sheet_1.getRow(0)) {
            if (cell.getStringCellValue().toLowerCase().contains("gene symbol")) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        // Search for diseased genes in patient's mutated gen pool
        ArrayList<Integer> diseased_rows = new ArrayList<>();

        for (int i=1; i < sheet_1.getLastRowNum(); i++) {
            Cell curr_cell = sheet_1.getRow(i).getCell(colIndex);

            // System.out.println("\t" + String.valueOf(i) + " -> "+ curr_cell);  // Uncomment when debugging (elongates the process a lot)

            if (curr_cell != null && curr_cell.getCellTypeEnum() == CellType.STRING) {
                for (String gene: genes) {
                    if (Arrays.asList(curr_cell.getStringCellValue().split("[/,=;]")).contains(gene)) {
                        System.out.println("\tFound gene in " + i + ":\t" + curr_cell.getStringCellValue());
                        diseased_rows.add(i);  // save the line of occurrence
                    }
                }
            } else {
                System.out.println("\n\t(For debugging purposes) Current cell is either null or cell type is not string.   ->   Cell index: "
                        + String.valueOf(i) + " \t- curr_cell: " + curr_cell + " \t- Cell type: " 
                        + (curr_cell == null ? "\"curr_cell = null\"\t" : curr_cell.getCellTypeEnum()));
            }
        }

        System.out.println("Total gene occurrences in \"" + file + "\": " + diseased_rows.size());
        System.out.println("\tFound in " + (System.nanoTime()-startTime)/1000000000.0 + " seconds...");
        wb.close();
        return diseased_rows;
    }

    public static void createNewExcelWithOccurrences(String newFpath, String patientFile, ArrayList<Integer> occurrences) throws IOException {
        long startTime = System.nanoTime();
        System.out.println("Creating new Excel with occurrences...");

        // Create new Excel file as OUTPUT
        XSSFWorkbook wb_out   = new XSSFWorkbook();
        XSSFSheet spreadsheet = wb_out.createSheet("Gene Occurrences");

        // Create workbook of patient file
        XSSFWorkbook wb      = new XSSFWorkbook(new FileInputStream(patientFile));
        XSSFSheet    sheet_1 = wb.getSheetAt(0);

        // First row (headings)
        spreadsheet.createRow(0);
        for (int i=0; i < sheet_1.getRow(0).getLastCellNum(); i++) {
            spreadsheet.setColumnWidth(i, sheet_1.getColumnWidth(i));  // Column width

            spreadsheet.getRow(0).createCell(i).setCellValue(  // Import contents
                    (sheet_1.getRow(0).getCell(i) == null) ? "" : sheet_1.getRow(0).getCell(i).toString()
            );
        }

        // Other rows (data)
        for (int row = 0; row < occurrences.size(); row++) {                                   // rows

            spreadsheet.createRow(row+1);

            for (int i=0; i < sheet_1.getRow(occurrences.get(row)).getLastCellNum(); i++) {    // cells

                spreadsheet.getRow(row+1).createCell(i).setCellValue(  // Import contents
                        (sheet_1.getRow(occurrences.get(row)).getCell(i) == null) ? "" : sheet_1.getRow(occurrences.get(row)).getCell(i).toString()
                );
            }
        }


        // Add filters
        spreadsheet.setAutoFilter(new CellRangeAddress(0, 0, 0, spreadsheet.getRow(0).getLastCellNum()-1));

        // Writing the workbook into the file
        wb_out.write(new FileOutputStream(newFpath));
        wb_out.close();
        wb.close();
        
        System.out.println("\tCreated in " + (System.nanoTime()-startTime)/1000000000.0 + " seconds...");
        System.out.println("\nFile saved as: " + newFpath);
    }

    public static void removeRow(XSSFSheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();

        if(rowIndex >= 0 && rowIndex < lastRowNum)
            sheet.shiftRows(rowIndex+1, lastRowNum, -1);

        if(rowIndex == lastRowNum){
            XSSFRow removingRow = sheet.getRow(rowIndex);

            if(removingRow != null)
                sheet.removeRow(removingRow);
        }
    }
    
    // intersect: Durchschnitt/kesişim -> intersect multiple arrays (without duplicates)
    public static List<Integer> intersect(List<List<Integer>> arrays) {
        Set<Integer> s1 = new HashSet<>(arrays.get(0));

        for (int i=1; i < arrays.size(); i++) {
            Set<Integer> s2 = new HashSet<>(arrays.get(i));
            s1.retainAll(s2);  // retain only those elements in the arraylist that are also present in the other array
        }

        return new ArrayList<>(s1);
    }


    // Get positions of every variation
    // positions -> positions on whole genome as a number indicating that gene with alterations/mutations
    public static List<Integer> getCommonPositionsVCFs(String[] paths) throws IOException {
        long startTime = System.nanoTime();
        System.out.println("Obtaining common positions of: " + Arrays.toString(paths));

        List<List<Integer>> positions = new ArrayList<>();  // [1st_files_positions, 2nd_files_positions, ...]

        // Iterate through all files (READ)
        for (String path: paths) {
            File file             = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            List<Integer> pos = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                // Get POS_INDEX
                if (line.contains("CHROM") && line.contains("POS")) {
                    POS_INDEX = Arrays.asList(line.split("\\s+")).indexOf("POS");
                    continue;
                }

                // Check if this line is header part (#)
                if (line.startsWith("#")) continue;

                // else, append/print it
                pos.add(Integer.valueOf(line.split("\\s+")[POS_INDEX]));
            }

            reader.close();
            positions.add(pos);
            System.out.println("\tCompleted " + (Arrays.asList(paths).indexOf(path)+1) + "/" + paths.length);
        }

        System.out.println("\tObtained common positions in " + (System.nanoTime()-startTime)/1000000000.0 + " seconds... (from .vcf)");
        return intersect(positions);
    }


    public static void VCF_createNewXLSXwithCommonPositions(String newFpath, String[] paths, List<Integer> commonPositions) throws IOException {
        // n dosya karşılaştırılıyorsa n tane (o dosya adında) spreadsheet olacak, sadece occurrences kalacak

        long startTime = System.nanoTime();
        System.out.println("Creating new Excel with common intersected occurrences...");

        // Create new Excel file as OUTPUT
        XSSFWorkbook wb_out = new XSSFWorkbook();

        for (String path : paths) {
            // read original file to get occurrence-rows
            File file             = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String n = file.getName();
            if (file.getName().startsWith("GDTEMP_"))  // means we're dealing with an temp file
                n = n.substring(0, n.lastIndexOf("_")).replaceFirst("GDTEMP_", "") + ".xlsx";
            XSSFSheet spreadsheet = wb_out.createSheet(n);


            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                // Don't write config
                if (line.startsWith("##"));  // continue; varmış gibi düşün (if else olduğundan unnecessary)
                
                // Categories
                else if (line.contains("CHROM") && line.contains("POS")) {
                    spreadsheet.createRow(row);
                    for (int i = 0; i < line.split("\\s+").length; i++) {
                        spreadsheet.getRow(row).createCell(i).setCellValue(line.split("\\s+")[i]);
                    }
                    row++;
                }

                // check if occurrence is in that line. If so, insert it.
                else if (commonPositions.contains( Integer.valueOf(line.split("\\s+")[POS_INDEX]) )) {
                    spreadsheet.createRow(row);

                    for (int i = 0; i < line.split("\\s+").length; i++) {    // cells
                        spreadsheet.getRow(row).createCell(i).setCellValue(        // Import contents
                                line.split("\\s+")[i]
                        );
                    }
                    row++;
                }
            }

            reader.close();
            System.out.println("\tFile " + path + " completed (" + (Arrays.asList(paths).indexOf(path)+1) + "/" + paths.length + ")");
        }


        // Writing the workbook into the file
        wb_out.write(new FileOutputStream(newFpath));
        wb_out.close();

        System.out.println("\tCreated in " + (System.nanoTime()-startTime)/1000000000.0 + " seconds...");
        System.out.println("\nFile saved as: " + newFpath);
    }


    public static String convert_XLSX_to_VCF(String XLSX_path, String newFilePath, boolean temporary) throws IOException, OpenXML4JException {
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
        
        File f_xlsx          = new File(XLSX_path);    // read xlsx file
        FileOutputStream fop = new FileOutputStream(f);
        ZipSecureFile.setMaxTextSize(1000000000);      // to prevent excessive memory usage security error

        String str = new XSSFExcelExtractor(new XSSFWorkbook(f_xlsx)).getText();  // get raw text of xlsx file
        
        if (!str.startsWith("#"))
            str = str.substring(str.indexOf("\n")+1);  // for some reason str starts with xlsx file name (need to get rid of it)
        
        fop.write(str.getBytes());

        // close the output stream and buffer reader
        fop.flush();
        fop.close();

        System.out.println("XLSX -> VCF conversion accomplished:\t" + XLSX_path + "    -->    " + f.getPath() + " \t(in " + (System.nanoTime()-startTime)/1000000000.0 + " secs)");
        return f.getPath();
    }



    public static void start_detector(String patientFile, String genesFile, String newFileLoc, String newFileName) throws IOException {
        long startTime = System.nanoTime();

        System.out.println("Started genome detection...");
        System.out.println("\tPatient file:\t" + patientFile);
        System.out.println("\tGenes file:\t" + genesFile);
        System.out.println("\tOutput file:\t" + newFileLoc + newFileName);


        ArrayList<String>  genes       = getSearchedGenes(genesFile);

        ArrayList<Integer> occurrences = searchForGenes(patientFile, genes);

        createNewExcelWithOccurrences(newFileLoc + newFileName, patientFile, occurrences);

        System.out.println("\n\nTotal creation time: " + (System.nanoTime()-startTime)/1000000000.0 + " secs");
    }
    
    public static void start_VCF_comparator(String[] paths, String newFileLoc, String newFileName) throws IOException {
        long startTime = System.nanoTime();

        System.out.println("Started VCF files comparison...");
        System.out.println("\tInput VCF files:\t" + Arrays.toString(paths));
        System.out.println("\tOutput file:\t" + newFileLoc + newFileName);


        List<Integer> commonPositions = getCommonPositionsVCFs(paths);
        VCF_createNewXLSXwithCommonPositions(newFileLoc + newFileName, paths, commonPositions);

        System.out.println("\n\nTotal creation time: " + (System.nanoTime()-startTime)/1000000000.0 + " secs");
    }
    
    public static void start_XLSX_comparator(String[] paths, String newFileLoc, String newFileName) throws IOException {
        long startTime = System.nanoTime();

        // xlsx to vcf conversion
        for (int i=0; i < paths.length; i++) {
            try {
                String path = convert_XLSX_to_VCF(paths[i], paths[i].replace(".xlsx", ".vcf"), true);
                paths[i]    = path;
            } catch (OpenXML4JException e) {System.out.println(e);}
        }

        System.out.println("Started XLSX files comparison...");
        System.out.println("\tInput XLSX files:\t" + Arrays.toString(paths));
        System.out.println("\tOutput file:\t" + newFileLoc + newFileName);


        List<Integer> commonPositions = getCommonPositionsVCFs(paths);
        VCF_createNewXLSXwithCommonPositions(newFileLoc + newFileName, paths, commonPositions);

        System.out.println("\n\nTotal creation time: " + (System.nanoTime()-startTime)/1000000000.0 + " secs");
    }
    
    
    public static int WinX = width/2, WinY = height/2;

    public static void main(String[] args) {
        MainMenu.wakeup();
    }
}

package sayitobar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Detector {
    public static String P_GenesCellValue = "Gene Symbol";  // Patient's first row's cell value, which contains genes
    public static String L_GenesCellValue = "Gene Symbol";  // Other list's first row's cell value, which contains genes
    
    public static String splitters = "/|,|=|;";  // if wanted, split cell values by these (must be pure java REGEX form)

    public static ArrayList<String> getSearchedGenes(String file, int sheetNum) throws IOException {
        ArrayList<String> geneNames = new ArrayList<>();

        // Creating workbook instance that refers to .xls file
        XSSFWorkbook wb   = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet_1 = wb.getSheetAt(sheetNum);  // get first sheet of Excel file

        // Find right column index
        int colIndex = -1;

        for (Cell cell: sheet_1.getRow(0)) {
            if (cell.getStringCellValue().toLowerCase().trim().contains(L_GenesCellValue.toLowerCase().trim())) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        // Get gene names (if name contains /,=; split it and store them separately)
        for (int i=1; i < sheet_1.getLastRowNum(); i++) {
            if (sheet_1.getRow(i).getCell(colIndex) != null) {
                String gene = sheet_1.getRow(i).getCell(colIndex).getStringCellValue().replaceAll("\\s+","");

                if (!gene.equals(""))
                    geneNames.addAll(Arrays.asList(gene.split(splitters)));  // /,=; olsa da olmasa da çalışıyor, array veriyor
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

    public static ArrayList<Integer> searchForGenes(String file, ArrayList<String> genes, int sheetNum) throws IOException {
        long startTime = System.nanoTime();

        // Create workbook of patient file
        XSSFWorkbook wb      = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet    sheet_1 = wb.getSheetAt(sheetNum);

        // Find right column index at first row
        int colIndex = -1;

        for (Cell cell: sheet_1.getRow(0)) {
            if (cell.getStringCellValue().toLowerCase().trim().contains(P_GenesCellValue.toLowerCase().trim())) {
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
                    if (Arrays.asList(curr_cell.getStringCellValue().split(splitters)).contains(gene)) {
                        System.out.println("\tFound gene in " + i + ":\t" + curr_cell.getStringCellValue());
                        diseased_rows.add(i);  // save the line of occurrence
                    }
                }
            } else {
                System.out.println("\n\t(For debugging purposes) Current cell is either null or cell type is not string.   ->   Cell index: "
                        + i + " \t- curr_cell: " + curr_cell + " \t- Cell type: "
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
        XSSFSheet spreadsheet = wb_out.createSheet("Occurrences - " + patientFile.substring(patientFile.lastIndexOf("/")+1));

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


    



    public static void start(String patientFile, String genesFile, String newFileLoc, String newFileName) throws IOException {
        long startTime = System.nanoTime();

        System.out.println("Started genome detection...");
        System.out.println("\tPatient file:\t" + patientFile);
        System.out.println("\tGenes file:\t" + genesFile);
        System.out.println("\tOutput file:\t" + newFileLoc + newFileName);


        ArrayList<String>  genes       = getSearchedGenes(genesFile, 0);

        ArrayList<Integer> occurrences = searchForGenes(patientFile, genes, 0);

        createNewExcelWithOccurrences(newFileLoc + newFileName, patientFile, occurrences);

        System.out.println("\n\nTotal creation time: " + (System.nanoTime()-startTime)/1000000000.0 + " secs");
    }
}

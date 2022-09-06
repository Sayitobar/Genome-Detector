# Genome-Detector
* Searches for some genes in an Excel file
* Compares multiple VCF or Excel files for common positions (multisample comparison)

# Gene Detector
Searches for some genes in an Excel file

### How to use Genome Detector - Gene Detector:
* Select your patient's gene file (must be Excel .xlsx file)
* Select your gene pool (must be Excel .xlsx file)
* Select your output folder which comes pre-selected (must be a folder)
* Create a name for your new Excel file, which will be created automatically
All (four) input places must be filled in order to work.
As output, an Excel (.xlsx) file will be created in chosen output folder with gene occurrences of your gene pool in your patient's gene data.

### Rules of input Excel files:
1. Excel file of patients gene data must have a column named "Gene symbols" at 1st row (can be changed in settings)
2. Excel file of gene pool must have a column named "Gene" at 1st row and others shouldn't have "Gene" in their names (can be changed in settings)
3. Excel file of gene pool shouldn't have a text (gene) that is spread across two rows/columns (and stuff like that), keep things simple...
4. Excel file of gene pool is allowed to have "\" or "," or "=" or ";" between two gene names in one cell (!only those!). In that case both genes will be searched. (i.e. -> KIF23=MKLP1)
5. All files should be in Excel Workbook "_.xlsx" format (not .xls or not "Strict Open XML Spreadsheet" as it has the same suffix .xlsx)
6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 51, you accidently highlighted row 62. Code thinks there are gene data in row 62, therefore it crashes.)

### How to use Genome Detector - VCF Comparator:
* Add at least 2 VCF (Variant Call Format) files by clicking "Add file" button (there is no number limit)
* Select your output folder which comes pre-selected (must be a folder)
* Create a name for your new Excel file, which will be created automatically

At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files.
### Rules of input VCF files:
1. It is assumed that first rows are metadata for VCF file, which start with "##". If they don't exist, it is no problem.
2. Headers/categories row should have "CHROM" at it's first cell. Keyword can be changed in settings. (As the headers rows index is not known, we must find it by searching some keywords in each row)
3. Headers/categories row should have "POS" at the cell/column, where positions of mutations are displayed. Keyword can be changed in settings.
4. Numbers under the positions column may have ONLY "." which make reading more easy (like 132.681). Cell will be read as an integer value.
5. It is the best that you don't alter/change/touch the fresh VCF file before processing it, to make sure a potential error is not your doing.

### How to use Genome Detector - XLSX Comparator:
* Add at least 2 XLSX files by clicking "Add file" button (there is no number limit)
* Select your output folder which comes pre-selected (must be a folder)
* Create a name for your new Excel file, which will be created automatically
At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of XLSX files.

### Rules of input XLSX files:
1. First rows of XLSX files may have metadata starting with "##". If they don't exist, it is no problem. (As these kinds of files mostly get converted from a VCF file to Excel, they tend to have "##" rows)
2. Headers/categories row should have "CHROM" at it's first cell. Keyword can be changed in settings. (As the headers rows index is not known, we must find it by searching some keywords in each row)
3. Headers/categories row should have "POS" at the cell/column, where positions of mutations are displayed. Keyword can be changed in settings.
4. Numbers under the positions column may have ONLY "." which make reading more easy (like 132.681). Cell will be read as an integer value.
5. All files should be in Excel Workbook "_.xlsx" format (not .xls or not "Strict Open XML Spreadsheet" as it has the same suffix .xlsx)
6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Code thinks there are more data in row 462, therefore it crashes.)


###### Contact
bsayitoglu@gmail.com

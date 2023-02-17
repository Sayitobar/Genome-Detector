# Genome-Detector
* Searches for some genes in an Excel file
* Compares multiple VCF or XLSX files for common/different positions (multisample comparison). It works like a simple comparator, but faster.

# Gene Detector
Searches for some genes in an XLSX file

### How to use Genome Detector - Gene Detector:
* Select first file, for example your patient's genes   (must be Excel .xlsx file)
* Select second file, for example your gene pool        (must be Excel .xlsx file)
* Write the "Column name of gene symbols" for both of your files. (i.e. If "Genes" is the header of your genes column, write that in.)

* If desired, change your pre-selected output folder
* If desired, name your new Excel file (gets created automatically)
* If desired, mark or unmark "Split cell text" or add your custom splitter. Just put a space between every splitters. (i.e. KIF23=MKLP1 will be splitted and recognized as two seperate genes)

All (four) input places must be filled in order to work.
As output, an Excel (.xlsx) file will be created in chosen output folder with gene occurrences of your gene pool in your patient's gene data. (second file - first file)

### Rules of input Excel files:
1. Excel file of gene pool shouldn't have a text (gene) that is spread across two rows/columns (and stuff like that), keep things simple...
2. All files should be in Excel Workbook "_.xlsx" format (not .xls or not "Strict Open XML Spreadsheet" as it has the same suffix .xlsx)
3. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 51, you accidently highlighted row 62. Algorythm thinks there are gene data in row 62, therefore it crashes.)
4. Excel file of gene pool is allowed to have "\\" or "," or "=" or ";" between two gene names in one cell and more if you add your custom splitter. In that case both genes will be searched.

# VCF Comparison
Compares multiple VCF files for common positions (multisample comparison)

### How to use Genome Detector - VCF Comparator:
* Add at least 2 VCF (Variant Call Format) files by clicking "Add file" button (there is no number limit)
* Select your output folder which comes pre-selected (must be a folder)
* Create a name for your new Excel file, which will be created automatically

At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files.
### Rules of input VCF files:
1. It is assumed that first rows are metadata for VCF file, which start with "##". If they don't exist, it is no problem.
2. Headers/categories row should have "CHROM" at it's first cell. Keyword can be changed in settings. (As the headers rows index is not known, we must find it by searching some keywords in each row)
3. Headers/categories row should have "POS" at the cell/column, where positions of mutations are displayed. Keyword can be changed in settings.
4. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.
5. It is the best that you don't alter/change/touch the fresh VCF file before processing it, to make sure a potential error is not your doing.

# XLSX Comparison
Compares multiple XLSX files for common positions (multisample comparison)

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
5. All files should be in Excel Workbook ".xlsx" format (not .xls or not "Strict Open XML Spreadsheet" as it has the same suffix .xlsx)
6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Code thinks there are more data in row 462, therefore it crashes.)


###### Contact
bsayitoglu@gmail.com

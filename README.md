# Genome-Detector
* Searches for some genes in an Excel file
* Compares multiple VCF or XLSX files for common/different positions (multisample comparison). It works like a simple comparator, but faster.

# Gene Detector
Searches for some genes in an XLSX file
(Slower than XLSX Comparator. If your files are big, use XLSX Comparator instead.)

### How to use Genome Detector - Gene Detector:
* Select first file, for example your patient's genes   (must be Excel .xlsx file)
* Select second file, for example your gene pool        (must be Excel .xlsx file)
* Write the `Column name of gene symbols` for both of your files. (i.e. If "Genes" is the header of your genes column, write that in.)

* If desired, change your pre-selected `Output folder`
* If desired, name your new Excel file `Output file (.xlsx)`
* If desired, mark or unmark `Split cell text` or add your custom splitter. Just put a space between every splitters. (i.e. KIF23=MKLP1 will be splitted and recognized as two seperate genes)

All (four) input places must be filled in order to work.
As output, an Excel (.xlsx) file will be created in chosen output folder with gene occurrences of your gene pool in your patient's gene data. (second file - first file)

### Rules of input Excel files:
1. Excel file of gene pool shouldn't have a text (gene) that is spread across two rows/columns (and stuff like that), keep things simple...
2. All files should be in Excel Workbook "_.xlsx" format (not .xls or not "Strict Open XML Spreadsheet" as it has the same suffix .xlsx)
3. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 51, you accidently highlighted row 62. Algorithm thinks there are gene data in row 62, therefore it crashes.)
4. Excel file of gene pool is allowed to have "\\" or "," or "=" or ";" or your custom splitters between two gene names in one cell and more if you add your custom splitter. In that case both genes will be searched.

# VCF Comparison
Compares multiple VCF files for common positions (multisample comparison)

(Faster than XLSX Comparator and Gene Detector)

### How to use Genome Detector - VCF Comparator:
* Add at least 2 VCF (Variant Call Format) files by clicking `Add file` button (there is no file number limit)
* Change`FCHR` & `ACHR` of every file!
  - FCHR: First Cell of the Categories Row. Find your headers row, (usually the first row) write the first cell's value here.
  - ACHR: Aimed Cell of the Categories Row. Find your headers row, (usually the first row) find the column you want to compare, write the header of that column here.
* If desired, change your pre-selected `Output folder`
* If desired, name your new Excel file `Output file (.xlsx)`
* If desired, mark or unmark `Split cell text` or add your custom splitter.
* If desired, mark or unmark `Output the differences`. (Check this if you need discordant comparison)

At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files. If `Output the differences` checked, each spreadsheet will have it's own differences.

### Rules of input VCF files:
1. Input file name mustn't contain illegal characters like `.` `:` or `'`.              Otherwise, program will not work.
2. You should write `FCHR` & `ACHR` names correctly, these are **_case-sensitive_**.    Otherwise, program will not work.
3. It is assumed that first rows are metadata for VCF file, which start with "##". If they don't exist, it is no problem.
4. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.
5. It is the best that you don't alter/change/touch the fresh VCF file before processing it, to make sure a potential error is not your doing.

# XLSX Comparison
Compares multiple XLSX files for common positions (Suitable for every use. Fast and reliable. Engineered for multisample comparison)

### How to use Genome Detector - XLSX Comparator:
* Add at least 2 XLSX Excel files by clicking `Add file` button (there is no file number limit)
* Change`FCHR` & `ACHR` of every file!
  - FCHR: First Cell of the Categories Row. Find your headers row, (usually the first row) write the first cell's value here.
  - ACHR: Aimed Cell of the Categories Row. Find your headers row, (usually the first row) find the column you want to compare, write the header of that column here.
* If desired, change your pre-selected `Output folder`
* If desired, name your new Excel file `Output file (.xlsx)`
* If desired, mark or unmark `Split cell text` or add your custom splitter.
* If desired, mark or unmark `Output the differences`. (Check this if you need discordant comparison)

At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of XLSX files. If `Output the differences` checked, each spreadsheet will have it's own differences.

### Rules of input XLSX files: (for self debugging)
1. Input file name mustn't contain illegal characters like `.` `:` or `'`.              Otherwise, program will not work.
2. You should write `FCHR` & `ACHR` names correctly, these are **_case-sensitive_**.    Otherwise, program will not work.
3. All files should be in Excel Workbook ".xlsx" format (not .xls or not `Strict Open XML Spreadsheet` as it has the same suffix .xlsx)
4. First rows of XLSX files may have metadata starting with "##". If they don't exist, it's perfect. (As these kinds of files mostly get converted from a VCF file to Excel, they tend to have "##" rows)
5. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.
6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Algorithm thinks there are more data in row 462, therefore it crashes.)

###### Contact
bsayitoglu@gmail.com

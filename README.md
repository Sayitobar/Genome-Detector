# Genome-Detector
* Compares multiple VCF or XLSX files for common/different positions (multisample comparison). It works like a simple comparator, but faster.
<img width="552" alt="image" src="https://github.com/Sayitobar/Genome-Detector/assets/95364352/827da7fe-86d4-4892-a0e4-72b9346ca734">

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
6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Algorithm thinks there are more data in row 462, therefore it crashes.
<img width="756" alt="example xlsx" src="https://user-images.githubusercontent.com/95364352/232305398-b3ae6cd7-fda7-4a0a-9d43-17b2a27e007f.png">

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

## New feature: Dev Console
Click the gear icon on the top right corner, next to the (i) button. It will open a DevConsole window. There you can see every operation done. As there is no progress bar and sometimes program gets stuck due to faulty files, open dev console and see it with your eyes, if your file is being processed.

<sub><sup><a href="https://www.flaticon.com/free-icons/healthcare-and-medical" title="healthcare and medical icons">Healthcare and medical icons created by Muhammad_Usman - Flaticon</a></sup></sub>
<br></br>
##### Contact
bsayitoglu@gmail.com

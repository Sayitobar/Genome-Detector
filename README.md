# Genome-Detector
* Compares multiple XLSX or VCF files for common/different positions (intended for multisample comparison). It works like a simple file comparator, but faster.
<img width="552" alt="image" src="https://github.com/Sayitobar/Genome-Detector/assets/95364352/827da7fe-86d4-4892-a0e4-72b9346ca734">

# XLSX Comparison
Compares multiple XLSX files for common/different positions (Suitable for every use. Fast and reliable. Engineered for multisample comparison)

### How to use Genome Detector - XLSX Comparator:
* Add at least 2 XLSX Excel files by clicking `Add file` button (there is no file number limit)
* Change`FCHR` & `ACHR` of every file!
  - FCHR: First Cell of the Categories row. Find your headers row, (usually the first row) write the first cell's value here (most left cell).
  - ACHR: Aimed Cell of the Categories row. Find your headers row, find the column you want to compare, write the header of that column here.
* If desired, you can:
  - change your pre-selected `Output folder`
  - name your new Excel file `Output file (.xlsx)`
  - mark/unmark `Split cell text` or add your custom splitter
  - mark/unmark `Output the differences` (check this if you need discordant comparison)

At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of XLSX files. If `Output the differences` checked, each spreadsheet will have it's own differences.

### Rules of input XLSX files: (for self debugging)
1. Input file name mustn't contain illegal characters like `.` `:` or `'`.              Otherwise, program will crash.
2. You should fill `FCHR` & `ACHR` cells correctly, these are **_case-sensitive_**.    Otherwise, program will crash.
3. All files should be in Excel Workbook ".xlsx" format (not .xls or not `Strict Open XML Spreadsheet` as it has the same suffix .xlsx)
4. First rows of XLSX files are allowed to have metadata starting with "##". If they don't exist, it's perfect. (As these kinds of files mostly get converted from a VCF file to Excel, they tend to have "##" rows)
5. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.
6. There mustn't be a highlighted/edited cell in a row under the final row. (For example, last row is row number 351, you accidently highlighted row 462. Algorithm thinks there are more data between those, therefore it crashes.
<img width="756" alt="example xlsx" src="https://user-images.githubusercontent.com/95364352/232305398-b3ae6cd7-fda7-4a0a-9d43-17b2a27e007f.png">

# VCF Comparison
Compares multiple VCF files for common/different positions (for multisample comparison)

(Faster than XLSX Comparator)

### How to use Genome Detector - VCF Comparator:
* Add at least 2 VCF (Variant Call Format) files by clicking `Add file` button (there is no file number limit)
* Change`FCHR` & `ACHR` of every file!
  - FCHR: First Cell of the Categories row. Find your headers row, (usually the first row) write the first cell's value here (most left cell).
  - ACHR: Aimed Cell of the Categories row. Find your headers row, find the column you want to compare, write the header of that column here.
* If desired, you can:
  - change your pre-selected `Output folder`
  - name your new Excel file `Output file (.xlsx)`
  - mark/unmark `Split cell text` or add your custom splitter
  - mark/unmark `Output the differences` (check this if you need discordant comparison)

At last an Excel (.xlsx) file will be created in chosen output folder with multiple spreadsheets containing common positions of VCF files. If `Output the differences` checked, each spreadsheet will have it's own differences.

### Rules of input VCF files:
1. Input file name mustn't contain illegal characters like `.` `:` or `'`.              Otherwise, program will crash.
2. You should fill `FCHR` & `ACHR` cells correctly, these are **_case-sensitive_**.    Otherwise, program will crash.
3. It is assumed that first rows are metadata for VCF file, which start with "##". If they don't exist, it is no problem.
4. Numbers under the positions column may have String values to make reading more easy (like 132.681). In that case, cell will be read as an integer value.
5. It's the best if you don't alter/change/touch the VCF file before processing it, to make sure a potential error is not your doing.

## New feature: Dev Console
Click the gear icon on the top right corner, next to the (i) button. It will open a DevConsole window. There you can see every operation done. As there is no progress bar and sometimes program gets stuck due to faulty files, open dev console and see it with your eyes, if your file is being processed.

<sub><sup><a href="https://www.flaticon.com/free-icons/healthcare-and-medical" title="healthcare and medical icons">App icon by: Healthcare and medical icons created by Muhammad_Usman - Flaticon</a></sup></sub>
<br></br>
##### Contact
bsayitoglu@gmail.com

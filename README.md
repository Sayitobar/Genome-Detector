# Genome-Detector
Efficiently compares multiple XLSX or VCF (Variant Call Format) files to identify common/different genomic positions, designed specifically for multisample comparisons. This tool functions as a specialized file comparator, optimized for speed and tailored to handle the intricacies of VCF files.

<img width="557" alt="mainss" src="https://github.com/user-attachments/assets/467943d9-9448-417f-906d-26ec101113e0">


## XLSX Comparison
Compares multiple XLSX files to identify common/different positions. Suitable for any use. Fast and reliable. Engineered for multisample comparisons.

### How to Use Genome-Detector: XLSX Comparator
1. **Add Files**: Click the `Add file` button to add at least two XLSX Excel files (no file number limit).
2. **Set Parameters**: 
   - `First Cell`: First Cell of the Categories row. Find your headers row (usually the first row) and write the first cell's value here (the most left cell).
   - `Aimed Cell`: Aimed Cell of the Categories row. Find the column you want to compare, and write the header of that column here.
3. **Optional Settings**:
   - Change the `Output folder` and `Output file type`.
   - Name your new Excel file `Output file (.xlsx)`.
   - Mark/unmark `Split cell text` or add your custom splitter (Add a space between every splitter).
   - Mark/unmark `Discordant Comparison`. Check this to output differences instead of commonities.

An Excel (.xlsx) file will be created in the chosen output folder with multiple spreadsheets containing common/different positions of the XLSX files. If VCF is selected as output type, each VCF file will contain its commonities/differences.


### Input XLSX File Rules (for Self Debugging)
1. Input file name must not contain illegal characters like `.` `:` or `'`.
2. `First Cell` & `Aimed Cell` parameters must be typed in correctly and are **_case-sensitive_**.
3. All files should be in Excel Workbook ".xlsx" format (not ".xls" or `Strict Open XML Spreadsheet`).
4. The first rows of XLSX files can have metadata starting with "##". If they don't exist, it's even better.
6. There must not be a highlighted/edited cell in a row under the final row. For example, if the last row is 351 and you accidentally highlighted row 462, the algorithm will think there are more data between those rows, causing it to crash.

<img width="757" alt="xlsxss" src="https://github.com/user-attachments/assets/956e0a47-61ef-43c3-b10f-626f07739706">

## VCF Comparison
Compares multiple VCF (Variant Call Format) files for common/different positions, optimized for multisample comparisons. (Faster than XLSX Comparator)

### How to use Genome-Detector: VCF Comparator
1. **Add Files**: Click the `Add file` button to add at least two VCF files (no file number limit).
2. **Hit `Compare`**
3. **Optional Settings**:
   - Change the `Output folder` and `Output file type`.
   - Name your new Excel file `Output file (.vcf)`.
   - Mark/unmark `Discordant Comparison`. Check this to output differences instead of commonities.

An Excel or VCF file will be created in the chosen output folder with multiple spreadsheets containing common/different positions of the VCF files. If VCF is selected as output type, each VCF file will contain its commonities/differences.

### Input VCF File Rules
1. Input file name must not contain illegal characters like `.` `:` or `'`.
2. Metadata lines in a VCF file should begin with `##` and headers line should begin with `#`, following the standard VCF file format.
3. The first header cell is designated for chromosomes and the second for positions, as commonly formatted in VCF files. However, the names of these headers can be customized.
4. It is best not to alter or change the VCF file before processing it to avoid potential errors.

## Important feature: Dev Console
Click the disk drive icon on the top right corner, next to the (i) button, to open the Dev-Console window. Here, you can see every operation done. In case of an unexpected error, open the Dev-Console to check if your file is being processed correctly, or print the console log to share it with others.

<sub><sup><a href="https://www.flaticon.com/free-icons/healthcare-and-medical" title="healthcare and medical icons">App icon by: Healthcare and medical icons created by Muhammad_Usman - Flaticon</a></sup></sub>
<br></br>
##### Contact
For any questions or support, contact: bsayitoglu@gmail.com

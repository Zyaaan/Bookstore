package function;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JTable;

public class TableToPdfExporter {

    // Method to export JTable data to a PDF file
    public static void export(JTable table, String filePath) {
        try {
            // Create a new Document with landscape orientation
            Document document = new Document(PageSize.A4.rotate());

            // Create a PdfWriter instance with the specified file path
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open the document for writing
            document.open();

            // Create a PdfPTable with the same number of columns as the JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            // Add table column names as headers to the PDF table
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }

            // Add table data to the PDF table
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    pdfTable.addCell(table.getValueAt(i, j).toString());
                }
            }

            // Add the PDF table to the document
            document.add(pdfTable);

            // Close the document after writing
            document.close();
        } catch (Exception e) {
            // Print stack trace if an exception occurs during the PDF creation process
            e.printStackTrace();
        }
    }

}


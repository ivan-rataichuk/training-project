package ua.nure.rataichuk.SummaryTask4.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ua.nure.rataichuk.SummaryTask4.entities.ReportEntry;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.FacultyReport;

/**
 * PDF generation service
 * 
 * @author Ivan Rataichuk
 *
 */
public class PDF {
	
	public void GenerateReportPDF(List<FacultyReport> frl, String realPath) throws IOException {
		// Create a document and add a page to it
		  PDDocument document = new PDDocument();
		  PDPage page = new PDPage();
		  document.addPage( page );

		  // Create a new font object selecting one of the PDF base fonts
		  PDFont font = PDType1Font.TIMES_ROMAN;

		  // Start a new content stream which will "hold" the to be created content
		  PDPageContentStream contentStream = new PDPageContentStream(document, page);

		  // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
		  contentStream.beginText();
		  contentStream.setFont( font, 12 );
		  contentStream.setLeading(12 * 1.2);
		  contentStream.newLineAtOffset(50, 750);
		  contentStream.showText("Report");
		  contentStream.newLine();
		  
		  for (FacultyReport fr : frl) {
			  contentStream.showText(fr.getFacultyName());
			  contentStream.newLine();
			  
			  contentStream.showText( "Budget" );
			  contentStream.newLine();
			  
			  for (ReportEntry re : fr.getBudget()) {
				  StringBuilder sb = new StringBuilder();
				  if (re.isChecked()) {
					  sb.append(" + ");
				  } else {
					  sb.append(" ? ");
				  }
				  sb.append(re.getFullName() + " " + re.getGradesSum());
				  contentStream.showText(sb.toString());
				  contentStream.newLine();
				  
			  }
			  
			  contentStream.showText( "Contract" );
			  contentStream.newLine();
			  
			  for (ReportEntry re : fr.getContract()) {
				  StringBuilder sb = new StringBuilder();
				  if (re.isChecked()) {
					  sb.append(" + ");
				  } else {
					  sb.append(" ? ");
				  }
				  sb.append(re.getFullName() + " " + re.getGradesSum());
				  
				  contentStream.showText(sb.toString());
				  contentStream.newLine();
				  
			  }
			  
			  contentStream.showText("----------------------------------------------------------");
			  contentStream.newLine();
		  }
		  
		  contentStream.endText();

		  // Make sure that the content stream is closed:
		  contentStream.close();

		  // Save the results and ensure that the document is properly closed:
		  String uploadPath = realPath + "report";
		  File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
		  document.save( uploadPath + File.separator + "Report.pdf");
		  document.close();
	  }
}

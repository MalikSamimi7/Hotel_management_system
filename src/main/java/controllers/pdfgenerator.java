package controllers;



import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pdfgenerator {

    public pdfgenerator(String customerid, String cfname, String clname,String checkedin,String checkedout,String total) {


        try {

            String filename="C:\\Reciefts\\Recieft"+customerid+".pdf";
            System.out.println(filename);
            File file=new File(filename);
            file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        PdfWriter pdfWriter=new PdfWriter(file);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document=new Document(pdfDocument);


         float colwidth[]={280f};




            Paragraph p=new Paragraph("Hotel Management System").setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20);

            Paragraph customeridtext=new Paragraph("Customer ID:"+customerid);
            Paragraph date=new Paragraph("Reception Date:"+new SimpleDateFormat().format(new Date()));

            Table text = new Table(colwidth);
            text.addCell(new Cell().add(new Paragraph("Customer Information")).setBorder(Border.NO_BORDER).setFontSize(15));

            float ccolswidth[]={280f,280f,280f,280f,280f};
            Table customerinfo = new Table(ccolswidth);
            customerinfo.addCell(" First Name ");
            customerinfo.addCell(" Last Name ");
            customerinfo.addCell(" Check-In Date ");
            customerinfo.addCell(" Checked-Out Date ");
            customerinfo.addCell(" Total (Afghanis) ");

            customerinfo.addCell(cfname);
            customerinfo.addCell(clname);
            customerinfo.addCell(checkedin.toString());
            customerinfo.addCell(checkedout.toString());
            customerinfo.addCell(total);




            document.add(p);
            document.add(date);
            document.add(customeridtext);


            document.add(new Paragraph("\n"));
            document.add(text);
            document.add(customerinfo);


            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


package reportedecalificaciones;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.Desktop;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Datos {
    Scanner sc= new Scanner(System.in);
    String nombre,ncontrol;
    
    String[] materias=new String[]{"Frameworks & APIS","NET","REDES","MOVILES"};
    int[] calificaciones=new int[4];
    
    public void pedirDatos(){
            System.out.println("Introduce tu nombre:");
            nombre=sc.nextLine();
            
            System.out.println("Introduce tu No. de Control:");
            ncontrol=sc.nextLine();
            
            
            for (int i=0;i<calificaciones.length;i++){
                System.out.println("Digita la calificacion de la materia "+(i+1)+" :");
                 calificaciones[i]=sc.nextInt();
            }
    }
    
    public void desplegarDatos(){
        System.out.println("Nombre: "+nombre);
        System.out.println("No. de control: "+ncontrol);
        for (int c : calificaciones) {
            System.out.println("Calificacion: "+c);
        }
        
        generarPDF(nombre, ncontrol, calificaciones,materias);
    }
    
    
    public void generarPDF(String nombre,String ncontrol,int []c,String[] m) {
        try {
             Document doc=new Document();
             FileOutputStream archivopdf= new FileOutputStream("/home/bitback/Escritorio/ReporteCalificaciones.pdf");
        
             PdfWriter.getInstance(doc, archivopdf).setInitialLeading(20);
            doc.open();
            
            
            Image logo1=Image.getInstance("/home/bitback/Resources for APIS/logo1.png");
            logo1.scaleToFit(250,80);
            logo1.setAlignment(Chunk.ALIGN_LEFT);
            doc.add(logo1);
            
            
            Image logo2=Image.getInstance("/home/bitback/Resources for APIS/logo2.png");
            logo2.scaleToFit(200,80);
            //logo2.setAlignment(Chunk.ALIGN_RIGHT);
            logo2.setAbsolutePosition(480f, 720f);
           
            doc.add(logo2);
            
             doc.add(new Paragraph(" "));
             doc.add(new Paragraph(" "));
            
            
            Paragraph parrafito1 =new Paragraph("Calificaciones",FontFactory.getFont("arial",22));
            parrafito1.setAlignment(Element.ALIGN_CENTER);
            doc.add(parrafito1);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
            
             PdfPTable tabla=new PdfPTable(4);
             
             
             PdfPCell carrera=new PdfPCell(new Paragraph(
                     "TECNOLOGIAS DE LA INFORMACION Y COMUNICACIONES",
                     FontFactory.getFont("arial",14,Font.BOLD,BaseColor.RED)));
             carrera.setColspan(4);
             tabla.addCell(carrera);
             
            PdfPCell nom=new PdfPCell(new Paragraph("Nombre",FontFactory.getFont("arial",12,Font.BOLD)));
            nom.setColspan(1);
             tabla.addCell(nom);
             
             PdfPCell nomvalue=new PdfPCell(new Paragraph(nombre));
            nomvalue.setColspan(1);
            tabla.addCell(nomvalue);
            
            PdfPCell nc=new PdfPCell(new Paragraph("No. de Control",FontFactory.getFont("arial",12,Font.BOLD)));
            nc.setColspan(1);
            tabla.addCell(nc);
             
             PdfPCell ncvalue=new PdfPCell(new Paragraph(ncontrol));
            ncvalue.setColspan(1);
            tabla.addCell(ncvalue);
            
            
            
             PdfPCell titulo1=new PdfPCell(new Paragraph("MATERIAS",FontFactory.getFont("arial",12,Font.BOLD)));
            titulo1.setColspan(2);
            tabla.addCell(titulo1);
            
            
             
            
            
             PdfPCell titulo2=new PdfPCell(new Paragraph("CALIFICACION",FontFactory.getFont("arial",12,Font.BOLD)));
            titulo2.setColspan(2);
            tabla.addCell(titulo2);
            
             for (int i = 0; i < c.length; i++) {
                 Paragraph pa=new Paragraph(""+m[i]);
                    pa.setAlignment(Element.ALIGN_CENTER);
                //PdfPCell materia=new PdfPCell(new Paragraph(""+m[i]));
                    PdfPCell materia=new PdfPCell(pa);
                  materia.setColspan(2);
                  tabla.addCell(materia);
                  
                  PdfPCell materiavalue=new PdfPCell(new Paragraph(" "+c[i]));
                  materiavalue.setColspan(2);
                  
                  tabla.addCell(materiavalue);
            }
            
              
//             for (int d : c ) {
//                 PdfPCell materia=new PdfPCell(new Paragraph("Materia ",FontFactory.getFont("arial",12,Font.BOLD)));
//                  materia.setColspan(2);
//                  tabla.addCell(materia);
//                  
//                  PdfPCell materiavalue=new PdfPCell(new Paragraph(" "+d));
//                  materiavalue.setColspan(2);
//                  
//                  tabla.addCell(materiavalue);
//               
//            }
              
        doc.add(tabla);
        
        doc.close();
           int o= JOptionPane.showConfirmDialog(null,
                    "Tu Reporte esta Listo , Deseas Abrirlo?", "Proceso Exitoso", JOptionPane.YES_NO_OPTION);
            
            if (o == JOptionPane.YES_OPTION){
                try {
                    File path = new File ("/home/bitback/Escritorio/ReporteCalificaciones.pdf");
                    Desktop.getDesktop().open(path);
                }catch (IOException ex) {
                        ex.printStackTrace();
                    }
            }
        
        } catch (Exception e) {
        }
       
    }
    
    
    
}
;
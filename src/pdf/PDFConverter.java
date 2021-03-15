package pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.itextpdf.layout.Document;
import slides.Slide;
import slides.SlideShow;

public class PDFConverter {
    //TODO: Convert database files of selected slideshow into a PDF
    public static Image convertToImage(Pane pane, String fileLocation) {
        File file = new File(fileLocation);

        WritableImage a = new WritableImage(1200, 600);
        Image temp = pane.snapshot(null, a);
        //BufferedImage image = SwingFXUtils.fromFXImage(temp, null);

        /*
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException exception) {
            exception.printStackTrace();

        }
         */
        System.out.println(a);
        return temp;
    }
    public static void saveToPDF(SlideShow slideshow) {
        ArrayList<Image> images = new ArrayList<>();
        String location = System.getProperty("user.dir");
        File file = new File(location + "\\temp\\");
        try {
            file.mkdir();
            File pdfFile = new File(location + "\\temp\\stuff.pdf");
            pdfFile.createNewFile();
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(location + "\\temp\\stuff.pdf"));
            Document doc = new Document(pdfDoc, new PageSize(1200, 600));
            int i = 0;
            ArrayList<Slide> slides = slideshow.getSlides();
            ArrayList<Pane> panes = new ArrayList<>();
            for (Slide slide : slides) {
                panes.add(slide.pane);
            }
            for (Pane pane : panes) {
                PdfWriter writer = new PdfWriter(location + "\\temp\\" + i + ".png");
                String imageFile = location + "\\temp\\" + i + ".png";
                System.out.println(imageFile);
                ImageData data = ImageDataFactory.create(imageFile);
                com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);
                doc.add(image);
                i++;
            }
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.*;
import com.itextpdf.layout.Document;

public class PDFConverter {
    //TODO: Convert database files of selected slideshow into a PDF
    public static void convertToImage(Pane pane, String fileLocation) {
        File file = new File(fileLocation);

        WritableImage a = new WritableImage(1200, 600);
        Image temp = pane.snapshot(null, a);
        BufferedImage image = SwingFXUtils.fromFXImage(temp, null);

        try {
            ImageIO.write(image, "png", file);
        } catch (IOException exception) {
            exception.printStackTrace();

        }
        System.out.println(a);
    }
    public static void saveToPDF(Pane[] panes) {
        ArrayList<Image> images = new ArrayList<>();
        String location = System.getProperty("user.dir");
        File file = new File(location + "\\temp\\");
        try {
            file.createNewFile();
            File[] dirListing = file.listFiles();
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(location));
            Document doc = new Document(pdfDoc, new PageSize(1200, 600));
            int i = 0;
            for (Pane pane : panes) {
                convertToImage(pane, location + "\\temp\\" + i + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

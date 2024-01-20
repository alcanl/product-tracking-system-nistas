package com.alcanl.app.application.gui.util.print;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.entity.SaleItem;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

import static com.google.common.io.Resources.getResource;

public class JListPrinter implements Printable {
    private final  ListModel<SaleItem> m_listModel;

    public JListPrinter(ListModel<SaleItem> listModel)
    {
        m_listModel = listModel;

    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawString("TEKLİF", (int)pf.getImageableWidth() / 2 - 5, 100);
        drawJList(g2d, pf);
        drawImages(g2d, pf);
        g2d.drawString("Telefon: (0224) 2488088", (int)pf.getImageableWidth() / 2 - 28, (int)pf.getImageableHeight() - 40);
        g2d.drawString("eMail: info@nistas.com.tr", (int)pf.getImageableWidth() / 2 - 30, (int)pf.getImageableHeight() - 20);
        g2d.drawString("Adres: Yunuseli Fuat Kuşçuoğlu Cd. No:126 Osmangazi/Bursa", (int)pf.getImageableWidth() / 3 - 42, (int)pf.getImageableHeight());

        return Printable.PAGE_EXISTS;
    }

    private void drawJList(Graphics2D g2d, PageFormat pf) {

        int listX = 20;
        int listY = 150;

        g2d.drawRect(listX, listY, (int)pf.getImageableWidth(), m_listModel.getSize() * 24);

        for (int i = 0; i < m_listModel.getSize(); i++) {
            g2d.drawString((i + 1) + "- " + m_listModel.getElementAt(i).toString(), listX + 20, listY + 20 * (i + 1));
        }
    }

    private void drawImages(Graphics2D g2d, PageFormat pf) {


        int imageSpacing = 50;
        int startX = 100;
        int startY = (int)pf.getHeight();

        for (int i = 0; i < 10; i++) {
            try {
                String imagePath = getResource("resim" + (i + 1) + ".png").getPath();
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();
                var test = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                g2d.drawImage(test, startX + (i * (50 + imageSpacing)), startY, null);
            } catch (Exception ex) {
                Resources.showUnknownErrorMessageDialog(ex.getMessage());
            }
        }

    }
    public static PageFormat customizePageFormat(PageFormat pageFormat) {
        Paper paper = new Paper();
        double margin = 20.0;
        paper.setImageableArea(margin, margin, pageFormat.getWidth() - 2 * margin, pageFormat.getHeight() - 2 * margin);
        paper.setSize(pageFormat.getWidth(), pageFormat.getHeight());
        pageFormat.setPaper(paper);
        return pageFormat;
    }
}

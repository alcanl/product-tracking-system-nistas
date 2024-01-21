package com.alcanl.app.application.gui.util.print;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.entity.SaleItem;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.google.common.io.Resources.getResource;

public class JListPrinter implements Printable {
    private final  ListModel<SaleItem> m_listModel;
    private final JLabel m_labelPrice;

    public JListPrinter(ListModel<SaleItem> listModel, JLabel labelPrice)
    {
        m_listModel = listModel;
        m_labelPrice = labelPrice;
    }
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;

        drawHeader(g2d, pf);
        g2d.drawString("TEKLİF", (int)pf.getImageableWidth() / 2, 100);
        drawJList(g2d, pf);
        drawImages(g2d, pf);
        g2d.drawString("Telefon: (0224) 2488088", (int)pf.getImageableWidth() / 2 - 28, (int)pf.getImageableHeight() - 130);
        g2d.drawString("eMail: info@nistas.com.tr", (int)pf.getImageableWidth() / 2 - 30, (int)pf.getImageableHeight() - 110);
        g2d.drawString("Adres: Yunuseli Fuat Kuşçuoğlu Cd. No:126 Osmangazi/Bursa", (int)pf.getImageableWidth() / 3 - 42, (int)pf.getImageableHeight() - 90);

        return Printable.PAGE_EXISTS;
    }

    private void drawJList(Graphics2D g2d, PageFormat pf) {

        int listX = 20;
        int listY = 150;

        g2d.drawString("Ürün Adı", 20, 130);
        g2d.drawString("Adet", (int)pf.getImageableWidth() - 100, 130);
        g2d.drawString("Tutar", (int)pf.getImageableWidth() - 20, 130);
        g2d.drawRect(listX, listY, (int)pf.getImageableWidth(), m_listModel.getSize() * 24);

        for (int i = 0; i < m_listModel.getSize(); i++) {
            g2d.drawString((i + 1) + "- " + m_listModel.getElementAt(i).toString(), listX + 20, listY + 20 * (i + 1));
        }

        g2d.drawString(String.format("Toplam Tutar : %s TL", m_labelPrice.getText()),
                (int)pf.getImageableWidth() - 140, listY + m_listModel.getSize() * 24 + 20);
    }
    private void drawHeader(Graphics2D g2d, PageFormat pf)
    {
        ImageIcon icon = new ImageIcon(getResource("logoPrint.png").getPath());
        g2d.drawImage(icon.getImage(), 20, 20, icon.getImageObserver());
        var now = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        g2d.drawString(now, (int)pf.getImageableWidth() - 50, 50);

    }

    private void drawImages(Graphics2D g2d, PageFormat pf) {


        int imageSpacing = 6;
        int startX = 20;
        int startY = (int)pf.getImageableHeight() - 50;

        for (int i = 0; i < 10; i++) {
            try {
                ImageIcon icon = new ImageIcon(getResource("pic" + (i + 1) + ".png").getPath());

                g2d.drawImage(icon.getImage(), startX + (i * (50 + imageSpacing)), startY, icon.getImageObserver());
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

package GiaoDien;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class CurvedLineChartPanel extends JPanel {
    private int[] tongSoLuong = {4700, 400,111, 400};
    private String[] tenSP = {"Adidas Stan Smith","zz", "Converse Chuck Taylor All Star", "New Balance 574"};

    public CurvedLineChartPanel() {
        

        setSize(1000, 600);

        // Vẽ biểu đồ line chart cong cong
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int padding = 50;
                int width = getWidth() - 2 * padding;
                int height = getHeight() - 2 * padding;

                // Vẽ trục x và trục y
                g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);
                g2d.drawLine(padding, padding, padding, getHeight() - padding);

                // Vẽ các đường thẳng đánh dấu cho các giá trị trục y
                int numTicks = 11; // Số lượng đường thẳng đánh dấu trục y
                int tickSpacing = height / (numTicks - 1);

                for (int i = 0; i < numTicks; i++) {
                    int x = padding;
                    int y = getHeight() - padding - i * tickSpacing;

                    g2d.drawLine(x, y, x - 5, y);
                    g2d.drawString(String.valueOf(i * 500), x - 40, y + 5);
                }

                // Tính toán độ dài của mỗi cột
                int columnWidth = width / tenSP.length;

                // Vẽ các điểm dữ liệu và đường cong giữa chúng
                for (int i = 0; i < tenSP.length; i++) {
                    int x = padding + i * columnWidth + columnWidth / 2;
                    int y = getHeight() - padding - (int) (((double) tongSoLuong[i] / getMaxValue()) * height);

                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(x - 5, y - 5, 10, 10);

                    if (i > 0) {
                        int prevX = padding + (i - 1) * columnWidth + columnWidth / 2;
                        int prevY = getHeight() - padding - (int) (((double) tongSoLuong[i - 1] / getMaxValue()) * height);

                        g2d.setColor(Color.RED);
                        g2d.draw(curvedLine(prevX, prevY, x, y));
                    }

                    g2d.setColor(Color.BLACK);
                    g2d.drawString(tenSP[i], x - 20, getHeight() - padding + 15);
                    g2d.drawString(String.valueOf(tongSoLuong[i]), x - 10, y - 10);
                }
            }
        };

      
    }

    private int getMaxValue() {
        int max = tongSoLuong[0];
        for (int value : tongSoLuong) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private Shape curvedLine(int x1, int y1, int x2, int y2) {
        int ctrlX = (x1 + x2) / 2;
        int ctrlY = Math.min(y1, y2) - Math.abs(x1 - x2) / 2;

        return new QuadCurve2D.Double(x1, y1, ctrlX, ctrlY, x2, y2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurvedLineChartPanel example = new CurvedLineChartPanel();
            example.setVisible(true);
        });
    }
}
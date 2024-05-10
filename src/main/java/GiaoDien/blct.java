package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

public class blct extends JPanel {

	public JLabel blct_manv, blct_tennc, blct_songaycong, blct_manvsongaynghikhongphep, blct_songaynghicophep, blct_phat, blct_bhxh, blct_phucap, blct_tiencong,  blct_tongluog;
	public JButton nutInPhieuluong;
    public blct() {
        initUI();
    }

    private void initUI() {
    	setLayout(null);
    	 
        // Khởi tạo giao diện, các JLabel và JButton
    	JLabel lblNewLabel = new JLabel("Công Ty Sản Xuất Giày EvaShoes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(293, 11, 324, 27);
		 add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phiếu Lương Chi Tiết");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(359, 70, 174, 23);
		 add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày ");
		lblNewLabel_2.setBounds(369, 104, 28, 14);
		 add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tháng");
		lblNewLabel_2_1.setBounds(441, 104, 30, 14);
		 add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Năm");
		lblNewLabel_2_2.setBounds(512, 104, 21, 14);
		 add(lblNewLabel_2_2);
		
		  nutInPhieuluong = new JButton("In Phiếu Lương");
		nutInPhieuluong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nutInPhieuluong.setBounds(765, 12, 139, 37);
		 add(nutInPhieuluong);
		
		JLabel lblNewLabel_3 = new JLabel("Mã Nhân Viên");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(80, 138, 87, 16);
		 add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tên Nhân Viên");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_1.setBounds(80, 191, 91, 16);
		 add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Số Ngày Công");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2.setBounds(80, 249, 88, 16);
		 add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Số Ngày Nghỉ Có Phép");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2_1.setBounds(80, 340, 139, 16);
		 add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("Phạt");
		lblNewLabel_3_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2_2.setBounds(539, 127, 30, 16);
		 add(lblNewLabel_3_2_2);
		
		JLabel lblNewLabel_3_2_3 = new JLabel("BHXH");
		lblNewLabel_3_2_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2_3.setBounds(539, 191, 35, 16);
		 add(lblNewLabel_3_2_3);
		
		JLabel lblNewLabel_3_2_4 = new JLabel("Phụ Cấp");
		lblNewLabel_3_2_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2_4.setBounds(535, 249, 52, 16);
		 add(lblNewLabel_3_2_4);
		
		JLabel lblNewLabel_3_2_5 = new JLabel("Tiền Công");
		lblNewLabel_3_2_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2_5.setBounds(535, 296, 62, 16);
		 add(lblNewLabel_3_2_5);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Số Ngày Nghỉ Không Phép");
		lblNewLabel_3_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3_2_1_1.setBounds(80, 296, 163, 16);
		 add(lblNewLabel_3_2_1_1);
		
		  blct_manv = new JLabel("manv");
		blct_manv.setBounds(215, 140, 62, 14);
		 add(blct_manv);
		
		  blct_tennc = new JLabel("manv");
		blct_tennc.setBounds(215, 193, 87, 14);
		 add(blct_tennc);
		
		  blct_songaycong = new JLabel("manv");
		blct_songaycong.setBounds(215, 251, 87, 14);
		 add(blct_songaycong);
		
		  blct_manvsongaynghikhongphep = new JLabel("manv");
		blct_manvsongaynghikhongphep.setBounds(293, 298, 69, 14);
		 add(blct_manvsongaynghikhongphep);
		
		  blct_songaynghicophep = new JLabel("manv");
		blct_songaynghicophep.setBounds(293, 342, 69, 14);
		 add(blct_songaynghicophep);
		
		  blct_phat = new JLabel("manv");
		blct_phat.setBounds(650, 129, 62, 14);
		 add(blct_phat);
		
		  blct_bhxh = new JLabel("manv");
		blct_bhxh.setBounds(650, 193, 77, 14);
		 add(blct_bhxh);
		
		  blct_phucap = new JLabel("manv");
		blct_phucap.setBounds(650, 251, 77, 14);
		 add(blct_phucap);
		
		  blct_tiencong = new JLabel("manv");
		blct_tiencong.setBounds(650, 298, 77, 14);
		 add(blct_tiencong);
		
		  blct_tongluog = new JLabel("New label");
		blct_tongluog.setBounds(681, 393, 119, 14);
		 add(blct_tongluog);
		
        nutInPhieuluong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printPhieuLuong();
            }
        });

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(972, 481);
        
        JLabel lblNewLabel_3_2_6_1 = new JLabel("Tổng Lương :");
        lblNewLabel_3_2_6_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_3_2_6_1.setBounds(523, 384, 132, 25);
        add(lblNewLabel_3_2_6_1);
//        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void printPhieuLuong() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                if (page > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pf.getImageableX(), pf.getImageableY());

                drawPhieuLuong(g2d);

                return PAGE_EXISTS;
            }
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi in: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void drawPhieuLuong(Graphics2D g2d) {
    	 // Đặt kích thước trang in
    	 int x = 50; // Tọa độ x ban đầu
    	    int y = 50; // Tọa độ y ban đầu
    	    int lineHeight = 20; // Khoảng cách giữa các dòng

    	    g2d.drawString("Mã nhân viên: " + blct_manv.getText(), x, y);
    	    g2d.drawString("Phạt: " + blct_phat.getText(), x + 380, y  ); // Điều chỉnh tọa độ x cho phù hợp
    	    y += lineHeight;
    	    g2d.drawString("Tên nhân viên: " + blct_tennc.getText(), x, y);
    	    g2d.drawString("BHXH: " + blct_bhxh.getText(), x + 380, y  ); // Điều chỉnh tọa độ x cho phù hợp
    	    y += lineHeight;
    	    g2d.drawString("Số ngày công: " + blct_songaycong.getText(), x, y);
    	    g2d.drawString("Phụ cấp: " + blct_phucap.getText(), x + 380, y  ); // Điều chỉnh tọa độ x cho phù hợp
    	    y += lineHeight;
    	    g2d.drawString("Số ngày nghỉ có phép: " + blct_songaynghicophep.getText(), x, y);
    	    g2d.drawString("Tiền công: " + blct_tiencong.getText(), x + 380, y  ); // Điều chỉnh tọa độ x cho phù hợp
    	    y += lineHeight;
    	    g2d.drawString("Số ngày nghỉ không phép: " + blct_manvsongaynghikhongphep.getText(), x, y);
    	    
    	    y += lineHeight;
     
    	 
    	 
    	 
    
    	    g2d.drawString("Tổng lương: " + blct_tongluog.getText(), x + 380, y  ); // Điều chỉnh tọa độ x cho phù hợp
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            blct ex = new blct();
            ex.setVisible(true);
        });
    }
}

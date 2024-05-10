package GiaoDien;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import Dao.BangLuongCongNhanDAO;
import Dao.nhanVienDAO;
import Dao.thongkeDAO;
import Entity.BangLuongCongNhan;
import Entity.BangLuongNhanVien;
import Entity.ChamCongCongNhan;
import Entity.NhanVien;
import services.QuanLyLuongService;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.QuadCurve2D;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class TabThongKe extends JPanel {

	/**
	 * Create the panel.
	 */
	private thongkeDAO tkdao= new thongkeDAO();
	 private int[] tongSoLuong   ;
	    private String[] tenSP  ;
//	
		private DefaultTableModel modelthongkeluongnhanvien;
		private DefaultTableModel modelthongkeluongcongnhan;
		private DefaultTableModel modelthongkesanpham;
		
		private JTable tablethongkeluongnhanvien;
		private JTable tablethongkeluongcongnhan;
		private JTable tablethongkesanpham;
	public TabThongKe(QuanLyLuongService service) {
//		Database.ConnectDB.getInstance().connect();
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1402, 848);
		add(tabbedPane);
		
		JPanel panelQuanLyNhanVien = new JPanel();
		tabbedPane.addTab("Thống kê lương", null, panelQuanLyNhanVien, null);
		panelQuanLyNhanVien.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(13, 152, 204));
		panel.setBounds(10, 11, 1365, 765);
		panelQuanLyNhanVien.add(panel);
		
		FixButton lammoibangthongke = new FixButton("Lọc");
		lammoibangthongke.setText("Lọc thống kê lương nhân viên");
		
		lammoibangthongke.setIcon(new ImageIcon(TabThongKe.class.getResource("/image/search.png")));
		lammoibangthongke.setFont(new Font("Tahoma", Font.BOLD, 14));
		lammoibangthongke.setForeground(new Color(255, 255, 255));
		lammoibangthongke.setBackground(new Color(69, 129, 142));
		lammoibangthongke.setBounds(546, 282, 277, 40);
		panel.add(lammoibangthongke);
FixButton btnlammoithongkeluong = new FixButton("Làm Mới");
		btnlammoithongkeluong.setIcon(new ImageIcon(TabThongKe.class.getResource("/image/reload (1).png")));
		btnlammoithongkeluong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnlammoithongkeluong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnlammoithongkeluong.setForeground(new Color(255, 255, 255));
		btnlammoithongkeluong.setBackground(new Color(0, 158, 15));
		btnlammoithongkeluong.setBounds(1129, 282, 150, 40);
		panel.add(btnlammoithongkeluong);
		btnlammoithongkeluong.setEnabled(false);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(114, 67, 251, 124);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel = new JLabel("Tổng công nhân");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(69, 11, 133, 19);
		panel_1_1.add(lblNewLabel);
		
		JLabel tongluongcongnhan = new JLabel("tongluongcongnhanoday");
		tongluongcongnhan.setHorizontalAlignment(SwingConstants.CENTER);
		tongluongcongnhan.setFont(new Font("Dialog", Font.BOLD, 15));
		tongluongcongnhan.setBounds(10, 61, 231, 19);
		panel_1_1.add(tongluongcongnhan);
		
		
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(533, 67, 251, 124);
		panel.add(panel_1_1_1);
		
		JLabel lblTngNhnVin = new JLabel("Tổng nhân viên");
		lblTngNhnVin.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTngNhnVin.setBounds(69, 11, 133, 19);
		panel_1_1_1.add(lblTngNhnVin);
		
		JLabel tongluongnhanvien = new JLabel("tongluongcongnhanoday");
		tongluongnhanvien.setHorizontalAlignment(SwingConstants.CENTER);
		tongluongnhanvien.setFont(new Font("Dialog", Font.BOLD, 15));
		tongluongnhanvien.setBounds(10, 64, 231, 19);
		panel_1_1_1.add(tongluongnhanvien);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(957, 67, 251, 124);
		panel.add(panel_1_2);
		
		JLabel lblTngS = new JLabel("Tổng số Lương");
		lblTngS.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTngS.setBounds(67, 11, 133, 19);
		panel_1_2.add(lblTngS);
		
		JLabel tongluong = new JLabel("tongluongcongnhanoday");
		tongluong.setHorizontalAlignment(SwingConstants.CENTER);
		tongluong.setFont(new Font("Dialog", Font.BOLD, 15));
		tongluong.setBounds(10, 65, 231, 19);
		panel_1_2.add(tongluong);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tháng");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(114, 282, 78, 36);
		panel.add(lblNewLabel_2_1);
		
		JComboBox thangthongKeLuong = new JComboBox();
		thangthongKeLuong.setBounds(202, 290, 78, 28);
		panel.add(thangthongKeLuong);
		
		thangthongKeLuong.addItem("1");
		thangthongKeLuong.addItem("2");
		thangthongKeLuong.addItem("3");
		thangthongKeLuong.addItem("4");
		thangthongKeLuong.addItem("5");
		thangthongKeLuong.addItem("6");
		thangthongKeLuong.addItem("7");
		thangthongKeLuong.addItem("8");
		thangthongKeLuong.addItem("9");
		thangthongKeLuong.addItem("10");
		thangthongKeLuong.addItem("11");
		thangthongKeLuong.addItem("12");
		
		
		
		LocalDate locallaythangnamhientai = LocalDate.now();
		int thang = locallaythangnamhientai.getMonthValue();
		 
		thangthongKeLuong.setSelectedItem(String.valueOf(thang));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Năm");
		lblNewLabel_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(356, 282, 78, 36);
		panel.add(lblNewLabel_2_1_1);
		
		
		
		
		JComboBox namthongke = new JComboBox();
		namthongke.setBounds(430, 290, 78, 28);
		panel.add(namthongke);
		
		namthongke.addItem("2022");
		namthongke.addItem("2023");
		namthongke.addItem("2024");
		namthongke.addItem("2025");
		namthongke.addItem("2026");
		namthongke.addItem("2027");
		
		
 
		
		 int namHienTai = LocalDate.now().getYear();

	        // Đặt giá trị mặc định cho `JComboBox` là năm hiện tại
		 namthongke.setSelectedItem(String.valueOf(namHienTai));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(42, 351, 1253, 386);
		panel.add(tabbedPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane_1.addTab("Nhân Viên", null, panel_2, null);
		
		
		
		modelthongkeluongnhanvien= new DefaultTableModel(); 
		modelthongkeluongnhanvien.addColumn("STT");
		modelthongkeluongnhanvien.addColumn("Mã NV");
		modelthongkeluongnhanvien.addColumn("Họ Tên ");
		modelthongkeluongnhanvien.addColumn("Tổng Lương");
 
		tablethongkeluongnhanvien= new JTable(modelthongkeluongnhanvien);
		
		JScrollPane scrollPane = new JScrollPane(tablethongkeluongnhanvien);
		scrollPane.setBounds(10, 11, 1228, 353);
		panel_2.add(scrollPane);
		
		
		//load tk luong nhan vien
				tkdao = new thongkeDAO();
				int i=1;
				for (BangLuongNhanVien nv : tkdao.getthongkeluongnhanvien(Integer.parseInt(thangthongKeLuong.getSelectedItem().toString()),Integer.parseInt(namthongke.getSelectedItem().toString()))) {
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//					Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
					Object []obj= {i,nv.getNV().getMaNV(),nv.getNV().getTenNV(),currencyFormatter.format(nv.getTongluong()) };
					i++;
					modelthongkeluongnhanvien.addRow(obj);
					
				}
		
				
				
				lammoibangthongke.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int thang =Integer.parseInt((String) thangthongKeLuong.getSelectedItem());
						int nam =Integer.parseInt((String) namthongke.getSelectedItem());
						  List<BangLuongNhanVien> locbangluong=tkdao.getthongkeluongnhanvien(thang,nam);
							int i=1;
							modelthongkeluongnhanvien.getDataVector().removeAllElements();
							for (BangLuongNhanVien nv : locbangluong) {
								NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//								Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
								Object []obj= {i,nv.getNV().getMaNV(),nv.getNV().getTenNV(),currencyFormatter.format(nv.getTongluong()) };
								i++;
								modelthongkeluongnhanvien.addRow(obj);
								
								
							}
							tablethongkeluongnhanvien.setModel(modelthongkeluongnhanvien);
							
							
//							System.out.println("hello model cong doan"+	modelthongkeluongnhanvien.getValueAt(selectedRow,  2).toString());
							 int rowCount = modelthongkeluongnhanvien.getRowCount();
								System.out.println("so dong loc bang :"+rowCount+"");
								if(rowCount==0) {
									JOptionPane.showMessageDialog(null, "Error:Không tìm thấy dữ liệu thống kê của thời điểm này");
									modelthongkeluongnhanvien.getDataVector().removeAllElements();
									 Object []obj= {"","","",""};
									 
									 modelthongkeluongnhanvien.addRow(obj);
									if (modelthongkeluongnhanvien.getRowCount() > 0) {
										modelthongkeluongnhanvien.removeRow(0);
									}
								}
							
							
							
					}
				});
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		tabbedPane_1.addTab("Công Nhân", null, panel_3_1, null);
		
		modelthongkeluongcongnhan= new DefaultTableModel(); 
		modelthongkeluongcongnhan.addColumn("STT");
		modelthongkeluongcongnhan.addColumn("Mã CN");
		modelthongkeluongcongnhan.addColumn("Họ Tên ");
		modelthongkeluongcongnhan.addColumn("Số Lượng ");
		modelthongkeluongcongnhan.addColumn("Tổng Lương");
 
		tablethongkeluongcongnhan= new JTable(modelthongkeluongcongnhan);
		
		
		JScrollPane scrollPane_1_1 = new JScrollPane(tablethongkeluongcongnhan);
		scrollPane_1_1.setBounds(10, 11, 1228, 353);
		panel_3_1.add(scrollPane_1_1);
		
		//load tk luong cong nhan
		
		tkdao = new thongkeDAO();
		int i1=1;
		for (BangLuongCongNhan nv : tkdao.getthongkeluongcongnhan(Integer.parseInt(thangthongKeLuong.getSelectedItem().toString()),Integer.parseInt(namthongke.getSelectedItem().toString()))) {
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//			Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
			Object []obj= {i,nv.getMaCongNhan().getMaCongNhan(),nv.getMaCongNhan().getTencongNhan(),nv.getTongluong(),currencyFormatter.format(nv.getSoluonglamdc()) };
			i1++;
			modelthongkeluongcongnhan.addRow(obj);
			
		}

		
		
//		lammoibangthongke.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				
//					
//					
//			}
//		});
		
		
		
		JPanel panelccnv = new JPanel();
		tabbedPane.addTab("Thống kê sản phâm", null, panelccnv, null);
		panelccnv.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1338, 760);
		panelccnv.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(13, 152, 204));
		panel_3.setBounds(10, 11, 1328, 738);
		panel_1.add(panel_3);
		
		FixButton locthongkesanpham = new FixButton("Lọc");
	
		locthongkesanpham.setIcon(new ImageIcon(TabThongKe.class.getResource("/image/search.png")));
locthongkesanpham.setFont(new Font("Tahoma", Font.BOLD, 14));
		locthongkesanpham.setForeground(Color.WHITE);
		locthongkesanpham.setBackground(new Color(69, 129, 142));
		locthongkesanpham.setBounds(651, 218, 150, 40);
		panel_3.add(locthongkesanpham);
		
		FixButton btnthongkesanpham = new FixButton("Làm Mới");
		btnthongkesanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnthongkesanpham.setIcon(new ImageIcon(TabThongKe.class.getResource("/image/reload (1).png")));
		btnthongkesanpham.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnthongkesanpham.setForeground(Color.WHITE);
		btnthongkesanpham.setBackground(new Color(0, 158, 15));
		btnthongkesanpham.setBounds(871, 218, 150, 40);
		btnthongkesanpham.setEnabled(false);
		panel_3.add(btnthongkesanpham);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBounds(761, 67, 438, 124);
		panel_3.add(panel_1_2_1);
		
		JLabel lblTngS_1 = new JLabel("Tổng số Doanh Thu");
		lblTngS_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTngS_1.setBounds(152, 11, 154, 19);
		panel_1_2_1.add(lblTngS_1);
		
		JLabel tongdoanhthu = new JLabel("<dynamic>");
		tongdoanhthu.setHorizontalAlignment(SwingConstants.CENTER);
		tongdoanhthu.setFont(new Font("Dialog", Font.BOLD, 15));
		tongdoanhthu.setBounds(10, 57, 418, 19);
		panel_1_2_1.add(tongdoanhthu);
		
		JLabel lblNewLabel_2 = new JLabel("Tháng");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(141, 219, 78, 36);
		panel_3.add(lblNewLabel_2);
		
		JComboBox thangthongsanpham = new JComboBox();
		thangthongsanpham.setBounds(224, 226, 78, 28);
		panel_3.add(thangthongsanpham);
		
		
		thangthongsanpham.addItem("1");
		thangthongsanpham.addItem("2");
		thangthongsanpham.addItem("3");
		thangthongsanpham.addItem("4");
		thangthongsanpham.addItem("5");
		thangthongsanpham.addItem("6");
		thangthongsanpham.addItem("7");
		thangthongsanpham.addItem("8");
		thangthongsanpham.addItem("9");
		thangthongsanpham.addItem("10");
		thangthongsanpham.addItem("11");
		thangthongsanpham.addItem("12");
		
		
		
		 
		LocalDate currentDate = LocalDate.now();
		int thangHienTai = currentDate.getMonthValue();

		// Đặt giá trị tháng hiện tại cho JComboBox
		thangthongsanpham.setSelectedItem(String.valueOf(thangHienTai));
		
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Năm");
		lblNewLabel_2_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1_2.setBounds(338, 219, 78, 36);
		panel_3.add(lblNewLabel_2_1_2);
		
		JComboBox namthongKesanpham = new JComboBox();
		namthongKesanpham.setBounds(418, 226, 78, 28);
		panel_3.add(namthongKesanpham);
		
		
		namthongKesanpham.addItem("2022");
		namthongKesanpham.addItem("2023");
		namthongKesanpham.addItem("2024");
		namthongKesanpham.addItem("2025");
		namthongKesanpham.addItem("2026");
		namthongke.addItem("2027");
// Đặt giá trị mặc định cho `JComboBox` là năm hiện tại
		 namthongKesanpham.setSelectedItem(String.valueOf(namHienTai));
		
		JPanel panel_1_2_1_1 = new JPanel();
		panel_1_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2_1_1.setLayout(null);
		panel_1_2_1_1.setBounds(130, 67, 438, 124);
		panel_3.add(panel_1_2_1_1);
		
		JLabel lblTngSSn = new JLabel("Tổng số sản phẩm đã hoàn thành");
		lblTngSSn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTngSSn.setBounds(107, 11, 237, 19);
		panel_1_2_1_1.add(lblTngSSn);
		
		JLabel tongsanpham = new JLabel("<dynamic>");
		tongsanpham.setHorizontalAlignment(SwingConstants.CENTER);
		tongsanpham.setFont(new Font("Dialog", Font.BOLD, 15));
		tongsanpham.setBounds(10, 61, 418, 19);
		panel_1_2_1_1.add(tongsanpham);
		 
 

		
		//set các tổng lương
		tongluongnhanvien.setText(tkdao.tongluongnhanvien(Integer.parseInt(thangthongKeLuong.getSelectedItem().toString()), Integer.parseInt(namthongke.getSelectedItem().toString()))+"");
		tongluongcongnhan.setText(tkdao.tongluongcongnhan(Integer.parseInt(thangthongKeLuong.getSelectedItem().toString()), Integer.parseInt(namthongke.getSelectedItem().toString()))+"");
		tongluong.setText(tkdao.tongluong(Integer.parseInt(thangthongKeLuong.getSelectedItem().toString()), Integer.parseInt(namthongke.getSelectedItem().toString()))+"");
		
		FixButton locthongkecongnhan = new FixButton("Lọc");
		locthongkecongnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int thang =Integer.parseInt((String) thangthongKeLuong.getSelectedItem());
				int nam =Integer.parseInt((String) namthongke.getSelectedItem());
				  List<BangLuongCongNhan> locbangluong=tkdao.getthongkeluongcongnhan(thang,nam);
					int i=1;
					modelthongkeluongcongnhan.getDataVector().removeAllElements();
					for (BangLuongCongNhan nv : locbangluong) {
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//						Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
						Object []obj= {i,nv.getMaCongNhan().getMaCongNhan(),nv.getMaCongNhan().getTencongNhan(),nv.getTongluong(),currencyFormatter.format(nv.getSoluonglamdc()) };
						i++;
						modelthongkeluongcongnhan.addRow(obj);
						
						
					}
					tablethongkeluongcongnhan.setModel(modelthongkeluongcongnhan);
					
					
//					System.out.println("hello model cong doan"+	modelthongkeluongnhanvien.getValueAt(selectedRow,  2).toString());
					 int rowCount = modelthongkeluongcongnhan.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						if(rowCount==0) {
							JOptionPane.showMessageDialog(null, "Error:Không tìm thấy dữ liệu thống kê của thời điểm này");
							modelthongkeluongcongnhan.getDataVector().removeAllElements();
							 Object []obj= {"","","",""};
							 
							 modelthongkeluongcongnhan.addRow(obj);
							if (modelthongkeluongcongnhan.getRowCount() > 0) {
								modelthongkeluongcongnhan.removeRow(0);
							}
						}
					
			}
		});
		locthongkecongnhan.setText("Lọc thống kê công nhân");
locthongkecongnhan.setIcon(new ImageIcon(TabThongKe.class.getResource("/image/search.png")));
		locthongkecongnhan.setForeground(Color.WHITE);
		locthongkecongnhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		locthongkecongnhan.setBackground(new Color(69, 129, 142));
		locthongkecongnhan.setBounds(864, 282, 255, 40);
		panel.add(locthongkecongnhan);
		
		
		
		tongdoanhthu.setText(tkdao.tongdoanhthu(Integer.parseInt(thangthongsanpham.getSelectedItem().toString()), Integer.parseInt(namthongKesanpham.getSelectedItem().toString()))+"");
		tongsanpham.setText(tkdao.tongsanphamhoanthanh(Integer.parseInt(thangthongsanpham.getSelectedItem().toString()), Integer.parseInt(namthongKesanpham.getSelectedItem().toString()))+"");
		
		
		
		
		modelthongkesanpham= new DefaultTableModel(); 
		modelthongkesanpham.addColumn("STT");
		modelthongkesanpham.addColumn("Mã Sản Phẩm");
		modelthongkesanpham.addColumn("Họ Tên ");
		modelthongkesanpham.addColumn("Số Lượng Đã Làm ");
		modelthongkesanpham.addColumn("Tổng Đạt Được");
 
		tablethongkesanpham= new JTable(modelthongkesanpham);
		
		JScrollPane scrollPanetksanpham = new JScrollPane(tablethongkesanpham);
		scrollPanetksanpham.setBounds(30, 295, 1276, 432);
		panel_3.add(scrollPanetksanpham);
		
		//load tk luong nhan vien
		tkdao = new thongkeDAO();
		int i11=1;
		for (ChamCongCongNhan nv : tkdao.getthongkesanpham(Integer.parseInt(thangthongKeLuong.getSelectedItem().toString()),Integer.parseInt(namthongke.getSelectedItem().toString()))) {
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//			Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
			Object []obj= {i11,nv.getSanpham().getMaSp(),nv.getSanpham().getTenSp(),nv.getSoluongspdachamcong(),currencyFormatter.format(nv.getTongtiensanpham()) };
			i11++;
			modelthongkesanpham.addRow(obj);
			
		}
		
		locthongkesanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int thang =Integer.parseInt((String) thangthongsanpham.getSelectedItem());
				int nam =Integer.parseInt((String) namthongKesanpham.getSelectedItem());
				  List<ChamCongCongNhan> locbangluong=tkdao.getthongkesanpham(thang,nam);
					int i=1;
					modelthongkesanpham.getDataVector().removeAllElements();
					for (ChamCongCongNhan nv : locbangluong) {
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//						Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
						Object []obj= {i,nv.getSanpham().getMaSp(),nv.getSanpham().getTenSp(),nv.getSoluongspdachamcong(),currencyFormatter.format(nv.getTongtiensanpham()) };
						i++;
						modelthongkesanpham.addRow(obj);
						
						
					}
					tablethongkesanpham.setModel(modelthongkesanpham);
					
					
//					System.out.println("hello model cong doan"+	modelthongkeluongnhanvien.getValueAt(selectedRow,  2).toString());
					 int rowCount = modelthongkesanpham.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						if(rowCount==0) {
							JOptionPane.showMessageDialog(null, "Error:Không tìm thấy dữ liệu thống kê của thời điểm này");
							modelthongkesanpham.getDataVector().removeAllElements();
							 Object []obj= {"","","",""};
							 
							 modelthongkesanpham.addRow(obj);
if (modelthongkesanpham.getRowCount() > 0) {
								modelthongkesanpham.removeRow(0);
							}
						}
						
						tongdoanhthu.setText(tkdao.tongdoanhthu(Integer.parseInt(thangthongsanpham.getSelectedItem().toString()), Integer.parseInt(namthongKesanpham.getSelectedItem().toString()))+"");
						tongsanpham.setText(tkdao.tongsanphamhoanthanh(Integer.parseInt(thangthongsanpham.getSelectedItem().toString()), Integer.parseInt(namthongKesanpham.getSelectedItem().toString()))+"");
			}
		});
		
//	 
      
    }
}
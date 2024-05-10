package GiaoDien;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import Dao.BangLuongCongNhanDAO;
import Dao.BangLuongNhanVienDAO;
import Dao.ChamCongCongNhanDAO;
import Dao.CongDoanDAO;
import Dao.CongDoanDonHangDAO;
import Dao.bangPhanCongDAO;
import Dao.congNhanDAO;
import Dao.donHangDao;
import Dao.nhanVienDAO;
import Dao.sanPhamDAO;
import Entity.BangLuongCongNhan;
import Entity.BangLuongNhanVien;
import Entity.BangPhanCong;
import Entity.ChamCongCongNhan;
import Entity.ChamCongNhanVien;
import Entity.CongDoan;
import Entity.CongDoanDonHang;
import Entity.CongNhan;
import Entity.NhanVien;
import Entity.SanPham;
import services.QuanLyLuongService;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class TabCongNhan extends JPanel  {
	private JTextField tencn;
	private JTextField macn;
	private JTextField sdtcn;
	private JTextField diachicn;
	private JTextField cmndcn;
	private JTextField trocapCongNhan;
	private JTextField textField_6;
	private JTable table;
	private JTable tableQuanLyCongNhan;
	private DefaultTableModel modelquanLyCongNhan;
	private String linkHinh="";
	private congNhanDAO cndao= new congNhanDAO();
	private sanPhamDAO spdao= new sanPhamDAO();
	private CongDoanDAO cddao= new CongDoanDAO();
	private bangPhanCongDAO bpcdao=new bangPhanCongDAO();
	private ChamCongCongNhanDAO cccndao= new ChamCongCongNhanDAO();
	private BangLuongCongNhanDAO blcndao= new BangLuongCongNhanDAO();
	private CongDoanDonHangDAO cddhdao= new CongDoanDonHangDAO();
	private donHangDao dhdao=new donHangDao();
	
	private JTable tablesanphamchamcong;
	private JTable tablecongdoanchamcong;
	private DefaultTableModel modeldsanphamchamcong;
	private DefaultTableModel modeldcongdoanchamcong;
	private DefaultTableModel modelbangluongCongNhan;
	private DefaultTableModel modeldDSCongNhanChamCong;
	private DefaultTableModel modelbangchamcongtheoma;
	private JTable tabledscongnhanChamCong;
	private JTable tableBangLuongCongNhan;
	private String madonhangdungdeupdate="";
	private JDateChooser datengaysinhcn;
	/**
	 * Create the panel.
	 * @throws RemoteException 
	 */
	public TabCongNhan( QuanLyLuongService service) throws RemoteException {
		setLayout(null);
		
//		Database.ConnectDB.getInstance().connect();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1365, 760);
		add(tabbedPane);
		
		JPanel panelQuanLyNhanVien = new JPanel();
		tabbedPane.addTab("Quản Lý Công Nhân", null, panelQuanLyNhanVien, null);
		panelQuanLyNhanVien.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(13, 152, 204));
		panel.setBounds(10, 11, 1332, 215);
		panelQuanLyNhanVien.add(panel);
	
		
		
		
		JLabel lblNewLabel = new JLabel("Mã công nhân");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(232, 29, 111, 22);
		panel.add(lblNewLabel);
		
		JLabel lblTnNhnVin = new JLabel("Tên công nhân");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin.setForeground(new Color(255, 255, 255));
		lblTnNhnVin.setBounds(439, 29, 138, 22);
		panel.add(lblTnNhnVin);
		
		JLabel lblSinThao = new JLabel("Số điện thoại");
		lblSinThao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSinThao.setForeground(new Color(255, 255, 255));
		lblSinThao.setBounds(232, 93, 93, 22);
		panel.add(lblSinThao);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaCh.setForeground(new Color(255, 255, 255));
		lblaCh.setBounds(439, 93, 93, 22);
		panel.add(lblaCh);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiiTnh.setForeground(new Color(255, 255, 255));
		lblGiiTnh.setBounds(640, 29, 93, 22);
		panel.add(lblGiiTnh);
		
		JLabel lblCmnd = new JLabel("CMND");
		lblCmnd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCmnd.setForeground(new Color(255, 255, 255));
		lblCmnd.setBounds(640, 93, 93, 22);
		panel.add(lblCmnd);
		
		JLabel lblHSLuon = new JLabel("BHXH");
		lblHSLuon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHSLuon.setForeground(new Color(255, 255, 255));
		lblHSLuon.setBounds(1072, 29, 93, 22);
		panel.add(lblHSLuon);
		
		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrngThi.setForeground(new Color(255, 255, 255));
		lblTrngThi.setBounds(867, 93, 93, 22);
		panel.add(lblTrngThi);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgySinh.setForeground(new Color(255, 255, 255));
		lblNgySinh.setBounds(867, 29, 93, 22);
		panel.add(lblNgySinh);
		
		tencn = new JTextField();
		tencn.setColumns(10);
		tencn.setBounds(439, 62, 138, 20);
		panel.add(tencn);
		
		macn = new JTextField();
		macn.setEditable(false);
		macn.setEnabled(false);
		macn.setColumns(10);
		macn.setBounds(232, 62, 138, 20);
		panel.add(macn);
		
		sdtcn = new JTextField();
		sdtcn.setColumns(10);
		sdtcn.setBounds(232, 126, 138, 20);
		panel.add(sdtcn);
		
		diachicn = new JTextField();
		diachicn.setColumns(10);
		diachicn.setBounds(439, 126, 138, 20);
		panel.add(diachicn);
		
		cmndcn = new JTextField();
		cmndcn.setColumns(10);
		cmndcn.setBounds(640, 126, 138, 20);
		panel.add(cmndcn);
		
		JRadioButton rdoNamcn = new JRadioButton("Nam");
		rdoNamcn.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdoNamcn.setForeground(new Color(255, 255, 255));
		rdoNamcn.setSelected(true);
		rdoNamcn.setBackground(new Color(13, 152, 204));
		rdoNamcn.setBounds(640, 58, 70, 23);
		panel.add(rdoNamcn);
		
		JRadioButton rdoNucn = new JRadioButton("Nữ");
		rdoNucn.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdoNucn.setForeground(new Color(255, 255, 255));
		rdoNucn.setBackground(new Color(13, 152, 204));
		rdoNucn.setBounds(728, 58, 70, 23);
		panel.add(rdoNucn);
		
		ButtonGroup rdogt=new ButtonGroup();
		rdogt.add(rdoNamcn);
		rdogt.add(rdoNucn);
		
		  datengaysinhcn = new JDateChooser();
		datengaysinhcn.setBounds(867, 62, 103, 20);
		panel.add(datengaysinhcn);
		
		JComboBox trangthaicn = new JComboBox();
		trangthaicn.setBounds(867, 125, 124, 22);
		panel.add(trangthaicn);
		
		trangthaicn.addItem("Nghỉ việc");
		trangthaicn.addItem("Đang làm việc");
		trangthaicn.addItem("Bị sa thải");
		
		
		trangthaicn.setSelectedItem("Đang làm việc");
		
		JRadioButton bhxhcnCo = new JRadioButton("Có");
		bhxhcnCo.setFont(new Font("Tahoma", Font.BOLD, 14));
		bhxhcnCo.setForeground(new Color(255, 255, 255));
		bhxhcnCo.setSelected(true);
		bhxhcnCo.setBackground(new Color(13, 152, 204));
		bhxhcnCo.setBounds(1072, 58, 59, 23);
		panel.add(bhxhcnCo);
		
		JRadioButton bhxhcnKhong = new JRadioButton("Không");
		bhxhcnKhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		bhxhcnKhong.setForeground(new Color(255, 255, 255));
		bhxhcnKhong.setBackground(new Color(13, 152, 204));
		bhxhcnKhong.setBounds(1133, 59, 93, 23);
		panel.add(bhxhcnKhong);
		
		
		ButtonGroup rdobxh=new ButtonGroup();
		rdobxh.add(bhxhcnCo);
		rdobxh.add(bhxhcnKhong);
		
		JLabel lblTrCp = new JLabel("Trợ Cấp");
		lblTrCp.setForeground(new Color(255, 255, 255));
		lblTrCp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrCp.setBounds(1072, 97, 93, 22);
		panel.add(lblTrCp);
		
		trocapCongNhan = new JTextField();
		trocapCongNhan.setColumns(10);
		trocapCongNhan.setBounds(1072, 126, 138, 20);
		panel.add(trocapCongNhan);
		
		JScrollPane scrollPaneBangcongnhan = new JScrollPane();
		scrollPaneBangcongnhan.setBounds(10, 234, 1322, 361);
		panelQuanLyNhanVien.add(scrollPaneBangcongnhan);
		
	 
		
		modelquanLyCongNhan= new DefaultTableModel();
		modelquanLyCongNhan.addColumn("STT");
		modelquanLyCongNhan.addColumn("Mã CN");
		modelquanLyCongNhan.addColumn("Họ Tên ");
		modelquanLyCongNhan.addColumn("Ngày Sinh");
		modelquanLyCongNhan.addColumn("Ngày bắt đầu làm việc");
		modelquanLyCongNhan.addColumn("Số điện thoại");
		modelquanLyCongNhan.addColumn("Địa Chỉ");
		modelquanLyCongNhan.addColumn("CMND");
	
		modelquanLyCongNhan.addColumn("Phụ Cấp");
		modelquanLyCongNhan.addColumn("Trang thai");
		modelquanLyCongNhan.addColumn("BHXH");
		modelquanLyCongNhan.addColumn("HInh anh");
		modelquanLyCongNhan.addColumn("GioiTinh");
		tableQuanLyCongNhan = new JTable(modelquanLyCongNhan);

		
		tableQuanLyCongNhan.getColumnModel().getColumn(10).setMinWidth(0);
		tableQuanLyCongNhan.getColumnModel().getColumn(10).setMaxWidth(0);
		tableQuanLyCongNhan.getColumnModel().getColumn(11).setMinWidth(0);
		tableQuanLyCongNhan.getColumnModel().getColumn(11).setMaxWidth(0);
		tableQuanLyCongNhan.getColumnModel().getColumn(12).setMinWidth(0);
		tableQuanLyCongNhan.getColumnModel().getColumn(12).setMaxWidth(0);
		
//		scrollPane.setViewportView(table);
		scrollPaneBangcongnhan.setViewportView(tableQuanLyCongNhan);
		
		
		
		// load bảng công nhân
		cndao = new congNhanDAO();
		int i=1;
		for (CongNhan cn : service.getAllcongNhan()) {
			Object []obj= {i,cn.getMaCongNhan(),cn.getTencongNhan(),cn.getNgaysinh(),cn.getNgaybatdaulamviec(),cn.getSodienthoai(),cn.getDiachi(),cn.getCmnd(),cn.getTroCap(),cn.getTrangthai(),cn.getBhxh(),cn.getHinhanhnhanvien(),cn.getGioitinh()};
			i++;
			modelquanLyCongNhan.addRow(obj);
			
		}
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Thao T\u00E1c", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(new Color(13, 152, 204));
		panel_2.setBounds(10, 620, 1322, 101);
		panelQuanLyNhanVien.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm Theo Mã");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 27, 155, 14);
		panel_2.add(lblNewLabel_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(20, 52, 233, 20);
		panel_2.add(textField_6);
		
		// làm mới công nhân
		FixButton lammoiqlcnBtn = new FixButton("Làm mới");
		lammoiqlcnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cndao = new congNhanDAO();
				modelquanLyCongNhan.getDataVector().removeAllElements();
				int i=1;
				try {
					for (CongNhan cn : service.getAllcongNhan()) {
						Object []obj= {i,cn.getMaCongNhan(),cn.getTencongNhan(),cn.getNgaysinh(),cn.getNgaybatdaulamviec(),cn.getSodienthoai(),cn.getDiachi(),cn.getCmnd(),cn.getTroCap(),cn.getTrangthai(),cn.getBhxh(),cn.getHinhanhnhanvien(),cn.getGioitinh()};
						i++;
						modelquanLyCongNhan.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				 
			}
		});
		lammoiqlcnBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lammoiqlcnBtn.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/reload (1).png")));
		lammoiqlcnBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lammoiqlcnBtn.setForeground(Color.WHITE);
		lammoiqlcnBtn.setBackground(new Color(69, 129, 142));
		lammoiqlcnBtn.setBounds(436, 40, 150, 40);
		panel_2.add(lammoiqlcnBtn);
		
		
		
		//lấy giá trị cuối trong cột công nhân
		int lastRow = modelquanLyCongNhan.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
		int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị

		if (lastRow >= 0) {
		    Object value = modelquanLyCongNhan.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

		    if (value != null) {
		    	
		    	String input = modelquanLyCongNhan.getValueAt(lastRow, column).toString();  
		    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
		    	String numberPart = input.substring(2); // Lấy phần số "079"
		    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
		    	number++; // Cộng 1 vào số
		    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

		    	macn.setText(result);
		    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
		        
		       
		    }
		}
		
//		nút lưu công nhân
		FixButton themqlcnBtn = new FixButton("Thêm");
		themqlcnBtn.setEnabled(false);
		themqlcnBtn.setText("Lưu");
		themqlcnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkRegexCongNhan()) {
				String maCN = macn.getText().toString();
				String tenCN = tencn.getText().toString();
				String sdt = sdtcn.getText().toString();
				String diachi = diachicn.getText().toString();
				boolean gioiTinh =true;
				if(rdoNamcn.isSelected()) {
					gioiTinh=true;
				}
				if(rdoNucn.isSelected()) {
					gioiTinh=false;
				}
				String cmndCN = cmndcn.getText().toString();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dateengaysinh= datengaysinhcn.getDate();
				
				
				// Chuyển đổi java.util.Date thành Instant
		        Instant instant = dateengaysinh.toInstant();
		    
		        // Chuyển đổi Instant thành LocalDate
		        LocalDate datesinh = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate();
		        
				// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
				 
				String strDatengaysinh = dateFormat.format(dateengaysinh);

				// Chuyển đổi chuỗi thành kiểu java.sql.Date
				java.sql.Date sqlDatengaysinh = java.sql.Date.valueOf(strDatengaysinh);
				
				
				//ngay bat dau lam viec
				LocalDate datevaolam = LocalDate.now();

				// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
			
//				String strDatevaolam = dateFormat.format(datevaolam);

				// Chuyển đổi chuỗi thành kiểu java.sql.Date
				java.sql.Date sqlDatevaolam = java.sql.Date.valueOf(datevaolam);
				
				int trangthai=0;
				
				if(trangthaicn.getSelectedItem()=="Nghỉ việc") {
					trangthai=0;
				}
				if(trangthaicn.getSelectedItem()=="Đang làm việc") {
					trangthai=1;
				}
				if(trangthaicn.getSelectedItem()=="Bị sa thải") {
					trangthai=2;
				}
				
				
				int bhxh =0;
				if(bhxhcnCo.isSelected()) {
					bhxh=0;
				}
				if(bhxhcnKhong.isSelected()) {
					bhxh=1;
				}
				
				double troCap=Double.parseDouble(trocapCongNhan.getText());
				double luongcb=4500000;
				CongNhan cn= new CongNhan( tenCN, gioiTinh, datesinh, datevaolam, cmndCN, diachi, sdt, bhxh,trangthai, troCap, luongcb, linkHinh);
				System.out.println(cn.toString());
				try {
					service.themDanhSachCN(cn);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int i=1;
				
				int lastRow = modelquanLyCongNhan.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
				int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị

				String zz="";
				if (lastRow >= 0) {
				    Object value = modelquanLyCongNhan.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

				    if (value != null) {
				    	
				    	String input = modelquanLyCongNhan.getValueAt(lastRow, column).toString();  
				    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
				    	String numberPart = input.substring(2); // Lấy phần số "079"
				    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				    	number++; // Cộng 1 vào số
				    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

				    	macn.setText(result);
				    	zz+=result;
				    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
				        
				       
				    }
				}
				modelquanLyCongNhan.getDataVector().removeAllElements();
				int i1=1;
				try {
					for (CongNhan cn1 : service.getAllcongNhan()) {
						Object []obj= {i1,cn1.getMaCongNhan(),cn1.getTencongNhan(),cn1.getNgaysinh(),cn1.getNgaybatdaulamviec(),cn1.getSodienthoai(),cn1.getDiachi(),cn1.getCmnd(),cn1.getTroCap(),cn1.getTrangthai(),cn1.getBhxh(),cn1.getHinhanhnhanvien(),cn1.getGioitinh()};
						i1++;
						modelquanLyCongNhan.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		themqlcnBtn.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/floppy-disk.png")));
		themqlcnBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		themqlcnBtn.setForeground(Color.WHITE);
		themqlcnBtn.setBackground(new Color(0, 158, 15));
		themqlcnBtn.setBounds(786, 40, 150, 40);
		panel_2.add(themqlcnBtn);
		
		
//		nút sửa công nhân
		FixButton suaqlcnbtn = new FixButton("Sửa");
		suaqlcnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 
					int row=tableQuanLyCongNhan.getSelectedRow();
								
								if(row>=0) {
									String ma  = macn.getText().toString();
									String tenNV = tencn.getText().toString();
									String sdt = sdtcn.getText().toString();
								
									String diachi = diachicn.getText().toString();
//									 
									
									int trangthai=0;
									
									
									String trangthais = (String) trangthaicn.getSelectedItem();
									if(trangthais=="Nghỉ việc") {
										trangthai=0;
									}
									if(trangthais=="Đang làm việc") {
										trangthai=1;
									}
									if(trangthais=="Bị sa thải") {
										trangthai=2;
									}
									 
									

									CongNhan nv= new CongNhan( trangthai,tenNV,   diachi ,sdt,ma );
									System.out.println(nv.toString());
						 
									
									 JOptionPane optionPane = new JOptionPane(this, JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
			                            JDialog dialog = optionPane.createDialog("Bạn có chắc muốn thay đổi thông tin của nhân viên này !!");
			                            dialog.setVisible(true);

			                            // Kiểm tra xem người dùng đã đóng hộp thoại
			                            if (optionPane.getValue() != null && (int) optionPane.getValue() == JOptionPane.YES_OPTION) {
			                            	try {
												service.update(nv);
											} catch (RemoteException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
			                            	
//			                            	blnv.updateHeSoLuong(hslnv1, maNV);
			                            	
//			                            table.setValueAt(manv.getText(), row, 1);
			                            	tableQuanLyCongNhan.setValueAt(tencn.getText(), row, 2);
//										table.setValueAt(txtcmnd.getText(), row, 3);
//										table.setValueAt(comboBoxChucvunv.getSelectedItem(), row, 4);
//										table.setValueAt(trangthainv.getSelectedItem(), row, 10);
			                            	tableQuanLyCongNhan.setValueAt(sdtcn.getText(), row, 5);
			                            	tableQuanLyCongNhan.setValueAt(diachicn.getText(), row, 6);
//										table.setValueAt(emailnv.getText(), row, 7);
//										table.setValueAt(hslnv.getSelectedItem(), row, 8);
//										table.setValueAt(cmndnv.getText(), row, 9);
										JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin thành công!!");
//									 
										
										modelquanLyCongNhan.getDataVector().removeAllElements();
										int i1=1;
										try {
											for (CongNhan cn1 : service.getAllcongNhan()) {
												Object []obj= {i1,cn1.getMaCongNhan(),cn1.getTencongNhan(),cn1.getNgaysinh(),cn1.getNgaybatdaulamviec(),cn1.getSodienthoai(),cn1.getDiachi(),cn1.getCmnd(),cn1.getTroCap(),cn1.getTrangthai(),cn1.getBhxh(),cn1.getHinhanhnhanvien(),cn1.getGioitinh()};
												i1++;
												modelquanLyCongNhan.addRow(obj);
												
											}
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
			                            }
			                            
									
//									 
						 	}
						 
				}
		 
		});
		suaqlcnbtn.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/edit (1).png")));
		suaqlcnbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		suaqlcnbtn.setForeground(Color.WHITE);
		suaqlcnbtn.setBackground(new Color(164, 164, 0));
		suaqlcnbtn.setBounds(948, 40, 150, 40);
		panel_2.add(suaqlcnbtn);
		
		FixButton huyqlcnbtn = new FixButton("Hủy");
		
		huyqlcnbtn.setEnabled(false);
		huyqlcnbtn.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/cancel (1).png")));
		huyqlcnbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		huyqlcnbtn.setForeground(Color.WHITE);
		huyqlcnbtn.setBackground(new Color(207, 42, 39));
		huyqlcnbtn.setBounds(1108, 42, 150, 40);
		panel_2.add(huyqlcnbtn);
		
		JButton timcongnhantheoMa = new JButton("");
		timcongnhantheoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 List<CongNhan> timtheoma = null;
				try {
					timtheoma = service.getAllCongNhanTheoma(textField_6.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					modelquanLyCongNhan.getDataVector().removeAllElements();
					 
					 
					for (CongNhan cn : timtheoma) {
						Object []obj= {i,cn.getMaCongNhan(),cn.getTencongNhan(),cn.getNgaysinh(),cn.getNgaybatdaulamviec(),cn.getSodienthoai(),cn.getDiachi(),cn.getCmnd(),cn.getTroCap(),cn.getTrangthai(),cn.getBhxh(),cn.getHinhanhnhanvien(),cn.getGioitinh()};
						i++;
						modelquanLyCongNhan.addRow(obj);
						
					}
					tableQuanLyCongNhan.setModel(modelquanLyCongNhan);
					
					int rowCount = modelquanLyCongNhan.getRowCount();
					System.out.println("so dong loc bang :"+rowCount+"");
					if(rowCount==0) {
						JOptionPane.showMessageDialog(null, "Error: Không tìm thấy nhân viên có tên "+textField_6.getText());
					}
				
			}
		});
		timcongnhantheoMa.setBackground(new Color(97, 218, 193));
		timcongnhantheoMa.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/search.png")));
		timcongnhantheoMa.setBounds(276, 40, 57, 40);
		panel_2.add(timcongnhantheoMa);
		
//		nút thêm công nhân
		FixButton fxbtnThm = new FixButton("Thêm");
		fxbtnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themqlcnBtn.setEnabled(true);
				huyqlcnbtn.setEnabled(true);
				suaqlcnbtn.setEnabled(false);
				fxbtnThm.setEnabled(false);
				
				tencn.requestFocus();
				diachicn.setText("");
				tencn.setText("");
				sdtcn.setText("");
				 
				cmndcn.setText("");
				trocapCongNhan.setText("");
				
			}
		});
		fxbtnThm.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/add (1).png")));
		fxbtnThm.setForeground(Color.WHITE);
		fxbtnThm.setFont(new Font("Tahoma", Font.BOLD, 14));
		fxbtnThm.setBackground(new Color(0, 158, 15));
		fxbtnThm.setBounds(610, 40, 150, 40);
		panel_2.add(fxbtnThm);
		
		
//		nút hủy công nhân
		huyqlcnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fxbtnThm.setEnabled(true);
				themqlcnBtn.setEnabled(false);
				suaqlcnbtn.setEnabled(true);
				huyqlcnbtn.setEnabled(false);
			}
		});
		
//		table mouse bảng công nhân
tableQuanLyCongNhan.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row=tableQuanLyCongNhan.getSelectedRow();
				macn.setText(modelquanLyCongNhan.getValueAt(row, 1).toString());
				tencn.setText(modelquanLyCongNhan.getValueAt(row, 2).toString());
				
				
			 
				
				sdtcn.setText(modelquanLyCongNhan.getValueAt(row, 5).toString());
				diachicn.setText(modelquanLyCongNhan.getValueAt(row, 6).toString());
			 
				
			 
				trocapCongNhan.setText(modelquanLyCongNhan.getValueAt(row, 8).toString());
				
				String trangthai=modelquanLyCongNhan.getValueAt(row, 9).toString();
				
				if(trangthai.equals("0")) {
					trangthaicn.setSelectedItem("Nghỉ việc");
				}
				if(trangthai.equals("1")) {
					trangthaicn.setSelectedItem("Đang làm việc");
				}	if(trangthai.equals("2")) {
					trangthaicn.setSelectedItem("Bị sa thải");
				}
				
				String bhxh=modelquanLyCongNhan.getValueAt(row, 10).toString();
				if(bhxh.equals("0")) {
					bhxhcnCo.setSelected(true);
				}
				if(bhxh.equals("1")) {
					bhxhcnKhong.setSelected(true);
				}
				
				 String gt=modelquanLyCongNhan.getValueAt(row, 9).toString();
				 
				 if(gt.equals("true")) {
					 rdoNamcn.setSelected(true);
					 rdoNamcn.setSelected(false);
				 }
				 else {
					 rdoNamcn.setSelected(false);
					 rdoNamcn.setSelected(true);
				 }
				 
				cmndcn.setText(modelquanLyCongNhan.getValueAt(row, 7).toString());
			 
				SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				 
//				 
			 
				
				try {
					date = format.parse(modelquanLyCongNhan.getValueAt(row,3).toString());
					datengaysinhcn.setDate(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				rdoNamcn.setSelected(modelquanLyCongNhan.getValueAt(row, 12).toString().equalsIgnoreCase("true") ? true : false);
				rdoNucn.setSelected(modelquanLyCongNhan.getValueAt(row, 12).toString().equalsIgnoreCase("false") ? true : false);
				
			 
			 
//			 
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
//		Chấm công
		JPanel panelccnv = new JPanel();
		tabbedPane.addTab("Chấm Công Công Nhân", null, panelccnv, null);
		panelccnv.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1338, 721);
		panelccnv.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(13, 152, 204));
		panel_3.setBounds(10, 11, 1322, 699);
		panel_1.add(panel_3);
		
		JDateChooser datengaychamCongCongNhan = new JDateChooser();
		datengaychamCongCongNhan.setBounds(665, 310, 139, 20);
		panel_3.add(datengaychamCongCongNhan);

		Date ngayHienTai = new Date();

		// Đặt giá trị của JDateChooser thành ngày hiện tại
		datengaychamCongCongNhan.setDate(ngayHienTai);
		
		// Lấy giá trị từ JDateChooser
				Date date = datengaychamCongCongNhan.getDate();

				// Chuyển đổi Date thành LocalDate
				Instant instant = date.toInstant();
				ZoneId zoneId = ZoneId.systemDefault(); // Hoặc bạn có thể chọn ZoneId cụ thể
				LocalDate localDatengaycham = instant.atZone(zoneId).toLocalDate();
				
		
		JLabel lblNewLabel_2 = new JLabel("Ngày Chấm Công");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(499, 303, 216, 36);
		panel_3.add(lblNewLabel_2);
		
		JScrollPane scrollPaneDSSP = new JScrollPane();
		scrollPaneDSSP.setBounds(49, 79, 476, 128);
		panel_3.add(scrollPaneDSSP);
		
		
		modeldsanphamchamcong= new DefaultTableModel();
		modeldsanphamchamcong.addColumn("STT");
		modeldsanphamchamcong.addColumn("Mã SP");
		modeldsanphamchamcong.addColumn("Tên SP ");
		 
		
//		load table sản phẩm 
		tablesanphamchamcong = new JTable(modeldsanphamchamcong);
		scrollPaneDSSP.setViewportView(tablesanphamchamcong);
		//load sản phẩm
		int icd1=1;
		for (SanPham sp : service.getAllsanPhamDangDathang()) {
			 
			Object []obj= {icd1, sp.getMaSp(),sp.getTenSp()   };
			icd1++;
			modeldsanphamchamcong.addRow(obj);
			
		}
		
		JLabel lblNewLabel_1_1 = new JLabel("Danh sách sản phẩm");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(49, 39, 273, 29);
		panel_3.add(lblNewLabel_1_1);
		
		JScrollPane scrollpanDSCD = new JScrollPane();
		scrollpanDSCD.setBounds(801, 81, 476, 128);
		panel_3.add(scrollpanDSCD);
		
	
		
		//load bảng sản phẩm và tạo sự kiện bấm vào sảm phẩm nào là hiện công đoạn đang sản xuất ở sản phẩm đó
		
		modeldcongdoanchamcong=new DefaultTableModel();
		modeldcongdoanchamcong.addColumn("Ma Cong Doan");
		modeldcongdoanchamcong.addColumn("Ten Cong Doan");
		modeldcongdoanchamcong.addColumn("Gia Cong Doan");
		modeldcongdoanchamcong.addColumn("Ma Cong Doan Don Hang");
		
		tablecongdoanchamcong = new JTable(modeldcongdoanchamcong);
		scrollpanDSCD.setViewportView(tablecongdoanchamcong);
		
		
		
//		  show cong doan tương ứng theo mã sản phẩm  khi bấm vào một dòng trong bảng
		ListSelectionModel selectionModelCDPC = tablesanphamchamcong.getSelectionModel();

		selectionModelCDPC.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            // Xử lý sự kiện ở đây
		            int selectedRow = tablesanphamchamcong.getSelectedRow();
		            if (selectedRow != -1) {
		             
		            	
		            	tablecongdoanchamcong.clearSelection();
 
		            	
		            	
		            	madonhangdungdeupdate=modeldsanphamchamcong.getValueAt(selectedRow,  1).toString();
		            	
		            	
		            	 List<CongDoanDonHang> loccongdoandonhang = null;
						try {
							loccongdoandonhang = service.getAllcongdoanDonHangtheosanphamCo2MaSptrungnhau(modeldsanphamchamcong.getValueAt(selectedRow,  2).toString(),modeldsanphamchamcong.getValueAt(selectedRow,  1).toString());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            	 modeldcongdoanchamcong.getDataVector().removeAllElements();
						for (CongDoanDonHang cd : loccongdoandonhang) {
							String tt="";
							if(cd.getTrangthai()==1) {
								tt="Chưa hoàn thành";
							}
							
//							Object []obj= {cd.getCongdoan().getMaCongDoan(),cd.getCongdoan().getTenCongDoan(),cd.getCongdoan().getGiaCongDoan(),cd.getSoluongdathang(),cd.getTiendo(),tt,cd.getMaCongDoanDonHang() };
							Object []obj= {cd.getCongdoan().getMaCongDoan(),cd.getCongdoan().getTenCongDoan(),cd.getCongdoan().getGiaCongDoan() ,cd.getMaCongDoanDonHang() };
							modeldcongdoanchamcong.addRow(obj);

						}
						tablecongdoanchamcong.setModel(modeldcongdoanchamcong);
						
						
//					 System.out.println("hello model cong doan"+	modeldcongdoanchamcong.getValueAt(selectedRow,  2).toString());
					 int rowCount = modeldcongdoanchamcong.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						if(rowCount==0) {
							JOptionPane.showMessageDialog(null, "Error:San pham nay chua co cong doan vui long thêm công đoạn trước khi phân công");
							modeldcongdoanchamcong.getDataVector().removeAllElements();
							 Object []obj= {"","","",""};
							 
							 modeldcongdoanchamcong.addRow(obj);
							if (modeldcongdoanchamcong.getRowCount() > 0) {
								modeldcongdoanchamcong.removeRow(0);
							}
						}
		            	
		            }
		        }
		    }

			 
		});
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Danh sách công nhân cần chấm");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(53, 310, 273, 29);
		panel_3.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Danh sách công đoạn");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(801, 39, 273, 29);
		panel_3.add(lblNewLabel_1_2);
		
		JScrollPane scrollPaneDSCNCC = new JScrollPane();
		scrollPaneDSCNCC.setBounds(54, 350, 1223, 300);
		panel_3.add(scrollPaneDSCNCC);
		
		
		modeldDSCongNhanChamCong=new DefaultTableModel();
//		modeldDSCongNhanChamCong.addColumn("Mã Chấm Công");
//		modeldDSCongNhanChamCong.addColumn("Mã Công Nhân");
//		modeldDSCongNhanChamCong.addColumn("Tên Công Nhân");
//		
//		modeldDSCongNhanChamCong.addColumn("Ngày Chấm Công");
//		modeldDSCongNhanChamCong.addColumn("Số lượng hoàn thành");
//		modeldDSCongNhanChamCong.addColumn("Số giờ tăng ca");
		
		modeldDSCongNhanChamCong.addColumn("Mã Chấm Công");
		modeldDSCongNhanChamCong.addColumn("Mã Công Nhân");
		modeldDSCongNhanChamCong.addColumn("Tên Công Nhân");
		
		modeldDSCongNhanChamCong.addColumn("Ngày Chấm Công");
		modeldDSCongNhanChamCong.addColumn("Số lượng hoàn thành");
		modeldDSCongNhanChamCong.addColumn("Số lượng còn lại");
		modeldDSCongNhanChamCong.addColumn("Số giờ tăng ca");
		modeldDSCongNhanChamCong.addColumn("Mã Công Đoạn");
		modeldDSCongNhanChamCong.addColumn("Số lượng được giao");
		modeldDSCongNhanChamCong.addColumn("Mã công đoạn đơn hàng hiện tại");
		modeldDSCongNhanChamCong.addColumn("Mã công đoạn đơn hàng trước ");
		tabledscongnhanChamCong = new JTable(modeldDSCongNhanChamCong);
		scrollPaneDSCNCC.setViewportView(tabledscongnhanChamCong);
		
		
		//them combo zo so gio tang ca
		
	    JComboBox giotangca = new JComboBox();
	       giotangca.addItem("0");
	       giotangca.addItem("1");
	       giotangca.addItem("2");
	       giotangca.addItem("3");
	       giotangca.addItem("4");
		
		 TableColumn sogiotangcaCol = tabledscongnhanChamCong.getColumnModel().getColumn(6);
		 sogiotangcaCol.setCellEditor(new DefaultCellEditor(giotangca));

	       
	   
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	       centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Đặt căn giữa cho nội dung cột

	       tabledscongnhanChamCong.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Áp dụng trình điều khiển tùy chỉnh cho cột thứ columnIndex
	       tabledscongnhanChamCong.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	       tabledscongnhanChamCong.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	       tabledscongnhanChamCong.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	       tabledscongnhanChamCong.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	       tabledscongnhanChamCong.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		
	       
	       
		//-load bảng chấm công công nhân theo mã công đoạn đã chọn
		ListSelectionModel selectionModelDSPC = tablecongdoanchamcong.getSelectionModel();
		
		
		selectionModelDSPC.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            // Xử lý sự kiện ở đây
		            int selectedRow = tablecongdoanchamcong.getSelectedRow();
		            if (selectedRow != -1) {
		    
		            	modeldDSCongNhanChamCong.getDataVector().removeAllElements();
		         		LocalDate currentDate =  LocalDate.now();
		         		
		         		
		         		try {
							for (ChamCongCongNhan cccn : service.getDSChamCongCongDoan(modeldcongdoanchamcong.getValueAt(selectedRow,  0).toString(),modeldcongdoanchamcong.getValueAt(selectedRow,  3).toString())) {
								 
								 String matruoc="";
									if(cccn.getSoLuong()<cccn.getSoluongduocgiao()) {
										
//			         				if(modeldcongdoanchamcong.getValueAt(selectedRow-1,  3).toString()==null) {
//			         					matruoc="";
//			         				}
//			         				else {
//			         					matruoc=modeldcongdoanchamcong.getValueAt(selectedRow-1,  3).toString();
//			         				}
										if( selectedRow-1 ==-1) {
											matruoc="";
										}
										else {
											matruoc=modeldcongdoanchamcong.getValueAt(selectedRow-1,  3).toString();
										}
										
										Object []obj= { cccn.getMaChamCongCongNhan(),cccn.getMaCongNhan().getMaCongNhan(),cccn.getMaCongNhan().getTencongNhan(),localDatengaycham,"",cccn.getSoluongduocgiao()-cccn.getSoLuong(),cccn.getSogiotangca(),cccn.getMaCongDoan().getMaCongDoan(),cccn.getSoluongduocgiao(),modeldcongdoanchamcong.getValueAt(selectedRow,  3).toString(),matruoc};
										
										modeldDSCongNhanChamCong.addRow(obj);
									}
									
								}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//		         		String previousValue = ""; // Biến để lưu giá trị trước đó
//
//		         		for (ChamCongCongNhan cccn : cccndao.getDSChamCongCongDoan(modeldcongdoanchamcong.getValueAt(selectedRow,  0).toString())) {
//		         		    String currentValue = cccn.getMaChamCongCongNhan();
//
//		         		    if (!currentValue.equals(previousValue)) {
//		         		        String matruoc = (selectedRow - 1 == -1) ? "" : modeldcongdoanchamcong.getValueAt(selectedRow - 1, 3).toString();
//		         		        Object[] obj = {cccn.getMaChamCongCongNhan(), cccn.getMaCongNhan().getMaCongNhan(), cccn.getMaCongNhan().getTencongNhan(), localDatengaycham, "", cccn.getSoluongduocgiao() - cccn.getSoLuong(), cccn.getSogiotangca(), cccn.getMaCongDoan().getMaCongDoan(), cccn.getSoluongduocgiao(), modeldcongdoanchamcong.getValueAt(selectedRow, 3).toString(), matruoc};
//
//		         		        modeldDSCongNhanChamCong.addRow(obj);
//		         		    }
//		         		    
//		         		    previousValue = currentValue;
//		         		}



		         		 tabledscongnhanChamCong.setModel(modeldDSCongNhanChamCong);
		         	  
		         		 
		         		int rowCount = modeldDSCongNhanChamCong.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						
						System.out.println(modeldcongdoanchamcong.getValueAt(selectedRow,  0).toString()+"");
						if(rowCount==0) {
//							JOptionPane.showMessageDialog(null, "Error:zz");
							modeldDSCongNhanChamCong.getDataVector().removeAllElements();
							 Object []obj= {"","","",""};
							 
							 modeldDSCongNhanChamCong.addRow(obj);
							if (modeldDSCongNhanChamCong.getRowCount() > 0) {
								modeldDSCongNhanChamCong.removeRow(0);
							}
						}
		         		 
						
						
						//Test nè ba
						
						
						datengaychamCongCongNhan.addPropertyChangeListener(new PropertyChangeListener() {
						    @Override
						    public void propertyChange(PropertyChangeEvent evt) {
						        if ("date".equals(evt.getPropertyName())) {
						            // Xử lý sự kiện thay đổi ngày ở đây
						            Date selectedDate = (Date) evt.getNewValue();
						            
						            // Chuyển đổi Date thành LocalDate
						            LocalDate ngaycham = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						            
						            // Thực hiện các thao tác cần thiết với localDate
						            System.out.println(ngaycham+"");
						            
						      		//
						            modeldDSCongNhanChamCong.getDataVector().removeAllElements();
					         		try {
										for (ChamCongCongNhan cccn : service.getDSChamCongCongDoantheoNgay(modeldcongdoanchamcong.getValueAt(selectedRow,  0).toString(),ngaycham,modeldcongdoanchamcong.getValueAt(selectedRow,  3).toString())) {
											 
//						         		 
												if(cccn.getSoLuong()<cccn.getSoluongduocgiao()) {
													
													Object []obj= { cccn.getMaChamCongCongNhan(),cccn.getMaCongNhan().getMaCongNhan(),cccn.getMaCongNhan().getTencongNhan(),localDatengaycham,"",cccn.getSoluongduocgiao()-cccn.getSoLuong(),cccn.getSogiotangca(),cccn.getMaCongDoan().getMaCongDoan(),cccn.getSoluongduocgiao(),modeldcongdoanchamcong.getValueAt(selectedRow,  3).toString()};
													
													modeldDSCongNhanChamCong.addRow(obj);
												}
												
											}
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					         		 tabledscongnhanChamCong.setModel(modeldDSCongNhanChamCong);
					         	  
					         		 
					         		int rowCount = modeldDSCongNhanChamCong.getRowCount();
									System.out.println("so dong loc bang :"+rowCount+"");
									
									System.out.println(modeldcongdoanchamcong.getValueAt(selectedRow,  0).toString()+"");
									if(rowCount==0) {
//										JOptionPane.showMessageDialog(null, "Error:zz");
										modeldDSCongNhanChamCong.getDataVector().removeAllElements();
										 Object []obj= {"","","",""};
										 
										 modeldDSCongNhanChamCong.addRow(obj);
										if (modeldDSCongNhanChamCong.getRowCount() > 0) {
											modeldDSCongNhanChamCong.removeRow(0);
										}
									}
									
									
						        }
						    }
						});
						
						//
						}
		            else {
		            	modeldDSCongNhanChamCong.getDataVector().removeAllElements();
						 Object []obj= { "" ,"","","","",""};
						 
						 modeldDSCongNhanChamCong.addRow(obj);
						if (modeldDSCongNhanChamCong.getRowCount() > 0) {
							modeldDSCongNhanChamCong.removeRow(0);
						}
						tabledscongnhanChamCong.setModel(modeldDSCongNhanChamCong);
		            }
		            }
		        }
//		    }

			 
		});
		
 
 

	
		
		
		
		
//		nút chấm công
		FixButton btnchamCongCongNhan = new FixButton("Chấm Công");
		btnchamCongCongNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//lay ma don hang
				int row1=tablecongdoanchamcong.getSelectedRow();
				String macongdoan= modeldcongdoanchamcong.getValueAt(row1, 0).toString() ;
				//
				String macongdoandonhangdesetBangChamCong=modeldcongdoanchamcong.getValueAt(row1, 3).toString() ;
				//
				int rowCount = modeldDSCongNhanChamCong.getRowCount();
				System.out.println(rowCount);
				for (int row = 0; row < rowCount; row++) {
				    String maChamCong = (String) modeldDSCongNhanChamCong.getValueAt(row, 0); // Lấy mã chấm công  
				    String maCongNhan = (String) modeldDSCongNhanChamCong.getValueAt(row, 1); // Lấy mã công nhân
				    String tenCongNhan = (String) modeldDSCongNhanChamCong.getValueAt(row, 2); // Lấy tên công nhân 
 
				    
				  
				    //
				    String maCongDoan= (String) modeldDSCongNhanChamCong.getValueAt(row, 7);
				    String maCongDoanDonHang= (String) modeldDSCongNhanChamCong.getValueAt(row, 9);
				    String maCongDoanDonHangtruoc= (String) modeldDSCongNhanChamCong.getValueAt(row, 10);
				    //
    
				    // kiểm tra số lương sản phẩm và số giờ tăng ca đã được chọn chưa
				     if((modeldDSCongNhanChamCong.getValueAt(row, 4).toString()!="") ) {
 			 
				    	 int soluong = Integer.parseInt( modeldDSCongNhanChamCong.getValueAt(row, 4).toString()); // Lấy số lượng 
				    	 int sogiotangca =  Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 6).toString()); // Lấy số giờ tăng ca 
		 
				    		    	 
				    	 int setlaisoluongconlai=Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 5).toString())- Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 4).toString());
				    	 int slcl=Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 5).toString());
				    	 
				    	 System.out.println("So luong con lai "+slcl);
//				    	 int sscl= Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 5).toString());
				    	 //lay ma tien trinh truoc 
				    	 
				    	 String input = maCongDoanDonHang;  
				 		String prefix = input.substring(0, 4); // Lấy phần tiền tố "NV"
				 		String numberPart = input.substring(4); // Lấy phần số "079"
				 		int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				 		number--; // Cộng 1 vào số
				 		String result = prefix + String.format("%03d", number); // Kết hợp lại thành mã tiến trình trước
				 		
				 		//lấy mã công đoạn trước thông qua cột thứ 10 trong bảng ds chấm công
				if(maCongDoanDonHangtruoc!="" || maCongDoanDonHangtruoc=="") {
					//nếu khác rỗng thì đầy sẽ là những dòng từ dòng 1 trở xuống sẽ có mã trước
					if(maCongDoanDonHangtruoc!=""){
						//nếu tiến độ hiện tại lớn hơn tiến độ của công đoạn trước thì sẽ k thể phân công ,công đoạn trước bn thì công đoạn sau thì dc = hoặc bé hơn
			 			try {
							if(service.tiendocuacongdoandonhangHientai(maCongDoanDonHang) >  service.tiendocuacongdoandonhangtruoc(maCongDoanDonHangtruoc) ){
								modeldDSCongNhanChamCong.setValueAt("",row,4);
								System.out.println("êe"+service.tiendocuacongdoandonhangtruoc(maCongDoanDonHangtruoc));
								JOptionPane.showMessageDialog(null, "Đã đủ số lượng ở công đoạn hiện tại !!Số lượng chấm công vượt qua tiến độ !!  ");
								
							}
//				    	 else  if(soluong>cddhdao.tiendocuacongdoandonhangtruoc(result)) {
							//nếu số lượng (giá trị này lấy ở cột thứ 4) lớn hơn số lượng dư ra của 2 tiến độ thì không chấm công được ,nó sẽ thông số lương tối thiểu để có thể chấm công 
							else if(soluong >(service.tiendocuacongdoandonhangtruoc(maCongDoanDonHangtruoc)-service.tiendocuacongdoandonhangHientai(maCongDoanDonHang))){
								modeldDSCongNhanChamCong.setValueAt("",row,4);
								JOptionPane.showMessageDialog(null, "Số lượng chấm công vượt qua tiến độ !! Chấm công thất bại!!");
								JOptionPane.showMessageDialog(null, "số lượng sản phẩm Chấm công phải dưới hoặc bằng :"+(service.tiendocuacongdoandonhangtruoc(result)-service.tiendocuacongdoandonhangHientai(maCongDoanDonHang)));
							}
							
							else if(Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 5).toString())<Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 4).toString()) ){
								modeldDSCongNhanChamCong.setValueAt("",row,4);
								JOptionPane.showMessageDialog(null, "Số lượng chấm công quá giới hạn !!! saii");
								 
							}
							//đây là khi thỏa toản bộ điều kiện phía trên mới có thể chấm công thành công
							
							 else {
								 	modeldDSCongNhanChamCong.setValueAt("",row,4);
								 	 modeldDSCongNhanChamCong.setValueAt(setlaisoluongconlai,row,5);  
//						    	 int zz=setlaisoluongconlai;
								 	service.updateChamCong(  sogiotangca,soluong  ,localDatengaycham, maChamCong);
								 	service.updateTiendo(soluong, maCongDoan, maCongDoanDonHang);
								 	service.updatetrangthaiCongDoanDonHang(maCongDoan, maCongDoanDonHang);
//					    	 blcndao.updateBangLuong( modeldDSCongNhanChamCong.getValueAt(row, 1).toString());
								  JOptionPane.showMessageDialog(null, "Chấm công thành công  !!");
							 }
						} catch (HeadlessException | NumberFormatException | RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			 					// ở mỗi dòng chấm công của công nhân sẽ đếm số lượng chưa hoàn thành ,nếu nó đếm về =0 thì có nghĩa là hoàn thành chấm công cho công nhân này
		         				 
									try {
										if(service.layketquasosanhcuasoluongdalamvoisoluongdagiao(macongdoan, macongdoandonhangdesetBangChamCong,maChamCong,maCongNhan)==1 ) {
											 modeldDSCongNhanChamCong.setValueAt("",row,4); 
//		         				 cccndao.updateChamCong(  sogiotangca,soluong+cccn.getSoLuong() ,localDatengaycham, maChamCong);
											try {
												service.updateHoanThanh( maCongNhan, maCongDoan,maCongDoanDonHang );
											} catch (RemoteException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											  
										}
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								 
			 			//
					}	
				    	//đây sẽ dành cho dòng đầu tiên (không có macongdoandonhang trước nó)
				    	 else {
				    		 
				    		 
				    		 if(Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 5).toString())<Integer.parseInt(modeldDSCongNhanChamCong.getValueAt(row, 4).toString()) ){
					 				modeldDSCongNhanChamCong.setValueAt("",row,4);
					 				JOptionPane.showMessageDialog(null, "Số lượng chấm công quá giới hạn !!! saii");
					 				 
					 			}
				    		 else {
				    		 	modeldDSCongNhanChamCong.setValueAt("",row,4);
				    		 	 modeldDSCongNhanChamCong.setValueAt(setlaisoluongconlai,row,5);  
//						    	 int zz=setlaisoluongconlai;
				    		 	try {
									service.updateChamCong(  sogiotangca,soluong  ,localDatengaycham, maChamCong);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				    		 	try {
									service.updateTiendo(soluong, maCongDoan, maCongDoanDonHang);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				    		 	try {
									service.updatetrangthaiCongDoanDonHang(maCongDoan, maCongDoanDonHang);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
//					    	 blcndao.updateBangLuong( modeldDSCongNhanChamCong.getValueAt(row, 1).toString());
			         		  JOptionPane.showMessageDialog(null, "Chấm công thành công  !!");
				    		 }
				    	 }
					
					// ở mỗi dòng chấm công của công nhân sẽ đếm số lượng chưa hoàn thành ,nếu nó đếm về =0 thì có nghĩa là hoàn thành chấm công cho công nhân này
 		         				 
// 		         					&& service.layketquasosanhcuasoluongdalamvoisoluongdagiao(madonhang, maCongDoanDonHang)==1
									try {
										if(service.layketquasosanhcuasoluongdalamvoisoluongdagiao(macongdoan, macongdoandonhangdesetBangChamCong,maChamCong,maCongNhan)==1 ) {
											 modeldDSCongNhanChamCong.setValueAt("",row,4); 
// 		         				 cccndao.updateChamCong(  sogiotangca,soluong+cccn.getSoLuong() ,localDatengaycham, maChamCong);
											try {
												service.updateHoanThanh( maCongNhan, maCongDoan,maCongDoanDonHang );
											} catch (RemoteException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											  
										}
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								 
 
  			         			 
				     }
			}	
				     
				     try {
						service.updateHoanThanh(localDatengaycham,maCongDoanDonHang,madonhangdungdeupdate);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				     
				     
				     // load lại bảng lương sau khi chấm công
				     LocalDate currentDate = LocalDate.now();
				        int nam = currentDate.getYear();
				        int thang = currentDate.getMonthValue();
						
						 List<BangLuongCongNhan> locbangluong=blcndao.getchamcongtheongayCN(thang,nam);
							int i=1;
							modelbangluongCongNhan.getDataVector().removeAllElements();
							for (BangLuongCongNhan cn : locbangluong) {
								String luongtangca = String.format("%.0f", cn.getLuongtangca());
								String luongsanpham = String.format("%.0f", cn.getLuongsanpham());
								String tongluong = String.format("%.0f", cn.getTongluong());
								Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),luongtangca,luongsanpham,cn.getTroCap(),cn.getPhat(),tongluong };
								 
								modelbangluongCongNhan.addRow(obj);

							}
							tableBangLuongCongNhan.setModel(modelbangluongCongNhan);
							
							
							tabledscongnhanChamCong.clearSelection();
							
							
							
							//
							

			 				//laymadonhang
							 
							
							//
				}
			 

			}
		});
		
		
		btnchamCongCongNhan.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/Flat_tick_icon.svg.png")));
		btnchamCongCongNhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnchamCongCongNhan.setBackground(new Color(69, 129, 142));
		btnchamCongCongNhan.setBounds(1059, 283, 160, 47);
		panel_3.add(btnchamCongCongNhan);
		
		FixButton btnloadchamcongcongnhan = new FixButton("Chấm Công");
		btnloadchamcongcongnhan.setText("Làm mới");
	
		btnloadchamcongcongnhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnloadchamcongcongnhan.setBackground(new Color(32, 178, 170));
		btnloadchamcongcongnhan.setBounds(857, 283, 160, 47);
		panel_3.add(btnloadchamcongcongnhan);
		
		JPanel panelblnv = new JPanel();
		tabbedPane.addTab("Lương Công Nhân", null, panelblnv, null);
		panelblnv.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(null);
		panel_4.setBackground(new Color(13, 152, 204));
		panel_4.setBounds(10, 11, 1322, 710);
		panelblnv.add(panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Tháng");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(67, 44, 78, 36);
		panel_4.add(lblNewLabel_3);
		
		JComboBox thangLuongCongNhan = new JComboBox();
		thangLuongCongNhan.setBounds(155, 53, 78, 22);
		panel_4.add(thangLuongCongNhan);
		
		
		 thangLuongCongNhan.addItem("1");
		 thangLuongCongNhan.addItem("2");
		 thangLuongCongNhan.addItem("3");
		 thangLuongCongNhan.addItem("4");
		 thangLuongCongNhan.addItem("5");
		 thangLuongCongNhan.addItem("6");
		 thangLuongCongNhan.addItem("7");
		 thangLuongCongNhan.addItem("8");
		 thangLuongCongNhan.addItem("9");
		 thangLuongCongNhan.addItem("10");
		 thangLuongCongNhan.addItem("11");
		 thangLuongCongNhan.addItem("12");
		
			LocalDate locallaythangnamhientai = LocalDate.now();
			int thang = locallaythangnamhientai.getMonthValue();
			 
			thangLuongCongNhan.setSelectedItem(thang+"");
		 
		JLabel lblNm = new JLabel("Năm");
		lblNm.setForeground(new Color(255, 255, 255));
		lblNm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNm.setBounds(310, 44, 55, 36);
		panel_4.add(lblNm);
		
		JComboBox namLuongCongNhan = new JComboBox();
		namLuongCongNhan.setBounds(375, 53, 78, 22);
		panel_4.add(namLuongCongNhan);
		
		 int namHienTai = LocalDate.now().getYear();

	        // Đặt giá trị mặc định cho `JComboBox` là năm hiện tại
		 namLuongCongNhan.addItem(String.valueOf(namHienTai));
		
		
		 
		 FixButton locluongCongnhan = new FixButton("Lọc");
			
			locluongCongnhan.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/search.png")));
			locluongCongnhan.setForeground(Color.WHITE);
			locluongCongnhan.setFont(new Font("Tahoma", Font.BOLD, 14));
			locluongCongnhan.setBackground(new Color(69, 129, 142));
			locluongCongnhan.setBounds(539, 623, 206, 55);
			panel_4.add(locluongCongnhan);
			
			FixButton lammoiLuongCongNhan = new FixButton("Làm Mới");
			lammoiLuongCongNhan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LocalDate currentDate = LocalDate.now();
			        int nam = currentDate.getYear();
			        int thang = currentDate.getMonthValue();
					 
					  List<BangLuongCongNhan> locbangluong = null;
					try {
						locbangluong = service.getchamcongtheongayCN(thang,nam);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						int i=1;
						modelbangluongCongNhan.getDataVector().removeAllElements();
						for (BangLuongCongNhan cn : locbangluong) {
//							String luongtangca = String.format("%.0f", cn.getLuongtangca());
//							String luongsanpham = String.format("%.0f", cn.getLuongsanpham());
//							String tongluong = String.format("%.0f", cn.getTongluong());
							NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
							Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
							i++;
							modelbangluongCongNhan.addRow(obj);
							
							
						}
						tableBangLuongCongNhan.setModel(modelbangluongCongNhan);
						
					System.out.println("e loc ne");
					System.out.println(thang+"");
					System.out.println(nam+"");
					int rowCount = modelbangluongCongNhan.getRowCount();
					System.out.println("so dong loc bang :"+rowCount+"");
					if(rowCount==0) {
						modelbangluongCongNhan.getDataVector().removeAllElements();
						 Object []obj= {"","","","","","","",""};
						 
						 modelbangluongCongNhan.addRow(obj);
						if (modelbangluongCongNhan.getRowCount() > 0) {
							modelbangluongCongNhan.removeRow(0);
						}
						JOptionPane.showMessageDialog(null, "Error: Chưa có dữ liệu của bảng lương vào thời gian này ");
					}
				}
			});
			lammoiLuongCongNhan.setIcon(new ImageIcon(TabCongNhan.class.getResource("/image/reload (1).png")));
			lammoiLuongCongNhan.setForeground(Color.WHITE);
			lammoiLuongCongNhan.setFont(new Font("Tahoma", Font.BOLD, 14));
			lammoiLuongCongNhan.setBackground(new Color(0, 158, 15));
			lammoiLuongCongNhan.setBounds(856, 623, 206, 55);
			panel_4.add(lammoiLuongCongNhan);
			
			JScrollPane scrollPanebangLuongcongNhan = new JScrollPane();
			scrollPanebangLuongcongNhan.setBounds(10, 108, 1302, 449);
			panel_4.add(scrollPanebangLuongcongNhan);
			
			modelbangluongCongNhan= new DefaultTableModel();
			modelbangluongCongNhan.addColumn("Mã Lương");
			modelbangluongCongNhan.addColumn("Mã CN ");
	 
			modelbangluongCongNhan.addColumn("Số giờ tăng ca");
//			modelbangluongCongNhan.addColumn("Lương Cơ Bản");
			modelbangluongCongNhan.addColumn("Lương tăng ca");
			modelbangluongCongNhan.addColumn("Lương Sản Phẩm");
			modelbangluongCongNhan.addColumn("Phụ Cấp");
			modelbangluongCongNhan.addColumn("Tiền Phạt");
			modelbangluongCongNhan.addColumn("Tổng Lương Tháng");
			
			tableBangLuongCongNhan = new JTable(modelbangluongCongNhan);
			scrollPanebangLuongcongNhan.setViewportView(tableBangLuongCongNhan);
			
//			lọc lương công nhân
			locluongCongnhan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int thang =Integer.parseInt((String) thangLuongCongNhan.getSelectedItem());
					int nam =Integer.parseInt((String) namLuongCongNhan.getSelectedItem());
					  List<BangLuongCongNhan> locbangluong = null;
					try {
						locbangluong = service.getchamcongtheongayCN(thang,nam);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						int i=1;
						modelbangluongCongNhan.getDataVector().removeAllElements();
						for (BangLuongCongNhan cn : locbangluong) {
							
							
//							String luongtangca = String.format("%.0f", cn.getLuongtangca());
//							String luongsanpham = String.format("%.0f", cn.getLuongsanpham());
//							String tongluong = String.format("%.0f", cn.getTongluong());
//							Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),luongtangca,luongsanpham,cn.getTroCap(),cn.getPhat(),tongluong };
							
							NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
							Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
							i++;
							modelbangluongCongNhan.addRow(obj);
							
							
						}
						tableBangLuongCongNhan.setModel(modelbangluongCongNhan);
						
					System.out.println("e loc ne");
					System.out.println(thang+"");
					System.out.println(nam+"");
					int rowCount = modelbangluongCongNhan.getRowCount();
					System.out.println("so dong loc bang :"+rowCount+"");
					if(rowCount==0) {
						modelbangluongCongNhan.getDataVector().removeAllElements();
						 Object []obj= {"","","","","","","",""};
						 
						 modelbangluongCongNhan.addRow(obj);
						if (modelbangluongCongNhan.getRowCount() > 0) {
							modelbangluongCongNhan.removeRow(0);
						}
						
						JOptionPane.showMessageDialog(null, "Error: Chưa có dữ liệu của bảng lương vào thời gian này ");
					}
				}
			});
			
			FixButton fxbtnTnhLng = new FixButton("Lọc");
			
			fxbtnTnhLng.setText("Tính Lương");
			fxbtnTnhLng.setForeground(Color.WHITE);
			fxbtnTnhLng.setFont(new Font("Tahoma", Font.BOLD, 14));
			fxbtnTnhLng.setBackground(new Color(69, 129, 142));
			fxbtnTnhLng.setBounds(1106, 623, 206, 55);
			panel_4.add(fxbtnTnhLng);
			
			
//			in bang luong
			FixButton fxbtnInPdf = new FixButton("In");
			fxbtnInPdf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int zoom = 150; // Giá trị zoom mong muốn (%)
					int newWidth = (int) (tableBangLuongCongNhan.getWidth() * (zoom / 100.0));
					int newHeight = (int) (tableBangLuongCongNhan.getHeight() * (zoom / 100.0));

					tableBangLuongCongNhan.setPreferredScrollableViewportSize(new Dimension(newWidth, newHeight));

					// Tiếp theo, bạn có thể tiến hành in bảng
					// code in bảng ở đây

			        // Print the table
					// Create header format
			        MessageFormat headerFormat = new MessageFormat("Bảng Lương Nhân Viên tháng 12" + "\n");

			        // Create footer format
			        MessageFormat footerFormat = new MessageFormat("Page {0}");

			        // Set header and footer alignment
//			        headerFormat.setAlignment(MessageFormat.CENTER);
//			        footerFormat.setAlignment(MessageFormat.RIGHT);

			        // Create a PrinterJob instance
			        PrinterJob printerJob = PrinterJob.getPrinterJob();

			        // Set the printable object as the JTable
			        printerJob.setPrintable(tableBangLuongCongNhan.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat));

			        // Set the page format to match the screen size
			        PageFormat pageFormat = printerJob.defaultPage();
			        pageFormat.setOrientation(PageFormat.PORTRAIT);

			        // Set the zoom to 100%
			        
			        printerJob.setPrintable(tableBangLuongCongNhan.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat), pageFormat);
			     

			        // Print the table
			        try {
			        	   printerJob.print();
			        } catch (PrinterException e1) {
			            e1.printStackTrace();
			        }
	 
				}
			});
			fxbtnInPdf.setText("In PDF");
			fxbtnInPdf.setForeground(Color.WHITE);
			fxbtnInPdf.setFont(new Font("Tahoma", Font.BOLD, 14));
			fxbtnInPdf.setBackground(new Color(69, 129, 142));
			fxbtnInPdf.setBounds(1053, 25, 206, 55);
			panel_4.add(fxbtnInPdf);
			 
			
			
			blcndao =  new BangLuongCongNhanDAO();	   
			
			//load bảng lương
			int thangcombo =Integer.parseInt((String) thangLuongCongNhan.getSelectedItem());
			int namcombo =Integer.parseInt((String) namLuongCongNhan.getSelectedItem());
	        List<BangLuongCongNhan> bangluog=service.getchamcongtheongayCN(thangcombo,namcombo);
			int i11=1;
			for (BangLuongCongNhan cn : bangluog) {
//				String luongtangca = String.format("%.0f", cn.getLuongtangca());
//				String luongsanpham = String.format("%.0f", cn.getLuongsanpham());
//				String tongluong = String.format("%.0f", cn.getTongluong());
//				Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),luongtangca,luongsanpham,cn.getTroCap(),cn.getPhat(),tongluong };
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
				i11++;
				modelbangluongCongNhan.addRow(obj);
				
				
			}
		
 
		
//		bam vao mot dong trong bang luong cong nhan thi sẽ hiện bảng chấm công tương ứng
		
		 ListSelectionModel selectionModel = tableBangLuongCongNhan.getSelectionModel();
	        selectionModel.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    // Lấy dòng được chọn
	                    int selectedRow = tableBangLuongCongNhan.getSelectedRow();

	                    
	                	modelbangchamcongtheoma = new DefaultTableModel( );
	                	modelbangchamcongtheoma.addColumn("Mã Chấm Công");
	                	modelbangchamcongtheoma.addColumn("Mã Công Nhân");
	                	modelbangchamcongtheoma.addColumn("Tên Công Nhân");
	                	modelbangchamcongtheoma.addColumn("Ngày Chấm Công");
	                	modelbangchamcongtheoma.addColumn("Số lượng hoàn thành");
	            		modelbangchamcongtheoma.addColumn("Số giờ tăng ca");
	            		modelbangchamcongtheoma.addColumn("Mã Công Đoạn");
	            		modelbangchamcongtheoma.addColumn("Tổng Tiền Công");
	                   JTable bangchamcongtheoma = new JTable(modelbangchamcongtheoma) {                     
	                   };

	                    
	                    
	                    // Kiểm tra nếu có dòng được chọn
	                  
	                   String ma="";
	                   String ten ="";
	                    if (selectedRow >= 0) {
 
	                            modelbangluongCongNhan.getValueAt(selectedRow, 1).toString();
	                            System.out.println(modelbangluongCongNhan.getValueAt(selectedRow, 1).toString());
	                            int thang = Integer.parseInt(thangLuongCongNhan.getSelectedItem().toString());
	                            int nam = Integer.parseInt(namLuongCongNhan.getSelectedItem().toString());

	                            List<ChamCongCongNhan> dsChamCongCNtheoma = null;
								try {
									dsChamCongCNtheoma = service.getchamcongtheomaCn(modelbangluongCongNhan.getValueAt(selectedRow, 1).toString(),thang,nam );
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                			int i1=1;
	                			for (ChamCongCongNhan cccn : dsChamCongCNtheoma) {
                 				 
	                				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//	    							Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
	                				Object []newData= { cccn.getMaChamCongCongNhan(),cccn.getMaCongNhan().getMaCongNhan(),cccn.getMaCongNhan().getTencongNhan(),cccn.getNgaycham(),cccn.getSoLuong(),cccn.getSogiotangca(),cccn.getMaCongDoan().getMaCongDoan(),currencyFormatter.format(cccn.getTiencong()) };
	                				i1++;
	                				 modelbangchamcongtheoma.addRow(newData);
	                				 
	                					ma=cccn.getMaCongNhan().getMaCongNhan();
		                				ten =cccn.getMaCongNhan().getTencongNhan();
	                				 
	                			}
	                            
	                			bangchamcongtheoma.getColumnModel().getColumn(0).setMinWidth(0);
	                			bangchamcongtheoma.getColumnModel().getColumn(0).setMaxWidth(0);
	                            JScrollPane scroolchamcongtrongbangluong = new JScrollPane(bangchamcongtheoma);
	                        
	                     
	                            JLabel hello = new JLabel("Bảng Lương Nhân Viên Chi Tiết");
	                            JLabel manv = new JLabel("Mã Công viên: " + ma);
	                            JLabel tennv = new JLabel("Tên Công nhân: " + ten);
	                            JLabel thang1 = new JLabel("Tháng ");
	                            JLabel nam1 = new JLabel("Năm");
	                            JButton yourButton = new JButton("In PDF");

	                            JPanel newPanel = new JPanel(new GridBagLayout());
	                            GridBagConstraints gbc = new GridBagConstraints();

	                            // Đặt các thành phần vào vị trí tương ứng
	                            gbc.gridx = 0;
	                            gbc.gridy = 0;
	                            gbc.anchor = GridBagConstraints.CENTER;
	                            gbc.insets = new Insets(10, 10, 10, 10); // Điều chỉnh khoảng cách nếu cần thiết
	                            gbc.gridwidth = 2;
	                            newPanel.add(hello, gbc);

	                            gbc.gridx = 0;
	                            gbc.gridy = 1;
	                            gbc.anchor = GridBagConstraints.WEST;
	                            newPanel.add(manv, gbc);

	                            gbc.gridx = 1;
	                            gbc.gridy = 1;
	                            gbc.anchor = GridBagConstraints.EAST;
	                            newPanel.add(thang1, gbc);

	                            gbc.gridx = 0;
	                            gbc.gridy = 2;
	                            gbc.anchor = GridBagConstraints.WEST;
	                            newPanel.add(tennv, gbc);

	                            gbc.gridx = 1;
	                            gbc.gridy = 2;
	                            gbc.anchor = GridBagConstraints.EAST;
	                            newPanel.add(nam1, gbc);

	                            gbc.gridx = 1;
	                            gbc.gridy = 0;
	                            gbc.anchor = GridBagConstraints.NORTHEAST;
	                            gbc.insets = new Insets(10, 10, 10, 10);
	                            newPanel.add(yourButton, gbc);

	                            gbc.gridx = 0;
	                            gbc.gridy = 3;
	                            gbc.gridwidth = 2;
	                            gbc.fill = GridBagConstraints.BOTH;
	                            gbc.weightx = 1.0;
	                            gbc.weighty = 1.0;
	                            newPanel.add(scroolchamcongtrongbangluong, gbc);

	                            
	                            //in phieu chi tiet
	                            yourButton.addActionListener(new ActionListener() {
	                                @Override
	                                public void actionPerformed(ActionEvent e) {
	                                    
	                                	String ma=(String) bangchamcongtheoma.getValueAt(0, 1);
	                                	String ten=(String) bangchamcongtheoma.getValueAt(0, 1);
	                                	
	                                	int zoom = 150; // Giá trị zoom mong muốn (%)
	                					int newWidth = (int) (bangchamcongtheoma.getWidth() * (zoom / 100.0));
	                					int newHeight = (int) (bangchamcongtheoma.getHeight() * (zoom / 100.0));

	                					bangchamcongtheoma.setPreferredScrollableViewportSize(new Dimension(newWidth, newHeight));

	                					// Tiếp theo, bạn có thể tiến hành in bảng
	                					// code in bảng ở đây

	                			        // Print the table
	                					// Create header format
	                					String headerText = "Bảng Lương Chi tiết Công Nhân\n" +
	                		                    "Mã Công Nhân: " + ma + "\n" +
	                		                    "Tên Công Nhân: " + ten + "\n";

	                		MessageFormat headerFormat = new MessageFormat(headerText);

	                		// ... The rest of your code ...

	                			        // Create footer format
	                			        MessageFormat footerFormat = new MessageFormat("Page {0}");

	                			        // Set header and footer alignment
//	                			        headerFormat.setAlignment(MessageFormat.CENTER);
//	                			        footerFormat.setAlignment(MessageFormat.RIGHT);

	                			        // Create a PrinterJob instance
	                			        PrinterJob printerJob = PrinterJob.getPrinterJob();

	                			        // Set the printable object as the JTable
	                			        printerJob.setPrintable(bangchamcongtheoma.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat));

	                			        // Set the page format to match the screen size
	                			        PageFormat pageFormat = printerJob.defaultPage();
	                			        pageFormat.setOrientation(PageFormat.PORTRAIT);

	                			        // Set the zoom to 100%
	                			        
	                			        printerJob.setPrintable(bangchamcongtheoma.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat), pageFormat);
	                			     

	                			        // Print the table
	                			        try {
	                			        	
	                			        	   printerJob.print();
	                			        	   JOptionPane.showMessageDialog(null, "In bảng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                			        } catch (PrinterException e1) {
	                			            e1.printStackTrace();
	                			        }
	                	 
	                                	
	                                	
	                                	
	                                    
	                                }
	                            });

	                            newPanel.setPreferredSize(new Dimension(1289, 461));
	                         // Hiển thị panel bên trong một hộp thoại thông báo
	                            JOptionPane optionPane = new JOptionPane(newPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
	                            JDialog dialog = optionPane.createDialog("Chấm công chi tiết");
	                            dialog.setVisible(true);

	                            // Kiểm tra xem người dùng đã đóng hộp thoại
	                            if (optionPane.getValue() != null && (int) optionPane.getValue() == JOptionPane.OK_OPTION) {
	                            	tableBangLuongCongNhan.clearSelection();
	                            }

	                    }
	                }
	            }
	        });
	        
	        
//	        nút tính lương 
	        
	        fxbtnTnhLng.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					 // Xử lý sự kiện ở đây
//		            int selectedRow = tablecongdoanchamcong.getSelectedRow();
//		            if (selectedRow != -1) {
// 
//		         		String macua1congnhan= modeldcongdoanchamcong.getValueAt(selectedRow,  1).toString() ;
//		         		blcndao.updateBangLuong( macua1congnhan);
//		         		}
//		            else {
		            	
		            	int rowCount = modelbangluongCongNhan.getRowCount();
		            	System.out.println(rowCount);
		            	for (int row = 0; row < rowCount; row++) {
		            		
		            		try {
								service.updateBangLuongCN( modelbangluongCongNhan.getValueAt(row, 1).toString());
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		            	}
//		            }

 
					  // load lại bảng lương sau khi chấm công
		            	LocalDate currentDate = LocalDate.now();
				        int nam = currentDate.getYear();
				        int thang = currentDate.getMonthValue();
						
						 List<BangLuongCongNhan> locbangluong = null;
						try {
							locbangluong = service.getchamcongtheongayCN(thang,nam);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							int i=1;
							modelbangluongCongNhan.getDataVector().removeAllElements();
							for (BangLuongCongNhan cn : locbangluong) {
//								String luongtangca = String.format("%.0f", cn.getLuongtangca());
//								String luongsanpham = String.format("%.0f", cn.getLuongsanpham());
//								String tongluong = String.format("%.0f", cn.getTongluong());
//								Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),luongtangca,luongsanpham,cn.getTroCap(),cn.getPhat(),tongluong };
								 
								NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
								Object []obj= {cn.getMaBangLuongCongNhan(),cn.getMaCongNhan().getMaCongNhan(),cn.getTongsogiotangca(),currencyFormatter.format(cn.getLuongtangca()),currencyFormatter.format(cn.getLuongsanpham()),currencyFormatter.format(cn.getTroCap()),currencyFormatter.format(cn.getPhat()),currencyFormatter.format(cn.getTongluong()) };
								
								modelbangluongCongNhan.addRow(obj);

							}
							tableBangLuongCongNhan.setModel(modelbangluongCongNhan);
							 	
				}
			});

	        
	        
	    	btnloadchamcongcongnhan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					modeldsanphamchamcong.getDataVector().removeAllElements();
					tablesanphamchamcong.clearSelection();
					
					
//					modeldcongdoanchamcong.getDataVector().removeAllElements();
					tablecongdoanchamcong.clearSelection();
					modeldcongdoanchamcong.getDataVector().removeAllElements();
					 Object []obj1= {"","","",""};
					 
					 modeldcongdoanchamcong.addRow(obj1);
					if (modeldcongdoanchamcong.getRowCount() > 0) {
						modeldcongdoanchamcong.removeRow(0);
					}
				
					//load sản phẩm
					int icd1=1;
					try {
						for (SanPham sp : service.getAllsanPhamDangDathang()) {
							 
							Object []obj= {icd1, sp.getMaSp(),sp.getTenSp()   };
							icd1++;
							modeldsanphamchamcong.addRow(obj);
							
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					tablesanphamchamcong.setModel(modeldsanphamchamcong);
				
					
					
					//
					
					 int rowCount = modeldsanphamchamcong.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						if(rowCount==0) {
							 
							modeldcongdoanchamcong.getDataVector().removeAllElements();
							 Object []obj= {"","","" };
							 
							 modeldsanphamchamcong.addRow(obj);
							if (modeldsanphamchamcong.getRowCount() > 0) {
								modeldsanphamchamcong.removeRow(0);
							}
						}
					//
				}
			});
	        
	}
	
	
	private boolean checkRegexCongNhan() {
		 
		 
		 
		String tenCN = tencn.getText().toString();
		String sdt = sdtcn.getText().toString();
		String diachi = diachicn.getText().toString();
		String cmndCN = cmndcn.getText().toString();
		String troCap= trocapCongNhan.getText().toString();
		
		if (!(tenCN.length() > 0 && tenCN.matches("^[\\p{Lu}][\\p{L}]+(\\s[\\p{Lu}][\\p{L}]+){2,}$"))) {
		    JOptionPane.showMessageDialog(this, "Lỗi: Viết đủ họ tên, chữ cái đầu viết hoa và không chứa số, kí tự đặc biệt. Tối thiểu 3 từ.");
		    return false;
		}


		
		if(!(sdt.length() > 0 && sdt.matches("^0[0-9]{9,10}$"))) {
			JOptionPane.showMessageDialog(this, "Error: Số điện thoại phải có 10 11 số và bắt đầu là số 0");
			return false;
		}
		
//		if (!(email.length() > 0 && email.matches("^[a-zA-Z0-9_+&*-]+(\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$"))) {
//		    JOptionPane.showMessageDialog(this, "Lỗi: Email phải theo định dạng (tenemail)@gmail.com");
//		    return false;
//		}

		
		
		
		if(!(cmndCN.length() > 0 && cmndCN.matches("^[0-9]{9,12}$"))) {
			JOptionPane.showMessageDialog(this, "Error: Căn Cước công dân (CMND) :9 hoặc 12 số");
			return false;
		}
		
		if (!(diachi.length() > 0 && diachi.matches("^[\\p{L}]+(\\s[\\p{L}]+)+$"))) {
		    JOptionPane.showMessageDialog(this, "Lỗi: Nhập tên tỉnh thành bạn đang sống");
		    return false;
		}

	 
		if (!(troCap.length() > 0 && troCap.matches("^[5-9]\\d{5,}$"))) {
		    JOptionPane.showMessageDialog(this, "Lỗi: Trợ cấp phải nhập số và tối thiểu là 500,000");
		    return false;
		}

		
		Date dateengaysinh= datengaysinhcn.getDate();
		
		
		// Chuyển đổi java.util.Date thành Instant
        Instant instant = dateengaysinh.toInstant();
    
        // Chuyển đổi Instant thành LocalDate
        LocalDate datesinh = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        
        if (!isAgeGreaterThan18(datesinh)) {
        	JOptionPane.showMessageDialog(this, "Error: Tuổi phải lớn hơn 18");
        	return false;
        }  
        
        
		return true;
	}
	
	private static boolean isAgeGreaterThan18(LocalDate ngaySinh) {
        LocalDate ngayHienTai = LocalDate.now();
        Period period = Period.between(ngaySinh, ngayHienTai);

        // Kiểm tra xem tuổi có lớn hơn 18 không
        return period.getYears() > 18 || (period.getYears() == 18 && period.getMonths() > 0);
    }
}

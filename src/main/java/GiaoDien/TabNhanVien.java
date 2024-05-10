package GiaoDien;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import Dao.BangLuongNhanVienDAO;
import Dao.ChamCongNhanVienDAO;
import Dao.nhanVienDAO;
import Dao.test1;
import Dao.test1.QRCodeListener;
import Entity.BangLuongCongNhan;
import Entity.BangLuongNhanVien;
import Entity.ChamCongNhanVien;
import Entity.CongNhan;
import Entity.NhanVien;
import services.QuanLyLuongService;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencv.core.Core;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.border.EtchedBorder;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;


public class TabNhanVien extends JPanel implements QRCodeListener{
	private JTextField tennv;
	private JTextField manv;
	private JTextField sdtnv;
	private JTextField trocapnv;
	private JTextField diachinv;
	private JTextField emailnv;
	private JTextField cmndnv;
	private JTable table;
	private JTextField fieldtimten;
	private JDateChooser datengaysinhnv;
	private Timer timer;
	private ChamCongNhanVienDAO ccnvdao= new ChamCongNhanVienDAO();
	private BangLuongNhanVienDAO blnv= new BangLuongNhanVienDAO();
	private JTable tablenhanvien;
private JTable tableluongnhanvien;
	private DefaultTableModel model;
	private DefaultTableModel modelchamcong;
	private DefaultTableModel modelbangchamcongtheoma;
	private DefaultTableModel modelbangluong;
	private String linkHinh="";
	private JDateChooser datengaychamCongNhanVien;
	private ChamCongNhanVienDAO ccnv;
//	JTable bangchamcongtheoma;
	private String previousQRCode = "";
	private nhanVienDAO nvdao= new nhanVienDAO();
	/**
	 * Create the panel.
	 * @throws RemoteException 
	 */
	public TabNhanVien(QuanLyLuongService service) throws RemoteException {
		setLayout(null);
//		Database.ConnectDB.getInstance().connect();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.loadLibrary("opencv_java341");
		
//		nu.pattern.OpenCV.loadLocally();
		
        test1 scanner = new test1();
        scanner.addQRCodeListener(this);
        scanner.startCamera();
       
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1381, 765);
		add(tabbedPane);
		
		JPanel panelQuanLyNhanVien = new JPanel();
		tabbedPane.addTab("Quản Lý Nhân Viên", null, panelQuanLyNhanVien, null);
		panelQuanLyNhanVien.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(13, 152, 204));
		panel.setBounds(10, 11, 1339, 248);
		panelQuanLyNhanVien.add(panel);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(232, 29, 93, 22);
		panel.add(lblNewLabel);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setForeground(new Color(255, 255, 255));
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin.setBounds(439, 29, 103, 22);
		panel.add(lblTnNhnVin);
		
		JLabel lblSinThao = new JLabel("Số điện thoại");
		lblSinThao.setForeground(new Color(255, 255, 255));
		lblSinThao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSinThao.setBounds(232, 93, 93, 22);
		panel.add(lblSinThao);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setForeground(new Color(255, 255, 255));
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaCh.setBounds(438, 162, 93, 22);
		panel.add(lblaCh);
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setForeground(new Color(255, 255, 255));
		lblChcV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChcV.setBounds(609, 29, 93, 22);
		panel.add(lblChcV);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setForeground(new Color(255, 255, 255));
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiiTnh.setBounds(794, 29, 93, 22);
		panel.add(lblGiiTnh);
		
		JLabel lblTrCp = new JLabel("Trợ cấp");
		lblTrCp.setForeground(new Color(255, 255, 255));
		lblTrCp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrCp.setBounds(232, 162, 93, 22);
		panel.add(lblTrCp);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(439, 93, 93, 22);
		panel.add(lblEmail);
		
		JLabel lblCmnd = new JLabel("CMND");
		lblCmnd.setForeground(new Color(255, 255, 255));
		lblCmnd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCmnd.setBounds(609, 93, 93, 22);
		panel.add(lblCmnd);
		
		JLabel lblTrnh = new JLabel("Trình độ");
		lblTrnh.setForeground(new Color(255, 255, 255));
		lblTrnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrnh.setBounds(951, 29, 93, 22);
		panel.add(lblTrnh);
		
		JLabel lblHSLuon = new JLabel("Hệ số lương");
		lblHSLuon.setForeground(new Color(255, 255, 255));
		lblHSLuon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHSLuon.setBounds(1111, 29, 93, 22);
		panel.add(lblHSLuon);
		
		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setForeground(new Color(255, 255, 255));
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrngThi.setBounds(951, 93, 93, 22);
		panel.add(lblTrngThi);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setForeground(new Color(255, 255, 255));
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgySinh.setBounds(794, 93, 93, 22);
		panel.add(lblNgySinh);
		
		tennv = new JTextField();
		tennv.setColumns(10);
		tennv.setBounds(439, 62, 138, 20);
		panel.add(tennv);
		
		manv = new JTextField();
		manv.setEnabled(false);
		manv.setColumns(10);
		manv.setBounds(232, 62, 138, 20);
		panel.add(manv);
		
		sdtnv = new JTextField();
		sdtnv.setColumns(10);
		sdtnv.setBounds(232, 126, 138, 20);
		panel.add(sdtnv);
		
		trocapnv = new JTextField();
		trocapnv.setColumns(10);
		trocapnv.setBounds(232, 195, 138, 20);
		panel.add(trocapnv);
		
		diachinv = new JTextField();
		diachinv.setColumns(10);
		diachinv.setBounds(439, 195, 138, 20);
		panel.add(diachinv);
		
		emailnv = new JTextField();
		emailnv.setColumns(10);
		emailnv.setBounds(439, 126, 138, 20);
		panel.add(emailnv);
		
		cmndnv = new JTextField();
		cmndnv.setColumns(10);
		cmndnv.setBounds(609, 126, 138, 20);
		panel.add(cmndnv);
		
		JComboBox comboBoxChucvunv = new JComboBox();
		comboBoxChucvunv.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBoxChucvunv.setBounds(609, 61, 138, 22);
		panel.add(comboBoxChucvunv);
		
		comboBoxChucvunv.addItem("Quản lý");
		comboBoxChucvunv.addItem("Quản lý phân xưởng");
		comboBoxChucvunv.addItem("Nhân Viên");
		comboBoxChucvunv.addItem("Kế Toán");
		
		JRadioButton rdoNamnv = new JRadioButton("Nam");
		rdoNamnv.setForeground(new Color(255, 255, 255));
		rdoNamnv.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdoNamnv.setSelected(true);
		rdoNamnv.setBackground(new Color(13, 152, 204));
		rdoNamnv.setBounds(794, 63, 57, 23);
		panel.add(rdoNamnv);
		
		JRadioButton rdoNunv = new JRadioButton("Nữ");
		rdoNunv.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdoNunv.setForeground(new Color(255, 255, 255));
		rdoNunv.setBackground(new Color(13, 152, 204));
		rdoNunv.setBounds(862, 63, 53, 23);
		panel.add(rdoNunv);
		
		ButtonGroup rdogt=new ButtonGroup();
		rdogt.add(rdoNamnv);
		rdogt.add(rdoNunv);
		
		  datengaysinhnv = new JDateChooser();
		datengaysinhnv.setBounds(794, 126, 103, 20);
		panel.add(datengaysinhnv);
		
		JComboBox trinhdonv = new JComboBox();
		trinhdonv.setFont(new Font("Tahoma", Font.BOLD, 13));
		trinhdonv.setBounds(951, 62, 103, 22);
		panel.add(trinhdonv);
		
		trinhdonv.addItem("Trung cấp");
		trinhdonv.addItem("Cao Đẳng");
		trinhdonv.addItem("Đại Học");
		
		JComboBox hslnv = new JComboBox();
		hslnv.setFont(new Font("Tahoma", Font.BOLD, 13));
		hslnv.setBounds(1111, 62, 61, 22);
		panel.add(hslnv);
		
		hslnv.addItem("1.9");
		hslnv.addItem("2.12");
		hslnv.addItem("2.37");
		
		
		trinhdonv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTrinhDo = (String) trinhdonv.getSelectedItem();
                if (selectedTrinhDo != null) {
                    if (selectedTrinhDo.equals("Trung cấp")) {
                        hslnv.setSelectedItem("1.9");
                    } else if (selectedTrinhDo.equals("Cao Đẳng")) {
                        hslnv.setSelectedItem("2.12");
                    } else if (selectedTrinhDo.equals("Đại Học")) {
                        hslnv.setSelectedItem("2.37");
                    }
                }
            }
        });
		JComboBox trangthainv = new JComboBox();
		trangthainv.setFont(new Font("Tahoma", Font.BOLD, 13));
		trangthainv.setForeground(new Color(0, 0, 0));
		trangthainv.setBounds(951, 126, 122, 22);
		panel.add(trangthainv);
		
		trangthainv.addItem("Nghỉ việc");
		trangthainv.addItem("Đang làm việc");
		trangthainv.addItem("Bị sa thải");

		trangthainv.setSelectedItem("Đang làm việc");
		
		JLabel lblBhxh = new JLabel("BHXH");
		lblBhxh.setForeground(new Color(255, 255, 255));
		lblBhxh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBhxh.setBounds(1111, 93, 93, 22);
		panel.add(lblBhxh);
		
		JRadioButton bhxhnvCo = new JRadioButton("Có");
		bhxhnvCo.setForeground(new Color(255, 255, 255));
		bhxhnvCo.setFont(new Font("Tahoma", Font.BOLD, 13));
		bhxhnvCo.setSelected(true);
		bhxhnvCo.setBackground(new Color(13, 152, 204));
		bhxhnvCo.setBounds(1111, 124, 46, 23);
		panel.add(bhxhnvCo);
		
		JRadioButton bhxhnvKhong = new JRadioButton("Không");
		bhxhnvKhong.setForeground(new Color(255, 255, 255));
		bhxhnvKhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		bhxhnvKhong.setBackground(new Color(13, 152, 204));
		bhxhnvKhong.setBounds(1159, 124, 70, 23);
		panel.add(bhxhnvKhong);
		
		ButtonGroup rdobxh=new ButtonGroup();
		rdobxh.add(bhxhnvKhong);
		rdobxh.add(bhxhnvCo);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 269, 1339, 345);
		panelQuanLyNhanVien.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(13, 152, 204));
		panel_2.setBounds(10, 625, 1365, 101);
		panelQuanLyNhanVien.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo mã");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 27, 162, 14);
		panel_2.add(lblNewLabel_1);
		
		fieldtimten = new JTextField();
		fieldtimten.setColumns(10);
		fieldtimten.setBounds(20, 52, 175, 20);
		panel_2.add(fieldtimten);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tìm kiếm theo chức vụ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(279, 27, 175, 14);
		panel_2.add(lblNewLabel_1_1);
		
		FixButton lammoiqlnvBtn = new FixButton("Làm mới");
		
		//làm mới load lại bảng nhân viên 
		lammoiqlnvBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nvdao = new nhanVienDAO();
				model.getDataVector().removeAllElements();
				int i=1;
				try {
					for (NhanVien nv : service.getAllnhanvien()) {
						Object []obj= {i,nv.getMaNV(),nv.getTenNV(),nv.getNgaybatdaulamViec(),nv.getChucVu(),nv.getSodienthoai(),nv.getDiachi(),nv.getEmail(),nv.getHeSoLuong(),nv.getTroCap(),nv.getTrangthai(),nv.getBhxh(),nv.getTrinhdo(),nv.getNgaySinh(),nv.getCmnd(),nv.getGioitinh()};
						i++;
						model.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				int lastRow = model.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
				int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị

				if (lastRow >= 0) {
				    Object value = model.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

				    if (value != null) {
				    	
				    	String input = model.getValueAt(lastRow, column).toString();  
				    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
				    	String numberPart = input.substring(2); // Lấy phần số "079"
				    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				    	number++; // Cộng 1 vào số
				    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

				    	manv.setText(result);
				    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
				        
				       
				    }
				}
				
				
				table.setModel(model);
				diachinv.setText("");
				tennv.setText("");
				sdtnv.setText("");
				emailnv.setText("");
				cmndnv.setText("");
				trocapnv.setText("");
			}
		});
		lammoiqlnvBtn.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/reload (1).png")));
		lammoiqlnvBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lammoiqlnvBtn.setBackground(new Color(69, 129, 142));
		lammoiqlnvBtn.setBounds(546, 40, 150, 40);
		panel_2.add(lammoiqlnvBtn);
		
		FixButton themqlnv = new FixButton("Thêm");
		themqlnv.setEnabled(false);
		themqlnv.setText("Lưu");
		themqlnv.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/floppy-disk.png")));
		themqlnv.setFont(new Font("Tahoma", Font.BOLD, 14));
		themqlnv.setBackground(new Color(0, 158, 15));
		themqlnv.setBounds(860, 40, 150, 40);
		panel_2.add(themqlnv);
		
		FixButton suaqlnvbtn = new FixButton("Sửa");
		
//		sửa nhân viên
		suaqlnvbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
							
							if(row>=0) {
								String maNV = manv.getText().toString();
								String tenNV = tennv.getText().toString();
								String sdt = sdtnv.getText().toString();
							
								String diachi = diachinv.getText().toString();
//								String chucvu = txtchucvuu.getText().toString();
								
								String chucvu = (String) comboBoxChucvunv.getSelectedItem();
								
								String trangthais = (String) trangthainv.getSelectedItem();
								boolean gioiTinh =true;
								if(rdoNamnv.isSelected()) {
									gioiTinh=true;
								}
								if(rdoNunv.isSelected()) {
									gioiTinh=false;
								}
								
								 
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								
								//lay ngay sinh
								
								Date dateengaysinh= datengaysinhnv.getDate();
								
								
								// Chuyển đổi java.util.Date thành Instant
						        Instant instant = dateengaysinh.toInstant();
						    
						        // Chuyển đổi Instant thành LocalDate
						        LocalDate datesinh = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate();
						        
								// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
								 
								String strDatengaysinh = dateFormat.format(dateengaysinh);

								// Chuyển đổi chuỗi thành kiểu java.sql.Date
								java.sql.Date sqlDatengaysinh = java.sql.Date.valueOf(strDatengaysinh);
								
								//
								
								
								//ngay bat dau lam viec
								LocalDate datevaolam = LocalDate.now();

								// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
							
//								String strDatevaolam = dateFormat.format(datevaolam);

								// Chuyển đổi chuỗi thành kiểu java.sql.Date
								java.sql.Date sqlDatevaolam = java.sql.Date.valueOf(datevaolam);
								//
								
								
								String cccd = cmndnv.getText().toString();
								
								
								String hesoluong = (String) hslnv.getSelectedItem();
								float hslnv1=Float.parseFloat(hesoluong);
								String idtaikhoan="";
								String matkhau="";
								if(chucvu=="Quản lý") {
									idtaikhoan=maNV;
									matkhau=sdt;
								}
								if(chucvu=="Quản lý phân xưởng") {
									idtaikhoan=maNV;
									matkhau=sdt;
								}
								if(chucvu=="Kế Toán") {
									idtaikhoan=maNV;
									matkhau=sdt;
								}
								if(chucvu=="Nhân Viên") {
									idtaikhoan="";
									matkhau="";
								}
								
								double luongcoban=4500000;
						 
								
								String email=emailnv.getText();
								
								int bhxh =0;
								if(bhxhnvCo.isSelected()) {
									bhxh=0;
								}
								if(bhxhnvKhong.isSelected()) {
									bhxh=1;
								}
								
								String trinhdonv1 = (String) trinhdonv.getSelectedItem();
								
								int trinhdo=0;
								
								if(trinhdonv1.equals("Trung cấp")) {
									trinhdo+=1;
								}
								if(trinhdonv1.equals("Cao Đẳng")) {
									trinhdo+=2;
								}
								if(trinhdonv1.equals("Đại Học")) {
									trinhdo+=3;
								}
								
								double troCap=Double.parseDouble(trocapnv.getText());
								
//								String hinhanhnhanvien=linkHinh;
								
								int trangthai=0;
								
								if(trangthais=="Nghỉ việc") {
									trangthai=0;
								}
								if(trangthais=="Đang làm việc") {
									trangthai=1;
								}
								if(trangthais=="Bị sa thải") {
									trangthai=2;
								}
								

								NhanVien nv= new NhanVien( tenNV, sdt, diachi, chucvu, gioiTinh, datesinh, datevaolam, cccd, hslnv1, idtaikhoan, luongcoban, trinhdo,email,bhxh,trangthai ,troCap,maNV );
								System.out.println(nv.toString());
					 
								
								 JOptionPane optionPane = new JOptionPane(this, JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
		                            JDialog dialog = optionPane.createDialog("Bạn có chắc muốn thay đổi thông tin của nhân viên này !!");
		                            dialog.setVisible(true);

		                            // Kiểm tra xem người dùng đã đóng hộp thoại
		                            if (optionPane.getValue() != null && (int) optionPane.getValue() == JOptionPane.YES_OPTION) {
		                            	try {
											service.update(nv);
											
											//
											nvdao = new nhanVienDAO();
											model.getDataVector().removeAllElements();
											int i=1;
											try {
												for (NhanVien nv1 : service.getAllnhanvien()) {
													Object []obj= {i,nv1.getMaNV(),nv1.getTenNV(),nv1.getNgaybatdaulamViec(),nv1.getChucVu(),nv1.getSodienthoai(),nv1.getDiachi(),nv1.getEmail(),nv1.getHeSoLuong(),nv1.getTroCap(),nv1.getTrangthai(),nv1.getBhxh(),nv1.getTrinhdo(),nv1.getNgaySinh(),nv1.getCmnd(),nv1.getGioitinh()};
													i++;
													model.addRow(obj);
													
												}
											} catch (RemoteException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											
											//
											
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
		                            	
		                            	try {
											boolean updateHeSoLuong = service.updateHeSoLuong(hslnv1, maNV);
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
		                            	
		                            table.setValueAt(manv.getText(), row, 1);
									table.setValueAt(tennv.getText(), row, 2);
//									table.setValueAt(txtcmnd.getText(), row, 3);
									table.setValueAt(comboBoxChucvunv.getSelectedItem(), row, 4);
									table.setValueAt(trangthainv.getSelectedItem(), row, 10);
									table.setValueAt(sdtnv.getText(), row, 5);
									table.setValueAt(diachinv.getText(), row, 6);
									table.setValueAt(emailnv.getText(), row, 7);
									table.setValueAt(hslnv.getSelectedItem(), row, 8);
									table.setValueAt(cmndnv.getText(), row, 10);
									JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin thành công!!");
//								 
		                            }
		                            
		                            
		                        	
//									modelquanLyCongNhan.getDataVector().removeAllElements();
//									int i1=1;
//									try {
//										for (CongNhan cn1 : service.getAllcongNhan()) {
//											Object []obj= {i1,cn1.getMaCongNhan(),cn1.getTencongNhan(),cn1.getNgaysinh(),cn1.getNgaybatdaulamviec(),cn1.getSodienthoai(),cn1.getDiachi(),cn1.getCmnd(),cn1.getTroCap(),cn1.getTrangthai(),cn1.getBhxh(),cn1.getHinhanhnhanvien(),cn1.getGioitinh()};
//											i1++;
//											modelquanLyCongNhan.addRow(obj);
//											
//										}
//									} catch (RemoteException e1) {
//										// TODO Auto-generated catch block
//										e1.printStackTrace();
//									}
//		                            }
		                            
		                            model.getDataVector().removeAllElements();
		                           
		                    		int i=1;
		                    		try {
										for (NhanVien nv1 : service.getAllnhanvien()) {
											Object []obj= {i,nv1.getMaNV(),nv1.getTenNV(),nv1.getNgaybatdaulamViec(),nv1.getChucVu(),nv1.getSodienthoai(),nv1.getDiachi(),nv1.getEmail(),nv1.getHeSoLuong(),nv1.getTroCap(),nv1.getTrangthai(),nv1.getBhxh(),nv1.getTrinhdo(),nv1.getNgaySinh(),nv1.getCmnd(),nv1.getGioitinh()};
											i++;
											model.addRow(obj);
											
										}
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								
//								 
					 	}
					 
			}
		});
		suaqlnvbtn.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/edit (1).png")));
		suaqlnvbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		suaqlnvbtn.setForeground(new Color(255, 255, 255));
		suaqlnvbtn.setBackground(new Color(164, 164, 0));
		suaqlnvbtn.setBounds(1020, 40, 150, 40);
		panel_2.add(suaqlnvbtn);
		
		FixButton huyqlnvbtn = new FixButton("Hủy");
		
		huyqlnvbtn.setEnabled(false);
		huyqlnvbtn.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/cancel (1).png")));
		huyqlnvbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		huyqlnvbtn.setBackground(new Color(207, 42, 39));
		huyqlnvbtn.setBounds(1180, 42, 150, 40);
		panel_2.add(huyqlnvbtn);
		
		JButton timtheoten = new JButton("");
		timtheoten.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/search.png")));
		timtheoten.setSelectedIcon(new ImageIcon(TabNhanVien.class.getResource("/image/search.png")));
		timtheoten.setBounds(205, 42, 57, 40);
		panel_2.add(timtheoten);
		
		JButton timtheochucvu = new JButton("");
		timtheochucvu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		timtheochucvu.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/search.png")));
		timtheochucvu.setBounds(466, 42, 57, 40);
		panel_2.add(timtheochucvu);
		
		JComboBox cbotimchucvu = new JComboBox();
		cbotimchucvu.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbotimchucvu.setBounds(279, 51, 138, 22);
		panel_2.add(cbotimchucvu);
		
		cbotimchucvu.addItem("Quản lý");
		cbotimchucvu.addItem("Quản lý phân xưởng");
		cbotimchucvu.addItem("Nhân Viên");
		cbotimchucvu.addItem("Kế Toán");
		
		
//		nút thêm nhân viên
		FixButton fxbtnThm = new FixButton("Thêm");
		
		//seticon
		fxbtnThm.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/add (1).png")));
		fxbtnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				btnThem.s
				fxbtnThm.setEnabled(false);
				themqlnv.setEnabled(true);
				suaqlnvbtn.setEnabled(false);
				huyqlnvbtn.setEnabled(true);

				
				tennv.requestFocus();
				diachinv.setText("");
				tennv.setText("");
				sdtnv.setText("");
				emailnv.setText("");
				cmndnv.setText("");
				trocapnv.setText("");
			tennv.setEditable(true);
			
			int lastRow = model.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
			int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị
			String matam="";
			if (lastRow >= 0) {
			    Object value = model.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

			    if (value != null) {
			    	
			    	String input = model.getValueAt(lastRow, column).toString();  
			    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
			    	String numberPart = input.substring(2); // Lấy phần số "079"
			    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
			    	number++; // Cộng 1 vào số
			    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

			    	manv.setText(result);
			    	matam+=result;
			    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
			        
			       
			    }
			}
			table.clearSelection();
			
			
			}
			
			
		});
		
		
//		nút hủy
		huyqlnvbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fxbtnThm.setEnabled(true);
				themqlnv.setEnabled(false);
				suaqlnvbtn.setEnabled(true);
				huyqlnvbtn.setEnabled(false);
			}
		});
		fxbtnThm.setFont(new Font("Tahoma", Font.BOLD, 14));
		fxbtnThm.setBackground(new Color(0, 158, 15));
		fxbtnThm.setBounds(706, 40, 140, 40);
		panel_2.add(fxbtnThm);
		
		
		model= new DefaultTableModel(); 
		model.addColumn("STT");
		model.addColumn("Mã NV");
		model.addColumn("Họ Tên ");
		model.addColumn("Ngày Vào Làm");
		model.addColumn("Chức Vụ");
		model.addColumn("Số Điện Thoại");
		model.addColumn("Địa Chỉ");
		model.addColumn("Email");
		model.addColumn("Hệ Số lương");
		model.addColumn("Phụ Cấp");
		model.addColumn("Trạng Thái");
		model.addColumn("Bhxh");
		model.addColumn("Trình Độ ");
		model.addColumn("Ngày sinh");
//		model.addColumn("Hình");
		model.addColumn("CMND");
		model.addColumn("Giới tính");
		table= new JTable(model);

        table.getColumnModel().getColumn(10).setMinWidth(0);
        table.getColumnModel().getColumn(10).setMaxWidth(0);
        table.getColumnModel().getColumn(11).setMinWidth(0);
        table.getColumnModel().getColumn(11).setMaxWidth(0);
        table.getColumnModel().getColumn(12).setMinWidth(0);
        table.getColumnModel().getColumn(12).setMaxWidth(0);
        table.getColumnModel().getColumn(13).setMinWidth(0);
        table.getColumnModel().getColumn(13).setMaxWidth(0);
        table.getColumnModel().getColumn(15).setMinWidth(0);
        table.getColumnModel().getColumn(15).setMaxWidth(0);
//      table.getColumnModel().getColumn(16).setMinWidth(0);
//      table.getColumnModel().getColumn(16).setMaxWidth(0);
		scrollPane.setViewportView(table);
		

		
		//load thông tin nhân viên
		nvdao = new nhanVienDAO();
		int i=1;
		for (NhanVien nv : service.getAllnhanvien()) {
			Object []obj= {i,nv.getMaNV(),nv.getTenNV(),nv.getNgaybatdaulamViec(),nv.getChucVu(),nv.getSodienthoai(),nv.getDiachi(),nv.getEmail(),nv.getHeSoLuong(),nv.getTroCap(),nv.getTrangthai(),nv.getBhxh(),nv.getTrinhdo(),nv.getNgaySinh(),nv.getCmnd(),nv.getGioitinh()};
			i++;
			model.addRow(obj);
			
		}
		
//		Mã NV tiep theo :lấy giá trị cuối cùng của của cột mã nhân viên
		
		
		int lastRow = model.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
		int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị

		if (lastRow >= 0) {
		    Object value = model.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

		    if (value != null) {
		    	
		    	String input = model.getValueAt(lastRow, column).toString();  
		    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
		    	String numberPart = input.substring(2); // Lấy phần số "079"
		    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
		    	number++; // Cộng 1 vào số
		    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

		    	manv.setText(result);
		    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
		        
		       
		    }
		}
		
		
//		end 
		
		
//		nút lưu nhân viên
		themqlnv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aa
				
				
				if(checkRegexNhanVien()) {
				
				String maNV = manv.getText().toString();
				String tenNV = tennv.getText().toString();
				String sdt = sdtnv.getText().toString();
				 
				String diachi = diachinv.getText().toString();
//				String chucvu = txtchucvuu.getText().toString();
				
				String chucvu = (String) comboBoxChucvunv.getSelectedItem();
				
				boolean gioiTinh =true;
				if(rdoNamnv.isSelected()) {
					gioiTinh=true;
				}
				if(rdoNunv.isSelected()) {
					gioiTinh=false;
				}
				
				 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				//lay ngay sinh
				
				Date dateengaysinh= datengaysinhnv.getDate();
				
				
				// Chuyển đổi java.util.Date thành Instant
		        Instant instant = dateengaysinh.toInstant();
		    
		        // Chuyển đổi Instant thành LocalDate
		        LocalDate datesinh = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate();
		        
				// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
				 
				String strDatengaysinh = dateFormat.format(dateengaysinh);

				// Chuyển đổi chuỗi thành kiểu java.sql.Date
				java.sql.Date sqlDatengaysinh = java.sql.Date.valueOf(strDatengaysinh);
				
				//
				
				
				//ngay bat dau lam viec
				LocalDate datevaolam = LocalDate.now();

				// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
			
//				String strDatevaolam = dateFormat.format(datevaolam);

				// Chuyển đổi chuỗi thành kiểu java.sql.Date
				java.sql.Date sqlDatevaolam = java.sql.Date.valueOf(datevaolam);
				//
				
				
				String cccd = cmndnv.getText().toString();
				
				
				String hesoluong = (String) hslnv.getSelectedItem();
				float hslnv=Float.parseFloat(hesoluong);
				String idtaikhoan="";
				String matkhau="";
				if(chucvu=="Quản lý") {
					idtaikhoan=maNV;
					matkhau=sdt;
				}
				if(chucvu=="Quản lý phân xưởng") {
					idtaikhoan=maNV;
					matkhau=sdt;
				}
				if(chucvu=="Kế Toán") {
					idtaikhoan=maNV;
					matkhau=sdt;
				}
				if(chucvu=="Nhân Viên") {
					idtaikhoan="";
					matkhau=sdt;
				}
				
				double luongcoban=4500000;
		 
				
				String email=emailnv.getText();
				
				int bhxh =0;
				if(bhxhnvCo.isSelected()) {
					bhxh=0;
				}
				if(bhxhnvKhong.isSelected()) {
					bhxh=1;
				}
				
				String trinhdonv1 = (String) trinhdonv.getSelectedItem();
				
				int trinhdo=0;
				
				if(trinhdonv1.equals("Trung cấp")) {
					trinhdo+=1;
				}
				if(trinhdonv1.equals("Cao Đẳng")) {
					trinhdo+=2;
				}
				if(trinhdonv1.equals("Đại Học")) {
					trinhdo+=3;
				}
				
				double troCap=Double.parseDouble(trocapnv.getText());
				
				String hinhanhnhanvien=linkHinh;
				
				int trangthai=0;
				
				if(trangthainv.getSelectedItem()=="Nghỉ việc") {
					trangthai=0;
				}
				if(trangthainv.getSelectedItem()=="Đang làm việc") {
					trangthai=1;
				}
				if(trangthainv.getSelectedItem()=="Bị sa thải") {
					trangthai=2;
				}
				
				
				
				NhanVien nv= new NhanVien( tenNV, sdt, diachi, chucvu, gioiTinh, datesinh, datevaolam, cccd, hslnv, idtaikhoan,matkhau, luongcoban, trinhdo,email,bhxh,trangthai ,troCap);
				System.out.println(nv.toString());
				try {
					service.themDanhSachNV(nv);
					
					
					
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int i=1;
				
				 
			 

				int lastRow = model.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
				int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị
				String matam="";
				if (lastRow >= 0) {
				    Object value = model.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

				    if (value != null) {
				    	
				    	String input = model.getValueAt(lastRow, column).toString();  
				    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
				    	String numberPart = input.substring(2); // Lấy phần số "079"
				    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				    	number++; // Cộng 1 vào số
				    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

				    	manv.setText(result);
				    	matam+=result;
				    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
				        
				       
				    }
				}
				
				
//				 Object   []obj = {i, matam , tenNV,  datevaolam, chucvu, sdt, diachi, email,hslnv,troCap,cccd};
//				 i++;
//				model.addRow(obj);
				
				nvdao = new nhanVienDAO();
				model.getDataVector().removeAllElements();
				int i1=1;
				try {
					for (NhanVien nv1 : service.getAllnhanvien()) {
						Object []obj= {i1,nv1.getMaNV(),nv1.getTenNV(),nv1.getNgaybatdaulamViec(),nv1.getChucVu(),nv1.getSodienthoai(),nv1.getDiachi(),nv1.getEmail(),nv1.getHeSoLuong(),nv1.getTroCap(),nv1.getTrangthai(),nv1.getBhxh(),nv1.getTrinhdo(),nv1.getNgaySinh(),nv1.getCmnd(),nv1.getGioitinh()};
						i1++;
						model.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
			}
		});
			
table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row=table.getSelectedRow();
				manv.setText(model.getValueAt(row, 1).toString());
				tennv.setText(model.getValueAt(row, 2).toString());
				
				
				comboBoxChucvunv.setSelectedItem(model.getValueAt(row, 4).toString());
				
				sdtnv.setText(model.getValueAt(row, 5).toString());
				diachinv.setText(model.getValueAt(row, 6).toString());
				emailnv.setText(model.getValueAt(row, 7).toString());
				
				hslnv.setSelectedItem(model.getValueAt(row, 8).toString());
				trocapnv.setText(model.getValueAt(row, 9).toString());
				
				String trangthai=model.getValueAt(row, 10).toString();
				
				if(trangthai.equals("0")) {
					trangthainv.setSelectedItem("Nghỉ việc");
				}
				if(trangthai.equals("1")) {
					trangthainv.setSelectedItem("Đang làm việc");
				}	if(trangthai.equals("2")) {
					trangthainv.setSelectedItem("Bị sa thải");
				}
				
				String bhxh=model.getValueAt(row, 11).toString();
				if(bhxh.equals("0")) {
					bhxhnvCo.setSelected(true);
				}
				if(bhxh.equals("1")) {
					bhxhnvKhong.setSelected(true);
				}
				
				String trinhdo=model.getValueAt(row, 12).toString();
				if(trinhdo.equals("1")) {
					trinhdonv.setSelectedItem("Trung cấp");
				}
				if(trinhdo.equals("2")) {
					trinhdonv.setSelectedItem("Cao Đẳng");
				}
				if(trinhdo.equals("3")) {
					trinhdonv.setSelectedItem("Đại Học");
				}
				
				cmndnv.setText(model.getValueAt(row, 14).toString());
			 
				SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				 
//				 
				rdoNamnv.setSelected(model.getValueAt(row, 10).toString().equalsIgnoreCase("true") ? true : false);
				rdoNunv.setSelected(model.getValueAt(row, 10).toString().equalsIgnoreCase("false") ? true : false);
				
				try {
					date = format.parse(model.getValueAt(row,13).toString());
					datengaysinhnv.setDate(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				
 
	 
			 
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


	// tim theo chuc vu
		timtheochucvu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				 
				
				 List<NhanVien> timtheochucvu = null;
				try {
					timtheochucvu = service.getAllnhanvienTheoChucVu(cbotimchucvu.getSelectedItem().toString());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					model.getDataVector().removeAllElements();
					 
					for (NhanVien nv : timtheochucvu) {
						Object []obj= {i,nv.getMaNV(),nv.getTenNV(),nv.getNgaybatdaulamViec(),nv.getChucVu(),nv.getSodienthoai(),nv.getDiachi(),nv.getEmail(),nv.getHeSoLuong(),nv.getTroCap(),nv.getTrangthai(),nv.getBhxh(),nv.getTrinhdo(),nv.getNgaySinh(),nv.getCmnd(),nv.getGioitinh()};
						i++;
						model.addRow(obj);
						
					}
					table.setModel(model);
					
					int rowCount = model.getRowCount();
					System.out.println("so dong loc bang :"+rowCount+"");
					if(rowCount==0) {
					 
						JOptionPane.showMessageDialog(null, "Error: Không tìm thấy nhân viên có chức vụ "+cbotimchucvu.getSelectedItem());
					}
		 
			}
		
			 
		});

		//tim theo ten nhan vien
		timtheoten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				 
				
				 List<NhanVien> timtheoten = null;
				try {
					timtheoten = service.getAllnhanvienTheoten(fieldtimten.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					model.getDataVector().removeAllElements();
					 
					 
					for (NhanVien nv : timtheoten) {
						Object []obj= {i,nv.getMaNV(),nv.getTenNV(),nv.getNgaybatdaulamViec(),nv.getChucVu(),nv.getSodienthoai(),nv.getDiachi(),nv.getEmail(),nv.getHeSoLuong(),nv.getTroCap(),nv.getTrangthai(),nv.getBhxh(),nv.getTrinhdo(),nv.getNgaySinh(),nv.getCmnd(),nv.getGioitinh()};
						i++;
						model.addRow(obj);
						
					}
					table.setModel(model);
					
					int rowCount = model.getRowCount();
					System.out.println("so dong loc bang :"+rowCount+"");
					if(rowCount==0) {
						JOptionPane.showMessageDialog(null, "Error: Không tìm thấy nhân viên có tên "+fieldtimten.getText());
					}
					
					
			}
		
			 
		});
//	Cham cong Nhân Viên
		JPanel panelccnv = new JPanel();
		tabbedPane.addTab("Chấm Công Nhân Viên", null, panelccnv, null);
		panelccnv.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(13, 152, 204));
		panel_1.setBounds(0, 0, 1360, 726);
		panelccnv.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày chấm công:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(54, 46, 160, 36);
		panel_1.add(lblNewLabel_2);
		
		  datengaychamCongNhanVien = new JDateChooser();
		datengaychamCongNhanVien.setBounds(224, 51, 229, 31);
		panel_1.add(datengaychamCongNhanVien);
		
		Date ngayHienTai = new Date();

		// Đặt giá trị của JDateChooser thành ngày hiện tại
		datengaychamCongNhanVien.setDate(ngayHienTai);
		
		
		FixButton btncomatdaydu = new FixButton("Có mặt đầy đủ");
		btncomatdaydu.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/user (3).png")));
		btncomatdaydu.setForeground(Color.WHITE);
		btncomatdaydu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btncomatdaydu.setBackground(new Color(69, 129, 142));
		btncomatdaydu.setBounds(887, 599, 206, 55);
		panel_1.add(btncomatdaydu);
		
	
		
		
		
		JScrollPane scrollPanechamcong = new JScrollPane();
		scrollPanechamcong.setBounds(29, 102, 1289, 461);
		panel_1.add(scrollPanechamcong);
		
		
		modelchamcong = new DefaultTableModel( );
		modelchamcong.addColumn("Mã Chấm Công Nhân Viên");
		modelchamcong.addColumn("Mã NV");
		modelchamcong.addColumn("Họ Tên ");
		modelchamcong.addColumn("Ngày Chấm");
		modelchamcong.addColumn("Hiện diện");
		modelchamcong.addColumn("Ca Làm");
			modelchamcong.addColumn("Có phép / Không phép");
			modelchamcong.addColumn("Số giờ tăng ca");
			modelchamcong.addColumn("Ghi Chú");
			modelchamcong.addColumn("Thời gian đến");
			modelchamcong.addColumn("Thời gian đi");
			modelchamcong.addColumn("Đi trễ");
			
			
       JTable tableChamcong = new JTable(modelchamcong) {
           @Override
           public Class<?> getColumnClass(int column) {
               if (column == 4) {
                   return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
               }
               if (column == 6) {
                   return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
               }
               if (column == 11) {
                   return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
               }

               return super.getColumnClass(column);
           }
       };
       JCheckBox hiendien= new JCheckBox();
       JCheckBox cophepkhongphep= new JCheckBox();
       JCheckBox ditre= new JCheckBox();
       // Đặt kiểu dữ liệu cho cột Select
       tableChamcong.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(hiendien));
       tableChamcong.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cophepkhongphep));
       tableChamcong.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(ditre));
      
       
       
    // Create a JComboBox with your options
       JComboBox comboBoxCaLam = new JComboBox();
       comboBoxCaLam.addItem("Nguyên buổi");
       comboBoxCaLam.addItem("Nửa Buổi");
    
       // Set the default value to "Sáng"
       comboBoxCaLam.setSelectedItem("Nguyên buổi");

       // Set the JComboBox as the cell editor for the "Ca Làm" column : add comboBox Ca Làm và cột
       TableColumn caLamCol = tableChamcong.getColumnModel().getColumn(5);
       caLamCol.setCellEditor(new DefaultCellEditor(comboBoxCaLam));

       
       JComboBox giotangca = new JComboBox();
       giotangca.addItem("0");
       giotangca.addItem("1");
       giotangca.addItem("2");
       giotangca.addItem("3");
       giotangca.addItem("4");
    
       // Set the default value to "Sáng"
       giotangca.setSelectedItem("0");
       giotangca.setSelectedItem((Object) "0");

//        Set the JComboBox as the cell editor for the "Ca Làm" column
       TableColumn giotangcacolumn = tableChamcong.getColumnModel().getColumn(7);
       giotangcacolumn.setCellEditor(new DefaultCellEditor(giotangca));
       
       
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Đặt căn giữa cho nội dung cột

       
//       căn giữa cột
       tableChamcong.getColumnModel().getColumn( 0).setCellRenderer(centerRenderer); // Áp dụng trình điều khiển tùy chỉnh cho cột thứ columnIndex
       tableChamcong.getColumnModel().getColumn( 1).setCellRenderer(centerRenderer);
       tableChamcong.getColumnModel().getColumn( 2).setCellRenderer(centerRenderer);
       tableChamcong.getColumnModel().getColumn( 3).setCellRenderer(centerRenderer);
       tableChamcong.getColumnModel().getColumn( 5).setCellRenderer(centerRenderer);
       tableChamcong.getColumnModel().getColumn( 7).setCellRenderer(centerRenderer);
       tableChamcong.getColumnModel().getColumn( 8).setCellRenderer(centerRenderer);
//       tableChamcong.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
//       table.getColumnModel().getColumn( 0).setCellRenderer(centerRenderer);
       
       //ẩn cột số giờ tăng ca
       
       tableChamcong.getColumnModel().getColumn(7).setMinWidth(0);
       tableChamcong.getColumnModel().getColumn(7).setMaxWidth(0);
       
       
   
       
		scrollPanechamcong.setViewportView(tableChamcong);
		
		
		//auto set checkbox hiện diện khi mà nhân viên có mặt đầu đủ
		btncomatdaydu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
//		        DefaultTableModel model = (DefaultTableModel) tableChamcong.getModel();
		        int rowCount = modelchamcong.getRowCount();
		        int hiendienColumn = 4; // Chỉ số cột Hiendien

		        for (int i = 0; i < rowCount; i++) {
		        	modelchamcong.setValueAt(true, i, hiendienColumn);
		        }
		    }
		});
		 modelchamcong.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 4) { // Kiểm tra sự kiện cập nhật và cột "Hiện diện"
		            int row = e.getFirstRow();
		            boolean hiendienValue = (boolean) tableChamcong.getValueAt(row, 4);
		            
		            if (hiendienValue) {
		                tableChamcong.setValueAt(false, row, 6); // Bỏ chọn ô "Có phép / Không phép" trong cùng một hàng
		            }else {
		            	tableChamcong.setValueAt(null, row, 5);
		            }
		        }
				 if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 6) { // Kiểm tra sự kiện cập nhật và cột "Có phép / Không phép"
			            int row = e.getFirstRow();
			            boolean cophepkhongphepValue = (boolean) tableChamcong.getValueAt(row, 6);
			            
			            if (cophepkhongphepValue) {
			                tableChamcong.setValueAt(false, row, 4);
			                tableChamcong.setValueAt(null, row, 5);// Bỏ chọn ô "Hiện diện" trong cùng một hàng
			            }
			        }
			}
		});
//		
//		 tableChamcong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//	            public void valueChanged(ListSelectionEvent event) {
//	                // Dừng timer khi sự kiện xảy ra
//	                stopTimer();
//	            }
//	        });
	    
		// Lấy giá trị từ JDateChooser
		Date date = datengaychamCongNhanVien.getDate();

		// Chuyển đổi Date thành LocalDate
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault(); // Hoặc bạn có thể chọn ZoneId cụ thể
		LocalDate localDatengaycham = instant.atZone(zoneId).toLocalDate();
		
		
		//LOAD BANG CHAM CONG LEN 
		 ccnvdao = new ChamCongNhanVienDAO();
	        
	        
	        List<ChamCongNhanVien> dsChamCongNVhomnay=service.getchamcongtheongay(localDatengaycham );
			int i1=1;
			for (ChamCongNhanVien nv : dsChamCongNVhomnay) {
//				Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham()};
				Boolean codilam = null;
				Boolean nghilam = null;
				Boolean coditre = null;
				if (nv.getHiendien() == 2) {
				    codilam = true;
				    nghilam = null;
				} else if (nv.getHiendien() == 1) {
				    codilam = null;
				    nghilam = true;
				} else if (nv.getHiendien() == 0) {
				    codilam = null;
				    nghilam = null;
				}
				
				if(nv.getDitre()==1) {
					coditre=true;
				}
				else {
					coditre=null;
				}
				Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham(),codilam,nv.getCaLam(),nghilam,nv.getSogioTangca(),nv.getGhiChu(),nv.getThoigianden(),nv.getThoigiandi(),coditre};
				i1++;
				modelchamcong.addRow(obj);
				System.out.println(nv.getNV().getTenNV());
				
			}
			 int count = dsChamCongNVhomnay.size();
		        System.out.println(count);
		 
//		     LOAD XONG BẢNG CHẤM CÔNG
		
		        blnv= new BangLuongNhanVienDAO();
 
//		        Cham cong 
		        
		        FixButton btnchamCongNhanVien = new FixButton("Chấm Công");
		        btnchamCongNhanVien.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/Flat_tick_icon.svg.png")));
				btnchamCongNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnchamCongNhanVien.setBackground(new Color(31, 184, 84));
				btnchamCongNhanVien.setBounds(489, 599, 206, 55);
				panel_1.add(btnchamCongNhanVien);
				
				FixButton btnTaoBangChamCong = new FixButton("Thêm");
				
				btnTaoBangChamCong.setText("Tạo bảng chấm công");
				btnTaoBangChamCong.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnTaoBangChamCong.setBackground(new Color(0, 158, 15));
				btnTaoBangChamCong.setBounds(944, 42, 185, 40);
				panel_1.add(btnTaoBangChamCong);
				
				JComboBox thangLuongNhanVien = new JComboBox();
				JComboBox namLuongNhanVien = new JComboBox();
				
				
			
				
				btnTaoBangChamCong.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
//						cham = new nhanVienDAO();
						
						try {
							service.TaoBangChamCong();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						modelchamcong.getDataVector().removeAllElements();
						
						
						List<ChamCongNhanVien> dsChamCongNVhomnay = null;
						try {
							dsChamCongNVhomnay = service.getchamcongtheongay(localDatengaycham );
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int i1=1;
						for (ChamCongNhanVien nv : dsChamCongNVhomnay) {
//							Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham()};
							Boolean codilam = null;
							Boolean nghilam = null;
							Boolean coditre = null;
							if (nv.getHiendien() == 2) {
							    codilam = true;
							    nghilam = null;
							} else if (nv.getHiendien() == 1) {
							    codilam = null;
							    nghilam = true;
							} else if (nv.getHiendien() == 0) {
							    codilam = null;
							    nghilam = null;
							}
							
							if(nv.getDitre()==1) {
								coditre=true;
							}
							else {
								coditre=null;
							}
							Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham(),codilam,nv.getCaLam(),nghilam,nv.getSogioTangca(),nv.getGhiChu(),nv.getThoigianden(),nv.getThoigiandi(),coditre};
							i1++;
							modelchamcong.addRow(obj);
							System.out.println(nv.getNV().getTenNV());
							
						}
						 int count = dsChamCongNVhomnay.size();
					        System.out.println(count);
					}
				});
				
				//check set enabled nut tao cham cong
				
				
				//
				 
				// Lắng nghe sự kiện khi ngày thay đổi trên JDateChooser
				 datengaychamCongNhanVien.addPropertyChangeListener("date", new PropertyChangeListener() {
			            @Override
			            public void propertyChange(PropertyChangeEvent evt) {
			                if ("date".equals(evt.getPropertyName())) {
			                    Date selectedDate = (Date) evt.getNewValue();
			                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			                    String formattedDate = sdf.format(selectedDate);
//			                    System.out.println ("Ngày đã chọn: " + formattedDate);
//			                    Date date = datengaychamCongNhanVien.getDate();

			            		// Chuyển đổi Date thành LocalDate
			            		Instant instant = selectedDate.toInstant();
			            		ZoneId zoneId = ZoneId.systemDefault(); // Hoặc bạn có thể chọn ZoneId cụ thể
			            		LocalDate localDatengaycham = instant.atZone(zoneId).toLocalDate();
			                	
			            		modelchamcong.getDataVector().removeAllElements();
								
								System.out.println("load ngay theo jchoose "+ localDatengaycham);
								List<ChamCongNhanVien> dsChamCongNVhomnay = null;
								try {
									dsChamCongNVhomnay = service.getchamcongtheongay(localDatengaycham );
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								int i1=1;
								for (ChamCongNhanVien nv : dsChamCongNVhomnay) {
//									Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham()};
									Boolean codilam = null;
									Boolean nghilam = null;
									Boolean coditre = null;
									if (nv.getHiendien() == 2) {
									    codilam = true;
									    nghilam = null;
									} else if (nv.getHiendien() == 1) {
									    codilam = null;
									    nghilam = true;
									} else if (nv.getHiendien() == 0) {
									    codilam = null;
									    nghilam = null;
									}
									
									if(nv.getDitre()==1) {
										coditre=true;
									}
									else {
										coditre=null;
									}
									Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham(),codilam,nv.getCaLam(),nghilam,nv.getSogioTangca(),nv.getGhiChu(),nv.getThoigianden(),nv.getThoigiandi(),coditre};
									i1++;
									modelchamcong.addRow(obj);
									System.out.println(nv.getNV().getTenNV());
									
								}
								 int count = dsChamCongNVhomnay.size();
							        System.out.println("bang load dc "+count);
			            		
							        
							    	if(count==0) {
										JOptionPane.showMessageDialog(null, "Error:Không tìm thấy dữ liệu chấm công của ngày :"+formattedDate);
										modelchamcong.getDataVector().removeAllElements();
										 Object []obj= {"","","",""};
										 
										 modelchamcong.addRow(obj);
//										if (modelchamcong.getRowCount() > 0) {
											modelchamcong.removeRow(0);
//										}
									}
			            		
							    	int comparison = localDatengaycham.compareTo(LocalDate.now());
							    	if (comparison == 0) {
							    		System.out.println(localDatengaycham);
							    		System.out.println(LocalDate.now());
							    	    System.out.println("Hai ngày giống nhau.");
//							    	    btnTaoBangChamCong.setEnabled(true);
							    	    if(dsChamCongNVhomnay.isEmpty()) {
											 btnTaoBangChamCong.setEnabled(true);
										 }
										 else {
											 btnTaoBangChamCong.setEnabled(false);
										 }
							    	   
							    	} else {
							    		System.out.println(localDatengaycham);
							    		System.out.println(LocalDate.now());
							    	    System.out.println("Hai ngày không giống nhau.");
							    	    btnTaoBangChamCong.setEnabled(false);
							    	    btnTaoBangChamCong.setToolTipText("Không thể tạo chấm công có ngày hôm trước và hôm sau ");
							    	}
							    	
							   
							    	
			                }}
			        });
				 
				 List<ChamCongNhanVien> checksetenabldtaoChamCong=service.getchamcongtheongay(localDatengaycham );
				 int dembangchamcong = checksetenabldtaoChamCong.size();

				 if(checksetenabldtaoChamCong.isEmpty()) {
					 btnTaoBangChamCong.setEnabled(true);
				 }
				 else {
					 btnTaoBangChamCong.setEnabled(false);
				 }
				 

				 
				
		JPanel panelblnv = new JPanel();
		tabbedPane.addTab("Lương Nhân Viên", null, panelblnv, null);
		panelblnv.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(13, 152, 204));
		panel_3.setBounds(10, 11, 1366, 713);
		panelblnv.add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Tháng");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(56, 66, 78, 36);
		panel_3.add(lblNewLabel_3);
		
	
		
		JLabel lblNm = new JLabel("Năm");
		lblNm.setForeground(new Color(255, 255, 255));
		lblNm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNm.setBounds(293, 66, 55, 36);
		panel_3.add(lblNm);
		
		 
		
		FixButton locluongNhanVien = new FixButton("Lọc");
		locluongNhanVien.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/search.png")));
		locluongNhanVien.setForeground(Color.WHITE);
		locluongNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		locluongNhanVien.setBackground(new Color(69, 129, 142));
		locluongNhanVien.setBounds(532, 615, 206, 55);
		panel_3.add(locluongNhanVien);
		
		FixButton lammoiLuongNhanVien = new FixButton("Làm Mới");
		lammoiLuongNhanVien.setIcon(new ImageIcon(TabNhanVien.class.getResource("/image/pngegg.png")));
		lammoiLuongNhanVien.setForeground(Color.WHITE);
		lammoiLuongNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lammoiLuongNhanVien.setBackground(new Color(0, 158, 15));
		lammoiLuongNhanVien.setBounds(887, 615, 206, 55);
		panel_3.add(lammoiLuongNhanVien);
		
		JScrollPane scrollPaneBangLuong = new JScrollPane();
		scrollPaneBangLuong.setBounds(25, 131, 1316, 462);
		panel_3.add(scrollPaneBangLuong);
		 
 

		modelbangluong=new DefaultTableModel();
		 
		modelbangluong.addColumn("Mã Lương");
		modelbangluong.addColumn("Mã NV ");
		 
		modelbangluong.addColumn("Số ngày làm");
		modelbangluong.addColumn("Số giờ tăng ca");
		modelbangluong.addColumn("Tiền công");
		modelbangluong.addColumn("Lương tăng ca");
		modelbangluong.addColumn("Phụ Cấp");
		modelbangluong.addColumn("Tiền Phạt");
		modelbangluong.addColumn("Tổng Lương Tháng");
 
		
		tableluongnhanvien = new JTable(modelbangluong);
		 
		scrollPaneBangLuong.setViewportView(tableluongnhanvien);
		
		
		
		thangLuongNhanVien.setBounds(146, 75, 78, 22);
		panel_3.add(thangLuongNhanVien);
		thangLuongNhanVien.addItem("1");
		thangLuongNhanVien.addItem("2");
		thangLuongNhanVien.addItem("3");
		thangLuongNhanVien.addItem("4");
		thangLuongNhanVien.addItem("5");
		thangLuongNhanVien.addItem("6");
		thangLuongNhanVien.addItem("7");
		thangLuongNhanVien.addItem("8");
		thangLuongNhanVien.addItem("9");
		thangLuongNhanVien.addItem("10");
		thangLuongNhanVien.addItem("11");
		thangLuongNhanVien.addItem("12");

		LocalDate locallaythangnamhientai = LocalDate.now();
		int thang = locallaythangnamhientai.getMonthValue();
		 
		thangLuongNhanVien.setSelectedItem(thang+"");
		
		namLuongNhanVien.setBounds(367, 75, 78, 22);
		panel_3.add(namLuongNhanVien);
		
		 int namHienTai = LocalDate.now().getYear();

	        // Đặt giá trị mặc định cho `JComboBox` là năm hiện tại
	        namLuongNhanVien.addItem(String.valueOf(namHienTai));

		namLuongNhanVien.addItem("2022");
		namLuongNhanVien.addItem("2023");
		namLuongNhanVien.addItem("2024");
		namLuongNhanVien.addItem("2025");
		namLuongNhanVien.addItem("2026");
		namLuongNhanVien.addItem("2027");
		
		FixButton fxbtnTnhLng = new FixButton("Lọc");
		fxbtnTnhLng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 // Xử lý sự kiện ở đây
//	            int selectedRow = tablecongdoanchamcong.getSelectedRow();
//	            if (selectedRow != -1) {
//
//	         		String macua1congnhan= modeldcongdoanchamcong.getValueAt(selectedRow,  1).toString() ;
//	         		blcndao.updateBangLuong( macua1congnhan);
//	         		}
//	            else {
	            	
	            	int rowCount = modelbangluong.getRowCount();
	            	System.out.println(rowCount);
	            	for (int row = 0; row < rowCount; row++) {
	            		
	            		try {
							service.updateBangLuong( modelbangluong.getValueAt(row, 1).toString());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	            	}
//	            }


				  // load lại bảng lương sau khi chấm công
	            	LocalDate currentDate = LocalDate.now();
			        int nam = currentDate.getYear();
			        int thang = currentDate.getMonthValue();
					
					 List<BangLuongNhanVien> locbangluong = null;
					try {
						locbangluong = service.getchamcongtheongay(thang,nam);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						int i=1;
						modelbangluong.getDataVector().removeAllElements();
						for (BangLuongNhanVien nv : locbangluong) {
							String luongtangca = String.format("%.0f", nv.getLuongtangca());
//							String luongsanpham = String.format("%.0f", nv.getLuongsanpham());
							String tongluong = String.format("%.0f", nv.getTongluong());
							
							NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
							Object []obj= {nv.getMaBangLuongNhanVien(),nv.getNV().getMaNV(),nv.getSongaycong(),nv.getSoGiotangca(),currencyFormatter.format(nv.getTiencong()),currencyFormatter.format(nv.getLuongtangca()),nv.getTroCap(),nv.getPhat(),currencyFormatter.format(nv.getTongluong())};
							 
							modelbangluong.addRow(obj);

						}
						tableluongnhanvien.setModel(modelbangluong);
			}
		});
		fxbtnTnhLng.setText("Tính Lương");
		fxbtnTnhLng.setForeground(Color.WHITE);
		fxbtnTnhLng.setFont(new Font("Tahoma", Font.BOLD, 18));
		fxbtnTnhLng.setBackground(new Color(69, 129, 142));
		fxbtnTnhLng.setBounds(1160, 615, 181, 55);
		panel_3.add(fxbtnTnhLng);
		
		FixButton fxbtnInPdf = new FixButton("Lọc");
		fxbtnInPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 

				int zoom = 150; // Giá trị zoom mong muốn (%)
				int newWidth = (int) (table.getWidth() * (zoom / 100.0));
				int newHeight = (int) (table.getHeight() * (zoom / 100.0));

				table.setPreferredScrollableViewportSize(new Dimension(newWidth, newHeight));

				// Tiếp theo, bạn có thể tiến hành in bảng
				// code in bảng ở đây

		        // Print the table
				// Create header format
		        MessageFormat headerFormat = new MessageFormat("Bảng Lương Nhân Viên tháng 12" + "\n");

		        // Create footer format
		        MessageFormat footerFormat = new MessageFormat("Page {0}");

		        // Set header and footer alignment
//		        headerFormat.setAlignment(MessageFormat.CENTER);
//		        footerFormat.setAlignment(MessageFormat.RIGHT);

		        // Create a PrinterJob instance
		        PrinterJob printerJob = PrinterJob.getPrinterJob();

		        // Set the printable object as the JTable
		        printerJob.setPrintable(table.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat));

		        // Set the page format to match the screen size
		        PageFormat pageFormat = printerJob.defaultPage();
		        pageFormat.setOrientation(PageFormat.PORTRAIT);

		        // Set the zoom to 100%
		        
		        printerJob.setPrintable(table.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat), pageFormat);
		     

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
		fxbtnInPdf.setBounds(1135, 37, 206, 55);
		panel_3.add(fxbtnInPdf);
		
		
		
		blnv =  new BangLuongNhanVienDAO();	        
		int thangcombo =Integer.parseInt((String) thangLuongNhanVien.getSelectedItem());
		int namcombo =Integer.parseInt((String) namLuongNhanVien.getSelectedItem());
        List<BangLuongNhanVien> bangluog=service.getchamcongtheongay(thangcombo,namcombo);
		int i11=1;
		for (BangLuongNhanVien nv : bangluog) {
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			
			Object []obj= {nv.getMaBangLuongNhanVien(),nv.getNV().getMaNV(),nv.getSongaycong(),nv.getSoGiotangca(),currencyFormatter.format(nv.getTiencong()),currencyFormatter.format(nv.getLuongtangca()),nv.getTroCap(),nv.getPhat(),currencyFormatter.format(nv.getTongluong())};
			i11++;
			modelbangluong.addRow(obj);
			
			
		}
		
		
		
		//ẩn số giờ tăng ca
		
		tableluongnhanvien.getColumnModel().getColumn(3).setMinWidth(0);
		tableluongnhanvien.getColumnModel().getColumn(3).setMaxWidth(0);
		
		tableluongnhanvien.getColumnModel().getColumn(5).setMinWidth(0);
	  	tableluongnhanvien.getColumnModel().getColumn(5).setMaxWidth(0);
	       
		//load nhân viên theo ngày tháng
		locluongNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int thang =Integer.parseInt((String) thangLuongNhanVien.getSelectedItem());
				int nam =Integer.parseInt((String) namLuongNhanVien.getSelectedItem());
				  List<BangLuongNhanVien> locbangluong = null;
				try {
					locbangluong = service.getchamcongtheongay(thang,nam);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					modelbangluong.getDataVector().removeAllElements();
					for (BangLuongNhanVien nv : locbangluong) {
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
						Object []obj= {nv.getMaBangLuongNhanVien(),nv.getNV().getMaNV(),nv.getSongaycong(),nv.getSoGiotangca(),currencyFormatter.format(nv.getTiencong()),currencyFormatter.format(nv.getLuongtangca()),currencyFormatter.format(nv.getTroCap()),currencyFormatter.format(nv.getPhat()),currencyFormatter.format(nv.getTongluong())};
						i++;
						modelbangluong.addRow(obj);
						
						
					}
					tableluongnhanvien.setModel(modelbangluong);
					
				System.out.println("e loc ne");
				System.out.println(thang+"");
				System.out.println(nam+"");
				int rowCount = modelbangluong.getRowCount();
				System.out.println("so dong loc bang :"+rowCount+"");
				if(rowCount==0) {
					JOptionPane.showMessageDialog(null, "Error: Khong tim thay bang luong nay");
					modelbangluong.getDataVector().removeAllElements();
					 Object []obj= {"","","",""};
					 
					 modelbangluong.addRow(obj);
					 	if (modelbangluong.getRowCount() > 0) {
					 		modelbangluong.removeRow(0);
					}
				}
			}
		});
		
		// làm mới bảng nhân viên về tháng ,năm hiện tại
		lammoiLuongNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocalDate currentDate = LocalDate.now();
		        int nam = currentDate.getYear();
		        int thang = currentDate.getMonthValue();
				
				 List<BangLuongNhanVien> locbangluong = null;
				try {
					locbangluong = service.getchamcongtheongay(thang,nam);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					modelbangluong.getDataVector().removeAllElements();
					for (BangLuongNhanVien nv : locbangluong) {
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
						Object []obj= {nv.getMaBangLuongNhanVien(),nv.getNV().getMaNV(),nv.getSongaycong(),nv.getSoGiotangca(),currencyFormatter.format(nv.getTiencong()),currencyFormatter.format(nv.getLuongtangca()),currencyFormatter.format(nv.getTroCap()),currencyFormatter.format(nv.getPhat()),currencyFormatter.format(nv.getTongluong())};
						i++;
						modelbangluong.addRow(obj);

					}
					tableluongnhanvien.setModel(modelbangluong);
					
					
					
					LocalDate locallaythangnamhientai = LocalDate.now();
					int thangzz = locallaythangnamhientai.getMonthValue();
					 
					thangLuongNhanVien.setSelectedItem(thangzz+"");
					
					
					 int namHienTai = LocalDate.now().getYear();

				        // Đặt giá trị mặc định cho `JComboBox` là năm hiện tại
				        namLuongNhanVien.addItem(String.valueOf(namHienTai));

			}
		});
		
		 int countbl = bangluog.size();
	        System.out.println(countbl);
	        
	        
	        btnchamCongNhanVien.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					Date date = datengaychamCongNhanVien.getDate();

					// Chuyển đổi Date thành LocalDate
					Instant instant = date.toInstant();
					ZoneId zoneId = ZoneId.systemDefault(); // Hoặc bạn có thể chọn ZoneId cụ thể
					LocalDate localDatengaycham = instant.atZone(zoneId).toLocalDate();
					
					int rowCount = modelchamcong.getRowCount();
					System.out.println(rowCount);
					for (int row = 0; row < rowCount; row++) {
					    String maChamCong = (String) modelchamcong.getValueAt(row, 0); // Lấy mã chấm công từ cột 1
					   
					   //lay hien dien voi co phep khong phep
					    int hiendien=0;
					    
					    Boolean hiendiencheck = (Boolean) modelchamcong.getValueAt(row, 4);  
					    //lấy ca làm
					    String calam =""; 
					    Boolean cophepkhongphepcheck = (Boolean) modelchamcong.getValueAt(row, 6);
					    
//					    ủa sao nó chạy dc z trời
					    if (hiendiencheck == null  ) {
				        	calam="";
				            hiendien = 0;
				        }
		        			
				        else if (hiendiencheck == true  ) {
				            hiendien = 2;
//				        	 
				              calam = modelchamcong.getValueAt(row, 5).toString();
				        }
				        else {     	 
//				        	if(cophepkhongphepcheck == true) {
				        		
				        		hiendien = 1;
				        		calam="";
				        		
//				        	}
//				        	else {
//				        		hiendien = 0;
//				        		calam="";
//				        		
//				        	}
				        }
					    //
					    
					    //lấy ca làm
					  
					    
					    
					    //
					    
					    //lấy số giờ tăng ca
					    String stringValue = modelchamcong.getValueAt(row, 7).toString();
					    int sogiotangca = 0;
					    
					    
					    if(stringValue=="") {
					    	sogiotangca=0;
					    }
					    else {
					    	   sogiotangca = Integer.parseInt(stringValue);
					    }
					    
					    //lấy ghi chú
					    String ghichu =(String) modelchamcong.getValueAt(row, 8);
					    //
					    
//					    LocalTime thoigianden=(LocalTime) modelchamcong.getValueAt(row, 9);
					 // Đặt giá trị mặc định cho thoigianden và thoigiandi
					    LocalTime thoigianden = null;
					    LocalTime thoigiandi = null;

					    // Lấy giá trị từ bảng (TableModel)
					    Object valueThoigianden = modelchamcong.getValueAt(row, 9);
					    Object valueThoigiandi = modelchamcong.getValueAt(row, 10);

					    // Kiểm tra và chuyển đổi giá trị thành LocalTime nếu không rỗng hoặc chỉ chứa khoảng trắng
					    if (valueThoigianden instanceof LocalTime) {
					        thoigianden = (LocalTime) valueThoigianden;
					    } else if (valueThoigianden instanceof String && !((String) valueThoigianden).trim().isEmpty()) {
					        thoigianden = LocalTime.parse((String) valueThoigianden);
					    }

					    if (valueThoigiandi instanceof LocalTime) {
					        thoigiandi = (LocalTime) valueThoigiandi;
					    } else if (valueThoigiandi instanceof String && !((String) valueThoigiandi).trim().isEmpty()) {
					        thoigiandi = LocalTime.parse((String) valueThoigiandi);
					    }

					    // Kiểm tra nếu chuỗi là rỗng hoặc chỉ chứa khoảng trắng, gán là null
					    if (thoigianden != null && valueThoigianden instanceof String && ((String) valueThoigianden).trim().isEmpty()) {
					        thoigianden = null;
					    }

					    if (thoigiandi != null && valueThoigiandi instanceof String && ((String) valueThoigiandi).trim().isEmpty()) {
					        thoigiandi = null;
					    }

					    
					    Boolean ditre = (Boolean) modelchamcong.getValueAt(row, 11);
					    int ditreso=0;
					    if(ditre==null) {
					    	ditreso=0;
					    }else if(ditre==true){
					    	ditreso=1;
					    }
					    
					    
					    
					    LocalDateTime dateTimeden=null;
					    if (thoigianden != null) {
					    	dateTimeden = localDatengaycham.atTime(thoigianden);
					    } else {
					    	dateTimeden = localDatengaycham.atStartOfDay();
					    }

					    LocalDateTime dateTimedi=null;
					    if (thoigiandi != null) {
					    	dateTimedi = localDatengaycham.atTime(thoigiandi);
					    } else {
					    	dateTimedi = localDatengaycham.atStartOfDay();
					    }
					    
					    
					    System.out.println(localDatengaycham);
					    System.out.println(dateTimeden);
					    System.out.println(localDatengaycham);
					    System.out.println(dateTimedi);
					    // Gọi hàm update với các giá trị lấy được
					    try {
							service.updateChamCong( hiendien, calam, ghichu,sogiotangca, dateTimeden,dateTimedi,ditreso,maChamCong,localDatengaycham);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    try {
							service.updateBangLuong( modelchamcong.getValueAt(row, 1).toString());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    //
					    
					    
					    LocalDate currentDate = LocalDate.now();
					   
				        int nam = currentDate.getYear();
				        int thang = currentDate.getMonthValue();
						
						 List<BangLuongNhanVien> locbangluong = null;
						try {
							locbangluong = service.getchamcongtheongay(thang,nam);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							int i=1;
							modelbangluong.getDataVector().removeAllElements();
							for (BangLuongNhanVien nv : locbangluong) {
								Object []obj= {nv.getMaBangLuongNhanVien(),nv.getNV().getMaNV(),nv.getSongaycong(),nv.getSoGiotangca(),nv.getTiencong(),nv.getLuongtangca(),nv.getTroCap(),nv.getPhat(),nv.getTongluong()};
								i++;
								modelbangluong.addRow(obj);

							}
							tableluongnhanvien.setModel(modelbangluong);
					    //
					     
					}
					try {
						loadtblChamTay();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Cham cong thanh cong");
					
				}
				
			});
	        
	        
	        
	        //sự kiện khi bấm vào một dòng trong bảng nhân viên thì sẽ hiển thị bảng chấm công tương ứng
	        ListSelectionModel selectionModel = tableluongnhanvien.getSelectionModel();
	        selectionModel.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    // Lấy dòng được chọn
	                    int selectedRow = tableluongnhanvien.getSelectedRow();

	                    
	                	modelbangchamcongtheoma = new DefaultTableModel( );
	                	modelbangchamcongtheoma.addColumn("Mã Chấm Công Nhân Viên");
	                	modelbangchamcongtheoma.addColumn("Mã NV");
	                	modelbangchamcongtheoma.addColumn("Họ Tên ");
	                	modelbangchamcongtheoma.addColumn("Ngày Chấm");
	                	modelbangchamcongtheoma.addColumn("Hiện diện");
	                	modelbangchamcongtheoma.addColumn("Ca Làm");
	                	modelbangchamcongtheoma.addColumn("Có phép / Không phép");
	                	modelbangchamcongtheoma.addColumn("Lương trong ngày");
	                	modelbangchamcongtheoma.addColumn("Ghi Chú");
	                	modelbangchamcongtheoma.addColumn("Đi trễ");
	                   JTable bangchamcongtheoma = new JTable(modelbangchamcongtheoma) {
	                       @Override
	                       public Class<?> getColumnClass(int column) {
	                           if (column == 4) {
	                               return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
	                           }
	                           if (column == 6) {
	                               return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
	                           }

	                           if (column == 9) {
	                               return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
	                           }
	                           return super.getColumnClass(column);
	                       }
	                   };
	                   JCheckBox hiendien= new JCheckBox();
	                   JCheckBox cophepkhongphep= new JCheckBox();
	                   JCheckBox ditre= new JCheckBox();
	                   // Đặt kiểu dữ liệu cho cột Select
	                   bangchamcongtheoma.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(hiendien));
	                   bangchamcongtheoma.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cophepkhongphep));
	                   bangchamcongtheoma.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(ditre));
	                   
	                   hiendien.addActionListener(new ActionListener() {
	                       @Override
	                       public void actionPerformed(ActionEvent e) {
	                           // Kiểm tra xem hiendien có được tích không
	                           if (hiendien.isSelected()==true) {
	                               // Nếu hiendien đã được tích, vô hiệu hóa cophepkhongphep
	                        	   cophepkhongphep.setEnabled(false);
	                               cophepkhongphep.setSelected(false);
	                           } else {
	                               // Nếu hiendien không được tích, kích hoạt cophepkhongphep
	                               cophepkhongphep.setEnabled(true);
	                           }
	                       }
	                   });
	                   
	                   cophepkhongphep.addActionListener(new ActionListener() {
	                       @Override
	                       public void actionPerformed(ActionEvent e) {
	                           // Kiểm tra xem hiendien có được tích không
	                           if (cophepkhongphep.isSelected()) {
	                               // Nếu hiendien đã được tích, vô hiệu hóa cophepkhongphep
	                           	
	                           	hiendien.setSelected(false);
	                           } else {
	                               // Nếu hiendien không được tích, kích hoạt cophepkhongphep
	                           	hiendien.setEnabled(true);
	                           }
	                       }
	                   });
	                   
	                   
	                // Create a JComboBox with your options
	                   JComboBox comboBoxCaLam = new JComboBox();
	                   comboBoxCaLam.addItem("Nguyên buổi");
	                   comboBoxCaLam.addItem("Nửa Buổi");
	                
	                   // Set the default value to "Sáng"
	                   comboBoxCaLam.setSelectedItem("Nguyên buổi");

	                   // Set the JComboBox as the cell editor for the "Ca Làm" column
	                   TableColumn caLamCol = bangchamcongtheoma.getColumnModel().getColumn(5);
	                   caLamCol.setCellEditor(new DefaultCellEditor(comboBoxCaLam));

	                   
	                   JComboBox giotangca = new JComboBox();
	                   giotangca.addItem("0");
	                   giotangca.addItem("1");
	                   giotangca.addItem("2");
	                   giotangca.addItem("3");
	                   giotangca.addItem("4");
	                
	                   // Set the default value to "Sáng"
	                   giotangca.setSelectedItem("0");
	                   giotangca.setSelectedItem((Object) "0");

//	                    Set the JComboBox as the cell editor for the "Ca Làm" column
//	                   TableColumn giotangcacolumn = bangchamcongtheoma.getColumnModel().getColumn(7);
//	                   giotangcacolumn.setCellEditor(new DefaultCellEditor(giotangca));
//	                   
	                   
	                   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	                   centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Đặt căn giữa cho nội dung cột

	                   bangchamcongtheoma.getColumnModel().getColumn( 0).setCellRenderer(centerRenderer); // Áp dụng trình điều khiển tùy chỉnh cho cột thứ columnIndex
	                   bangchamcongtheoma.getColumnModel().getColumn( 1).setCellRenderer(centerRenderer);
	                   bangchamcongtheoma.getColumnModel().getColumn( 2).setCellRenderer(centerRenderer);
	                   bangchamcongtheoma.getColumnModel().getColumn( 3).setCellRenderer(centerRenderer);
	                   bangchamcongtheoma.getColumnModel().getColumn( 5).setCellRenderer(centerRenderer);
	                   bangchamcongtheoma.getColumnModel().getColumn( 7).setCellRenderer(centerRenderer);
//	                   bangchamcongtheoma.getColumnModel().getColumn( 8).setCellRenderer(centerRenderer);
	                    
	                    
	                    // Kiểm tra nếu có dòng được chọn
	                   String ma="";
	                   String ten ="";
	                    if (selectedRow >= 0) {
	                    	 
	                            // Tạo JTable mới với 9 cột
	                            String[] newColumnNames = {"Mã Chấm Công", "Mã NV", "Họ Tên", "Ngày Chấm", "Hiện diện", "Ca Làm", "Có phép / không phép",  "Lương trong ngày","Ghi Chú","Di tre"};
	                         
	                            
	                            
	                            //
	                            modelbangluong.getValueAt(selectedRow, 1).toString();
	                            int thang = Integer.parseInt(thangLuongNhanVien.getSelectedItem().toString());
	                            int nam = Integer.parseInt(namLuongNhanVien.getSelectedItem().toString());

	                            List<ChamCongNhanVien> dsChamCongNVtheoma = null;
								try {
									dsChamCongNVtheoma = service.getchamcongtheomaNV(modelbangluong.getValueAt(selectedRow, 1).toString(),thang,nam );
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                			int i1=1;
	                			for (ChamCongNhanVien nv : dsChamCongNVtheoma) {
//	                				Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham()};
	                				boolean codilam=true;
	                				boolean nghilam=true;
	                				Boolean coditre = null;
	                				if(nv.getHiendien()==2) {
	                					 codilam=true;
	                					 nghilam=false;
	                				}
	                				else if (nv.getHiendien()==1) {
	                					 codilam=false;
	                					 nghilam=true;
	                				}
	                				else {
	                					codilam=false;
	                					 nghilam=false;
	                				}
	                				
	                				if(nv.getDitre()==1) {
	                					coditre=true;
	                				}
	                				else {
	                					coditre=null;
	                				}
	                				double luongngay =0;
	                				if (!nv.getGhiChu().equals("")) {
	                				    luongngay = 0;
	                				} else {
	                				    if (codilam == true) {
	                				        luongngay = 4500000 / 26;
	                				    } else {
	                				        luongngay = 0;
	                				        
	                				    }
	                				}

//	                				double amount = Double.parseDouble(luongngay);

	                				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	                				
	                				
	                				Object []newData= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham(),codilam,nv.getCaLam(),nghilam,currencyFormatter.format(luongngay),nv.getGhiChu(),coditre};
	                				i1++;
	                				 modelbangchamcongtheoma.addRow(newData);
	                				System.out.println(nv.getNV().getTenNV());
	                				
	                				ma=nv.getNV().getMaNV();
	                				ten =nv.getNV().getTenNV();
	                			}
	                            
	                            
	                            //

	                            // Đặt JTable mới vào JScrollPane
	                			 JScrollPane scroolchamcongtrongbangluong = new JScrollPane(bangchamcongtheoma);
	                		        JLabel hello = new JLabel("Bảng Lương Nhân Viên Chi Tiết");
	                		        JLabel manv = new JLabel("Mã Nhân viên :"+ma);
	                		        JLabel tennv = new JLabel("Tên Nhân Viên :"+ten);
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
		                					String headerText = "Bảng Lương Chi tiết Nhân Viên\n" +
		                		                    "Mã Nhân Viên: " + ma + "\n" +
		                		                    "Tên Nhân Viên: " + ten + "\n";

		                		MessageFormat headerFormat = new MessageFormat(headerText);

		                		// ... The rest of your code ...

		                			        // Create footer format
		                			        MessageFormat footerFormat = new MessageFormat("Page {0}");

		                			        // Set header and footer alignment
//		                			        headerFormat.setAlignment(MessageFormat.CENTER);
//		                			        footerFormat.setAlignment(MessageFormat.RIGHT);

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
		                            	tableluongnhanvien.clearSelection();
		                            }
	                 // Tạo một JDialog mới và hiển thị blct bên trong nó
	                     
	                    
 
	                }
	            }}
	        });
	        
	        //bấm vào một dòng trpn
	        
	         // Tạo frame ở ngoài phương thức
//
//	        ListSelectionModel selectionModel1 = tableluongnhanvien.getSelectionModel();
//	        selectionModel1.addListSelectionListener(new ListSelectionListener() {
//	            @Override
//	            public void valueChanged(ListSelectionEvent e) {
//	                if (!e.getValueIsAdjusting()) {
//	                    int selectedRow = tableluongnhanvien.getSelectedRow();
//
//	                    if (selectedRow != -1) {
//	                        int thang = Integer.parseInt(thangLuongNhanVien.getSelectedItem().toString());
//	                        int nam = Integer.parseInt(namLuongNhanVien.getSelectedItem().toString());
//
//	                        // Lấy reference của blct đang hiển thị
//	                        blct n = new blct();
//
//	                        List<BangLuongNhanVien> dsbltheoma = blnv.getchamcongtheongayTheoTungNhanVien(modelbangluong.getValueAt(selectedRow, 1).toString(), thang, nam);
//
//	                        if (!dsbltheoma.isEmpty()) {
//	                            BangLuongNhanVien nv = dsbltheoma.get(0); // Lấy một đối tượng từ danh sách
//	                            // Cập nhật dữ liệu trong blct
//
//	            				n.blct_manv.setText(nv.getNV().getMaNV());
//	            				n.blct_tennc.setText(nv.getNV().getMaNV());
//	            				n.blct_songaycong.setText(nv.getSongaycong()+"");
//	            				n.blct_songaynghicophep.setText("");
//	            				 
// 
//	            				n.blct_phat.setText(nv.getPhat()+"");
//	            				n.blct_bhxh.setText(nv.getBhxh()+"");
//	            				n.blct_phucap.setText(nv.getTroCap()+"");
//	            				n.blct_tiencong.setText(nv.getTiencong()+"");
//
//	            				n.blct_tongluog.setText(nv.getTongluong()+"");
//	            				n.blct_songaynghicophep.setText(nv.getSoNgayNghiCoPhep()+"");
//	            				n.blct_manvsongaynghikhongphep.setText(nv.getSoNgayNghiKhongPhep()+"");
//	     
//	            				 
//	                            // Tạo một cửa sổ dialog
//	                            JOptionPane optionPane = new JOptionPane(n, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
//	                            JDialog dialog = optionPane.createDialog("Bảng Lương chi tiết");
//
//	                            // Hiển thị dialog và chờ người dùng thực hiện hành động
//	                            dialog.setBounds(10, 11, 1032,1032);
//	                            dialog.setLocationRelativeTo(null);
//	                            dialog.setVisible(true);
//
////	                            n.setVisible(false); // Ẩn frame ban đầu
//	                            
//	                            // Lấy giá trị khi người dùng đóng dialog
//	                            Object selectedValue = optionPane.getValue();
//	                            if (selectedValue != null) {
//	                                int option = (int) selectedValue;
//	                                if (option == JOptionPane.OK_OPTION) {
//	                                    // Xử lý khi người dùng chọn "OK"
//	                                    // Thực hiện các hành động cần thiết
//	                                	tableluongnhanvien.clearSelection();
//	                                } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
//	                                    // Xử lý khi người dùng chọn "Cancel" hoặc đóng dialog
//	                                    // Thực hiện các hành động cần thiết (nếu có)
//	                                }
//	                            }
//	                        }
//	                        
//	                       
////                            n.setPreferredSize(new Dimension(1289, 461));  
//	                    }
//	                }
//	            }
//	         });

	        

	        

	        

	       
	       
	}
	
	private boolean checkRegexNhanVien() {
		 
		 
		String tenNV = tennv.getText().toString();
		String sdt = sdtnv.getText().toString();
		String email=emailnv.getText();
		String cccd = cmndnv.getText().toString();
		String diachi = diachinv.getText().toString();
		String troCap= trocapnv.getText().toString();
		if (!(tenNV.length() > 0 && tenNV.matches("^[\\p{Lu}][\\p{L}]+(\\s[\\p{Lu}][\\p{L}]+){2,}$"))) {
		    JOptionPane.showMessageDialog(this, "Lỗi: Viết đủ họ tên, chữ cái đầu viết hoa và không chứa số, kí tự đặc biệt. Tối thiểu 3 từ.");
		    return false;
		}


		
		if(!(sdt.length() > 0 && sdt.matches("^0[0-9]{9,10}$"))) {
			JOptionPane.showMessageDialog(this, "Error: Số điện thoại phải có 10 11 số và bắt đầu là số 0");
			return false;
		}
		
		if (!(email.length() > 0 && email.matches("^[a-zA-Z0-9_+&*-]+(\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$"))) {
		    JOptionPane.showMessageDialog(this, "Lỗi: Email phải theo định dạng (tenemail)@gmail.com");
		    return false;
		}

		
		
		
		if(!(cccd.length() > 0 && cccd.matches("^[0-9]{9,12}$"))) {
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

		
		Date dateengaysinh= datengaysinhnv.getDate();
		
		
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
	public void loadtbl() throws RemoteException {
		Registry registry = LocateRegistry.getRegistry("localhost", 1091);

        // Lấy đối tượng từ registry thông qua tên đăng ký
        QuanLyLuongService service = null;
		try {
			service = (QuanLyLuongService) registry.lookup("QuanLyLuongService");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate ngayHienTai = LocalDate.now();
		List<ChamCongNhanVien> dsChamCongNVhomnay=service.getchamcongtheongay(ngayHienTai );
		int i1=1;
		modelchamcong.getDataVector().removeAllElements();
		for (ChamCongNhanVien nv : dsChamCongNVhomnay) {
//			Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham()};
			Boolean codilam = null;
			Boolean nghilam = null;
			Boolean coditre=null;
			
			if (nv.getHiendien() == 2) {
			    codilam = true;
			    nghilam = null;
			} else if (nv.getHiendien() == 1) {
			    codilam = null;
			    nghilam = true;
			} else if (nv.getHiendien() == 0) {
			    codilam = null;
			    nghilam = null;
			}
			
			if(nv.getDitre()==1) {
				coditre=true;
			}
			else {
				coditre=null;
			}
			Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham(),codilam,nv.getCaLam(),nghilam,nv.getSogioTangca(),nv.getGhiChu(),nv.getThoigianden(),nv.getThoigiandi(),coditre};
			i1++;
			modelchamcong.addRow(obj);
			
		}
    }

	
	
	public void loadtblChamTay() throws RemoteException {
		Registry registry = LocateRegistry.getRegistry("localhost", 1091);

        // Lấy đối tượng từ registry thông qua tên đăng ký
        QuanLyLuongService service = null;
		try {
			service = (QuanLyLuongService) registry.lookup("QuanLyLuongService");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		JDateChooser datengaychamCongNhanVien
//		LocalDate ngayHienTai = LocalDate.now();
		
		Date date = datengaychamCongNhanVien.getDate();

		// Chuyển đổi Date thành LocalDate
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault(); // Hoặc bạn có thể chọn ZoneId cụ thể
		LocalDate localDatengaycham = instant.atZone(zoneId).toLocalDate();
		
		List<ChamCongNhanVien> dsChamCongNVhomnay=service.getchamcongtheongay(localDatengaycham );
		int i1=1;
		modelchamcong.getDataVector().removeAllElements();
		for (ChamCongNhanVien nv : dsChamCongNVhomnay) {
//			Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham()};
			Boolean codilam = null;
			Boolean nghilam = null;
			Boolean coditre=null;
			
			if (nv.getHiendien() == 2) {
			    codilam = true;
			    nghilam = null;
			} else if (nv.getHiendien() == 1) {
			    codilam = null;
			    nghilam = true;
			} else if (nv.getHiendien() == 0) {
			    codilam = null;
			    nghilam = null;
			}
			
			if(nv.getDitre()==1) {
				coditre=true;
			}
			else {
				coditre=null;
			}
			Object []obj= {nv.getMaChamCongNhanVien(),nv.getNV().getMaNV(),nv.getNV().getTenNV(),nv.getNgayCham(),codilam,nv.getCaLam(),nghilam,nv.getSogioTangca(),nv.getGhiChu(),nv.getThoigianden(),nv.getThoigiandi(),coditre};
			i1++;
			modelchamcong.addRow(obj);
			
		}
    }
	@Override
	public void onQRCodeRead(String qrCode) {
		// TODO Auto-generated method stub
//		if (!qrCode.equals(previousQRCode)) {
//	        // Mã QR lần này khác với lần trước, thực hiện xử lý
//	        // ...
//	        
//	        // Lưu giá trị mã QR hiện tại làm giá trị lần quét trước
//	        previousQRCode = qrCode;
//	    } else {
//	        // Mã QR lần này giống với lần trước, không thực hiện xử lý
//	    }
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry("localhost", 1091);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Lấy đối tượng từ registry thông qua tên đăng ký
        QuanLyLuongService service = null;
		try {
			service = (QuanLyLuongService) registry.lookup("QuanLyLuongService");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate locallaythangnamhientai = LocalDate.now();
        ccnv=new ChamCongNhanVienDAO();
        List<ChamCongNhanVien> list = null;
		try {
			list = service.getchamcongdahiendien(locallaythangnamhientai);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        int a=0;
        for (ChamCongNhanVien chamCongNhanVien : list) {
        	if(qrCode.equals(chamCongNhanVien.getNV().getMaNV())&&chamCongNhanVien.getThoigianden()!=null&&chamCongNhanVien.getThoigiandi()==null) {
        		a=1;
        	}else if(qrCode.equals(chamCongNhanVien.getNV().getMaNV())&&chamCongNhanVien.getThoigianden()!=null&&chamCongNhanVien.getThoigiandi()!=null){
        		a=2;
        	}
		}
        if(a==0) {
        	try {
				service.updateChamCong1(2, qrCode, locallaythangnamhientai);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				service.updateditre(qrCode);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		 System.out.println("Điểm danh thành công");
        }else if(a==1){
        	try {
				service.updateChamCong2(qrCode, locallaythangnamhientai);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				service.updatecalamvaghichu(qrCode);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("Ra về thành công");
        }
        try {
			loadtbl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

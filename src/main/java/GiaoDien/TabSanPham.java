package GiaoDien;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import Dao.BangLuongNhanVienDAO;
import Dao.ChamCongCongNhanDAO;
import Dao.ChamCongNhanVienDAO;
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
import Entity.DonHang;
import Entity.NhanVien;
import Entity.SanPham;
import services.QuanLyLuongService;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;

public class TabSanPham extends JPanel {
	private JTable table;
	
 
	private ChamCongNhanVienDAO ccnvdao= new ChamCongNhanVienDAO();
	private BangLuongNhanVienDAO blnv= new BangLuongNhanVienDAO();
	private JTable tablenhanvien;
	private DefaultTableModel modelquanlysanpham;
	private DefaultTableModel modeldssanphamthemcongdoan;
	private DefaultTableModel modelcongdoan;
	private DefaultTableModel modelphanchiacongdoan;
	private DefaultTableModel modeldssanphamphancong;
	private DefaultTableModel modelcongdoanphancong;
	private DefaultTableModel modeldscongnhanphancong;
	private DefaultTableModel modeldssanphamDonHang;
	private DefaultTableModel modeldsDonhang;
	private String linkHinh="";
	
	private bangPhanCongDAO bpcdao= new bangPhanCongDAO();
	private CongDoanDAO cddao= new CongDoanDAO();
	private sanPhamDAO spdao= new sanPhamDAO();
	private nhanVienDAO nvdao= new nhanVienDAO();
	private congNhanDAO cndao= new congNhanDAO();
	private donHangDao dhdao= new donHangDao();
	 
	
	private ChamCongCongNhanDAO cccndao= new ChamCongCongNhanDAO();
	private CongDoanDonHangDAO cddhdao=new CongDoanDonHangDAO();
	private JTextField tensp;
	private JTextField masp;
	private JTextField giasp;
	private JTextField textField_3;
	private JTextField tenspcd;
	private JTextField maspcd;
	private JTextField giaspcd;
	private JTextField macd;
	private JTable tableDSSPThemCongDoan;
	private JTable tableCD;
	private JTable tablesppc;
	private JTable tablecdpc;
	private JTable tabledspc;
	private JTextField tenspdh;
	private JTextField maspdh;
	private JTable tablespDonHang;
	private JTextField slspdh;
	private JTable table_DatHang;
	
	
 
	/**
	 * Create the panel.
	 * @throws RemoteException 
	 */
	public TabSanPham(QuanLyLuongService service) throws RemoteException {
		setLayout(null);
		
//		Database.ConnectDB.getInstance().connect();
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1381, 765);
		add(tabbedPane);
		
		JPanel panelQuanLyNhanVien = new JPanel();
		tabbedPane.addTab("Quản lý sản phầm", null, panelQuanLyNhanVien, null);
		panelQuanLyNhanVien.setLayout(null);
		
		ButtonGroup rdogt=new ButtonGroup();
		
		ButtonGroup rdobxh=new ButtonGroup();
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 203, 1344, 411);
		panelQuanLyNhanVien.add(scrollPane);
		
		
		modelquanlysanpham= new DefaultTableModel();
		modelquanlysanpham.addColumn("STT");
		modelquanlysanpham.addColumn("Mã SP");
		modelquanlysanpham.addColumn("Tên SP ");
		modelquanlysanpham.addColumn("Giá");
		modelquanlysanpham.addColumn("Chất liệu");
		modelquanlysanpham.addColumn("Kiểu dáng");
		modelquanlysanpham.addColumn("Trạng thái");
		table= new JTable(modelquanlysanpham);
 
       
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(13, 152, 204));
		panel.setBounds(10, 21, 1344, 171);
		panelQuanLyNhanVien.add(panel);
		
		JLabel lblNewLabel = new JLabel("Mã sản phẩm");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(232, 29, 93, 22);
		panel.add(lblNewLabel);
		
		JLabel lblTnNhnVin = new JLabel("Tên sản phẩm");
		lblTnNhnVin.setForeground(new Color(255, 255, 255));
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin.setBounds(439, 29, 170, 22);
		panel.add(lblTnNhnVin);
		
		JLabel lblSinThao = new JLabel("Giá");
		lblSinThao.setForeground(new Color(255, 255, 255));
		lblSinThao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSinThao.setBounds(232, 93, 93, 22);
		panel.add(lblSinThao);
		
		JLabel lblaCh = new JLabel("Chất Liệu");
		lblaCh.setForeground(new Color(255, 255, 255));
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaCh.setBounds(439, 93, 106, 22);
		panel.add(lblaCh);
		
		JLabel lblNgySinh = new JLabel("Kiểu dáng");
		lblNgySinh.setForeground(new Color(255, 255, 255));
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgySinh.setBounds(660, 29, 93, 22);
		panel.add(lblNgySinh);
		
		tensp = new JTextField();
		tensp.setColumns(10);
		tensp.setBounds(439, 62, 170, 20);
		panel.add(tensp);
		
		masp = new JTextField();
		masp.setEditable(false);
		masp.setEnabled(false);
		masp.setColumns(10);
		masp.setBounds(232, 62, 138, 20);
		panel.add(masp);
		
		giasp = new JTextField();
		giasp.setColumns(10);
		giasp.setBounds(232, 126, 138, 20);
		panel.add(giasp);
		
		JComboBox cboClieu = new JComboBox();
		cboClieu.setBounds(442, 126, 135, 22);
		panel.add(cboClieu);
		
		cboClieu.addItem("Da");
		cboClieu.addItem("Vải");
		cboClieu.addItem("Cao su");
		cboClieu.addItem("Chất liệu tổng hợp");
		
		JComboBox cbokieudang = new JComboBox();
		cbokieudang.setBounds(650, 62, 103, 22);
		panel.add(cbokieudang);
		
		cbokieudang.addItem("Boot");
		cbokieudang.addItem("Giày Tây");
		cbokieudang.addItem("Giày thể thao");
		
		JComboBox cbotrangthai = new JComboBox();
		cbotrangthai.setBounds(660, 125, 135, 22);
		panel.add(cbotrangthai);
		
		cbotrangthai.addItem("Đang sản xuất");
		cbotrangthai.addItem("Ngưng sản xuất");
		
		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setForeground(Color.WHITE);
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrngThi.setBounds(660, 93, 93, 22);
		panel.add(lblTrngThi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Thao T\u00E1c", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(new Color(13, 152, 204));
		panel_2.setBounds(10, 625, 1344, 101);
		panelQuanLyNhanVien.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm Theo ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 11, 158, 30);
		panel_2.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(20, 52, 189, 20);
		panel_2.add(textField_3);
		
		
//		làm mới sản phẩm
		FixButton lammoiqlnvBtn = new FixButton("Làm mới");
		lammoiqlnvBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 
				int lastRow = modelquanlysanpham.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
				int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị

				if (lastRow >= 0) {
				    Object value = modelquanlysanpham.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

				    if (value != null) {
				    	
				    	String input = modelquanlysanpham.getValueAt(lastRow, column).toString();  
				    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
				    	String numberPart = input.substring(2); // Lấy phần số "079"
				    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				    	number++; // Cộng 1 vào số
				    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

				    	masp.setText(result);
				    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
				        
				       
				    }
				}
				
				tensp.setText("");
				giasp.setText("");
				
				 List<SanPham> timtheoten = null;
				try {
					timtheoten = service.getAllsanPham( );
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					modelquanlysanpham.getDataVector().removeAllElements();
					 
					 
					for (SanPham nv : timtheoten) {
						String tt="";
						if(nv.getTrangThai()==1) {
							tt="Đang sản xuất";
						}
						else {
							tt="Ngưng sản xuất";
						}
						Object []obj= {i,nv.getMaSp(),nv.getTenSp(),nv.getGiaSP(),nv.getChatLieu(),nv.getKieuDang(),tt};
						i++;
						modelquanlysanpham.addRow(obj);
					 
						
					}
					table.setModel(modelquanlysanpham);
				
				
			}
		});
		lammoiqlnvBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lammoiqlnvBtn.setForeground(Color.WHITE);
		lammoiqlnvBtn.setBackground(new Color(69, 129, 142));
		lammoiqlnvBtn.setBounds(366, 42, 150, 40);
		panel_2.add(lammoiqlnvBtn);
		
		
//		load ds sp
		int i=1;
		for (SanPham sp :  service.getAllsanPham()) {
			String tt="";
			if(sp.getTrangThai()==1) {
				tt="Đang sản xuất";
			}
			else {
				tt="Ngưng sản xuất";
			}
			Object []obj= {i,sp.getMaSp(),sp.getTenSp(),sp.getGiaSP(),sp.getChatLieu(),sp.getKieuDang(),tt};
			i++;
			modelquanlysanpham.addRow(obj);
			
		}
		
		//lấy trị cuối trong cột mã sản phẩm
		int lastRow = modelquanlysanpham.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
		int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị

		if (lastRow >= 0) {
		    Object value = modelquanlysanpham.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

		    if (value != null) {
		    	
		    	String input = modelquanlysanpham.getValueAt(lastRow, column).toString();  
		    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
		    	String numberPart = input.substring(2); // Lấy phần số "079"
		    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
		    	number++; // Cộng 1 vào số
		    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

		    	masp.setText(result);
		    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
		        
		       
		    }
		}
		
		
		//thêm sản phẩm
		FixButton themsanpham = new FixButton("Thêm");
		themsanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = masp.getText().toString();
				String ten  = tensp.getText().toString();
				double gia  = Double.parseDouble(giasp.getText().toString()) ;
				 
				String kieudang = cbokieudang.getSelectedItem().toString();
 ;
				
				String chatlieu = (String) cboClieu.getSelectedItem();
				String  trangthai=cbotrangthai.getSelectedItem().toString();
				int tt=1;
				
				if(trangthai.equals("Đang sản xuất")) {
					tt=1;
				}
				else {
					tt=0;
				}
				SanPham sp= new SanPham(ten,gia,kieudang,chatlieu,tt);
				System.out.println(sp.toString());
				try {
					service.themDanhSachSP(sp);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				//
				
				int lastRow = modelquanlysanpham.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
				int column = 1; // Chỉ mục của cột bạn muốn lấy giá trị
				String matam="";
				if (lastRow >= 0) {
				    Object value = modelquanlysanpham.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

				    if (value != null) {
				    	
				    	String input = modelquanlysanpham.getValueAt(lastRow, column).toString();  
				    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
				    	String numberPart = input.substring(2); // Lấy phần số "079"
				    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				    	number++; // Cộng 1 vào số
				    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới

				    	masp.setText(result);
				    	matam+=result;
				    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
				        
				       
				    }
				}
				//
				int i=1;
				int rowCount = modelquanlysanpham.getRowCount();
				 Object   []obj = {rowCount+1,matam , ten,  gia, chatlieu, kieudang, trangthai};
				 i++;
				 modelquanlysanpham.addRow(obj);
				
				JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công" );
			}
		});
		
table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row=table.getSelectedRow();
				masp.setText(modelquanlysanpham.getValueAt(row, 1).toString());
				tensp.setText(modelquanlysanpham.getValueAt(row, 2).toString());
				
				giasp.setText(modelquanlysanpham.getValueAt(row, 3).toString());
				
				cboClieu.setSelectedItem(modelquanlysanpham.getValueAt(row, 4).toString());
 
				cbokieudang.setSelectedItem(modelquanlysanpham.getValueAt(row, 5).toString());
				cbotrangthai.setSelectedItem(modelquanlysanpham.getValueAt(row, 6).toString());
		 
			 
				
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

		themsanpham.setFont(new Font("Tahoma", Font.BOLD, 14));
		themsanpham.setForeground(Color.WHITE);
		themsanpham.setBackground(new Color(0, 158, 15));
		themsanpham.setBounds(627, 42, 150, 40);
		panel_2.add(themsanpham);
		
		
		//nút sửa
		FixButton suaqlnspbtn = new FixButton("Sửa");
		suaqlnspbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int row=table.getSelectedRow();
							
							if(row>=0) {
								String ma  = masp.getText().toString();
								 
								String trangthai = cbotrangthai.getSelectedItem().toString();
//								 
								int tt=1;
								if(trangthai=="Đang sản xuất") tt=1;
								else tt=2;
								 
								 
								SanPham sp= new SanPham(trangthai,ma);

//								sa nv= new CongNhan( tenNV,   diachi ,sdt,ma );
								System.out.println(sp.toString());
					 
								
								 JOptionPane optionPane = new JOptionPane(this, JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
		                            JDialog dialog = optionPane.createDialog("Bạn có chắc muốn thay đổi thông tin của sản phẩm này !!");
		                            dialog.setVisible(true);

		                            // Kiểm tra xem người dùng đã đóng hộp thoại
		                            if (optionPane.getValue() != null && (int) optionPane.getValue() == JOptionPane.YES_OPTION) {
		                            	try {
											service.updatesanpham(tt, ma);
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
		                            	
//		                            	blnv.updateHeSoLuong(hslnv1, maNV);
		                            	
//		                            
									table.setValueAt(trangthai, row, 6);
//							 
									JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin thành công!!");
//								 
		                            }
					 
			}}
		});
		suaqlnspbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		suaqlnspbtn.setForeground(Color.WHITE);
		suaqlnspbtn.setBackground(Color.YELLOW);
		suaqlnspbtn.setBounds(872, 42, 150, 40);
		panel_2.add(suaqlnspbtn);
		
		
		//nút hủy
		FixButton huyqlnvbtn = new FixButton("Hủy");
		huyqlnvbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		huyqlnvbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		huyqlnvbtn.setForeground(Color.WHITE);
		huyqlnvbtn.setBackground(new Color(207, 42, 39));
		huyqlnvbtn.setBounds(1108, 42, 150, 40);
		panel_2.add(huyqlnvbtn);
		
		
//		nut tim san pham
		JButton timspbtn = new JButton("");
		timspbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				 List<SanPham> timtheoten = null;
				try {
					timtheoten = service.getAllsanPhamTheoten(textField_3.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					int i=1;
					modelquanlysanpham.getDataVector().removeAllElements();
					 
					 
					for (SanPham nv : timtheoten) {
						String tt="";
						if(nv.getTrangThai()==1) {
							tt="Đang sản xuất";
						}
						else {
							tt="Ngưng sản xuất";
						}
						Object []obj= {i,nv.getMaSp(),nv.getTenSp(),nv.getGiaSP(),nv.getChatLieu(),nv.getKieuDang(),tt};
						i++;
						modelquanlysanpham.addRow(obj);
					 
						
					}
					table.setModel(modelquanlysanpham);
					
					int rowCount = modelquanlysanpham.getRowCount();
					System.out.println("so dong loc bang :"+rowCount+"");
					if(rowCount==0) {
						JOptionPane.showMessageDialog(null, "Error: Không tìm thấy Sản Phẩm có tên "+textField_3.getText());
					}
					
			}
		});
		timspbtn.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/search.png")));
		timspbtn.setBounds(229, 32, 57, 40);
		panel_2.add(timspbtn);
		

//		nvdao = new nhanVienDAO();
//		int i=1;
//		for (NhanVien nv : nvdao.getAllnhanvien()) {
//			Object []obj= {i,nv.getMaNV(),nv.getTenNV(),nv.getNgaybatdaulamViec(),nv.getChucVu(),nv.getSodienthoai(),nv.getDiachi(),nv.getEmail(),nv.getHeSoLuong(),nv.getTroCap(),nv.getTrangthai(),nv.getBhxh(),nv.getTrinhdo(),nv.getNgaySinh(),nv.getHinhanhnhanvien(),nv.getCmnd()};
//			i++;
//			model.addRow(obj);
//			
//		}
//		
		Date ngayHienTai = new Date();
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Tạo Đơn Hàng", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(null);
		panel_3_1.setBackground(new Color(13, 152, 204));
		panel_3_1.setBounds(10, 11, 1356, 715);
		panel_5.add(panel_3_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(649, 100, 103, 22);
		panel_3_1.add(lblNewLabel_3_1);
		
		JLabel lblTnNhnVin_1_1 = new JLabel("Tên sản phẩm");
		lblTnNhnVin_1_1.setForeground(Color.WHITE);
		lblTnNhnVin_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin_1_1.setBounds(890, 100, 116, 22);
		panel_3_1.add(lblTnNhnVin_1_1);
		
		tenspdh = new JTextField();
		tenspdh.setColumns(10);
		tenspdh.setBounds(890, 133, 148, 20);
		panel_3_1.add(tenspdh);
		
		maspdh = new JTextField();
		maspdh.setColumns(10);
		maspdh.setBounds(649, 133, 138, 20);
		panel_3_1.add(maspdh);
		
		JScrollPane scrollPaneDssptaoDonHang = new JScrollPane();
		scrollPaneDssptaoDonHang.setBounds(10, 57, 545, 212);
		panel_3_1.add(scrollPaneDssptaoDonHang);
		
		
		
		modeldssanphamDonHang= new DefaultTableModel();
		modeldssanphamDonHang.addColumn("STT");
		modeldssanphamDonHang.addColumn("Mã SP");
		modeldssanphamDonHang.addColumn("Tên SP ");

		
		 
		
	 
		
		// load ds sản phẩm
		int icd=1;
		for (SanPham sp : service.getAllsanPhamDangsanxuat()) {
			 
			Object []obj= {icd, sp.getMaSp(),sp.getTenSp() };
			icd++;
			modeldssanphamDonHang.addRow(obj);
			
		}
		
		
		
		
		tablespDonHang = new JTable(modeldssanphamDonHang);
		scrollPaneDssptaoDonHang.setViewportView(tablespDonHang);
		
		tablespDonHang.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row=tablespDonHang.getSelectedRow();
				maspdh.setText(modeldssanphamDonHang.getValueAt(row, 1).toString());
				tenspdh.setText(modeldssanphamDonHang.getValueAt(row, 2).toString());
				
				 
 
				
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
		
		
//		làm mới đơn hàng
		FixButton lammoithemcongdoan_1_1 = new FixButton("Làm mới");
		lammoithemcongdoan_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int icd=1;
				modeldssanphamDonHang.getDataVector().removeAllElements();
				try {
					for (SanPham sp : service.getAllsanPhamDangsanxuat()) {
						 
						Object []obj= {icd, sp.getMaSp(),sp.getTenSp(),sp.getGiaSP() };
						icd++;
						modeldssanphamDonHang.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				tablespDonHang.setModel(modeldssanphamDonHang);
				tablespDonHang.clearSelection();
				slspdh.setText("");
				maspdh.setText("");
			}
		});
		lammoithemcongdoan_1_1.setForeground(Color.WHITE);
		lammoithemcongdoan_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lammoithemcongdoan_1_1.setBackground(new Color(69, 129, 142));
		lammoithemcongdoan_1_1.setBounds(648, 225, 150, 40);
		panel_3_1.add(lammoithemcongdoan_1_1);
		
		
		
		
		
		FixButton themdonhang = new FixButton("Thêm");
		
		
				
				
		themdonhang.setForeground(Color.WHITE);
		themdonhang.setFont(new Font("Tahoma", Font.BOLD, 14));
		themdonhang.setBackground(new Color(0, 158, 15));
		themdonhang.setBounds(890, 225, 150, 40);
		panel_3_1.add(themdonhang);
		
		
//		sửa đơn hàng
		FixButton suacdonhang = new FixButton("Sửa");
		suacdonhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		suacdonhang.setForeground(Color.WHITE);
		suacdonhang.setFont(new Font("Tahoma", Font.BOLD, 14));
		suacdonhang.setBackground(new Color(164, 164, 0));
		suacdonhang.setBounds(1126, 225, 150, 40);
		panel_3_1.add(suacdonhang);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Danh sách sản phẩm");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 27, 180, 19);
		panel_3_1.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPaneDathang = new JScrollPane();
		scrollPaneDathang.setBounds(10, 331, 1312, 373);
		panel_3_1.add(scrollPaneDathang);
		

		modeldsDonhang= new DefaultTableModel();
		 
		modeldsDonhang.addColumn("Mã Đơn Hàng");
		modeldsDonhang.addColumn("Mã SP");
		modeldsDonhang.addColumn("Tên SP");
		modeldsDonhang.addColumn("Số Lượng");
		modeldsDonhang.addColumn("Ngày Đặt Hàng");
		modeldsDonhang.addColumn("Ngày Hoàn Thành");
		modeldsDonhang.addColumn("Trạng thái");
		
		table_DatHang = new JTable(modeldsDonhang);
		scrollPaneDathang.setViewportView(table_DatHang);
		
		// load ds đơn hàng
		 
		for (DonHang dh : service.DSDH()) {
			 String tt="";
			if(dh.getTrangthai()==1) {
				tt="Chưa Hoàn Thành";
			}
			else if (dh.getTrangthai()==2) {
				tt="Hoàn Thành";
			}
			else {
				tt="Bị Hủy";
			}
			 String nht="";
			if(dh.getNgayhoanthanh()==null) {
				nht="";
			}
			else {
				nht=dh.getNgayhoanthanh().toString();
			}
			Object []obj= {  dh.getMaDonHang(),dh.getSanpham().getMaSp(),dh.getSanpham().getTenSp(),dh.getSoluong(),dh.getNgaytao(),nht,tt };
			 
			modeldsDonhang.addRow(obj);
			
		}
		
		JLabel lblTnNhnVin_1_1_1 = new JLabel("Số Lượng Đặt Hàng");
		lblTnNhnVin_1_1_1.setForeground(Color.WHITE);
		lblTnNhnVin_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin_1_1_1.setBounds(890, 164, 148, 22);
		panel_3_1.add(lblTnNhnVin_1_1_1);
		
		slspdh = new JTextField();
		slspdh.setBounds(890, 194, 148, 20);
		panel_3_1.add(slspdh);
		slspdh.setColumns(10);
		
		
//		int lastRow1 = modeldsDonhang.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
//		int column1 = 0; // Chỉ mục của cột bạn muốn lấy giá trị
//		String matiep="";
//		if (lastRow1 >= 0) {
//		    Object value = modeldsDonhang.getValueAt(lastRow1, column1); // Lấy giá trị ở dòng cuối cùng của cột cụ thể
//
//		    if (value != null) {
//		    	
//		    	String input = modeldsDonhang.getValueAt(lastRow1, column1).toString();  
//		    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
//		    	String numberPart = input.substring(2); // Lấy phần số "079"
//		    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
//		    	number++; // Cộng 1 vào số
//		    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới
//		    	matiep=result;
//		    	madonhang.setText(result);
//		    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
//		        
//		       
//		    }
//		}
		//
//		thêm đơn hàng
		themdonhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String masp = maspdh.getText().toString();
				String ten  = tenspdh.getText().toString();
				int soluong   = Integer.parseInt(slspdh.getText().toString());
				 
				int trangthai = 1;// trang thai chua hoan thanh

				
				//thêm trùng
				
				//ngay dat hang
				LocalDate ngaydathang= LocalDate.now();

				// Định dạng ngày thành chuỗi theo định dạng yyyy-MM-dd
			
//				String strDatevaolam = dateFormat.format(datevaolam);

				// Chuyển đổi chuỗi thành kiểu java.sql.Date
				java.sql.Date sqlDatedathang = java.sql.Date.valueOf(ngaydathang);
				
				DonHang dh= new DonHang(  new SanPham(masp,ten), soluong, trangthai,ngaydathang);
				
				
				
				
				//
				int lastRow = modeldsDonhang.getRowCount() - 1; // Lấy chỉ mục của dòng cuối cùng
				int column = 0; // Chỉ mục của cột bạn muốn lấy giá trị
				String matiep="";
				if (lastRow >= 0) {
				    Object value = modeldsDonhang.getValueAt(lastRow, column); // Lấy giá trị ở dòng cuối cùng của cột cụ thể

				    if (value != null) {
				    	
				    	String input = modeldsDonhang.getValueAt(lastRow, column).toString();  
				    	String prefix = input.substring(0, 2); // Lấy phần tiền tố "NV"
				    	String numberPart = input.substring(2); // Lấy phần số "079"
				    	int number = Integer.parseInt(numberPart); // Chuyển phần số thành số nguyên
				    	number++; // Cộng 1 vào số
				    	String result = prefix + String.format("%03d", number); // Kết hợp lại thành chuỗi mới
				    	matiep=result;
//				    	madonhang.setText(result);
				    	System.out.println("Kết quả tiếp theo là : " + result); // In ra kết quả
				        
				       
				    }
				}
				//
				try {
					service.insertDonhang(dh);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					service.themCongDoanDonHang(matiep);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				Object []obj= {  matiep,dh.getSanpham().getMaSp(),dh.getSanpham().getTenSp(),dh.getSoluong(),dh.getNgaytao(),"","Chưa Hoàn Thành" };
				 
				modeldsDonhang.addRow(obj);
				
 
				
				JOptionPane.showMessageDialog(null, "Thêm Đơn Hàng Thành Công");
			}
		});
		
		//thêm đơn hàng
		
	 
		
		
		
		 
//		Thêm công đoạn
		JPanel panelblnv = new JPanel();
		tabbedPane.addTab("Thêm công đoạn", null, panelblnv, null);
		panelblnv.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(13, 152, 204));
		panel_3.setBounds(10, 11, 1356, 715);
		panelblnv.add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Mã sản phẩm");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(653, 57, 103, 22);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblTnNhnVin_1 = new JLabel("Tên sản phẩm");
		lblTnNhnVin_1.setForeground(new Color(255, 255, 255));
		lblTnNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin_1.setBounds(653, 118, 116, 22);
		panel_3.add(lblTnNhnVin_1);
		
		JLabel lblSinThao_1 = new JLabel("Giá");
		lblSinThao_1.setForeground(new Color(255, 255, 255));
		lblSinThao_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSinThao_1.setBounds(653, 171, 93, 22);
		panel_3.add(lblSinThao_1);
		
		tenspcd = new JTextField();
		tenspcd.setColumns(10);
		tenspcd.setBounds(653, 140, 138, 20);
		panel_3.add(tenspcd);
		
		maspcd = new JTextField();
		maspcd.setColumns(10);
		maspcd.setBounds(653, 87, 138, 20);
		panel_3.add(maspcd);
		
		giaspcd = new JTextField();
		giaspcd.setColumns(10);
		giaspcd.setBounds(653, 193, 138, 20);
		panel_3.add(giaspcd);
		
		JScrollPane scrollPaneDsspThemcongdoan = new JScrollPane();
		scrollPaneDsspThemcongdoan.setBounds(10, 57, 545, 212);
		panel_3.add(scrollPaneDsspThemcongdoan);
		
		
		
		modeldssanphamthemcongdoan= new DefaultTableModel();
		modeldssanphamthemcongdoan.addColumn("STT");
		modeldssanphamthemcongdoan.addColumn("Mã SP");
		modeldssanphamthemcongdoan.addColumn("Tên SP ");
		modeldssanphamthemcongdoan.addColumn("Giá");
		
		tableDSSPThemCongDoan = new JTable(modeldssanphamthemcongdoan);
		scrollPaneDsspThemcongdoan.setViewportView(tableDSSPThemCongDoan);
		
	 
		
		// load ds sản phẩm
		int ispdh=1;
		for (SanPham sp : service.getAllsanPhamDangsanxuat()) {
			 
			Object []obj= {ispdh, sp.getMaSp(),sp.getTenSp(),sp.getGiaSP() };
			ispdh++;
			modeldssanphamthemcongdoan.addRow(obj);
			
		}
		
		JLabel lblTnCngon = new JLabel("Tên công đoạn");
		lblTnCngon.setForeground(new Color(255, 255, 255));
		lblTnCngon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnCngon.setBounds(951, 118, 93, 22);
		panel_3.add(lblTnCngon);
		
		JLabel lblGiCng = new JLabel("Giá Công Đoạn");
		lblGiCng.setForeground(new Color(255, 255, 255));
		lblGiCng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiCng.setBounds(951, 171, 103, 22);
		panel_3.add(lblGiCng);
		
		
		
		
		 
		tableDSSPThemCongDoan.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row=tableDSSPThemCongDoan.getSelectedRow();
				maspcd.setText(modeldssanphamthemcongdoan.getValueAt(row, 1).toString());
				tenspcd.setText(modeldssanphamthemcongdoan.getValueAt(row, 2).toString());
				
				giaspcd.setText(modeldssanphamthemcongdoan.getValueAt(row, 3).toString());
 
				
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
		
		
		// làm mới thêm công đoạn
		FixButton lammoithemcongdoan_1 = new FixButton("Làm mới");
		
		lammoithemcongdoan_1.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/reload (1).png")));
		lammoithemcongdoan_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lammoithemcongdoan_1.setForeground(Color.WHITE);
		lammoithemcongdoan_1.setBackground(new Color(69, 129, 142));
		lammoithemcongdoan_1.setBounds(581, 280, 150, 40);
		panel_3.add(lammoithemcongdoan_1);
		
		FixButton btnthemcongdoan = new FixButton("Thêm");
	
		btnthemcongdoan.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/add (1).png")));
		btnthemcongdoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnthemcongdoan.setForeground(Color.WHITE);
		btnthemcongdoan.setBackground(new Color(0, 158, 15));
		btnthemcongdoan.setBounds(776, 280, 150, 40);
		panel_3.add(btnthemcongdoan);
		
		FixButton suacongdoanbtn_1 = new FixButton("Sửa");
		
		suacongdoanbtn_1.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/edit (1).png")));
		suacongdoanbtn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		suacongdoanbtn_1.setForeground(Color.WHITE);
		suacongdoanbtn_1.setBackground(new Color(164, 164, 0));
		suacongdoanbtn_1.setBounds(975, 280, 150, 40);
		panel_3.add(suacongdoanbtn_1);
		
		FixButton luucongdoanbtn = new FixButton("Lưu");
		luucongdoanbtn.setEnabled(false);
		luucongdoanbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		luucongdoanbtn.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/Flat_tick_icon.svg.png")));
		luucongdoanbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		luucongdoanbtn.setForeground(Color.WHITE);
		luucongdoanbtn.setBackground(new Color(43, 120, 228));
		luucongdoanbtn.setBounds(1160, 278, 150, 40);
		panel_3.add(luucongdoanbtn);
 
		
		//thêm công đoạn
		btnthemcongdoan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (btnthemcongdoan.getText().equals("Thêm")) {
		            // Trạng thái thêm mới
		            luucongdoanbtn.setEnabled(true);
		            luucongdoanbtn.setText("Lưu");
		            
		            // Đặt trạng thái của btnthemcongdoan_1
		            btnthemcongdoan.setText("Hủy");
		            btnthemcongdoan.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/cancel (1).png")));
		            btnthemcongdoan.setBackground(new Color(207, 42, 39));
		        } else if (btnthemcongdoan.getText().equals("Hủy")) {
		            // Trạng thái hủy
		            int result = JOptionPane.showConfirmDialog(null, "Bạn muốn lưu thông tin và ngưng việc thêm công đoạn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		            if (result == JOptionPane.YES_OPTION) {
		                // Lưu thông tin và ngưng việc thêm
		                // Thực hiện lưu thông tin ở đây
		                // Sau khi lưu xong, quay lại trạng thái "Thêm tiếp"
		                btnthemcongdoan.setText("Thêm");
		                btnthemcongdoan.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/add (1).png")));
		                btnthemcongdoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		                btnthemcongdoan.setForeground(Color.WHITE);
		                btnthemcongdoan.setBackground(new Color(0, 158, 15));
		                luucongdoanbtn.setText("Lưu");
		                JOptionPane.showMessageDialog(null, "Thêm công đoạn thành công ");
		                luucongdoanbtn.setEnabled(false);
		            }
		            else {
		            	luucongdoanbtn.setText("Thêm tiếp");
//		             
		            }
		          
		        }
		    }
		});
		JComboBox cbotencd = new JComboBox();
		cbotencd.setBounds(951, 139, 148, 22);
		panel_3.add(cbotencd);
		cbotencd.addItem("Gia công");
		cbotencd.addItem("Làm khuôn giày");
		cbotencd.addItem("May");	 
		cbotencd.addItem("Ráp giày");
		cbotencd.addItem("Hoàn thành dôi giày");

		cbotencd.setSelectedItem("Cắt và gia công");
		
		JComboBox cbogiacd = new JComboBox();
		cbogiacd.setBounds(951, 192, 148, 22);
		panel_3.add(cbogiacd);
		cbogiacd.addItem("3000");
		cbogiacd.addItem("4000");
		cbogiacd.addItem("5000");
		cbogiacd.addItem("6000");
		cbogiacd.addItem("7000");
		cbogiacd.addItem("8000");
		
		cbogiacd.setSelectedItem("5000");
		cbotencd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTrinhDo = (String) cbotencd.getSelectedItem();
                if (selectedTrinhDo != null) {
                    if (selectedTrinhDo.equals("Gia công")) {
                    	cbogiacd.setSelectedItem("5000");
                    } else if (selectedTrinhDo.equals("Làm khuôn giày")) {
                    	cbogiacd.setSelectedItem("4000");
                    } else if (selectedTrinhDo.equals("May")) {
                    	cbogiacd.setSelectedItem("8000");
                    }
                    	else if (selectedTrinhDo.equals("Ráp giày")) {
                    		cbogiacd.setSelectedItem("6000");
                   }
                    	else if (selectedTrinhDo.equals("Hoàn thành dôi giày")) {
                    		cbogiacd.setSelectedItem("3000");
                          }
                }
            }
        });
		
		  // Đặt trạng thái mặc định là "Lưu"
		
	 
		
		luucongdoanbtn.addActionListener(new ActionListener() {
		    int luuState = 1; // Initialize the state

		    public void actionPerformed(ActionEvent e) {
		        if (luuState == 1) {
		            // Trạng thái "Lưu"
		        	String tencongdoan = cbotencd.getSelectedItem().toString();
		        	double giacongdoan = Double.parseDouble(cbogiacd.getSelectedItem().toString());
		        	String masanpcd = maspcd.getText().toString();

		        	// Kiểm tra trùng lặp
		        	boolean trungLap = false;
		        	for (int i = 0; i < modelcongdoan.getRowCount(); i++) {
		        	    String tenCongDoanCot = (String) modelcongdoan.getValueAt(i, 1); // Lấy giá trị từ cột "Ten Cong Doan"
		        	    if (tencongdoan.equals(tenCongDoanCot)) {
		        	        trungLap = true;
		        	        JOptionPane.showMessageDialog(null, "Tên công đoạn đã tồn tại. Không thể thêm.");
		        	        break;
		        	    }
		        	}

		        	if (!trungLap) {
		        	    // Thêm dữ liệu vào database
		        		
		        		int thutu=0;
		        		if(tencongdoan=="Gia công") thutu=1;
		        		else if (tencongdoan=="Làm khuôn giày") thutu=2;
		        		else if (tencongdoan=="May") thutu=3;
		        		else if (tencongdoan=="Ráp giày") thutu=4;
		        		else if (tencongdoan=="Hoàn thành dôi giày") thutu=5;
		        		
		        	    CongDoan cd = new CongDoan(tencongdoan, giacongdoan, new SanPham(masanpcd),thutu);
		        	    try {
							service.themDanhSachCD(cd);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

		        	    // Thêm dữ liệu vào table
		        	    Object[] obj = {1, tencongdoan, giacongdoan, masanpcd};
		        	    modelcongdoan.addRow(obj);

		        	    JOptionPane.showMessageDialog(null, "Thêm công đoạn thành công");
		        	}

		            if (luuState == 1) { // Check the state again before showing the dialog
		                int result = JOptionPane.showConfirmDialog(null, "Bạn muốn tiếp tục thêm không  ", "Xác nhận", JOptionPane.YES_NO_OPTION);
		                if (result == JOptionPane.YES_OPTION) {
		                    luucongdoanbtn.setText("Thêm tiếp");
		                    luuState = 2;
		                } else {
		                    JOptionPane.showMessageDialog(null, "Thêm công đoạn thành công ");
		                    luucongdoanbtn.setText("Lưu");
		                    luucongdoanbtn.setEnabled(false);
		                    btnthemcongdoan.setText("Thêm");
		                    btnthemcongdoan.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/add (1).png")));
		                    btnthemcongdoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		                    btnthemcongdoan.setForeground(Color.WHITE);
		                    btnthemcongdoan.setBackground(new Color(0, 158, 15));
		                    luuState = 1;
		                }
		            }
		        } else if (luuState == 2) {
		            // Trạng thái "Thêm tiếp"
		        	String tencongdoan = cbotencd.getSelectedItem().toString();
		        	double giacongdoan = Double.parseDouble(cbogiacd.getSelectedItem().toString());
		        	String masanpcd = maspcd.getText().toString();

		        	// Kiểm tra trùng lặp
		        	boolean trungLap = false;
		        	for (int i = 0; i < modelcongdoan.getRowCount(); i++) {
		        	    String tenCongDoanCot = (String) modelcongdoan.getValueAt(i, 1); // Lấy giá trị từ cột "Ten Cong Doan"
		        	    if (tencongdoan.equals(tenCongDoanCot)) {
		        	        trungLap = true;
		        	        JOptionPane.showMessageDialog(null, "Tên công đoạn đã tồn tại. Không thể thêm.");
		        	        break;
		        	    }
		        	}

		        	if (!trungLap) {
		        	    // Thêm dữ liệu vào database
		        		int thutu=0;
		        		if(tencongdoan=="Gia công") thutu=1;
		        		else if (tencongdoan=="Làm khuôn giày") thutu=2;
		        		else if (tencongdoan=="May") thutu=3;
		        		else if (tencongdoan=="Ráp giày") thutu=4;
		        		else if (tencongdoan=="Hoàn thành dôi giày") thutu=5;
		        	    CongDoan cd = new CongDoan(tencongdoan, giacongdoan, new SanPham(masanpcd),thutu);
		        	    try {
							service.themDanhSachCD(cd);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

		        	    // Thêm dữ liệu vào table
		        	    Object[] obj = {1, tencongdoan, giacongdoan, masanpcd};
		        	    modelcongdoan.addRow(obj);

		        	    JOptionPane.showMessageDialog(null, "Thêm công đoạn thành công");
		        	}


		         
		             
		        }
		    }
		});

//		sửa công đoạn
		suacongdoanbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int row=tableCD.getSelectedRow();
				
				if(row>=0) {
//					
					String macd = modelcongdoan.getValueAt(row, 0).toString();
					double giacd = Double.parseDouble(  cbogiacd.getSelectedItem().toString());
					
					try {
						service.updateCongDoan(giacd, macd);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Cập nhật thành công ");
					
					
					tableCD.setValueAt(giacd, row, 2);
							
				}
				
				
			}
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("Danh sách sản phẩm");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 27, 180, 19);
		panel_3.add(lblNewLabel_1_1);
		 
		JScrollPane scrollPaneCongDoan = new JScrollPane();
		scrollPaneCongDoan.setBounds(10, 372, 1312, 332);
		panel_3.add(scrollPaneCongDoan);
		
		
		modelcongdoan=new DefaultTableModel();
		modelcongdoan.addColumn("Ma Cong Doan");
		modelcongdoan.addColumn("Ten Cong Doan");
		modelcongdoan.addColumn("Gia Cong Doan");
		modelcongdoan.addColumn("Ma San Pham");
		
		
		//
		modelcongdoan.addColumn("Trạng Thái");
//		modelcongdoan.addColumn("Ma San Pham");
		//
		tableCD = new JTable(modelcongdoan);
		scrollPaneCongDoan.setViewportView(tableCD);
		
		lammoithemcongdoan_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				modelcongdoan.getDataVector().removeAllElements();
				 Object []objq= {"","","",""};
				 
					modelcongdoan.addRow(objq);
				if (modelcongdoan.getRowCount() > 0) {
					modelcongdoan.removeRow(0);
				}
				//
				
				tableDSSPThemCongDoan.clearSelection();
				 
				int icd=1;
				modeldssanphamthemcongdoan.getDataVector().removeAllElements();
				try {
					for (SanPham sp : service.getAllsanPhamDangsanxuat()) {
						 
						Object []obj= {icd, sp.getMaSp(),sp.getTenSp(),sp.getGiaSP() };
						icd++;
						modeldssanphamthemcongdoan.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				tableDSSPThemCongDoan.setModel(modeldssanphamthemcongdoan);
 
//				tableDSSPThemCongDoan.clearSelection();
//				modelcongdoan.getDataVector().removeAllElements();
				
			}
		});
		
		// bấm vào bảng sản phẩm thì sẽ hiển thị công đoạn tương ứng của sản phẩm đó
		ListSelectionModel selectionModel = tableDSSPThemCongDoan.getSelectionModel();

		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            // Xử lý sự kiện ở đây
		            int selectedRow = tableDSSPThemCongDoan.getSelectedRow();
		            if (selectedRow != -1) {
		            	modeldssanphamthemcongdoan.getValueAt(selectedRow,  1).toString();
		            	
		            	
		            	 List<CongDoan> loadcdtheosp = null;
						try {
							loadcdtheosp = service.getAllcongdoantheosanpham(modeldssanphamthemcongdoan.getValueAt(selectedRow,  1).toString());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            	 modelcongdoan.getDataVector().removeAllElements();
						for (CongDoan cd : loadcdtheosp) {
							Object []obj= {cd.getMaCongDoan(),cd.getTenCongDoan(),cd.getGiaCongDoan(),cd.getMaSp().getMaSp(),"Chưa Hoàn Thành"};
							 
							modelcongdoan.addRow(obj);

						}
						tableCD.setModel(modelcongdoan);
						
						
					 System.out.println("hello model cong doan"+	modeldssanphamthemcongdoan.getValueAt(selectedRow,  1).toString());
					 int rowCount = modelcongdoan.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						if(rowCount==0) {
							JOptionPane.showMessageDialog(null, "Error:San pham nay chua co cong doan");
							 modelcongdoan.getDataVector().removeAllElements();
							 Object []obj= {"","","",""};
							 
								modelcongdoan.addRow(obj);
							if (modelcongdoan.getRowCount() > 0) {
								modelcongdoan.removeRow(0);
							}
						}
		            }
		        }
		    }

			 
		});
 
		
 

		LocalDate locallaythangnamhientai = LocalDate.now();
		int thang = locallaythangnamhientai.getMonthValue();
		
		 int namHienTai = LocalDate.now().getYear();
		
  


//	Phan cong   cong doan
		 
		JPanel panelccnv = new JPanel();
		tabbedPane.addTab("Phân công công đoạn", null, panelccnv, null);
		panelccnv.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1360, 726);
		panelccnv.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(null);
		panel_4.setBackground(new Color(13, 152, 204));
		panel_4.setBounds(10, 11, 1332, 702);
		panel_1.add(panel_4);
		
		JScrollPane scrollPanesanphamphancong = new JScrollPane();
		scrollPanesanphamphancong.setBounds(10, 57, 452, 212);
		panel_4.add(scrollPanesanphamphancong);
		
		
		modeldssanphamphancong= new DefaultTableModel();
		modeldssanphamphancong.addColumn("STT");
		modeldssanphamphancong.addColumn("Mã SP");
		modeldssanphamphancong.addColumn("Tên SP ");
 
		
//		 
		
	 //load sản phẩm trong phan cong cong doan
		int icd1=1;
		for (SanPham sp : service.getAllsanPhamDangDathang()) {
			 
			Object []obj= {icd1, sp.getMaSp(),sp.getTenSp()  };
			icd1++;
			modeldssanphamphancong.addRow(obj);
			
		}
		tablesppc = new JTable(modeldssanphamphancong);
		scrollPanesanphamphancong.setViewportView(tablesppc);
		
		//
		JScrollPane scrollpanecdpc = new JScrollPane();
		scrollpanecdpc.setBounds(546, 57, 751, 212);
		panel_4.add(scrollpanecdpc);
		
		modelcongdoanphancong=new DefaultTableModel();
		modelcongdoanphancong.addColumn("Ma Cong Doan");
		modelcongdoanphancong.addColumn("Ten Cong Doan");
		modelcongdoanphancong.addColumn("Gia Cong Doan");
		modelcongdoanphancong.addColumn("Số Lượng Công Doan");
		modelcongdoanphancong.addColumn("Tiến độ Công Đoạn");
		modelcongdoanphancong.addColumn("Trạng thái công đoạn");
		modelcongdoanphancong.addColumn("ma cong doan don hang");
		modelcongdoanphancong.addColumn("Trạng thái phân công");
		tablecdpc = new JTable(modelcongdoanphancong);
		scrollpanecdpc.setViewportView(tablecdpc);
		
		
	 
		

		
		
		
		JScrollPane scrollpanedspc = new JScrollPane();
		scrollpanedspc.setBounds(10, 362, 1287, 250);
		panel_4.add(scrollpanedspc);
		
		
		modeldscongnhanphancong=new DefaultTableModel();
		modeldscongnhanphancong.addColumn("Mã Công Nhân");
		modeldscongnhanphancong.addColumn("Tên Công Nhân");
		modeldscongnhanphancong.addColumn("Mã Công Đoạn");
		modeldscongnhanphancong.addColumn("Mã Sản Phẩm ");
		modeldscongnhanphancong.addColumn("Ngày Phân Công");
		modeldscongnhanphancong.addColumn("Trạng thái Phân Công");
		modeldscongnhanphancong.addColumn("Số Lượng Phân Công");
		 JTable tabledspc = new JTable(modeldscongnhanphancong) {
	           @Override
	           public Class<?> getColumnClass(int column) {
	               if (column == 5) {
	                   return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
	               }
//	               if (column == 6) {
//	                   return Boolean.class; // Đặt kiểu dữ liệu cho cột Select là Boolean
//	               }

	               return super.getColumnClass(column);
	           }
	       };
//		tabledspc = new JTable(modeldscongnhanphancong);
		scrollpanedspc.setViewportView(tabledspc);
		
		
		   JCheckBox phancong= new JCheckBox();
	      
	       // Đặt kiểu dữ liệu cho cột Select
		   tabledspc.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(phancong));
	     
		//--
		
		cndao = new congNhanDAO();
//		 Date currentDate = new Date();
//		LocalDate currentDate = LocalDate.now();
//		
//		
//		for (BangPhanCong bpc : bpcdao.DSPC()) {
//			Boolean lun = null;
//			if(bpc.getTrangthaiphancong()==1) {
//				lun=true;
//			}
//			else {
//				lun=(Boolean) null;
//			}
//			if(bpc.getNgayCham()==null) {
//				currentDate = LocalDate.now();
//			}
//			else {
////				currentDate=bpc.getNgayCham();
//			}
//			Object []obj= { bpc.getMaCongNhan().getMaCongNhan(),bpc.getTenCongnhan() ,bpc.getMaCongDoan().getMaCongDoan(),"",currentDate,lun};
//			 
//			modeldscongnhanphancong.addRow(obj);
//			
//		}
			
		
		//--
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách sản phẩm");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 22, 182, 24);
		panel_4.add(lblNewLabel_2);
		
		JLabel lblDanhSchCng = new JLabel("Danh sách công đoạn");
		lblDanhSchCng.setForeground(new Color(255, 255, 255));
		lblDanhSchCng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDanhSchCng.setBounds(546, 22, 164, 24);
		panel_4.add(lblDanhSchCng);
		
		JLabel lblDanhSchCng_1 = new JLabel("Danh sách Công Nhân Phân Công");
		lblDanhSchCng_1.setForeground(new Color(255, 255, 255));
		lblDanhSchCng_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDanhSchCng_1.setBounds(10, 319, 349, 24);
		panel_4.add(lblDanhSchCng_1);
		
		FixButton bangsanphamphancongcongdoan = new FixButton("Làm mới");
		bangsanphamphancongcongdoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				modeldssanphamphancong.getDataVector().removeAllElements();
				int icd1=1;
				try {
					for (SanPham sp : service.getAllsanPhamDangDathang()) {
						 
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
						
						Object []obj= {icd1, sp.getMaSp(),sp.getTenSp(),currencyFormatter.format(sp.getGiaSP()) };
						icd1++;
						modeldssanphamphancong.addRow(obj);
						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				tablesppc.setModel(modeldssanphamphancong);
				tablesppc.clearSelection();
				
				
				modelcongdoanphancong.getDataVector().removeAllElements();
				tablecdpc.clearSelection();
			}
		});
		bangsanphamphancongcongdoan.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/reload (1).png")));
		bangsanphamphancongcongdoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		bangsanphamphancongcongdoan.setForeground(Color.WHITE);
		bangsanphamphancongcongdoan.setBackground(new Color(69, 129, 142));
		bangsanphamphancongcongdoan.setBounds(621, 637, 150, 40);
		panel_4.add(bangsanphamphancongcongdoan);
		
		FixButton suacongdoanbtn = new FixButton("Sửa");
		suacongdoanbtn.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/edit (1).png")));
		suacongdoanbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		suacongdoanbtn.setForeground(Color.WHITE);
		suacongdoanbtn.setBackground(Color.YELLOW);
		suacongdoanbtn.setBounds(858, 637, 150, 40);
		panel_4.add(suacongdoanbtn);
		
		FixButton btnphancongcongdoan = new FixButton("Phân công");
		
		btnphancongcongdoan.setIcon(new ImageIcon(TabSanPham.class.getResource("/image/Flat_tick_icon.svg.png")));
		btnphancongcongdoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnphancongcongdoan.setForeground(Color.WHITE);
		btnphancongcongdoan.setBackground(new Color(0, 158, 15));
		btnphancongcongdoan.setBounds(1077, 637, 150, 40);
		panel_4.add(btnphancongcongdoan);
		
		
 
		 
//		Phân công công nhân ở đây 
//		
		
		tabledspc.getColumnModel().getColumn(3).setMinWidth(0);
		tabledspc.getColumnModel().getColumn(3).setMaxWidth(0);
		
		tabledspc.getColumnModel().getColumn(2).setMinWidth(0);
		tabledspc.getColumnModel().getColumn(2).setMaxWidth(0);
		
		
		
//		Bấm vô bảng sản phẩm ở phân công công đoạn ,bảng phân công sẽ load lại theo mã sản phẩm đã chọn ở bảng sản phẩm
		
//		ListSelectionModel selectionModelCDPC = tablesppc.getSelectionModel();
//
//		selectionModelCDPC.addListSelectionListener(new ListSelectionListener() {
//		    public void valueChanged(ListSelectionEvent e) {
//		        if (!e.getValueIsAdjusting()) {
//		            // Xử lý sự kiện ở đây
//		            int selectedRow = tablesppc.getSelectedRow();
//		            if (selectedRow != -1) {
//		             
//		            	tablecdpc.clearSelection();
//		            	tabledspc.clearSelection();
////		            	modeldscongnhanphancong.getDataVector().removeAllElements();
//		            	
//		            	 List<CongDoan> locbangluong=cddao.getAllcongdoantheosanpham(modeldssanphamphancong.getValueAt(selectedRow,  1).toString());
//		            	 modelcongdoanphancong.getDataVector().removeAllElements();
//						for (CongDoan cd : locbangluong) {
//							Object []obj= {cd.getMaCongDoan(),cd.getTenCongDoan()  };
//							 
//							modelcongdoanphancong.addRow(obj);
//
//						}
//						tablecdpc.setModel(modelcongdoanphancong);
//						
//						
//					 System.out.println("hello model cong doan"+	modeldssanphamphancong.getValueAt(selectedRow,  1).toString());
//					 int rowCount = modelcongdoanphancong.getRowCount();
//						System.out.println("so dong loc bang :"+rowCount+"");
//						if(rowCount==0) {
//							JOptionPane.showMessageDialog(null, "Error:San pham nay chua co cong doan vui long thêm công đoạn trước khi phân công");
//							modelcongdoanphancong.getDataVector().removeAllElements();
//							 Object []obj= {"","","",""};
//							 
//							 modelcongdoanphancong.addRow(obj);
//							if (modelcongdoanphancong.getRowCount() > 0) {
//								modelcongdoanphancong.removeRow(0);
//							}
//						}
//		            }
//		        }
//		    }
//
//			 
//		});
		
		
		
		
		ListSelectionModel selectionModelCDPC = tablesppc.getSelectionModel();

		 
		selectionModelCDPC.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		        	
		         
		        	
		            // Xử lý sự kiện ở đây
		            int selectedRow = tablesppc.getSelectedRow();
		            if (selectedRow != -1) {
		             
		            	tablecdpc.clearSelection();
		            	tabledspc.clearSelection();
//		            	modeldscongnhanphancong.getDataVector().removeAllElements();
		            	
		            	 List<CongDoanDonHang> loccongdoandonhang = null;
						try {
							loccongdoandonhang = service.getAllcongdoanDonHangtheosanphamCo2MaSptrungnhau(modeldssanphamphancong.getValueAt(selectedRow,  2).toString(),modeldssanphamphancong.getValueAt(selectedRow,  1).toString());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            	 modelcongdoanphancong.getDataVector().removeAllElements();
						for (CongDoanDonHang cd : loccongdoandonhang) {
							String tt="";
							if(cd.getTrangthai()==1) {
								tt="Chưa hoàn thành";
							}
							if(cd.getTrangthai()==2) {
								tt="Hoàn thành";
							}
							Object []obj= {cd.getCongdoan().getMaCongDoan(),cd.getCongdoan().getTenCongDoan(),cd.getCongdoan().getGiaCongDoan(),cd.getSoluongdathang(),cd.getTiendo(),tt,cd.getMaCongDoanDonHang() };
							 
							
							
							
							modelcongdoanphancong.addRow(obj);

						}
						tablecdpc.setModel(modelcongdoanphancong);
						
						
					 System.out.println("hello model cong doan"+	modeldssanphamphancong.getValueAt(selectedRow,  2).toString());
					 int rowCount = modelcongdoanphancong.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						if(rowCount==0) {
							JOptionPane.showMessageDialog(null, "Error:Loi roi");
							modelcongdoanphancong.getDataVector().removeAllElements();
							 Object []obj= {"","","",""};
							 
							 modelcongdoanphancong.addRow(obj);
							if (modelcongdoanphancong.getRowCount() > 0) {
								modelcongdoanphancong.removeRow(0);
							}
						}
		            }
		        }
		    }

			 
		});
		
		
		
		
		
		ListSelectionModel selectionModelDSPC = tablecdpc.getSelectionModel();

		selectionModelDSPC.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            // Xử lý sự kiện ở đây
		            int selectedRow = tablecdpc.getSelectedRow();
		            if (selectedRow != -1) {
	 
		            	 modeldscongnhanphancong.getDataVector().removeAllElements();
		         		LocalDate currentDate = LocalDate.now();
		         		
		         		
		         		try {
							for (BangPhanCong bpc : service.DSPC(modelcongdoanphancong.getValueAt(selectedRow,  0).toString())) {
								Boolean lun = null;
								String slpc="";
								
								if(bpc.getSoluongphancong()==0) {
									slpc="";
								}
								else {
									
									slpc=bpc.getSoluongphancong()+"";
									
								}
								
								if(bpc.getTrangthaiphancong()==1) {
									slpc=bpc.getSoluongphancong()+"";
									lun=true;
								}
								else {
									slpc="";
									lun=(Boolean) null;
								}
								
								if(bpc.getNgayCham()==null) {
									currentDate = LocalDate.now();
								}
								else {
//		         				currentDate=bpc.getNgayCham();
								}
								
								
							
								
								Object []obj= { bpc.getMaCongNhan().getMaCongNhan(),bpc.getTenCongnhan() ,bpc.getMaCongDoan().getMaCongDoan(),"",currentDate,lun,slpc};
								 
								modeldscongnhanphancong.addRow(obj);
								
							}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		         		tabledspc.setModel(modeldscongnhanphancong);
		         		
		         		int rowCount = modeldscongnhanphancong.getRowCount();
						System.out.println("so dong loc bang :"+rowCount+"");
						
//						System.out.println(modeldscongnhanphancong.getValueAt(selectedRow,  0).toString()+"");
							if(rowCount==0) {
	//							JOptionPane.showMessageDialog(null, "Error:zz");
								modeldscongnhanphancong.getDataVector().removeAllElements();
								 Object []obj= {"","","",""};
								 
								 modeldscongnhanphancong.addRow(obj);
								if (modeldscongnhanphancong.getRowCount() > 0) {
									modeldscongnhanphancong.removeRow(0);
								}
							}
						}
		            else {
		            	modeldscongnhanphancong.getDataVector().removeAllElements();
						 Object []obj= { "" ,"","","","",""};
						 
						 modeldscongnhanphancong.addRow(obj);
						if (modeldscongnhanphancong.getRowCount() > 0) {
							modeldscongnhanphancong.removeRow(0);
						}
						tabledspc.setModel(modeldscongnhanphancong);
		            }
		            }
		        }
//		    }

			 
		});
		
//
//		
//		int rowCount = modelcongdoanphancong.getRowCount();
//		System.out.println(rowCount);
//		for (int row = 0; row < rowCount; row++) {
//		    String trangthai = (String) modelcongdoanphancong.getValueAt(row, 5);
//		    if (trangthai.equals("Hoàn thành")) {
//		         btnphancongcongdoan.setEnabled(false);
//		         btnphancongcongdoan.setToolTipText("Công đoạn này đã hoàn thành không thể phân công !!!");
//		    }
//		}
//		
		//
		
		
		//tét tào lao : công đoạn nào đủ chỉ tiêu hoàn thành rồi thì không phân công được nữa!!
		ListSelectionModel selectionModelsuanutphancong = tablecdpc.getSelectionModel();

		selectionModelsuanutphancong.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            // Xử lý sự kiện ở đây
		            int selectedRow = tablecdpc.getSelectedRow();
		            if (selectedRow != -1) {
		            	
		            	
		            	  int soluongdaphancongcua1congdoan = 0;
						try {
							soluongdaphancongcua1congdoan = service.soluongcua1congdoan((String)modelcongdoanphancong.getValueAt(selectedRow, 0),(String)modelcongdoanphancong.getValueAt(selectedRow, 6));
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            	  int soluongdathang=(int)modelcongdoanphancong.getValueAt(selectedRow, 3);
			            		System.out.println("eeeeee so luong cua 1 cong doan ne cuu" + soluongdaphancongcua1congdoan);
//				            	modelcongdoanphancong.setValueAt("Đã đủ số lượng phân công", selectedRow, 7);
			             if(soluongdaphancongcua1congdoan==soluongdathang) {
			            	 modelcongdoanphancong.setValueAt("Đã đủ số lượng phân công", selectedRow, 7);
			            	 btnphancongcongdoan.setEnabled(false);
	    		         JOptionPane.showMessageDialog(null, "Công đoạn này đã phân công đủ số lượng, không thể phân công");
	    		         btnphancongcongdoan.setToolTipText("Công đoạn này đã phân công đủ không thể phân công !!!");
			             }
			             else {
			    		    	btnphancongcongdoan.setEnabled(true);
			    		    }
			             
			             
		            	System.out.println("CCCCCCCCCCCCCCCCCCCCCCCC"+modelcongdoanphancong.getValueAt(selectedRow, 3));
		            	
		            	
//		            	String trangthai = (String) modelcongdoanphancong.getValueAt(selectedRow, 5);
//		            
//		    		    if (trangthai.equals("Hoàn thành")) {
//		    		         btnphancongcongdoan.setEnabled(false);
////		    		         JOptionPane.showMessageDialog(null, "Công đoạn này đã hoàn thành, không thể phân công");
//		    		         btnphancongcongdoan.setToolTipText("Công đoạn này đã hoàn thành không thể phân công !!!");
//		    		    }
//		    		    else {
//		    		    	btnphancongcongdoan.setEnabled(true);
//		    		    }
//		            	
		            	
		            	
		    			 
						 
						 

		    			
					}
		            }
		        }
//		    }

			 
		});
		 
		//
		 
		
		btnphancongcongdoan.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 
 				
 				//laymadonhang
				 String madonhang = "";
				 String masp="";
				int selectedRowsppc = tablesppc.getSelectedRow();
			    if (selectedRowsppc != -1) {
			        madonhang= tablesppc.getValueAt(selectedRowsppc, 1).toString();
			        masp= tablesppc.getValueAt(selectedRowsppc, 2).toString();
			    }
				
 				
 				 //lấy trang thai phan cong
 				int rowCount = modeldscongnhanphancong.getRowCount();
				System.out.println(rowCount);
				 
				 
				int sum = 0; // Khởi tạo sum ở đây để tính tổng số lượng phân công
				for (int row = 0; row < rowCount; row++) {
				    Boolean trangthaicheck = (Boolean) modeldscongnhanphancong.getValueAt(row, 5);

				    String maCongNhan = (String) modeldscongnhanphancong.getValueAt(row, 0);
				    String maCongDoan = "";
				    String maCongcongdoandonhang="";
				    int selectedRowCNCD = tablecdpc.getSelectedRow();
				    if (selectedRowCNCD != -1) {
				        maCongDoan = tablecdpc.getValueAt(selectedRowCNCD, 0).toString();
				        maCongcongdoandonhang=tablecdpc.getValueAt(selectedRowCNCD, 6).toString();
				    }

				    int ttpc = 0;
				    if (trangthaicheck != null && trangthaicheck) {
				        Object value = modeldscongnhanphancong.getValueAt(row, 6);
				        if (value == null || value.toString().isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Chưa phân công số lượng cho công nhân có mã " + maCongNhan);
				        } else {
				            int soluongphancong = Integer.parseInt(value.toString());

				            if (soluongphancong < 100) {
				                JOptionPane.showMessageDialog(null, "Số lượng phân công không thể ít hơn 100. Vui lòng chỉnh sửa số lượng.");
				            } else {
				                sum += soluongphancong;
				            	
				              
				            	
				                try {
									if (sum > service.tongsoluongphancong(maCongDoan,madonhang)) {
									    JOptionPane.showMessageDialog(null, "Tổng số lượng phân công vượt quá giới hạn. Không thể phân công.");
									    System.out.println(sum+"");
									    break; // Dừng vòng lặp nếu vượt quá giới hạn
									    
									   
										
									}
									else {

//										LocalDate localNgayPhanCong = LocalDate.now();
//										java.sql.Date sqlDatePhanCong = java.sql.Date.valueOf(localNgayPhanCong);
//										
//										ttpc = 1;
//										BangPhanCong bpc= new  BangPhanCong(new CongNhan(maCongNhan), new CongDoan(maCongDoan),localNgayPhanCong , ttpc,new CongDoanDonHang(maCongcongdoandonhang ),soluongphancong);
//										System.out.println(bpc.toString());
//										service.insertPhanCong(bpc);
//										JOptionPane.showMessageDialog(null, "Phân công thành công");
//										 
//										ChamCongCongNhan cccn=new ChamCongCongNhan(   new CongNhan(maCongNhan),new CongDoan(maCongDoan), localNgayPhanCong , 0, 0);
//										service.themChamCongCongNhan(cccn);
										int phancongdusoluongthikchophancong=service.phancongdusoluongthikchophancong(maCongDoan, maCongcongdoandonhang, madonhang, masp);
										
										if(phancongdusoluongthikchophancong!=1) {
											LocalDate localNgayPhanCong = LocalDate.now();
//											java.sql.Date sqlDatePhanCong = java.sql.Date.valueOf(localNgayPhanCong);
//											
											ttpc = 1;
											BangPhanCong bpc= new  BangPhanCong(new CongNhan(maCongNhan), new CongDoan(maCongDoan),localNgayPhanCong , ttpc,new CongDoanDonHang(maCongcongdoandonhang ),soluongphancong);
											System.out.println(bpc.toString());
											service.insertPhanCong(bpc);
											JOptionPane.showMessageDialog(null, "Phân công thành công");
											 
											ChamCongCongNhan cccn=new ChamCongCongNhan(   new CongNhan(maCongNhan),new CongDoan(maCongDoan), localNgayPhanCong , 0, 0);
											service.themChamCongCongNhan(cccn);
										}
										else {
											JOptionPane.showMessageDialog(null, "Phân công đã đủ không thể phân công");
										}
									}
								} catch (HeadlessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				                
				            }
				        }
				    }
				}





 			}
 		});
		
		
		 
		 


		
		 
		//các nút phân loại phân công 
		 
		 JRadioButton filtertatcaPhanCong = new JRadioButton("Tất cả");
			filtertatcaPhanCong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 int selectedRow = tablecdpc.getSelectedRow();
					  if (selectedRow != -1) {
				             
			            	
			            	
//			            	 List<BangPhanCong> dspc=bpcdao.DSPC( );
			            	 modeldscongnhanphancong.getDataVector().removeAllElements();
			         		LocalDate currentDate = LocalDate.now();
			         		
			         		
			         		try {
								for (BangPhanCong bpc : service.DSPC(modelcongdoanphancong.getValueAt(selectedRow,  0).toString())) {
									Boolean lun = null;
									String slpc="";
									
									if(bpc.getSoluongphancong()==0) {
										slpc="";
									}
									else {
										
										slpc=bpc.getSoluongphancong()+"";
										
									}
									
									if(bpc.getTrangthaiphancong()==1) {
										slpc=bpc.getSoluongphancong()+"";
										lun=true;
									}
									else {
										slpc="";
										lun=(Boolean) null;
									}
									
									if(bpc.getNgayCham()==null) {
										currentDate = LocalDate.now();
									}
									else {
//			         				currentDate=bpc.getNgayCham();
									}
									
									Object []obj= { bpc.getMaCongNhan().getMaCongNhan(),bpc.getTenCongnhan() ,bpc.getMaCongDoan().getMaCongDoan(),"",currentDate,lun,slpc};
									 
									modeldscongnhanphancong.addRow(obj);
									
								}
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			         		tabledspc.setModel(modeldscongnhanphancong);
			         		
			 
			         		
			         		int rowCount = modeldscongnhanphancong.getRowCount();
							System.out.println("so dong loc bang :"+rowCount+"");
							
//							System.out.println(modeldscongnhanphancong.getValueAt(selectedRow,  0).toString()+"");
								if(rowCount==0) {
		//							JOptionPane.showMessageDialog(null, "Error:zz");
									modeldscongnhanphancong.getDataVector().removeAllElements();
									 Object []obj= {"","","",""};
									 
									 modeldscongnhanphancong.addRow(obj);
									if (modeldscongnhanphancong.getRowCount() > 0) {
										modeldscongnhanphancong.removeRow(0);
									}
								}
			         		
							}
			            else {
			            	modeldscongnhanphancong.getDataVector().removeAllElements();
							 Object []obj= { "" ,"","","","",""};
							 
							 modeldscongnhanphancong.addRow(obj);
							if (modeldscongnhanphancong.getRowCount() > 0) {
								modeldscongnhanphancong.removeRow(0);
							}
							tabledspc.setModel(modeldscongnhanphancong);
			            }
					
				}
			});
			filtertatcaPhanCong.setBounds(906, 322, 77, 23);
			panel_4.add(filtertatcaPhanCong);
			
			JRadioButton fildaphancong = new JRadioButton("Đã phân công");
			fildaphancong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int selectedRow = tablecdpc.getSelectedRow();
					  if (selectedRow != -1) {
				             
			            	
			            	
//			            	 List<BangPhanCong> dspc=bpcdao.DSPC( );
			            	 modeldscongnhanphancong.getDataVector().removeAllElements();
			         		LocalDate currentDate = LocalDate.now();
			         		
			         		
			         		try {
								for (BangPhanCong bpc : service.DSPC(modelcongdoanphancong.getValueAt(selectedRow,  0).toString(),1) ) {
									 
									Boolean lun = null;
									String slpc="";
									
									if(bpc.getSoluongphancong()==0) {
										slpc="";
									}
									else {
										
										slpc=bpc.getSoluongphancong()+"";
										
									}
									
									if(bpc.getTrangthaiphancong()==1) {
										slpc=bpc.getSoluongphancong()+"";
										lun=true;
									}
									else {
										slpc="";
										lun=(Boolean) null;
									}
									
									if(bpc.getNgayCham()==null) {
										currentDate = LocalDate.now();
									}
									else {
//			         				currentDate=bpc.getNgayCham();
									}
									
									
									Object []obj= { bpc.getMaCongNhan().getMaCongNhan(),bpc.getTenCongnhan() ,bpc.getMaCongDoan().getMaCongDoan(),"",currentDate,lun,slpc};
									 
									modeldscongnhanphancong.addRow(obj);
									
								}
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			         		tabledspc.setModel(modeldscongnhanphancong);
			         		
//						 
			         		int rowCount = modeldscongnhanphancong.getRowCount();
							System.out.println("so dong loc bang :"+rowCount+"");
							
//							System.out.println(modeldscongnhanphancong.getValueAt(selectedRow,  0).toString()+"");
								if(rowCount==0) {
		//							JOptionPane.showMessageDialog(null, "Error:zz");
									modeldscongnhanphancong.getDataVector().removeAllElements();
									 Object []obj= {"","","",""};
									 
									 modeldscongnhanphancong.addRow(obj);
									if (modeldscongnhanphancong.getRowCount() > 0) {
										modeldscongnhanphancong.removeRow(0);
									}
								}
			         		
					  }
			            else {
			            	modeldscongnhanphancong.getDataVector().removeAllElements();
							 Object []obj= { "" ,"","","","",""};
							 
							 modeldscongnhanphancong.addRow(obj);
							if (modeldscongnhanphancong.getRowCount() > 0) {
								modeldscongnhanphancong.removeRow(0);
							}
							tabledspc.setModel(modeldscongnhanphancong);
			            }
					
				}
					
				 
			});
			fildaphancong.setBounds(1016, 322, 106, 23);
			panel_4.add(fildaphancong);
			
			JRadioButton filterchuaphancong = new JRadioButton("Chưa phân công");
			filterchuaphancong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					

					int selectedRow = tablecdpc.getSelectedRow();
					  if (selectedRow != -1) {
				             
			            	
			            	
//			            	 List<BangPhanCong> dspc=bpcdao.DSPC( );
			            	 modeldscongnhanphancong.getDataVector().removeAllElements();
			         		LocalDate currentDate = LocalDate.now();
			         		
			         		
			         		try {
								for (BangPhanCong bpc : service.DSPC( ) ) {


									Boolean lun = null;
									String slpc="";
									
									if(bpc.getSoluongphancong()==0) {
										slpc="";
									}
									else {
										
										slpc=bpc.getSoluongphancong()+"";
										
									}
									
									if(bpc.getTrangthaiphancong()==1) {
										slpc=bpc.getSoluongphancong()+"";
										lun=true;
									}
									else {
										slpc="";
										lun=(Boolean) null;
									}
									
									if(bpc.getNgayCham()==null) {
										currentDate = LocalDate.now();
									}
									else {
//			         				currentDate=bpc.getNgayCham();
									}
									
									
									Object []obj= { bpc.getMaCongNhan().getMaCongNhan(),bpc.getTenCongnhan() ,bpc.getMaCongDoan().getMaCongDoan(),"",currentDate,lun,slpc};
									 
									modeldscongnhanphancong.addRow(obj);
									
								}
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			         		tabledspc.setModel(modeldscongnhanphancong);
			         		int rowCount = modeldscongnhanphancong.getRowCount();
							System.out.println("so dong loc bang :"+rowCount+"");
							
//							System.out.println(modeldscongnhanphancong.getValueAt(selectedRow,  0).toString()+"");
								if(rowCount==0) {
		//							JOptionPane.showMessageDialog(null, "Error:zz");
									modeldscongnhanphancong.getDataVector().removeAllElements();
									 Object []obj= {"","","",""};
									 
									 modeldscongnhanphancong.addRow(obj);
									if (modeldscongnhanphancong.getRowCount() > 0) {
										modeldscongnhanphancong.removeRow(0);
									}
								}
//						 
					  }
			            else {
			            	modeldscongnhanphancong.getDataVector().removeAllElements();
							 Object []obj= { "" ,"","","","",""};
							 
							 modeldscongnhanphancong.addRow(obj);
							if (modeldscongnhanphancong.getRowCount() > 0) {
								modeldscongnhanphancong.removeRow(0);
							}
							tabledspc.setModel(modeldscongnhanphancong);
			            }
					
				
					
				}
			}); 
		 
		
			filterchuaphancong.setBounds(1159, 322, 115, 23);
			panel_4.add(filterchuaphancong);
			
			ButtonGroup trangthaiBG=new ButtonGroup();
			trangthaiBG.add(filterchuaphancong);
			trangthaiBG.add(fildaphancong);
			trangthaiBG.add(filtertatcaPhanCong);
//		tabledspc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//		    public void valueChanged(ListSelectionEvent e) {
//		        if (!e.getValueIsAdjusting()) {
//		            int selectedRow = tabledspc.getSelectedRow();
//		            if (selectedRow != -1) {
//		             
//		            	
//		            	
//		             String maSanPhamSelected="";
//			            int selectedRowCNSP = tablesppc.getSelectedRow();
//				            if (selectedRowCNSP != -1) {
//				                maSanPhamSelected = tablesppc.getValueAt(selectedRowCNSP, 1).toString();
//				                tabledspc.setValueAt(maSanPhamSelected, selectedRow, 3);
//				            }
//				            
//				            
//		            	String maCongDoanSelected="";
//		            	 int  selectedRowCNCD= tablecdpc.getSelectedRow();
//			                if (selectedRowCNCD != -1) {
//			                	maCongDoanSelected = tablecdpc.getValueAt(selectedRowCNCD, 0).toString();
//			                	tabledspc.setValueAt(maCongDoanSelected, selectedRow, 2);
//			                }
			                
//			                --
			                
			                
//			       		 Date currentDate = new Date();
			        		
			                
//			                --
			                
//			                btnthemcongdoan.addActionListener(new ActionListener() {
//			        			public void actionPerformed(ActionEvent e) {
//			        				
//			        				tabledspc.setValueAt(true, selectedRow, 5);
//			        				
//			        				
//			        				String macn=tabledspc.getValueAt(selectedRow, 0).toString() ;
//			        				String macd=tabledspc.getValueAt(selectedRow, 2).toString() ;
//			        				LocalDate Localngayphancong= LocalDate.now() ;
//			        				java.sql.Date sqlDatePhanCong = java.sql.Date.valueOf(Localngayphancong);
//			        				
//			        				
//			    		        	BangPhanCong bpc= new  BangPhanCong(new CongNhan(macn), new CongDoan(macd),Localngayphancong , 1);
//			    					System.out.println(bpc.toString());
//			    					bpcdao.insertPhanCong(bpc);
//			    		        	
//			    		        	JOptionPane.showMessageDialog(null, "Phân công thành công");
//			    		            
//			        			}
//			        		});
//			           	
//			                
//		            }
//		        }
//		    }
//		});
//		
		
	}
}

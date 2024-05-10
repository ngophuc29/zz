package run    ;

import java.rmi.Remote  ;  
import java.rmi.RemoteException   ;
import java.time.LocalDate    ;
import java.time.LocalTime ;
import java.util.List    ;

import Entity.BangLuongCongNhan    ;
import Entity.BangLuongNhanVien   ;
import Entity.BangPhanCong    ;
import Entity.ChamCongCongNhan    ;
import Entity.ChamCongNhanVien  ;
import Entity.CongDoan    ;
import Entity.CongDoanDonHang  ;
import Entity.CongNhan   ;
import Entity.DonHang   ;
import Entity.NhanVien    ;
import Entity.SanPham   ;

public interface QuanLyLuongService extends Remote{
	public List<BangLuongCongNhan> getchamcongtheongayCN(int thang , int nam)  throws RemoteException;
	public boolean updateBangLuongCN(String maCN)throws RemoteException;
	public List<BangLuongNhanVien> getchamcongtheongay(int thang , int nam)throws RemoteException;
	public List<BangLuongNhanVien> getchamcongtheongayTheoTungNhanVien(String manv,int thang , int nam )throws RemoteException;
	public boolean updateBangLuong(String maNV)throws RemoteException;
	public boolean updateHeSoLuong(double hsLuong ,String ma  ) throws RemoteException;
	public List<BangPhanCong> DSPC(String maCongDoan)throws RemoteException;
	public  List<BangPhanCong> DSPC()throws RemoteException;
	public List<BangPhanCong> DSPC(String maCongDoan,int trangthai)throws RemoteException;
	public boolean insertPhanCong(BangPhanCong bpc)throws RemoteException;
	public int tongsoluongphancong(String maCD)throws RemoteException;
	public int soluongcua1congdoan(String maCD)throws RemoteException;
	public boolean deleteBangPhanCongTheoMaCN(String macn)throws RemoteException;
	public boolean updateHoanThanh(String macn,String maCongDoan,String macongdoandonhang )throws RemoteException;
	public List<ChamCongCongNhan> getDSChamCongCongDoan(String maCongDoan,String macongdoanphancong)throws RemoteException;
	public List<ChamCongCongNhan> getDSChamCongCongDoantheoNgay( String maCongDoan,LocalDate ngaycham1,String macongdoanphancong)throws RemoteException;
	public boolean themChamCongCongNhan(ChamCongCongNhan cccn)throws RemoteException;
	public boolean updateChamCong( int sogiotangca,int soluong,LocalDate ngaycham, String machamcong) throws RemoteException;
	public List<ChamCongCongNhan> getchamcongtheomaCn(String maCn,int thang ,int nam)throws RemoteException;
	public boolean TaoBangChamCong( ) throws RemoteException;
	public  List<ChamCongNhanVien> getchamcongtheongay(LocalDate ngaycham)throws RemoteException;
	public List<ChamCongNhanVien> getchamcongdahiendien(LocalDate ngaycham)throws RemoteException;
	public boolean updateChamCong(int hiendien,String calam,String ghichu,int sogiotangca,LocalTime thoigianden, LocalTime thoigiandi,int ditre,String machamcong,LocalDate ngaycham) throws RemoteException;
	public boolean updateChamCong1(int hiendien,String manv,LocalDate ngaycham)throws RemoteException;
	public boolean updateChamCong2(String manv,LocalDate ngaycham)throws RemoteException;
	public List<ChamCongNhanVien> getchamcongtheomaNV(String maNV,int thang ,int nam)throws RemoteException;
	public boolean updatethoigiandenvathoigiandi(String maNV )throws RemoteException;
	public boolean updatecalamvaghichu(String maNV )throws RemoteException;
	public boolean updateditre(String maNV )throws RemoteException;
	public List<CongDoan> getAllcongdoantheosanpham(String masp)throws RemoteException;
	public List<CongDoan> getAllcongdoanDonhANGtheosanpham(String masp)throws RemoteException;
	public   boolean themDanhSachCD(CongDoan cd)throws RemoteException;
	public   boolean updateCongDoan(double gia ,String ma  )throws RemoteException;
	public   boolean themCongDoanDonHang(String maDonHang)throws RemoteException;
	public List<CongDoanDonHang> getAllcongdoanDonHangtheosanpham(String masp)throws RemoteException;
	public List<CongDoanDonHang> getAllcongdoanDonHangtheosanphamCo2MaSptrungnhau(String masp ,String madonhang)throws RemoteException;
	public  boolean updateTiendo( int tiendo,String maCongDoan,String macongdoandonhang )throws RemoteException;
	public  boolean updatetrangthaiCongDoanDonHang(  String maCongDoan,String macongdoandonhang )throws RemoteException;
	public int tiendocuacongdoandonhangtruoc (String MaCongDoanDonHang)throws RemoteException;
	public int tiendocuacongdoandonhangHientai (String MaCongDoanDonHang)throws RemoteException;
	public List<CongNhan> getAllcongNhan()throws RemoteException;
	public  boolean themDanhSachCN(CongNhan cn)throws RemoteException;
	public  boolean update(CongNhan cn)throws RemoteException;
	public List<CongNhan> getAllCongNhanTheoma(String ma)throws RemoteException;
	public List<DonHang> DSDH()throws RemoteException;
	public  boolean insertDonhang(DonHang dh)throws RemoteException;
//	public  boolean deleteBangPhanCongTheoMaCN(String macn)throws RemoteException;
	public  boolean updateHoanThanh( LocalDate ngayhoanthanh,String macongdoanhonhang, String madonhangdungdeupdate )throws RemoteException;
	public List<NhanVien> getAllnhanvien()throws RemoteException;
	public  boolean themDanhSachNV(NhanVien nv)throws RemoteException;
	public List<NhanVien> getAllnhanvienTheoten(String ten)throws RemoteException;
	public List<NhanVien> getAllnhanvienTheoChucVu(String chucvu)throws RemoteException;
	public  boolean update(NhanVien nv)throws RemoteException;
	public String laychucvu (String manv)throws RemoteException;
	public List<SanPham> getAllsanPham()throws RemoteException;
	public List<SanPham> getAllsanPhamDangsanxuat()throws RemoteException;
	public  boolean themDanhSachSP(SanPham sp)throws RemoteException;
	public List<SanPham> getAllsanPhamDangDathang()throws RemoteException;
	public List<SanPham> getAllsanPhamTheoten(String ten)throws RemoteException;
	public  boolean updatesanpham(int trangthai ,String ma  )throws RemoteException;
	public String tongluongnhanvien(int thang ,int nam)throws RemoteException;
	public String tongluongcongnhan(int thang ,int nam)throws RemoteException;
	public String tongluong (int thang,int nam)throws RemoteException;
	public String tongdoanhthu (int thang,int nam)throws RemoteException;
	public int tongsanphamhoanthanh (int thang,int nam)throws RemoteException;
	public List<Integer> tongSoLuong(int thang, int nam)throws RemoteException;
	public List<String> tensanpham( ) throws RemoteException;
	public List<BangLuongNhanVien> getthongkeluongnhanvien( int thang , int nam )throws RemoteException;List<BangLuongCongNhan> getthongkeluongcongnhan( int thang , int nam )throws RemoteException;
	public List<ChamCongCongNhan> getthongkesanpham( int thang , int nam )throws RemoteException;


}

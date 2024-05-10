package run;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Entity.CongNhan;
import Entity.NhanVien;
import services.QuanLyLuongService;

public class mainclient {
	public static void main(String[] args) {
		try {
            // Kết nối tới registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lấy đối tượng từ registry thông qua tên đăng ký
            QuanLyLuongService service = (QuanLyLuongService) registry.lookup("QuanLyLuongService");
            
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    new LoginGUI(service);
//                }
//            });
            
           for (NhanVien c : service.getAllnhanvien()) {
			System.out.println(c.getCmnd());
		}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

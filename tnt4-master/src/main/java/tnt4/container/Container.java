package tnt4.container;

import java.util.Scanner;

import tnt4.controller.OperationController;
import tnt4.controller.Session;
import tnt4.dao.OperationDao;
import tnt4.dao.AdminDao;
import tnt4.dao.MemberDao;
import tnt4.db.DBConnection;
import tnt4.service.AdminService;
import tnt4.service.MemberService;
import tnt4.service.OperationService;

public class Container {
	public static Scanner sc;
	public static Session session;
	public static DBConnection dbConnection;
	public static OperationDao operationDao;
	public static MemberDao memberDao;
	public static OperationService operationService;
	public static MemberService memberService;
	public static AdminDao adminDao;
	public static AdminService adminService;
	public static OperationController operationController;

	static {
		operationDao = new OperationDao();
		memberDao = new MemberDao();
		adminDao = new AdminDao();

		operationService = new OperationService();
		memberService = new MemberService();
		adminService = new AdminService();
		
		operationController = new OperationController ();
	}

	public static Scanner getScanner() {
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		return sc;
	}

	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}
	
	public static AdminService getAdminService() {
        if (adminService == null) {
            adminService = new AdminService();
        }
        return adminService;
    }
}
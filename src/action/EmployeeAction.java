package action;

import java.util.List;

import service.EmployeeDAO;

import com.opensymphony.xwork2.ModelDriven;

import entity.Check;
import entity.Employee;
import entity.Order;
import entity.Room;

public class EmployeeAction extends SuperAction implements
		ModelDriven<Employee> {

	private static final long serialVersionUID = 1L;
	private Employee employee = new Employee();

	// Ա����½����
	public String login() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		if (employeeDAO.employeeLogin(employee)) {
			// ��session�б����½�ɹ����û���
			session.setAttribute("loginemployeeName", employee.getName());
			return "employee_login_success";
		} else {
			return "employee_login_faliure";
		}

	}

	// Ա���ǳ�����
	public String logout() {
		if (session.getAttribute("loginemployeeName") != null) {
			session.invalidate();
		}
		return "employee_logout";
	}

	// ��ѯ���з���ľ�����Ϣ
	public String queryHomeStatus() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Room> rlist = employeeDAO.queryAllHome();
		// �Ž�request��
		if (rlist != null) {
			// session.setAttribute("AllHome_list", rlist);
			request.setAttribute("AllHome_list", rlist);
			System.out.println(rlist.get(1).getPrice());
		}
		return "queryHomeStatus_success";
	}

	// ��ѯָ�����ͷ������Ϣ
	public String queryOneTypeHome() {
		String roomtype = request.getParameter("roomtype");
		if (roomtype.equals("*")) {
			roomtype = "���˷�','˫�˷�','�󴲷�','���Է�','���÷�";
		}
		String status = request.getParameter("status");
		if (status.equals("*")) {
			status = "�շ�','�ǿ�";
		}
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Room> rList = employeeDAO.queryOneTypeHome(roomtype, status);
		if (rList != null) {
			// session.setAttribute("AllHome_list", rList);
			request.setAttribute("AllHome_list", rList);
		}
		return "queryOneTypeHome_success";
	}

	// ��ʾԤ������
	public String dealOrder() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Order> olist = employeeDAO.queryUnDealOrder();
		session.setAttribute("undealorderList", olist);
		return "goto_dealOrder_success";
	}

	// ȷ��Ԥ������
	public String confirmOrder() {
		Integer orderid = Integer.valueOf(request
				.getParameter("orderConfirmid"));
		EmployeeDAO employeeDAO = new EmployeeDAO();
		String employeeName = (String) session
				.getAttribute("loginemployeeName");
		// System.out
		// .println(employeeName + "=2222222222222222222222222222222222");
		Employee employee = employeeDAO.queryEmployeeByName(employeeName);
		// System.out.println(employee + "=3333333333333333333333333333333");
		Order order = employeeDAO.queryOrderByID(orderid);

		Check check = new Check(employee, order.getRoom(), order.getUser(),
				order.getTimein(), order.getTimeout(), "����ס");
		// System.out.println(check + "=555555555555555555555555555555555");
		order.setStatus("Ԥ���ɹ�");
		System.out.println(order + "=444444444444444444444444444444444444");
		employeeDAO.dealOrder(order, check);
		return "confirmOrder_success";
	}

	// �鿴Ŀǰס��״̬
	public String NowStatus() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Check> cList = employeeDAO.queryNowHomeStatus();
		if (cList != null) {
			session.setAttribute("NowStatusList", cList);
		}
		return "query_NowStatus_success";
	}

	// ������ס
	public String dealLivein() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Check> cList = employeeDAO.queryUnLive();
		session.setAttribute("UnLiveList", cList);
		return "goto_dealLivein";
	}

	// ȷ����ס
	public String confirmLiveIn() {
		Integer checkid = Integer.valueOf(request
				.getParameter("liveConfirmcheckid"));
		EmployeeDAO employeeDAO = new EmployeeDAO();
		String employeeName = (String) session
				.getAttribute("loginemployeeName");
		Employee employee = employeeDAO.queryEmployeeByName(employeeName);
		Check check = employeeDAO.queryChrckByID(checkid);
		check.setStatus("��ס�ɹ�");
		employeeDAO.updateCheck(check);
		return "confirmLiveIn_success";
	}

	// �ص���ҳ����
	public String returnToIndex() {
		return "Employee_returnToIndex";
	}

	// �޸ķ�����Ϣ
	public String updateRoom() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// ���Ȳ�ѯ����δ��ס����ķ����
		List<Room> sList = employeeDAO.queryAllnumber_UnLive();
		session.setAttribute("UnLiveRoomList", sList);
		return "goto_updateRoom";
	}

	// �޸�ĳ�䷿�����Ϣ
	public String updateOneRoom() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Integer roomnumber = Integer
				.valueOf(request.getParameter("roomnumber"));
		Double price = Double.valueOf(request.getParameter("price"));
		Room room = employeeDAO.queryRoomByNumber(roomnumber);
		String roomtype = request.getParameter("roomtype");
		room.setPrice(price);
		room.setRoomtype(roomtype);
		employeeDAO.updateRoom(room);
		return "updateOneRoom_success";
	}

	// ȷ���˷�
	public String LeaveRoom() {
		Integer leavecheckid = Integer.valueOf(request
				.getParameter("leavecheckid"));
		EmployeeDAO employeeDAO = new EmployeeDAO();
		String employeeName = (String) session
				.getAttribute("loginemployeeName");
		Employee employee = employeeDAO.queryEmployeeByName(employeeName);
		Check check = employeeDAO.queryChrckByID(leavecheckid);
		Room room = check.getRoom();
		check.setStatus("���˷�");
		room.setStatus("�շ�");
		employeeDAO.updateCheckAndRoom(check, room);
		return "LeaveRoom_success";
	}

	// ������ʷס����Ϣ
	public String historyStatus() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Check> cList = employeeDAO.queryHistory();
		if (cList != null) {
			session.setAttribute("HistoryList", cList);
		}
		return "historyStatus_success";
	}

	@Override
	public Employee getModel() {
		return this.employee;
	}

}

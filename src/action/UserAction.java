package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import service.UserDAO;

import com.opensymphony.xwork2.ModelDriven;

import entity.Check;
import entity.Description;
import entity.Order;
import entity.Remainroom;
import entity.Room;
import entity.User;

public class UserAction extends SuperAction implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private User user = new User();

	// δע��ʱ�ص���ҳ
	public String returnToIndex1() {
		return "returnToIndex1";
	}

	// ע���ص���ҳ
	public String returnToIndex2() {
		return "returnToIndex2";
	}

	// ע���û����˳�
	public String logout() {
		session.invalidate();
		return "logout_success";
	}

	// �����û���¼ҳ��
	public String login() {
		return "goto_login";
	}

	// ��ת���û�ע��ҳ��
	public String goto_register() {
		return "goto_register";
	}

	// ��֤�û���½��Ϣ
	public String loginSuccess() {
		UserDAO userDAO = new UserDAO();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if (userDAO.userLogin(name, password)) {
			// ��session�б����½�ɹ����û���
			session.setAttribute("user_name", name);
			return "user_login_success";
		} else {
			return "user_login_faliure";
		}
	}

	// ������û�ע����Ϣ
	public String saveRegister() {
		User user = new User(request.getParameter("name"),
				request.getParameter("idnumber"),
				request.getParameter("password"), request.getParameter("phone"));
		UserDAO userDAO = new UserDAO();
		userDAO.saveNewUser(user);
		session.setAttribute("user_name", user.getName());
		return "saveRegister_success";
	}

	// �û�δע��ʱ�鿴�Ƶ귿��
	public String viewRoom() {
		UserDAO userDAO = new UserDAO();
		List<Remainroom> list = userDAO.queryRoom_View();
		// �Ž�request��
		if (list != null) {
			request.setAttribute("AllHome_list", list);
		}
		return "viewRoom_success";
	}

	// �û�δע��ʱ��ѯָ�����ͷ�����Ϣ
	public String queryOneTypeHome() {
		String roomtype = request.getParameter("roomtype");
		if (roomtype.equals("*")) {
			roomtype = "���˷�','˫�˷�','�󴲷�','���Է�','���÷�";
		}
		UserDAO userDAO = new UserDAO();
		List<Remainroom> rList = userDAO.queryOneTypeHome(roomtype);
		if (rList != null) {
			request.setAttribute("AllHome_list", rList);
		}
		return "Firstuser_queryOneTypeHome_success";
	}

	// �鿴�Ƶ�����
	public String queryMessage() {
		UserDAO userDAO = new UserDAO();
		List<Description> list = userDAO.queryDescriptions();
		if (list != null) {
			session.setAttribute("Description_list", list);
		}
		return "queryMessage_success";
	}

	// �޸��û���Ϣ
	public String updateUser() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.queryUserByName(session.getAttribute("user_name")
				.toString());
		// �Ž�session��
		if (user != null) {
			session.setAttribute("user_self", user);
		}
		return "goto_updateUser";
	}

	// �����û��ύ�ĸ�����Ϣ
	public String saveUpdateOne() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.queryUserByName(session.getAttribute("user_name")
				.toString());
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		userDAO.updateUser(user);
		session.setAttribute("user_self", user);
		return "saveUpdateOne_success";
	}

	// �û���¼���ѯԤ����Ϣ
	public String queryOrder() {
		UserDAO userDAO = new UserDAO();
		List<Order> list = userDAO.queryUserOrder(session.getAttribute(
				"user_name").toString());
		if (list != null) {
			session.setAttribute("User_OrderList", list);
		}
		return "user_queryOrder_success";
	}

	// ��ѯ��ʷס����¼
	public String historyRoom() {
		UserDAO userDAO = new UserDAO();
		List<Check> cList = userDAO.queryHistory(session.getAttribute(
				"user_name").toString());
		if (cList != null) {
			session.setAttribute("User_HistoryList", cList);
		}
		return "historyRoom_success";
	}

	// ת���Ƶ�����ҳ��
	public String giveComment() {
		UserDAO userDAO = new UserDAO();
		List<Check> cList = userDAO.queryUnComment(session.getAttribute(
				"user_name").toString());
		if (cList != null) {
			session.setAttribute("UnCommentList", cList);
		}
		return "goto_giveComment";
	}

	// ת������ҳ��
	public String giveOneComment() {
		UserDAO userDAO = new UserDAO();
		Integer checkid = Integer.valueOf(request
				.getParameter("UnCommentCheckId"));
		Check check = userDAO.queryCheckByID(checkid);
		if (check != null) {
			session.setAttribute("Commentcheck", check);
		}
		return "giveOneComment_success";
	}

	// �����û�����
	public String saveComment() throws ParseException {
		UserDAO userDAO = new UserDAO();
		Integer checkid = Integer.valueOf(request.getParameter("checkid"));
		Check check = userDAO.queryCheckByID(checkid);
		String content = request.getParameter("content");
		Date date = new Date();
		Description description = new Description(check, content, date);
		userDAO.saveComment(description);
		return "saveComment_success";
	}

	// ��½��鿴�Ƶ�����
	public String queryMessageAfter() {
		UserDAO userDAO = new UserDAO();
		List<Description> list = userDAO.queryDescriptions();
		if (list != null) {
			session.setAttribute("Description_list", list);
		}
		return "queryMessageAfter_success";
	}

	// ת��Ԥ���������
	public String orderRoom() {
		UserDAO userDAO = new UserDAO();
		List<Remainroom> list = userDAO.queryRoom_View();
		// �Ž�request��
		if (list != null) {
			request.setAttribute("AllHome_list", list);
		}
		return "goto_orderRoom";
	}

	// �û���½���ѯָ�����ͷ�����Ϣ
	public String queryOneTypeHomeAfter() {
		String roomtype = request.getParameter("roomtype");
		if (roomtype.equals("*")) {
			roomtype = "���˷�','˫�˷�','�󴲷�','���Է�','���÷�";
		}
		UserDAO userDAO = new UserDAO();
		List<Remainroom> rList = userDAO.queryOneTypeHome(roomtype);
		if (rList != null) {
			request.setAttribute("AllHome_list", rList);
		}
		return "queryOneTypeHomeAfter_success";
	}

	// Ԥ������
	public String selectOrderRoom() {
		String roomtype = request.getParameter("roomtype");
		UserDAO userDAO = new UserDAO();
		List<Integer> list = userDAO.queryOneTypeHomeNumber(roomtype);
		System.out.println(list.toString() + "===================="
				+ list.size());
		if (list != null) {
			session.setAttribute("OrderRoomNumberList", list);
		}
		session.setAttribute("roomtype", roomtype);
		return "selectOrderRoom_success";
	}

	// �ύԤ����Ϣ
	public String saveOrderRoom() throws ParseException {
		UserDAO userDAO = new UserDAO();
		Integer roomnumber = Integer
				.valueOf(request.getParameter("roomnumber"));
		User user = userDAO.queryUserByName(session.getAttribute("user_name")
				.toString());
		Room room = userDAO.queryRoomByNumber(roomnumber);
		Date timein = new SimpleDateFormat("yyyy-MM-dd").parse(request
				.getParameter("timein"));
		Date timeout = new SimpleDateFormat("yyyy-MM-dd").parse(request
				.getParameter("timeout"));
		Order order = new Order(room, user, timein, timeout, "������");
		userDAO.saveNewOrder(order);
		return "saveOrderRoom_success";
	}

	@Override
	public User getModel() {
		return this.user;
	}

}

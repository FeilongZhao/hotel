package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Check;
import entity.Employee;
import entity.Order;
import entity.Room;

public class EmployeeDAO {
	// Ա����¼
	public boolean employeeLogin(Employee employee) {
		// �������
		Transaction transaction = null;
		String hql = "";
		try {
			Session session = HibernateSessionFactory.getSessionFactory()
					.openSession();
			// ��������
			transaction = session.beginTransaction();
			hql = "from Employee where name=? and password=?";
			Query query = session.createQuery(hql);
			// ����hql������ռλ���Ĳ���
			query.setParameter(0, employee.getName());
			query.setParameter(1, employee.getPassword());
			List list = query.list();
			// �ύ����,�����ڷ���֮ǰ
			transaction.commit();
			// ��ʼ��ѯ
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯ�Ƶ����з�����Ϣ
	public List<Room> queryAllHome() {
		Transaction transaction = null;
		List<Room> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Room";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			transaction.commit();
			return rlist;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return rlist;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯָ�����ͷ�����Ϣ
	public List<Room> queryOneTypeHome(String roomtype, String status) {
		Transaction transaction = null;
		List<Room> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Room where roomtype in('" + roomtype
					+ "')and status in('" + status + "')";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			transaction.commit();
			return rlist;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return rlist;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯ���д�ȷ����Ԥ������
	public List<Order> queryUnDealOrder() {
		Transaction transaction = null;
		List<Order> list = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Order where status='������'";
			Query query = session.createQuery(hqlString);
			list = query.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return list;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯĿǰס����Ϣ
	public List<Check> queryNowHomeStatus() {
		Transaction transaction = null;
		List<Check> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = " from Check where status='��ס�ɹ�'";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			transaction.commit();
			return rlist;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return rlist;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯĿǰ����ס��Ϣ
	public List<Check> queryUnLive() {
		Transaction transaction = null;
		List<Check> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = " from Check where status='����ס'";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			transaction.commit();
			return rlist;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return rlist;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯ����δ��ס�ķ����
	public List<Room> queryAllnumber_UnLive() {
		Transaction transaction = null;
		List<Room> sList = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Room where status='�շ�'";
			Query query = session.createQuery(hqlString);
			sList = query.list();
			transaction.commit();
			return sList;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return sList;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ����Ԥ������,ͬʱ��check������¼�¼
	public void dealOrder(Order order, Check check) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(check);
			session.update(order);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ����orderid��ѯorder����Ϣ
	public Order queryOrderByID(Integer orderid) {
		Transaction transaction = null;
		List<Order> rlist = null;
		Order order = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Order where orderid='" + orderid + "'";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			order = rlist.get(0);
			transaction.commit();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return order;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ����checkid��ѯcheck����Ϣ
	public Check queryChrckByID(Integer checkid) {
		Transaction transaction = null;
		List<Check> rlist = null;
		Check check = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Check where checkid='" + checkid + "'";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			check = rlist.get(0);
			transaction.commit();
			return check;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return check;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ����check����Ϣ
	public void updateCheck(Check check) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(check);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// �鿴��ʷס����Ϣ
	public List<Check> queryHistory() {
		Transaction transaction = null;
		List<Check> sList = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Check where status='���˷�'";
			Query query = session.createQuery(hqlString);
			sList = query.list();
			transaction.commit();
			return sList;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return sList;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ����check���room����Ϣ
	public void updateCheckAndRoom(Check check, Room room) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(check);
			session.update(room);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ����room����Ϣ
	public void updateRoom(Room room) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(room);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ���ݷ���Ų�ѯ������Ϣ
	public Room queryRoomByNumber(Integer roomnumber) {
		Transaction transaction = null;
		List<Room> rlist = null;
		Room room = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Room where roomnumber='" + roomnumber + "'";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			room = rlist.get(0);
			transaction.commit();
			return room;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return room;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// ��ѯ��ǰԱ����Ϣ
	public Employee queryEmployeeByName(String name) {
		Transaction transaction = null;
		List<Employee> rlist = null;
		Employee employee = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Employee where name='" + name + "'";
			Query query = session.createQuery(hqlString);
			rlist = query.list();
			employee = rlist.get(0);
			transaction.commit();
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return employee;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}
}

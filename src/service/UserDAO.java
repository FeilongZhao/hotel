package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Check;
import entity.Description;
import entity.Order;
import entity.Remainroom;
import entity.Room;
import entity.User;

public class UserDAO {
	// �������û�
	public void saveNewUser(User user) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(user);
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

	// ��ѯ�Ƶ����пշ���Ϣ������ͼ��
	public List<Remainroom> queryRoom_View() {
		Transaction transaction = null;
		List<Remainroom> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Remainroom";
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
	public List<Remainroom> queryOneTypeHome(String roomtype) {
		Transaction transaction = null;
		List<Remainroom> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Remainroom where roomtype in('" + roomtype + "')";
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

	// ��ѯ�Ƶ���������
	public List<Description> queryDescriptions() {
		Transaction transaction = null;
		List<Description> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Description";
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

	// �û���¼
	public boolean userLogin(String name, String password) {
		// �������
		Transaction transaction = null;
		String hql = "";
		try {
			Session session = HibernateSessionFactory.getSessionFactory()
					.openSession();
			// ��������
			transaction = session.beginTransaction();
			hql = "from User where name=? and password=?";
			Query query = session.createQuery(hql);
			// ����hql������ռλ���Ĳ���
			query.setParameter(0, name);
			query.setParameter(1, password);
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

	// ��ʾ������Ϣ
	public User queryUserByName(String name) {
		Transaction transaction = null;
		List<User> clist = null;
		User user = null;
		String hql = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hql = "from User where name='" + name + "'";
			Query query = session.createQuery(hql);
			clist = query.list();
			user = clist.get(0);
			transaction.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return user;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	// �����û��޸ĵĸ�����Ϣ
	public boolean updateUser(User user) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			return true;
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

	// ��ѯ�û�����Ԥ������
	public List<Order> queryUserOrder(String name) {
		Transaction transaction = null;
		List<Order> list = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Order where user.name='" + name + "'";
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

	// �鿴��ʷס����Ϣ
	public List<Check> queryHistory(String name) {
		Transaction transaction = null;
		List<Check> sList = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Check where status in ('���˷�','��ס�ɹ�') and user.name='"
					+ name + "'";
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

	// ��ѯδ���۶���
	public List<Check> queryUnComment(String name) {
		Transaction transaction = null;
		List<Check> sList = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Check where status in ('���˷�','��ס�ɹ�') and user.name='"
					+ name
					+ "'and checkid not in(select check.checkid from Description)";
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

	// ����checkid��ȡcheck��Ϣ
	public Check queryCheckByID(Integer checkid) {
		Transaction transaction = null;
		List<Check> clist = null;
		Check check = null;
		String hql = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hql = "from Check where checkid='" + checkid + "'";
			Query query = session.createQuery(hql);
			clist = query.list();
			check = clist.get(0);
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

	// �����û�����
	public void saveComment(Description description) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(description);
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

	// ��ѯָ�����ͷ���ķ����
	public List<Integer> queryOneTypeHomeNumber(String roomtype) {
		Transaction transaction = null;
		List<Integer> rlist = null;
		String hqlString = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hqlString = "from Room where roomtype='" + roomtype
					+ "' and status='�շ�'";
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

	// ���ݷ���Ż�ȡ������Ϣ
	public Room queryRoomByNumber(Integer roomnumber) {
		Transaction transaction = null;
		List<Room> clist = null;
		Room room = null;
		String hql = "";
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			hql = "from Room where roomnumber='" + roomnumber + "'";
			Query query = session.createQuery(hql);
			clist = query.list();
			room = clist.get(0);
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

	// ���涩����Ϣ
	public void saveNewOrder(Order order) {
		Transaction transaction = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(order);
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
}

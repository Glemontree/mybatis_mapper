package test.com.glemontree.mybatis.bean;

import com.glemontree.mybatis.bean.Department;
import com.glemontree.mybatis.bean.Employee;
import com.glemontree.mybatis.dao.DepartmentMapper;
import com.glemontree.mybatis.dao.EmployeeMapper;
import com.glemontree.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Employee Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 1, 2017</pre>
 */
public class EmployeeTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    /**
     * mybatis������ɾ��ֱ�Ӷ������·���ֵ��
     *      Integer��Long��Boolean
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // ��ȡ��sqlSession�����Զ��ύ����
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            // �������
            Employee employee = new Employee(null, "jerry", "jerry@gmail.com", "1");
            employeeMapper.addEmp(employee);
            System.out.println(employee.getId());

            // �����޸�
//            Employee employee = new Employee(1, "jerry", "jerry@gmail.com", "1");
//            employeeMapper.updateEmp(employee);

            // ����ɾ��
//            employeeMapper.deleteEmpById(2);

            // �ֶ��ύ����
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test04() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(1, "jerry");
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test05() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1);
            map.put("lastName", "jerry");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test06() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.getEmpsByLastNameLike("%e%");
            for (Employee e : employees) {
                System.out.println(e);
            }
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test07() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
            System.out.println(map);
            System.out.println(map.size());
            System.out.println(map.get("email"));
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test08() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Employee> map = mapper.getEmpByLastNameReturnMap("%r%");
            System.out.println(map);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test09() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test10() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpAndDept(1);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test11() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee.getLastName());
            //System.out.println(employee.getDept());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test12() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department);
            System.out.println(department.getEmps());
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test13() throws IOException {
        // 1����ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2����ȡsqlSession����
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3����ȡ�ӿڵ�ʵ�������
            //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdStep(1);
            System.out.println(department);
            System.out.println(department.getEmps());
        } finally {
            openSession.close();
        }
    }

} 

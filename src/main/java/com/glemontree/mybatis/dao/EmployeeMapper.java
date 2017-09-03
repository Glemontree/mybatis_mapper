package com.glemontree.mybatis.dao;

import com.glemontree.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * mybatis参数处理：
 *      （1） 单个参数：mybatis不会做特殊处理，参数名任意取都可以
 *              #{参数名}：取出参数值
 *       (2) 多个参数：mybatis遇见多个参数，多个参数会被封装成一个map，#{}就是从map中获取指定key的值
 *              而多个参数封装成map的key为param1，...，paramN，而value就是传入的参数值
 *              或者参数的索引也可以
 *           多个参数的情况下推荐使用命名参数，明确指定封装参数时map的key
 *           key：使用@param注解指定的值
 *           value：参数值
 *           如果多个参数正好是业务逻辑的数据模型，就可以直接诶传入POJO
 *              #{属性名}：取出传入的pojo属性值
 *
 *           Map：
 *           如果多个参数不是业务模型中数据，没有对应的pojo，为了方便，我们也可以传入map
 *           这种情况下，#{key}就是取出map中对应的值
 *
 *           TO：
 *           如果多个参数不是业务模型中的数据，但是经常要使用，推荐编写一个TO(Transfer Object)数据传输对象
 *           Page {
 *               int index;
 *               int size;
 *           }
 *
 * 结合源码，看看mybatis怎么处理参数
 *
 */
public interface EmployeeMapper {

    // 多条记录封装一个map：Map<Integer, Employee>：key：这条记录的主键，value：记录封装后的javabean
    @MapKey("lastName")
    public Map<String, Employee> getEmpByLastNameReturnMap(String lastName);

    // 返回一条记录的map：key：列名，value：对应的值
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Employee getEmpByMap(Map<String, Object> map);
    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
    public Employee getEmpById(Integer id);
    public void addEmp(Employee employee);
    public boolean updateEmp(Employee employee);
    public void deleteEmpById(Integer id);
}

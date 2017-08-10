package com.wyx.daili;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**Cglib则使用的继承机制，具体说被代理类和代理类是继承关系，所以代理类是可以赋值给被代理类的,
 * 如果被代理类有接口，那么代理类也可以赋值给接口,
 * Cglib则是对实现类进行代理
 * Created by Administrator on 2017/8/9.
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = method.invoke(o, objects);
        return result;
    }

    public static void main(String[] args) {
        //生成代理类到本地
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "e:/");
        UserServiceImpl serviceimpl = new UserServiceImpl();
        CglibProxy cg = new CglibProxy();
        UserService proxy = (UserService) cg.getProxy(serviceimpl.getClass());
        proxy.add();
        proxy.toString();
    }
}

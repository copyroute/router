package com.penguinsrising.util.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
Making a Spring bean Applicationcontext aware
Suppose we have a webapplication with spring,
and we are initializing spring’s WebApplicationContext by configuring ContextLoaderListener in web.xml.
How can we get an instance of spring’s applicationContext object?

Possible solutions:
While configuring spring through web.xml the spring ApplicationContext object is set in the ServletContext.
To get ApplicationContext we say

ApplicationContext applicationContext = (ApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

So we can get spring ApplicationContext only where ServletContext is avaliable i.e. in Servlet,ServletFilter or any implementation class of ServletContextListener.
What if we dont want to write a servletFilter or ServletContextListener .

What if we need to access a spring bean before a servletFilter or servlet is called(for authentication)and we dont want to write a ServletContextListener.

How can we obtain spring’s applicationContext in this case?
One way to achieve this would be to use the ApplicationContextAware interface provided by Spring.

Create a class which implements ApplicationContextAware. The method, “setApplicationContext(…)” gets called during the creation of this bean, providing the reference to the context. Our program should store this for a later interaction with the context.
*/

public class ApplicationContextProvider implements ApplicationContextAware 
{
	private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext = applicationContext;
    }
}

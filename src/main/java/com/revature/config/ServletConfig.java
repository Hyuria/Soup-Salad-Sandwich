package com.revature.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ServletConfig implements WebApplicationInitializer {
	// this class replaces the deployment descriptor (web.xml) file
	// which maps servlets. spring mvc follows the front controller
	// design pattern in which there is only one servlet that takes
	// all requests and dispatches them to controllers.
	// here we are mapping spring's front controller

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		// registering our configuration file with the application context
		appContext.register(SpringConfig.class);
		// servlet context is essentially the group of servlets and
		// the information that they all share access to (as opposed
		// to servlet config which is unique to each servlet)
		appContext.setServletContext(servletContext);
		// here we are adding spring's front controller - the dispatcher servlet
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
		// here we are setting it to initialize the servlet as soon as the
		// server starts up
		servlet.setLoadOnStartup(1);
		// here we are basically just saying that all paths go to this servlet
		servlet.addMapping("/");
		//appContext.close();
	}

}

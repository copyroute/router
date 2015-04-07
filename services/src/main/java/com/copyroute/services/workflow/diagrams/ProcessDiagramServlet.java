//package com.penguinsrising.services.workflow.diagrams;
//
//
//import com.copyroute.services.workflow.engine.BusinessProcessEngine;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.repository.ProcessDefinition;
//import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//// Tutorial Resources
//// https://app.camunda.com/confluence/display/foxUserGuide/Process+Diagram+API
//
//@WebServlet(value = "/processDiagram", loadOnStartup = 1)
//public class ProcessDiagramServlet extends HttpServlet {
//
//  protected static Logger logger = LoggerFactory.getLogger(ProcessDiagramServlet.class);
//  private static final long serialVersionUID = 1L;
//
//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
//		  
//	String processDefinitionId = request.getParameter("c");    
//	if (processDefinitionId==null) 
//	{
//	    String processDefinitionKey = request.getParameter("processDefinitionKey");    
//	    ProcessDefinition pdef = 
//	  		  BusinessProcessEngine.getRepositoryService().createProcessDefinitionQuery()
//	    		.processDefinitionKey(processDefinitionKey)
//	    		.latestVersion()
//	    		.singleResult();
//	    processDefinitionId = pdef.getId();
//	}
//	
//	InputStream processDiagram = BusinessProcessEngine.getRepositoryService().getProcessDiagram(processDefinitionId);
//	response.setContentType("image/png");
//	response.getOutputStream().write(IOUtils.toByteArray(processDiagram));
//  }
//}
//

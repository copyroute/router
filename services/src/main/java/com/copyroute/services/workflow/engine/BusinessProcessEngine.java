//package com.penguinsrising.services.workflow.engine;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.inject.Named;
//import javax.inject.Singleton;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//import org.activiti.cdi.BusinessProcess;
//import org.activiti.engine.FormService;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.IdentityService;
//import org.activiti.engine.ManagementService;
//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.ProcessEngineInfo;
//import org.activiti.engine.ProcessEngines;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.history.HistoricActivityInstance;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.identity.User;
//import org.activiti.engine.identity.UserQuery;
//import org.activiti.engine.impl.RepositoryServiceImpl;
//import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
//import org.activiti.engine.runtime.Execution;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
////// Activiti BPMN2 : Engine Services ==========================================
////
////// Tools for creating, suspending and activating processes
////// Also Allows access to diagrams and visual model
//	//public static RepositoryService repositoryService;
////
////// RuntimeService starts new process instances based on a specific process definition. 
////// It also provides simple query functionality, and methods to set and retrieve process variables
//	//public static RuntimeService runtimeService;
////
////// TaskService provides a lot of functionality surrounding user tasks for the Activiti engine. 
////// The TaskService may query the engine for specific tasks, or create new standalone-tasks for a specific user. 
//	//public static TaskService taskService;
////
////// Uses the "customSessionFactories" in the processEngineConfiguration in applicationContext.xml
//	//public static IdentityService identityService;
////
////// Provides Historical Claim Data (metrics)
//	//public static HistoryService historyService ;
////
////// Tools for interacting with form-data
//	//public static FormService formService ;
////
////// CDI Toolkit (Context Dependency Injection) 
//	//public static BusinessProcess businessProcess;
////
////// Harvest Data from ActivitiDB in SQL-Style Pages 
//	//public static ManagementService managementService ;
////
//*/
//
//@Named
//@Singleton
//@Component
//@Service("businessProcessEngine")
//public class BusinessProcessEngine implements IBusinessProcessEngine, ServletContextListener 
//{
//	// Pretty-Print Logging
//	protected static Logger logger = LoggerFactory.getLogger(BusinessProcessEngine.class);
//    public static void print(String text) { logger.info(" === Activiti : " + text + " : === "); }
//
//	// Singleton Engine Accessors ==========================================
//	// Basically, just exercising the api, to access the Activiti Services below
//	public static ProcessEngine 	getProcessEngine()		{ return ProcessEngines.getDefaultProcessEngine(); 	}
//	public static RuntimeService 	getRuntimeService()		{ return getProcessEngine().getRuntimeService(); 	}
//	public static TaskService 		getTaskService()		{ return getProcessEngine().getTaskService();		}
//	public static IdentityService 	getIdentityService()	{ return getProcessEngine().getIdentityService();	}
//	public static HistoryService 	getHistoryService()		{ return getProcessEngine().getHistoryService();	}
//	public static FormService 		getFormService()		{ return getProcessEngine().getFormService();		}
//	public static RepositoryService getRepositoryService()	{ return getProcessEngine().getRepositoryService();		}
//
//	// Wrapper-Processor for IdentityService
//	private static UserService userService;
//	public static UserService getUserService()	{ return userService; }
//	
//	// CTOR
//    public BusinessProcessEngine(){ 
//    	print(" === Activiti : CTOR === ");    	
//    }    
//	public void contextInitialized(ServletContextEvent servletContextEvent) {
//    	print(" === Activiti : Context-Initializing === ");
//		init();
//	}
//	public void contextDestroyed(ServletContextEvent servletContextEvent) {
//		ProcessEngines.destroy();
//    	print(" === Activiti : Context-Destroyed === ");
//	}
//	
//	public boolean init() {
//    	print(" === Activiti : Engine-Initializing === ");
//
//    	ProcessEngines.init();
//		print("ProcessEngine: " 		+ getProcessEngine().getName());
//		print("ProcessEngine.VERSION: " + ProcessEngine.VERSION);
//		for ( ProcessEngineInfo pei : ProcessEngines.getProcessEngineInfos())
//			print(pei.toString());
//    	
//		print(" === Activiti : Init-Complete === ");
//    	return true;
//	}
//
//    //  ================================================= Processes =================================================
//
//    // Main Init Work Function = Creates a new BPMN2 Process Instance
//    @Transactional
//	public ProcessInstance runProcess(String processName, Map<String, Object> variableMap, String user, String pass) {
//
//        // Check User ID
//   		if( UserService.checkID(user, pass, getProcessEngine().getIdentityService()) ){
//
//   			// Run BPMN Process
//			ProcessInstance procInst = getProcessEngine().getRuntimeService().startProcessInstanceByKey(processName, variableMap);
//			logger.info(
//					"Running Workflow Instance -"+
//						"\nProcessDefinitionID: " + procInst.getProcessDefinitionId() + 
//						"\nProcessInstanceID: " + procInst.getProcessInstanceId() 
//			);
//			return procInst;
//   		}	
//   		return null;
//    }
//
//    // Get all current processes (eg- OS process)
//	public List<ProcessInstance> getProcessInstanceUnique()
//	{
//		Set<String> uniqueProcesses = new HashSet<String>(10);
//	  	List<ProcessInstance> uniqueProcessInstances = new ArrayList<ProcessInstance>();
//	
//	  	// Filter Uniques
//	  	for(ProcessInstance processInstance: getRuntimeService().createProcessInstanceQuery().list() )
//	  		if ( uniqueProcesses.add( processInstance.getProcessDefinitionId() ) ){
//				uniqueProcessInstances.add(processInstance);
//
//				logger.info("ProcessInstance:" + 
//					processInstance.getId() + "--" + 
//					processInstance.getProcessDefinitionId() + "--" + 
//	  						processInstance.getProcessInstanceId());    	
//	  	    }
//				return uniqueProcessInstances; 
//	}  
//
//    // Get all current processes (eg- OS process)
//    public List<ProcessInstance> getProcessInstances(){
//    	return getRuntimeService().createProcessInstanceQuery().list();
//    }
//
//    // Get a specific ProcessInstances by ID
//    public ProcessInstance getProcessInstance(String processInstanceId){
//    	return getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
//    }
//        
//    public List<ProcessInstance> getActiveProcessInstances(){
//    	return getRuntimeService().createProcessInstanceQuery().active().list();
//    }
//    
//    //  ================================================= Executions =================================================
//
//    // Get a specific Execution by ID
//    public Execution getExecution(String executionId){
//    	return getRuntimeService().createExecutionQuery().executionId(executionId).singleResult();
//    }
//    
//    // Get all Executions belonging to a ProcessId
//    public List<Execution> getExecutionsByProcessInstance(String processInstanceId){
//    	List<Execution> executions = getRuntimeService().createExecutionQuery().processInstanceId(processInstanceId).list();
//    	logger.info("Executions Found:" + executions.size() );    	
//    	for(Execution execution : executions)
//    		logger.info("Execution:" + execution.getId() + "--" + execution.getProcessInstanceId() + "--" + execution.isEnded() );    	
//    	return executions;
//    }
//
//    // Get all current executions (eg-OS thread) 
//    public List<Execution> getExecutions()
//    {
//    	List<Execution> executions = getRuntimeService().createExecutionQuery().list();
//    	for(Execution execution : executions)
//    		logger.info("Execution:" + execution.getId() + "--" + execution.getProcessInstanceId() + "--" + execution.isEnded() );    	
//    	return executions;
//    }
//    
//	// Get all BPMN Variables from BPMN RuntimeService
//	public Map<String, Object> getVariables(ProcessInstance procInst){
//		Map<String, Object> retVals = getRuntimeService().getVariables(procInst.getId());
//		return retVals;
//	}
//
//	
//    //  ================================================= Tasks =================================================
//    
//    // Get all Tasks Related to a ProcessInstance
//    public List<Task> getTasksByProcessInstance(String processInstanceId){
//    	return getTaskService().createTaskQuery().processInstanceId(processInstanceId).list();
//    }
//
//    public List<Task> getTasksByExecution(String executionId){
//    	logger.info("Getting Tasks:" );    	
//    	List<Task> tasks = getTaskService().createTaskQuery().executionId(executionId).list();
//    	logger.info("Tasks Found:" + tasks.size() );    	
//		for(Task task : tasks)
//			logger.info("Execution:" + task.getId() + "--" + task.getProcessInstanceId());    	
//		return tasks;
//    }
//
//	// Get All Tasks for a given Username
//	public List<Task> getTasksByUserName(String username){
//		return getTaskService().createTaskQuery().taskAssignee(username).list();
//	}
//	public Task getTask(String taskId){
//		return getTaskService().createTaskQuery().taskId(taskId).singleResult();
//	}	
//	public List<Task> getTasks(){
//		return getTaskService().createTaskQuery().list();
//	}
//	public List<Task> getTasks(String taskId){
//		return getTaskService().createTaskQuery().taskId(taskId).list();
//	}
//
//	// Look for Tasks to complete
//	public String completeTasks(String taskId){
//		// NOTE : In theory there should only be one task w/any Id
//		// During development @least, it seems sometimes this is not true
//		String processInstanceId = "";
//		for(Task task : getTasks(taskId) ) {
//			getTaskService().complete( task.getId() );
//			processInstanceId = task.getProcessInstanceId();
//			logger.info("Task Complete: " + task.getId());
//		}
//		return processInstanceId;
//	}
//    
//	//  ================================================= History =================================================
//
//    public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId){
//    	return getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
//    }
//    
//    public List<HistoricProcessInstance> getHistoricProcessInstances(String processInstanceId){
//    	return getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).list();
//    }
//    public List<HistoricActivityInstance> getHistoricActivityInstances(String processInstanceId){
//    	return getHistoryService().createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
//    }
//    public List<HistoricTaskInstance> getHistoricTaskInstances(String processInstanceId){
//    	return getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
//    }
//    
//    
//	//  ================================================= Forms =================================================
//    public ProcessInstance submitStartFormData(String processDefinitionId, Map<String, String> properties){
//    	return getFormService().submitStartFormData(processDefinitionId, properties);
//    }
//
//    public void submitTaskFormData(String taskId, Map<String, String> properties){
//    	getFormService().submitTaskFormData(taskId, properties);
//    }
//
//	@Override
//	public boolean checkID(String userId, String password){
//		return UserService.checkID(userId, password, getIdentityService());
//	}
//	@Override
//    public List <User> getUsers() {
//		return UserService.getUsers(getIdentityService());
//    }
//
//    //  ================================================= Variables =================================================
//	public Object getVariable(ProcessInstance procInst, String variableName){
//		// Get a BPMN Variable from BPMN RuntimeService
//		return getRuntimeService().getVariable(procInst.getId(), variableName);
//	}
//
//	//  ================================================= Deployment =================================================
//	public String deploy(String classPathResource) {
//		// Deploy a bpmn20.xml file programmatically
//	    // classPathResource = eg-"org/activiti/spring/test/hello.bpmn20.xml"
//		String deploymentId = getProcessEngine().getRepositoryService().createDeployment()
//    			  .addClasspathResource(classPathResource)
//    			  .deploy()
//    			  .getId();
//    	return deploymentId;
//    }
//    
//} // EOF
//
//

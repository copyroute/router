//package com.penguinsrising.services.workflow.engine;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Named;
//import javax.inject.Singleton;
//
//import org.activiti.engine.history.HistoricActivityInstance;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.identity.User;
//import org.activiti.engine.runtime.Execution;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Named
//@Singleton
//@Component
//@Service("businessProcessEngine")
//public interface IBusinessProcessEngine {
//
//	// Identity
//	public boolean checkID(String userId, String password);
//	public List <User> getUsers() ;
//	
//	// Processes
//	public ProcessInstance runProcess(String processName, Map<String, Object> variableMap, String user, String pass) ;
//	public List<ProcessInstance> getProcessInstanceUnique();
//	public List<ProcessInstance> getProcessInstances();
//	public ProcessInstance getProcessInstance(String processInstanceId);
//	public List<ProcessInstance> getActiveProcessInstances();
//
//	// Executions
//	public Execution getExecution(String executionId);
//	public List<Execution> getExecutionsByProcessInstance(String processInstanceId);
//	public List<Execution> getExecutions();
//
//	// Tasks
//	public List<Task> getTasksByProcessInstance(String processInstanceId);
//	public List<Task> getTasksByExecution(String executionId);
//	public List<Task> getTasksByUserName(String username);
//	public List<Task> getTasks();
//	public Task getTask(String taskId);
//	public String completeTasks(String taskId);
//
//	// Runtime Variables
//	public Map<String, Object> getVariables(ProcessInstance procInst);
//	public Object getVariable(ProcessInstance procInst, String variableName);
//
//	// Forms
//	public ProcessInstance submitStartFormData(String processDefinitionId, Map<String, String> properties);
//	public void submitTaskFormData(String taskId, Map<String, String> properties);
//
//	// Historical Data
//	public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId);
//	public List<HistoricProcessInstance> getHistoricProcessInstances(String processInstanceId);
//	public List<HistoricActivityInstance> getHistoricActivityInstances(String processInstanceId);
//	public List<HistoricTaskInstance> getHistoricTaskInstances(String processInstanceId);
//
//	// Deploy Resources
//	public String deploy(String classPathResource) ;
//	
//}

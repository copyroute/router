//package com.penguinsrising.services.workflow.diagrams;
//
//import com.penguinsrising.services.workflow.engine.BusinessProcessEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Named;
//
//import org.activiti.cdi.BusinessProcess;
//import org.activiti.engine.ActivitiException;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.history.HistoricActivityInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.repository.DiagramLayout;
//import org.activiti.engine.repository.DiagramNode;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Comment;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//@Named
//public class ProcessDiagramController {
//// 
////  private ProcessInstance getCurrentProcessInstance() 
////  {
////    //return VBMSProcess.businessProcess.getProcessInstance();
////    return CometDListener.procInst;
////  }
//	
//	  protected static Logger logger = LoggerFactory.getLogger(ProcessDiagramController.class);
//
//
//  public List<PositionedHistoricActivityInstance> getTraversedFlowNodes(ProcessInstance processInstance) 
//  {
//    ArrayList<PositionedHistoricActivityInstance> alist = new ArrayList<PositionedHistoricActivityInstance>();
//
//    if (processInstance != null) 
//    {
//    	DiagramLayout processDiagramLayout = 
//    			BusinessProcessEngine.getRepositoryService()
//    				.getProcessDiagramLayout( processInstance.getProcessDefinitionId() );
//
//    	List<HistoricActivityInstance> hlist = 
//    			BusinessProcessEngine.getHistoryService()
//    				.createHistoricActivityInstanceQuery().processInstanceId( processInstance.getId()).list();
//
//    	for (HistoricActivityInstance hact : hlist) {
//    		if (hact.getEndTime() != null) {
//    			PositionedHistoricActivityInstance pact = 
//    					new PositionedHistoricActivityInstance(
//    							hact, 
//    							processDiagramLayout.getNode(
//    							hact.getActivityId()));
//    			alist.add(pact);          
//        }
//      }
//    }
//
//    return alist;
//  }
//
//  public List<PositionedHistoricTaskInstance> getTraversedTasks(ProcessInstance processInstance) 
//  {
//	  	ArrayList<PositionedHistoricTaskInstance> alist = new ArrayList<PositionedHistoricTaskInstance>();
//
//	    if (processInstance != null) {
//	      DiagramLayout processDiagramLayout = BusinessProcessEngine.getRepositoryService().getProcessDiagramLayout(processInstance.getProcessDefinitionId());
//	      List<HistoricTaskInstance> hlist = BusinessProcessEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).orderByHistoricActivityInstanceStartTime().asc().list();
//	      for (HistoricTaskInstance htask : hlist) {
//	    	  if (htask.getEndTime() != null) {
//	        	PositionedHistoricTaskInstance ptask = new PositionedHistoricTaskInstance(htask, processDiagramLayout.getNode(htask.getTaskDefinitionKey()));
//	        	alist.add(ptask);
//	        }
//	      }
//	    }
//	    return alist;
//	  }
//
//  public List<PositionedHistoricTaskDefinition> getTraversedTaskDefinitions(ProcessInstance processInstance) 
//  {
//	  	ArrayList<PositionedHistoricTaskDefinition> alist = new ArrayList<PositionedHistoricTaskDefinition>();
//
//	    if (processInstance != null) {
//	      DiagramLayout processDiagramLayout = BusinessProcessEngine.getRepositoryService().getProcessDiagramLayout(processInstance.getProcessDefinitionId());
//	      
//	      //order by taskName as workaround because orderByTaskDefinitionKey is not working. This is not really safe!
//	      List<HistoricTaskInstance> hlist = BusinessProcessEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
//
//	      int i=0;
//	      for (HistoricTaskInstance htask : hlist) {
//	    	  if (htask.getEndTime() != null) {
//	    		  if (i>0) {
//		    		  if (alist.get(i-1).getTaskDefinitionKey().equals(htask.getTaskDefinitionKey())) {
//		        		alist.get(i-1).taskInstances.add(htask);
//		        	} else {
//		        		PositionedHistoricTaskDefinition ptask = new PositionedHistoricTaskDefinition(htask, processDiagramLayout.getNode(htask.getTaskDefinitionKey()));
//		  	          	alist.add(ptask);
//		  	          	i++;
//		        	}
//	    		  } else {
//		        		PositionedHistoricTaskDefinition ptask = new PositionedHistoricTaskDefinition(htask, processDiagramLayout.getNode(htask.getTaskDefinitionKey()));
//		  	          	alist.add(ptask);
//		  	          	i++;
//	    		  }
//	        }
//	      }
//	    }
//	    return alist;
//	  }
//
//
//  
//  
//  public List<DiagramNode> getActiveActivityBoundsOfLatestProcessInstance(ProcessInstance processInstance) 
//  {
//    ArrayList<DiagramNode> list = new ArrayList<DiagramNode>();
//    if (processInstance != null) {
//        try{
//	      DiagramLayout processDiagramLayout = BusinessProcessEngine.getRepositoryService().getProcessDiagramLayout(processInstance.getProcessDefinitionId());
//	      List<String> activeActivityIds = BusinessProcessEngine.getRuntimeService().getActiveActivityIds(processInstance.getId());
//	      for (String activeActivityId : activeActivityIds) {
//	        list.add(processDiagramLayout.getNode(activeActivityId));
//	      }
//    	}
//    	catch(ActivitiException ex){ logger.info("ActivitiException: " + ex.getMessage()); }
//    }
//    return list;
//  }
//  
//  public List<Comment> getCommentsOfLastHistoricTask(ProcessInstance processInstance) 
//  {
//    List<HistoricTaskInstance> hlist = 
//    		BusinessProcessEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(
//    				processInstance.getId()).orderByHistoricTaskInstanceEndTime().desc().list();
//	if (hlist.get(0).getEndTime() != null) {
//	    return getTaskComments(hlist.get(0).getId());		
//	} else {
//		return null;
//	}
//  }
//  
//  public String getTaskComment (String taskId) {
//	  String comment = "";
//	  if (!BusinessProcessEngine.getTaskService().getTaskComments(taskId).isEmpty()) 
//		  comment = BusinessProcessEngine.getTaskService().getTaskComments(taskId).get(0).getFullMessage();
//      return comment;
//  }
//  
//  public List<Comment> getTaskComments (String taskId) {
//	  return BusinessProcessEngine.getTaskService().getTaskComments(taskId);
//  }
//
//}
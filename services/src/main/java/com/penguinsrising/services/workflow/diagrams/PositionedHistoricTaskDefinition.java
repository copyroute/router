//package com.penguinsrising.services.workflow.diagrams;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.repository.DiagramNode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class PositionedHistoricTaskDefinition {
//
//	  protected static Logger logger = LoggerFactory.getLogger(PositionedHistoricTaskDefinition.class);
//
//        //private HistoricTaskD htask;
//        private DiagramNode bounds;
//        ArrayList<HistoricTaskInstance> taskInstances;
//        
//        public PositionedHistoricTaskDefinition(HistoricTaskInstance firstInstance, DiagramNode bounds) {
//                this.bounds = bounds;
//                this.taskInstances = new ArrayList<HistoricTaskInstance>();
//                this.taskInstances.add(firstInstance);
//        }
//        
//        public DiagramNode getBounds() {
//                return bounds;
//        }
//
//        public void setBounds(DiagramNode bounds) {
//                this.bounds = bounds;
//        }
//
//        public List<HistoricTaskInstance> getTaskInstances() {
//                return taskInstances;
//        }
//
//        public void setTaskInstances(ArrayList<HistoricTaskInstance> taskInstances) {
//                this.taskInstances = taskInstances;
//        }
//
//        public String getTaskDefinitionKey() {
//                return taskInstances.get(0).getTaskDefinitionKey();
//        }
//
//        public String getTaskName() {
//                return taskInstances.get(0).getName();
//        }
//        
//        public boolean isCurrent() {
//                if (taskInstances.size()==1 && taskInstances.get(0).getEndTime()==null) return true;
//                else return false;
//        }
//}
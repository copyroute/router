//package com.penguinsrising.services.workflow.diagrams;
//
//import org.activiti.engine.history.HistoricActivityInstance;
//import org.activiti.engine.repository.DiagramNode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class PositionedHistoricActivityInstance {
//
//	  protected static Logger logger = LoggerFactory.getLogger(PositionedHistoricActivityInstance.class);
//
//        private HistoricActivityInstance hact;
//        
//        public PositionedHistoricActivityInstance(
//                        HistoricActivityInstance hact, DiagramNode bounds) {
//                this.hact = hact;
//                this.bounds = bounds;
//        }
//
//        public HistoricActivityInstance getHact() {
//                return hact;
//        }
//
//        public void setHact(HistoricActivityInstance hact) {
//                this.hact = hact;
//        }
//
//        private DiagramNode bounds;
//
//        public DiagramNode getBounds() {
//                return bounds;
//        }
//
//        public void setBounds(DiagramNode bounds) {
//                this.bounds = bounds;
//        }
//        
//        
//
//}
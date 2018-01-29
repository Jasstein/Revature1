package com.revature.banking;

public class Audit {
	
	private int auditId;
    private int accountNum;
    private double checkingChange;
    private boolean oldApproved;
    private boolean newApproved;
    private boolean oldJoint;
    private boolean newJoint;
    
    public void setAuditId(int i) {
    	this.auditId = i;
    }
    
    public void setAccountNum(int i) {
    	this.accountNum = i;
    }
    
    public void setCheckingChange(double d) {
    	this.checkingChange = d;
    }
    
    public void setOldApproved(boolean b) {
    	this.oldApproved = b;
    }
    
    public void setNewApproved(boolean b) {
    	this.newApproved = b;
    }
    
    public void setOldJoint(boolean b) {
    	this.oldJoint = b;
    }
    
    public void setNewJoint(boolean b) {
    	this.newJoint = b;
    }
    
    public int getAuditId() {
    	return this.auditId;
    }
    
    public int getAccountNum() {
    	return this.accountNum;
    }
    
    public double getCheckingChange() {
    	return this.checkingChange;
    }
    
    public boolean getOldApproved() {
    	return this.oldApproved;
    }
    
    public boolean getNewApproved() {
    	return this.newApproved;
    }
    
    public boolean getOldJoint() {
    	return this.oldJoint;
    }
    
    public boolean getNewJoint() {
    	return this.newJoint;
    }

}

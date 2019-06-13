package cn.stylefeng.guns.modular.spark.model;

import cn.stylefeng.guns.modular.system.model.User;

import java.util.List;

public class ReviewJob {
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public PartTime getPartTime() {
        return partTime;
    }

    public void setPartTime(PartTime partTime) {
        this.partTime = partTime;
    }

    public List<AuditDetail> getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(List<AuditDetail> auditDetails) {
        this.auditDetails = auditDetails;
    }

    private Audit audit;
    private PartTime partTime;
    private List<AuditDetail> auditDetails;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

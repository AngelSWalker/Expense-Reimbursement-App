package models;

import java.util.Date;

public class Reimbursement {
    private Integer id;
    private Integer amount;
    private String submitTime;
    private String resolveTime;
    private String description;
    private String receipt;
    private Integer authorId;
    private Integer resolverId;
    private Integer statusId;
    private Integer typeId;


    public Reimbursement() {

    }

    public Reimbursement(Integer amount, String description, String receipt, Integer authorId, Integer typeId) {
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.authorId = authorId;
        this.typeId = typeId;
    }

    public Reimbursement(Integer id, Integer amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public Reimbursement(Integer id, Integer amount, String description, Integer authorId, Integer statusId, Integer typeId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(Integer amount, String submitTime, String resolveTime,
                         String description, String receipt, int authorId, int resolverId,
                         int statusId, int typeId) {
        this.amount = amount;
        this.submitTime = submitTime;
        this.resolveTime = resolveTime;
        this.description = description;
        this.receipt = receipt;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(Integer id, Integer amount, String submitTime, String resolveTime, String description, String receipt, Integer authorId, Integer resolverId, Integer statusId, Integer typeId) {
        this.id = id;
        this.amount = amount;
        this.submitTime = submitTime;
        this.resolveTime = resolveTime;
        this.description = description;
        this.receipt = receipt;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(String resolveTime) {
        this.resolveTime = resolveTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "\n\nReimbursement{" +
                "\nid=" + id +
                ", amount=" + amount +
                ", submitTime='" + submitTime + '\'' +
                ", resolveTime='" + resolveTime + '\'' +
                "\n, description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }
}

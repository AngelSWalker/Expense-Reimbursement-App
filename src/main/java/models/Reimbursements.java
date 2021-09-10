package models;

import java.util.Date;

public class Reimbursements {
    private Integer id;
    private Integer amount;
    private String submitTime;
    private String resolveTime;
    private String description;
    private String receipt;
    private Integer userId;
    private String authorFirst;
    private String authorLast;
    private String resolverFirst;
    private String resolverLast;
    private String status;
    private String type;

    public Reimbursements() {
    }

    public Reimbursements(Integer id, Integer amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public Reimbursements(Integer id, String description, Integer userId, String status) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.status = status;
    }

    public Reimbursements(Integer id, Integer amount, String submitTime, String resolveTime, String description, String receipt, Integer userId, String authorFirst, String authorLast, String resolverFirst, String resolverLast, String status, String type) {
        this.id = id;
        this.amount = amount;
        this.submitTime = submitTime;
        this.resolveTime = resolveTime;
        this.description = description;
        this.receipt = receipt;
        this.authorFirst = authorFirst;
        this.userId = userId;
        this.authorLast = authorLast;
        this.resolverFirst = resolverFirst;
        this.resolverLast = resolverLast;
        this.status = status;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    public String getResolverFirst() {
        return resolverFirst;
    }

    public void setResolverFirst(String resolverFirst) {
        this.resolverFirst = resolverFirst;
    }

    public String getResolverLast() {
        return resolverLast;
    }

    public void setResolverLast(String resolverLast) {
        this.resolverLast = resolverLast;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "\nReimbursements\n{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitTime='" + submitTime + '\'' +
                ", resolveTime='" + resolveTime + '\'' +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ",\n " + "userId= " + userId +
                ", authorFirst='" + authorFirst + '\'' +
                ", authorLast='" + authorLast + '\'' +
                ", resolverFirst='" + resolverFirst + '\'' +
                ", resolverLast='" + resolverLast + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}'+ "\n";
    }
}

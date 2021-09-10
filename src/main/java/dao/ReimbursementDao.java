package dao;

import models.Reimbursement;
import models.Reimbursements;

import java.util.List;

public interface ReimbursementDao{

    List<Reimbursements> getAllReimb();
    List<Reimbursements> getPendReimb();
    List<Reimbursements> getEmpReimb(Integer userId);
    List<Reimbursements> getEmpPendReimb(Integer userId);
    List<Reimbursements> getStatus(Integer statusId);
    List<Reimbursements> getEmpStatus(Integer userId, Integer statusId);
    boolean addReimb(Reimbursement newReimb);
    void updateStatus(Integer reimbId, Integer userId, Integer statusId);
    Boolean approveReimb(Integer reimbId, Integer userId);
    Boolean denyReimb(Integer reimbId, Integer userId);


    Reimbursements getReimb(Integer reimbId);
}

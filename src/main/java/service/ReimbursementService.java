package service;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import models.Reimbursement;
import models.Reimbursements;

import java.util.List;

public class ReimbursementService {
    ReimbursementDao reimbursementDao;

    public ReimbursementService(){
        reimbursementDao = ReimbursementDaoImpl.getInstance();
    }

    public ReimbursementService(ReimbursementDao reimbursementDao) {
        this.reimbursementDao = reimbursementDao;
    }


    //get all reimbursements
    public List<Reimbursements> getAllReimb(){
        return reimbursementDao.getAllReimb();
    }

    //get all pending reimbursements
    public List<Reimbursements> getPendReimb(){
        return reimbursementDao.getPendReimb();
    }

    //get all reimbursements for an employee
    public List<Reimbursements> getEmpReimb(Integer userId){
        return reimbursementDao.getEmpReimb(userId);
    }

    //get all pending reimbursements for an employee
    public List<Reimbursements> getEmpPendReimb(Integer userId){
        return reimbursementDao.getEmpPendReimb(userId);
    }

    //get all reimbursements with certain status
    public List<Reimbursements> getStatus(Integer statusId){
        return reimbursementDao.getStatus(statusId);
    }

    //get all reimbursements with certain status for an employee
    public List<Reimbursements> getEmpStatus(Integer userId, Integer statusId){
        return  reimbursementDao.getEmpStatus(userId, statusId);
    }

    //update status
    public void updateStatus(Integer reimbId, Integer userId, Integer statusId){
        reimbursementDao.updateStatus(reimbId, userId, statusId);
    }

    //add reimbursement to table
    public void addReimb(Reimbursement newReimb){ reimbursementDao.addReimb(newReimb);
    }

    //approve reimbursement
    public void approveReib(Integer reimbId, Integer userId){
        reimbursementDao.approveReimb(reimbId, userId);
    }

    //deny reimbursement
    public void denyReimb(Integer reimbId, Integer userId) {
        reimbursementDao.denyReimb(reimbId, userId);
    }

    public Reimbursements getReimb(Integer reimbId) {
        return reimbursementDao.getReimb(reimbId);
    }
}

package service;

import dao.ReimbursementDao;
import models.Reimbursement;
import models.Reimbursements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

class ReimbursementServiceTest {

    ReimbursementService reimbursementService;

    //mock object
    @Mock
    ReimbursementDao reimbursementDao = Mockito.mock(ReimbursementDao.class);


    @BeforeEach
    void setUp(){
        reimbursementService = new ReimbursementService(reimbursementDao);
    }

    @Test
    void getAllReimb() {

        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 2, "Approved"));
        list.add(new Reimbursements(2, "test", 4, "Pending"));

        Mockito.when(reimbursementDao.getAllReimb()).thenReturn(list);
        reimbursementService.getAllReimb();

        Mockito.verify(reimbursementDao, Mockito.times(1)).getAllReimb();
    }

    @Test
    void getPendReimb() {
        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 2, "Approved"));
        list.add(new Reimbursements(2, "test", 4, "Pending"));

        List<Reimbursements> pendList = new ArrayList<>();
        pendList.add(new Reimbursements(2, "test", 4, "Pending"));

        Mockito.when(reimbursementDao.getPendReimb()).thenReturn(pendList);

        List<Reimbursements> actual = reimbursementService.getPendReimb();

        assertEquals(pendList, actual);
    }

    @Test
    void getEmpReimb() {
        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 2, "Approved"));
        list.add(new Reimbursements(2, "test", 4, "Pending"));

        Mockito.when(reimbursementDao.getEmpReimb(4)).thenReturn(list);
         reimbursementService.getEmpReimb(4);

        Mockito.verify(reimbursementDao, Mockito.times(1)).getEmpReimb(4);
    }

    @Test
    void getEmpPendReimb() {
    }

    @Test
    void getStatus() {
        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 2, "Approved"));
        list.add(new Reimbursements(2, "test", 4, "Approved"));

        Mockito.when(reimbursementDao.getStatus(1)).thenReturn(list);

        List<Reimbursements> actual = reimbursementService.getStatus(1);

        assertEquals(list, actual);
    }

    @Test
    void getEmpStatus() {
        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 2, "Pending"));
        list.add(new Reimbursements(2, "test", 2, "Pending"));

        Mockito.when(reimbursementDao.getEmpStatus(2, 2)).thenReturn(list);

        List<Reimbursements> actual = reimbursementService.getEmpStatus(2, 2);

        assertEquals(list, actual);

    }

    @Test
    void updateStatus() {
        reimbursementDao.updateStatus(null, null, 1);
        Mockito.verify(reimbursementDao, Mockito.times(1));
    }

    @Test
    void addReimb() {

        Mockito.when(reimbursementDao.addReimb(new Reimbursement())).thenReturn(true);

        reimbursementService.addReimb(new Reimbursement());

        Mockito.verify(reimbursementDao, Mockito.times(1));
    }

    @Test
    void getReimb() {
        Reimbursements expected = new Reimbursements(1,100, "test");
        Mockito.when(reimbursementDao.getReimb(1)).thenReturn(expected);

        Reimbursements actual = reimbursementService.getReimb(1);
        //System.out.println(reimbursementService.getAllReimb());
        assertEquals(expected, actual);
        //Mockito.verify(reimbursementDao, Mockito.times(1));
    }
}
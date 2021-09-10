package dao;

import models.Reimbursement;
import models.Reimbursements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDaoTest {
    @Mock
    ReimbursementDao reimbursementDao = Mockito.mock(ReimbursementDaoImpl.class).getInstance();

    @BeforeEach
    void setUp() {
       /* Reimbursements r1 = new Reimbursements(1, "test", 1, "Pending");
        Reimbursements r2 = new Reimbursements(2, "test2", 1, "Pending");
        Reimbursements r3 = new Reimbursements(3, "test3", 1, "Pending");

        List<Reimbursements> list = null;
        list.add(r1);
        list.add(r2);
        list.add(r3);*/
        reimbursementDao = new ReimbursementDaoImpl();
    }


    @Test
    void getAllReimb() {
        System.out.println(reimbursementDao.getAllReimb());
    }

    @Test
    void getEmpReimb() {
        System.out.println(reimbursementDao.getEmpReimb(1));
    }

    @Test
    void getStatus() {
        System.out.println(reimbursementDao.getStatus(2));
    }

    @Test
    void getEmpStatus() {
        System.out.println(reimbursementDao.getEmpStatus(1, 1));
    }

    @Test
    void addReimb() {
       /* Boolean expected = false;

        ReimbursementDaoImpl reimbursementimpl = Mockito.mock(ReimbursementDaoImpl.class);
        Mockito.when(reimbursementimpl.addReimb(new Reimbursement())).thenReturn(true);
        Boolean actual = reimbursementDao.addReimb(new Reimbursement());

        assertEquals(expected, actual);
   */ }

    @Test
    void updateStatus() {
    }

    @Test
    void getReimb() {
    }
}
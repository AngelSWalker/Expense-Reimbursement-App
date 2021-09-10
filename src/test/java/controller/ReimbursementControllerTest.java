package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Reimbursement;
import models.Reimbursements;
import models.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementControllerTest extends Mockito{
    ReimbursementController reimbursementController;
    @Mock
    ReimbursementService reimbursementService = mock(ReimbursementService.class);

    @BeforeEach
    void setUp(){
        reimbursementController = new ReimbursementController(reimbursementService);
    }
    @Test
    void getAllReimb() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        //PrintWriter out = new PrintWriter((Writer) resp);

        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));

        //PrintWriter out = resp.getWriter();
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        //ObjectMapper obj = mock(ObjectMapper.class);
        //when(reimbursementService.getAllReimb()).thenReturn(list);

        reimbursementController.getAllReimb(req, resp);

        verify(reimbursementService, times(1)).getAllReimb();
        //out.flush();
    }

    @Test
    void getPendReimb() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));

        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        reimbursementController.getPendReimb(req, resp);

        verify(reimbursementService, times(1)).getPendReimb();

    }

    @Test
    void updateStatus() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        /*List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));
*/
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        when(req.getParameter("reimbId")).thenReturn("1");
        when(req.getParameter("userId")).thenReturn("1");
        when(req.getParameter("status")).thenReturn("1");

        reimbursementController.updateStatus(req, resp);

        verify(reimbursementService, times(1)).updateStatus(1, 1, 1);

    }

    @Test
    void getStatus() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        /*List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));
*/
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        when(req.getParameter("status")).thenReturn("1");

        reimbursementController.getStatus(req, resp);

        verify(reimbursementService, times(1)).getStatus(1);

    }

    @Test
    void getAllEmpReimb() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        /*List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));
*/
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        when(req.getParameter("userId")).thenReturn("1");

        reimbursementController.getAllEmpReimb(req, resp);

        verify(reimbursementService, times(1)).getEmpReimb(1);

    }

    @Test
    void getEmpPendReimb() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        /*List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));
*/
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        when(req.getParameter("userId")).thenReturn("1");

        reimbursementController.getEmpPendReimb(req, resp);

        verify(reimbursementService, times(1)).getEmpPendReimb(1);

    }

    /*@Test
    void newReimb() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        *//*List<Reimbursements> list = new ArrayList<>();
        list.add(new Reimbursements(1, "test", 1, "Pending"));
        list.add(new Reimbursements(2, "test2", 1, "Approved"));
        list.add(new Reimbursements(3, "test3", 1, "Denied"));
*//*
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        when(resp.getWriter()).thenReturn(out);

        when(req.getReader()).thenReturn(
                new BufferedReader(new StringReader("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"amount\" : 100,\n" +
                        "    \"submitTime\" : \"null\",\n" +
                        "    \"resolveTime\" : \"null\",\n" +
                        "    \"description\" : \"test\",\n" +
                        "    \"receipt\" : \"null\",\n" +
                        "    \"authorId\": 1,\n" +
                        "    \"submitTime\" : null,\n" +
                        "    \"statusId\" : 0,\n" +
                        "    \"typeId\" : 1\n" +
                        "}")));
        *//*when(req.getReader().lines().collect(Collectors.joining(System.lineSeparator()))).thenReturn("{\n" +
                "    \"amount\" : 100,\n" +
                "    \"description\" : \"test\",\n" +
                "    \"authorId\": 1,\n" +
                "    \"typeId\" : 1\n" +
                "}");*//*
        //reimbursementService.addReimb(new Reimbursement(100, "test", null, 1, 1));
        reimbursementController.newReimb(req, resp);

        verify(reimbursementService, times(1)).addReimb(new Reimbursement(1, 100, null, null, "test", null, 1, null, 0, 1));

    }*/

    @Test
    void reimbDetails() {

    }
}
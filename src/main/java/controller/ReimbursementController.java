package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Reimbursement;
import models.Response;
import models.Users;
import service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ReimbursementController {
    private static ReimbursementController reimbursementController;
    ReimbursementService reimbursementService;

    public ReimbursementController(){
        reimbursementService = new ReimbursementService();
    }

    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    public static ReimbursementController getInstance(){
        if(reimbursementController == null)
            reimbursementController = new ReimbursementController();
        return reimbursementController;
    }

    public void getAllReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        //get from service
        out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursements Retrieved",
                true, reimbursementService.getAllReimb())));
    }
    public void getPendReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        //get from service
        out.println(new ObjectMapper().writeValueAsString(new Response("Pending Reimbursements Retrieved",
                true, reimbursementService.getPendReimb())));
    }

    public void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(("application/json"));
        PrintWriter out = resp.getWriter();

        Integer reimbId = Integer.parseInt(req.getParameter("reimbId"));
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        Integer status = Integer.parseInt(req.getParameter("status"));

        reimbursementService.updateStatus(reimbId, userId, status);

        out.println(new ObjectMapper().writeValueAsString(new Response("status updated", true,
                null)));
    }

    public void getStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(("application/json"));
        PrintWriter out = resp.getWriter();

        Integer status = Integer.parseInt(req.getParameter("status"));


        out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursements with status " +
                status + " received", true, reimbursementService.getStatus(status))));
    }

    public void getAllEmpReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(("application/json"));
        PrintWriter out = resp.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("userId"));

        out.println(new ObjectMapper().writeValueAsString(new Response(
                "Reimbursements received for employee id: " + userId, true,
                reimbursementService.getEmpReimb(userId))));

    }

    public void getEmpPendReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(("application/json"));
        PrintWriter out = resp.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("userId"));

        out.println(new ObjectMapper().writeValueAsString(new Response(
                "Pending Reimbursements received for employee id: " + userId, true,
                reimbursementService.getEmpPendReimb(userId))));

    }

    public void newReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(("application/json"));
        PrintWriter out = resp.getWriter();

        //request body
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Reimbursement reimbursement = new ObjectMapper().readValue(requestBody, Reimbursement.class);

        reimbursementService.addReimb(reimbursement);

        out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursement created",
                true, null)));
    }

    public void reimbDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        //get param
        Integer reimbId = Integer.parseInt(req.getParameter("reimbId"));

        out.println(new ObjectMapper().writeValueAsString(new Response(
                "Reimbursement details retrieved for reimbursement: " + reimbId, true,
                reimbursementService.getReimb(reimbId))));
    }
}

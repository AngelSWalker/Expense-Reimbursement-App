/*
package frontcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/reimbursements/get-all-reimbursements")
public class ShowAllReimbServlet extends HttpServlet {
    private ReimbursementService reimbursementService;

    public ShowAllReimbServlet(){
        this.reimbursementService = new ReimbursementService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.print(new ObjectMapper().writeValueAsString(this.reimbursementService.getAllReimb()));


    }
}
*/

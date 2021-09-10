package frontcontroller;

import controller.ReimbursementController;
import controller.UsersController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="dispatcher", urlPatterns = "/api/*")
public class Dispatcher extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(uri);

        switch (uri){
            case "/Project01/api/login":
                if(req.getMethod().equals("POST"))
                    UsersController.getInstance().login(req, resp);
                break;
            case "/Project01/api/register":
                if(req.getMethod().equals("POST"))
                    UsersController.getInstance().register(req, resp);
                break;
            case "/Project01/api/financemgr/reimb":
                switch (req.getMethod()) {
                    case "GET":
                        ReimbursementController.getInstance().getAllReimb(req, resp);
                        break;
                    case "PATCH":
                        ReimbursementController.getInstance().updateStatus(req, resp);
                        break;
                }
                break;
            case "/Project01/api/financemgr/reimb/view":
                if(req.getMethod().equals("GET"))
                    ReimbursementController.getInstance().getPendReimb(req, resp);
                break;
            case "/Project01/api/financemgr/reimb/viewStatus":
                    if(req.getMethod().equals("GET"))
                        ReimbursementController.getInstance().getStatus(req, resp);
                    break;
            case "/Project01/api/employee/reimb":
                switch (req.getMethod()){
                    case "GET":
                        ReimbursementController.getInstance().getEmpPendReimb(req, resp);
                        break;
                    case "POST":
                        ReimbursementController.getInstance().newReimb(req, resp);
                        break;
                }
                break;
            case "/Project01/api/employee/reimb/views":
                if(req.getMethod().equals("GET"))
                    ReimbursementController.getInstance().getAllEmpReimb(req, resp);
                break;
            case "/Project01/api/reimbursement":
                if(req.getMethod().equals("GET"))
                    ReimbursementController.getInstance().reimbDetails(req, resp);
                break;
            case "/Project01/api/check-session":
                if(req.getMethod().equals("GET"))
                    UsersController.getInstance().checkSession(req, resp);
                break;
            case "/Project01/api/logout":
                if(req.getMethod().equals("GET"))
                    UsersController.getInstance().logout(req, resp);
                break;


        }
    }
}

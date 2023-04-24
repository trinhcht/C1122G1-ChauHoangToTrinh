package controller;

import model.LoaiThucPham;
import model.SieuThi;
import service.ISieuThiService;
import service.impl.SieuThiService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SieuThiServlet", value = "/sieuThi")
public class SieuThiServlet extends HttpServlet {
    ISieuThiService sieuThiService = new SieuThiService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(request,response);
                break;
            case "delete":
                showDelete(request,response);
                break;
            case "search":
                try {
                    formSearch(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showList(request,response);
                break;
        }
    }

    private void formSearch(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        List<SieuThi> sieuThiList = this.sieuThiService.findByName(name);
        request.setAttribute("sieuThiList", sieuThiList);
        try {
            request.setAttribute("nameSearch",name);
            request.setAttribute("sieuThiList", sieuThiService.findByName(name));
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        request.getRequestDispatcher("/view/list.jsp").forward(request, response);
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        sieuThiService.delete(id);
        List<SieuThi> sieuThiList = sieuThiService.findAll();
        request.setAttribute("sieuThiList", sieuThiList);
            request.getRequestDispatcher("/sieuThi").forward(request,response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/view/create.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        List<SieuThi> sieuThiList = sieuThiService.findAll();
        request.setAttribute("sieuThiList",sieuThiList);
        try {
            request.getRequestDispatcher("/view/list.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                save(request,response);
                break;
            default:
                showList(request,response);
                break;
        }

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String NSX = request.getParameter("NSX");
        String HSD = request.getParameter("HSD");
        Integer ltpId = Integer.parseInt(request.getParameter("ltpId"));
        String ltpName = request.getParameter("ltpName");
        LoaiThucPham ltp = new LoaiThucPham(ltpId,ltpName);
        String moTa = request.getParameter("moTa");

        SieuThi sieuThi = new SieuThi(id,name,NSX,HSD,ltp,moTa);
        sieuThiService.save(sieuThi);

        try {
            response.sendRedirect("/sieuThi");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

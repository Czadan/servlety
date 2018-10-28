package zadania;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class CalcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer a2 = Optional.ofNullable(req.getParameter("a"))
                .filter(e -> StringUtils.isNumeric(e))
                .map(e -> Integer.valueOf(e))
                .orElse(0);
        Integer b2 = Optional.ofNullable(req.getParameter("b"))
                .filter(e -> StringUtils.isNumeric(e))
                .map(e -> Integer.valueOf(e))
                .orElse(0);

//        String a = req.getParameter("a");
//        String b = req.getParameter("b");
//
//        Integer a1 = Integer.valueOf(StringUtils.isEmpty(a) ? "0" : a);
//        Integer b1 = Integer.valueOf(StringUtils.isEmpty(a) ? "0" : b);
        Integer wynik = a2 + b2;

        PrintWriter writer = resp.getWriter();
        writer.print("<h1> Wynik "+a2+" + "+b2+" = "+wynik+"</h1>");
    }
}

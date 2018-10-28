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

        Integer a2 = mapToInteger(req.getParameter("a"));
        Integer b2 = mapToInteger(req.getParameter("b"));


        CalculationResult wynik = calculate(req.getPathInfo(),a2,b2);



        PrintWriter writer = resp.getWriter();
        writer.print("<h1> Wynik "+wynik.resultRepresentation+"</h1>");
    }
    private CalculationResult calculate(String path, int a, int b) {
        if (path.endsWith("add")) {
            return new CalculationResult(a + b, a + " + " + b + " = " + (a + b));
        } else if (path.endsWith("subtract")) {
            return new CalculationResult(a - b, a + " - " + b + " = " + (a - b));
        }else if (path.endsWith("multiply")) {
            return new CalculationResult(a * b, a + " * " + b + " = " + (a * b));
        }else {
            return new CalculationResult(0,"Unsupported operation");
        }

    }

    private static class CalculationResult{
        private Integer resoult;
        private String resultRepresentation;

        public CalculationResult(Integer resoult, String resultRepresentation) {
            this.resoult = resoult;
            this.resultRepresentation = resultRepresentation;
        }


    }
    private  Integer mapToInteger(String param){
        return Optional.ofNullable(param)
                .filter(e -> StringUtils.isNumeric(e))
                .map(e -> Integer.valueOf(e))
                .orElse(0);
    }
}

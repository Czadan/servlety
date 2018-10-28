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

        String operation = Optional.ofNullable(req.getPathInfo())
                .orElse(req.getParameter("operation"));
        CalculationResult wynik = calculate(operation, a2, b2);

        if (!wynik.calculated) {
//            resp.setStatus(301);
//            resp.addHeader("Location", req.getContextPath()+"/calc-form");
            resp.sendRedirect(req.getContextPath() + "/calc-form?error_message=" +wynik.resultRepresentation );
        } else {
            PrintWriter writer = resp.getWriter();
            writer.print("<h1> Wynik " + wynik.resultRepresentation + "</h1>");
        }
    }

    private CalculationResult calculate(String operation, int a, int b) {
        if (operation.endsWith("add")) {
            return new CalculationResult(a + b, a + " + " + b + " = " + (a + b), true);
        } else if (operation.endsWith("subtract")) {
            return new CalculationResult(a - b, a + " - " + b + " = " + (a - b), true);
        } else if (operation.endsWith("multiply")) {
            return new CalculationResult(a * b, a + " * " + b + " = " + (a * b), true);
        } else {
            return new CalculationResult(0, "Unsupported operation", false);
        }

    }

    private static class CalculationResult {
        private Integer result;
        private String resultRepresentation;
        private boolean calculated;

        public CalculationResult(Integer result, String resultRepresentation, boolean calculated) {
            this.result = result;
            this.resultRepresentation = resultRepresentation;
            this.calculated = calculated;
        }


    }

    private Integer mapToInteger(String param) {
        return Optional.ofNullable(param)
                .filter(e -> StringUtils.isNumeric(e))
                .map(e -> Integer.valueOf(e))
                .orElse(0);
    }
}

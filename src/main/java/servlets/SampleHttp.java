import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "simpleServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class SampleHttp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("twig/index.twig");


        JtwigModel model = JtwigModel.newModel();
        response.getWriter().write(template.render(model));
    }

    @Override
    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String password = "";
        password = generatePassword(ALPHABET, password);

        JtwigTemplate template = JtwigTemplate.classpathTemplate("twig/index.twig");


        JtwigModel model = JtwigModel.newModel();
        model.with("password", password);
        response.getWriter().write(template.render(model));
    }

    private String generatePassword(String ALPHABET, String password) {
        for(int i = 0; i < 9; i++){
            int rollNumberCase = (int) (Math.random() * 10);
            if(rollNumberCase > 5) {
                int rollNumberSign = (int) (Math.random() * 10);
                if(rollNumberSign > 5) {
                    int numberAt = (int) ((Math.random() * 10) + 3) *2;
                    password += String.valueOf(ALPHABET.charAt(numberAt));
                }else {
                    String digit = String.valueOf((int) (Math.random() * 10));
                    password+=digit;
                }
            }else {
                int rollNumberSign = (int) (Math.random() * 10);
                if(rollNumberSign > 5) {
                    int numberAt = (int) ((Math.random() * 10) + 3) *2;
                    password += String.valueOf(ALPHABET.charAt(numberAt)).toLowerCase();
                }else {
                    String digit = String.valueOf((int) (Math.random() * 10));
                    password+=digit;
                }
            }
        }
        return password;
    }
}

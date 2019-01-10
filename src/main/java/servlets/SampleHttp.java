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
        final int PASSWORD_LENGTH = 9;
        final int ALPHABET_INDEX_MULTIPLIER = 10;
        final int ALPHABET_INDEX_MULTIPLIER_2 = 2;
        final int ALPHABET_INDEX_ADD = 3;
        final int DIGIT_MULTIPLIER = 10;
        final int IF_MULTIPLIER = 10;
        final int CASE_CONDITION = 5;
        final int SIGN_CONDITION = 5;
        for(int i = 0; i < PASSWORD_LENGTH; i++){
            int rollNumberCase = (int) (Math.random() * IF_MULTIPLIER);
            if(rollNumberCase > CASE_CONDITION) {
                int rollNumberSign = (int) (Math.random() * IF_MULTIPLIER);
                if(rollNumberSign >= SIGN_CONDITION) {
                    int numberAt = (int) ((Math.random() * ALPHABET_INDEX_MULTIPLIER) + ALPHABET_INDEX_ADD) *ALPHABET_INDEX_MULTIPLIER_2;
                    password += String.valueOf(ALPHABET.charAt(numberAt));
                }else {
                    String digit = String.valueOf((int) (Math.random() * DIGIT_MULTIPLIER));
                    password+=digit;
                }
            }else {
                int rollNumberSign = (int) (Math.random() * IF_MULTIPLIER);
                if(rollNumberSign >= SIGN_CONDITION) {
                    int numberAt = (int) ((Math.random() * ALPHABET_INDEX_MULTIPLIER) + ALPHABET_INDEX_ADD) *ALPHABET_INDEX_MULTIPLIER_2;
                    password += String.valueOf(ALPHABET.charAt(numberAt)).toLowerCase();
                }else {
                    String digit = String.valueOf((int) (Math.random() * DIGIT_MULTIPLIER));
                    password+=digit;
                }
            }
        }
        return password;
    }
}

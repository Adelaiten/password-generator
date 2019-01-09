import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "dupa", urlPatterns = {"/password"}, loadOnStartup = 1)
public class GeneratePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String password = "";
        List<String> list = new ArrayList<>();
        password = generatePassword(ALPHABET, password);
        list.add(password);
        String json = new Gson().toJson(list);

        response.getWriter().write(json);
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

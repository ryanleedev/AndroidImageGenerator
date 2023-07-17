//package algonquin.cst2335.projmy;
//
//import static java.lang.Character.isDigit;
//import static java.lang.Character.isLowerCase;
//import static java.lang.Character.isUpperCase;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
///** This class creates the first page of the app
// *
// * @author: Jeonghyeon Lee
// * @version: 1.0
// */
//public class MainActivity extends AppCompatActivity {
//
//    /** This holds the text at the centre of the screens */
//    TextView tv = null;
//    /** This holds the text at the centre of the screens below the TextView */
//    EditText et = null;
//    /** This holds the button at the bottom of the screens */
//    Button btn = null;
//
//    // static void main(String args[])
//    @Override   // starting point
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // loads the screen
//        // R represents resources folder /Res/layout/activity_main
//        setContentView(R.layout.activity_main);
//
//        tv = findViewById(R.id.textView);
//        et = findViewById(R.id.editText);
//        btn = findViewById(R.id.button);
//
//
//
//        btn.setOnClickListener( clk -> {
//            String password = et.getText().toString();
//
//            boolean checkPw = checkPasswordComplextiy( password );
//            if(checkPw) {
//                tv.setText("Your password meets the requirements");
//            } else {
//                tv.setText("You shall not pass!");
//            }
//        });
//
//
//
//
//
//    }
//
//    /** This function should check if this string has an Upper Case letter, a lower case letter,
//     * a number, and a special symbol (#$%^&*!@?).
//     *
//     * @param pw the String object that we are checking
//     * @return Returns true if this string has an Upper Case letter, a lower case letter,
//     * a number, and a special symbol (#$%^&*!@?).
//     */
//    boolean checkPasswordComplextiy(String pw) {
//
//        boolean check = false;
//
//        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
//        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
//
//        for(int i = 0; i < pw.length(); i++) {
//            char c = pw.charAt(i);
//
//            if (isDigit(c)) {
//                foundNumber = true;
//            }
//            if (isUpperCase(c)) {
//                foundUpperCase = true;
//            }
//            if (isLowerCase(c)) {
//                foundLowerCase = true;
//            }
//            if (isSpecialCharacter(c)) {
//                foundSpecial = true;
//            }
//        }
//            if(!foundUpperCase)
//            {
//                Toast.makeText( this /* MainActivity */, "missing an upper case letter;", Toast.LENGTH_SHORT ).show();
//                check = false;
//            } else if(!foundLowerCase)
//            {
//                Toast.makeText( this /* MainActivity */, "missing a lower case letter;", Toast.LENGTH_SHORT ).show();
//                check = false;
//            } else if(!foundNumber)
//            {
//                Toast.makeText( this /* MainActivity */, "missing a number;", Toast.LENGTH_SHORT ).show();
//                check = false;
//            } else if(!foundSpecial)
//            {
//                Toast.makeText( this /* MainActivity */, "missing a special character;", Toast.LENGTH_SHORT ).show();
//                check = false;
//            } else {
//                check = true;
//            }
//        return check;
//    }
//
//    /**
//     * This function is to check if there is a special character for each character of a string object
//     * @param c the character that we are checking
//     * @return Returns true if the string has a special symbol (#$%^&*!@?).
//     */
//    boolean isSpecialCharacter(char c) {
//        switch (c)
//        {
//            case '#':
//            case '?':
//            case '*':
//            case '$':
//            case '%':
//            case '&':
//            case '^':
//            case '!':
//            case '@':
//                return true;
//            default:
//                return false;
//        }
//    }
//
//}
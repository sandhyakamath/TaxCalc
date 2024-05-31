package Helper;

import java.util.ArrayList;

public class CSyntaxErrorLog {
    static int errorCount = 0;
    static ArrayList lst = new ArrayList();


    public static void Cleanup()
    {
        lst.clear();
        errorCount = 0;
    }
    /// <summary>
    ///    Add a Line from script
    /// </summary>
    /// <param name="str"></param>

    public static void addLine(String str)
    {
        lst.add(str.substring(0));
        errorCount++;

    }

    /// <summary>
    ///    Get Logged data as a String
    /// </summary>
    /// <returns></returns>
    public static String getLog()
    {

        String str = "Syntax Error" + "\r\n";
        str += "--------------------------------------\r\n";

        int xt = lst.size();

        if (xt == 0)
        {
            str += "NIL" + "\r\n";

        }
        else
        {

            for (int i = 0; i < xt; ++i)
            {
                str = str + lst.indexOf(i)+ "\r\n";
            }
        }
        str += "--------------------------------------\r\n";
        return str;
    }
}

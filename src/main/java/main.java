import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class main {

    public static void main(String[] args) throws IOException {
        List<String> fpcalcResult = fpcalc(args[0], "C:\\Users\\sav\\Music\\a.mp3");
        
    }

    private static String output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }

    private static List<String> fpcalc (String fpcalcPath, String filePath) throws IOException {
        File file = new File(filePath);
        ProcessBuilder pb = new ProcessBuilder(fpcalcPath, file.toString());
        Process process = pb.start();
        String preResult = output(process.getInputStream());
        List<String> list = Arrays.asList(preResult.replaceAll("FINGERPRINT", "").split("="));
        List<String> Result = new ArrayList<>();
        Result.add(list.get(1));
        Result.add(list.get(2));
        return Result;
    }
}
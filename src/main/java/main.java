import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class main {

    public static void main(String[] args) {
        
    }

    private static List<String> resultFpcalc(String fpcalcPath, String filePath) throws IOException {
        List<String> tempFpcalc = new ArrayList<>();
        List<String> resultFpcalc = new ArrayList<>();
        List<String> listTrack =  trackList(filePath);
        for (int i = 0; i < listTrack.size(); i++) {
            tempFpcalc = fpcalc(fpcalcPath, listTrack.get(i));
            resultFpcalc.add(tempFpcalc.get(0) + " @ " + tempFpcalc.get(1));
            System.out.println(resultFpcalc.get(i));
        }
        return resultFpcalc;
    }

    /**
     * Получаем адсолютный путь до каждого файла
     * @param path путь до папки с файлами
     * @return возвращаем List с абсолютными путями файлов
     */
    private static List<String> trackList(String path) {
        List<String> listTrack = new ArrayList<>();
        File directoryPath = new File(path);
        File filesList[] = directoryPath.listFiles();
        for(File file : filesList) {
            if(file.toString().contains(".mp3") ) {   //исключаем все файлы кроме mp3
                listTrack.add(file.getAbsolutePath());
            }
        }

        return listTrack;
    }

    /**
     *
     * @param inputStream получаем из метода fpcalc результат processBuilder
     * @return возвращаем в метод fpcalc строку
     * @throws IOException
     */
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

    /**
     *
     * @param fpcalcPath путь до утилиты fpcalc
     * @param filePath абсолютный путь до файла
     * @return возврящаем List содержащий fingerprint и duration файла
     * @throws IOException
     */
    private static List<String> fpcalc (String fpcalcPath, String filePath) throws IOException {
        File file = new File(filePath);
        ProcessBuilder pb = new ProcessBuilder(fpcalcPath, file.toString());
        Process process = pb.start();
        String preResult = output(process.getInputStream());
        List<String> list = Arrays.asList(preResult.replaceAll("FINGERPRINT", "").split("="));
        List<String> Result = new ArrayList<>();
        char[] temp = new char[3];
        list.get(1).getChars(0, 3, temp, 0);
        Result.add(String.valueOf(temp));
        Result.add(list.get(2).replaceAll("\n", ""));
        return Result;
    }
}
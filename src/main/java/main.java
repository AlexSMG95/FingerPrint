import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class main {

    public static void main(String[] args) throws IOException {
        List<String> listTrack =  trackList(args[1]);
        System.out.println(listTrack.get(0));
        List<String> fpcalcResult = fpcalc(args[0], listTrack.get(1));
        System.out.println(fpcalcResult.get(0));
        System.out.println(fpcalcResult.get(1));

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
            listTrack.add(file.getAbsolutePath());
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
        Result.add(list.get(1).replaceAll("\n", "").replaceAll(" ", ""));
        Result.add(list.get(2));
        return Result;
    }
}
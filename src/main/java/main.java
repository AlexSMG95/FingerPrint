import java.io.File;
import java.io.IOException;
import java.util.Map;

import chromaprint.AudioSource;
import chromaprint.Fingerprint;
import chromaprint.quick.Fingerprinter;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


public class main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {

//        File file = new File("C:\\Users\\sav\\Music\\a.mp3");
//
//        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
//        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
//        String key = "duration";
//        Long microseconds = (Long) properties.get(key);
//        System.out.println(microseconds / 1000000);

        System.out.println(System.getenv("ffmpeg"));


//        ProcessBuilder processBuilder = new ProcessBuilder( "ffmpeg -i", file.toString(), file.toString().replaceAll("mp3", "flac"));
//        processBuilder.start();
//        File file2 = new File(file.toString().replaceAll("mp3", "flac"));
//        AudioSource source = new AudioSource.AudioFileSource(file2);
//        Fingerprinter fingerprinter = new Fingerprinter();
//        Fingerprint fingerprint = fingerprinter.apply(source).unsafeRunSync();
//        System.out.println(fingerprint.compressed());
//        file2.delete();
    }
}

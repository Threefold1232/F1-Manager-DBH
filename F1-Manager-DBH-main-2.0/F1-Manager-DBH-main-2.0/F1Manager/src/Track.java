import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Track {
    public String Name;
    public int Length;
    int cornerCount = 0;
    int straightCount = 0;
    private final int Corners;
    private final int Straights;
    public ArrayList<TrackSection> TrackSections = new ArrayList<>();

    public Track(String name, int length, int corners, int straights) {
        Name = name;
        Length = length;
        Corners = corners;
        Straights = straights;
        TrackSections = GenerateTrack();
    }

    private ArrayList<TrackSection> GenerateTrack() {
        ArrayList<TrackSection> track = new ArrayList<TrackSection>();

        for (int i = 0; i < Corners; i++) {
            track.add(TrackSection.Curve);
        }

        for (int i = 0; i < Straights; i++) {
            track.add(TrackSection.Straight);
        }

        int randomNum = GetRandomNumber(5, 15);
        for (int i = 0; i < randomNum; i++) {
            track.add(TrackSection.Normal);
        }

        Collections.shuffle(track);
        // TODO: prüfe an dieser Stelle, ob im Track niemals zwei Kurven aufeinadner folgen und niemals mehr als drei Gerade aufeinander folgen => bei Widersprüchen einfach nochmal shuffeln
        return track;
    }

    private int GetRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Track {
    public String Name;
    int Length;
    static int Corners;
    static int Straights;

    public Track(){

    };

    public ArrayList<TrackSection> GenerateTrack(String Name, int length, int corners, int straights)
    {
        this.Name = Name;
        this.Length =length;
        this.Corners = corners;
        this.Straights = straights;

        ArrayList<TrackSection> track = new ArrayList<TrackSection>();
        for (int i = 0; i < Corners; i++){
            track.add(TrackSection.Curve);
        }

        for (int i = 0; i < Straights; i++){
            track.add(TrackSection.Straight);
        }

        int randomNum = GetRandomNumber(2, 10);
        for (int i = 0; i < randomNum; i++){
            track.add(TrackSection.Normal);
        }

        Collections.shuffle(track);
        return track;
    }

    private int GetRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}

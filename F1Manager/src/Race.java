import java.text.DecimalFormat;
import java.util.ArrayList;

public class Race {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_DARK = "\033[34m";
    public static final String ANSI_BLUE = "\033[96m";
    public static final String ANSI_ORANGE = "\033[33m";
    String Name;
    Track Track;
    int CurrentTrackSection;
    TrackSection section;
    ArrayList<Car> Cars;

    public Race(String name, Track track, ArrayList<Car> cars) {
        this.Name = name;
        this.Track = track;
        this.Cars = cars;
    }

    public void Simulate() {
        TrackSection currentTrackSection = Track.TrackSections.get(this.CurrentTrackSection);
        String output = "";
        for (Car car : this.Cars) {
            // TODO: Hier das Team, den Fahrer und die aktuelle Geschindigkeit for dem Fortschrittsbalkne ausgeben
            DecimalFormat df = new DecimalFormat("0.00");

            output += car.Team + ":\t" + car.Driver.Name + "\t(" + df.format(car.Speed) + "km/h) \t";

            car.Drive(currentTrackSection);

            // schritt 1: die Länge von einem Prozent der Strecke errechene
            double onePercentOfTrackLength = Track.Length / 100;

            // schritt 2: errechne wie oft die 1%-Strecke in die bisherige zurückgelegt Strecke des uto passt
            double trackProgressInPercent = car.Distance / onePercentOfTrackLength;

            // schritt 3: Gebe entsprechen des vorigen Ergebnisses den Progress aus
            for (int x = 0; x < trackProgressInPercent; x++) {

                if (car.Team.equals("RedBull")) {
                    output += ANSI_BLUE_DARK + "|" + ANSI_RESET;

                } else if (car.Team.equals("Mercedes")) {
                    output += ANSI_BLUE + "|" + ANSI_RESET;

                } else if (car.Team.equals("McLaren")) {
                    output += ANSI_ORANGE + "|" + ANSI_RESET;
                }

            }

            output += " \t" + df.format(trackProgressInPercent) + " % von 100" + "\n";

        }

        System.out.println(output);
    }

    private TrackSection GetNextTrackSection() {
        System.out.println(CurrentTrackSection++);
        if (Main.gameEngine.get_tracks().size() < CurrentTrackSection) {
            CurrentTrackSection = 0;
        }
        // gibt immer die  nächste TrackSection des Tracks zurück... sollte die Anzahl der TrackSections überschritten worden sein, dann die CurrenttrackSection auf 0 setzen
        {
            return GetNextTrackSection();
        }
    }

}

//CLEAR

// Track Monacco:
// Auto1: \t    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// Auto2: \t    ||||||||||||||||||||||||||

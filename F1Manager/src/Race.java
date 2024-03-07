import java.util.ArrayList;

public class Race {
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
        for (int i = 0; i < this.Cars.size(); i++) {
            Car car = this.Cars.get(i);
            car.Drive(Main.gameEngine.track1.get(this.CurrentTrackSection));
            // schritt 1: die Länge von einem Prozent der Strecke errechene
            double onePercentOfTrackLength = Track.Length / 100;

            // schritt 2: errechne wie oft die 1%-Strecke in die bisherige zurückgelegt Strecke des uto passt
            double trackProgressInPercent = car.Distance / onePercentOfTrackLength;

            // schritt 3: Gebe entsprechen des vorigen Ergebnisses den Progress aus
            for (int x = 0; x < trackProgressInPercent; x++){
                System.out.print("|");
            }

            System.out.flush();

        }

    }

    private TrackSection GetNextTrackSection(){
        System.out.println(CurrentTrackSection++);
        if (Main.gameEngine.get_tracks().size() < CurrentTrackSection) {
            CurrentTrackSection = 0;
        }
        // gibt immer die  nächste TrackSection des Tracks zurück... sollte die Anzahl der TrackSections überschritten worden sein, dann die CurrenttrackSection auf 0 setzen
        {
            return GetNextTrackSection();
        }
    }


    private class TrackProgress {

    }
}

//CLEAR

// Track Monacco:
// Auto1: \t    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
// Auto2: \t    ||||||||||||||||||||||||||

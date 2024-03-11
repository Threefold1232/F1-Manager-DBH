import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GameEngine {

    public List<TrackSection> track1;
    private ArrayList<Driver> _drivers;
    private ArrayList<Car> _cars;
    private ArrayList<Track> _tracks;
    String trackName;
    int trackCorners;
    int trackstraights;
    int tracklength;

    public GameEngine() {
        GenerateDrivers();
        GenerateCars();
        GenerateTracks();
    }

    public ArrayList<Track> get_tracks() {
        return _tracks;
    }

    public ArrayList<Car> GetAllRacingCars() {

        ArrayList<Car> allCars = new ArrayList<>();

        for (int i = 0; i < _drivers.size(); i++) {
            Car car = this._cars.get(i);
            car.SetDriver(this._drivers.get(i));
            allCars.add(car);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return allCars;
    }

    public void start() {
        Scanner trackInput = new Scanner(System.in);
        Scanner lengthinput = new Scanner(System.in);
        Scanner cornersInput = new Scanner(System.in);
        Scanner straightsInput = new Scanner(System.in);

        System.out.print("Enter Trackname: ");
        trackName = trackInput.nextLine();
        System.out.println("                                                  ");

        System.out.print("Enter Tracklength (between 4000 and 10000): ");
        tracklength = lengthinput.nextInt();
        System.out.println("                                                  ");

        System.out.print("Enter the Number of corners the Track should have: ");
        trackCorners = cornersInput.nextInt();
        System.out.println("                                                  ");

        System.out.print("Enter the Number of straights the Track should have: ");
        trackstraights = straightsInput.nextInt();


        var cars = GetAllRacingCars();
        Track track = new Track(trackName, tracklength, trackCorners, trackstraights);

        System.out.println("Track: " + track.Name + " (" + track.TrackSections + ")");

        int sectionIndex = 0;

        while (!RaceFinished(track, cars)) {
            System.out.println("                                 ");
            System.out.println("Current Track Section: " + track.TrackSections.get(sectionIndex));

            for (Car car : cars) {
                car.Drive(track.TrackSections.get(sectionIndex));
            }

            Race race = new Race("Monaco", track, cars);
            race.Simulate();

            sectionIndex++;

            if (sectionIndex >= track.TrackSections.size()) {
                sectionIndex = 0;
            }

            System.out.println("                                 ");
            System.out.println("------------------------------------------");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        for (Car car : cars) {
            if (car.Distance >= track.Length) {
                System.out.println(car.Driver.Name + "won in " + track.Name);
            }
        }


    }


    private int GetRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        return randomNum;
    }

    private boolean RaceFinished(Track track, ArrayList<Car> cars) {
        for (Car car : cars) {
            if (car.Distance >= track.Length) {
                return true;
            }
        }

        return false;

    }

    private void GenerateDrivers() {
        this._drivers = new ArrayList<Driver>();
        this._drivers.add(new Driver("Verstappen ", 38, 33));
        this._drivers.add(new Driver("Hamilton ", 33, 28));
        this._drivers.add(new Driver("Norris  ", 31, 39));
        this._drivers.add(new Driver("Piastri  ", 32, 36));
        this._drivers.add(new Driver("Perez    ", 33, 30));
        this._drivers.add(new Driver("Russel  ", 31, 31));
        Collections.shuffle(this._drivers);
    }

    private void GenerateCars() {
        this._cars = new ArrayList<Car>();
        this._cars.add(new Car("RedBull", 0, 1.2, 350, 50));
        this._cars.add(new Car("Mercedes", 0, 1.1, 300, 40));
        this._cars.add(new Car("McLaren", 0, 1.2, 320, 30));
        this._cars.add(new Car("RedBull", 0, 1.2, 350, 50));
        this._cars.add(new Car("Mercedes", 0, 1.1, 300, 40));
        this._cars.add(new Car("McLaren", 0, 1.2, 320, 30));
        Collections.shuffle(this._cars);
    }

    private void GenerateTracks() {

    }


}
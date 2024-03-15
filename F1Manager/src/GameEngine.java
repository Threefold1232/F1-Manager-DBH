import java.util.*;
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

    public boolean start() {
        Scanner trackInput = new Scanner(System.in);
        Scanner lengthinput = new Scanner(System.in);
        Scanner cornersInput = new Scanner(System.in);
        Scanner straightsInput = new Scanner(System.in);

        Scanner randomTrackInput = new Scanner(System.in);
        System.out.print("Do you want to create a Random Track or a Custom Track? (Input: 'Random' for Random Track and 'Custom' for Custom Track): ");
        String randomTrack = randomTrackInput.nextLine();

        if (randomTrack.equals("Random")){
            trackName = "Random";
            tracklength = GetRandomNumber(4000, 10000);
            trackCorners = GetRandomNumber(2, 20);
            trackstraights = GetRandomNumber(2, 10);
        }
        else if (randomTrack.equals("Custom")){
            System.out.print("Enter Trackname: ");
            trackName = trackInput.nextLine();
            System.out.println("                                                  ");


            System.out.print("Enter Tracklength (between 4000 and 12000): ");
            tracklength = lengthinput.nextInt();
            while ((tracklength > 10000) || (tracklength < 4000)){
                System.out.println("The Length can only be between 4000m and 10000m, otherwise it takes too long to simulate.");
                System.out.println("                                                  ");

                lengthinput = new Scanner(System.in);
                System.out.print("Enter Tracklength (between 4000 and 12000): ");
                tracklength = lengthinput.nextInt();
            }
            System.out.println("                                                  ");


            System.out.print("Enter the Number of corners the Track should have (max. 20): ");
            trackCorners = cornersInput.nextInt();
            while (trackCorners > 20){
                System.out.println("The maximum Number of corners can only be 20");
                System.out.println("                                                  ");

                cornersInput = new Scanner(System.in);
                System.out.print("Enter the Number of corners the Track should have (max. 20): ");
                trackCorners = cornersInput.nextInt();
            }
            System.out.println("                                                  ");


            System.out.print("Enter the Number of straights the Track should have (max. 10): ");
            trackstraights = straightsInput.nextInt();
            while (trackstraights > 10){
                System.out.println("The maximum Number of Straights can only be 10");
                System.out.println("                                                  ");

                straightsInput = new Scanner(System.in);
                System.out.print("Enter the Number of Straights the Track should have (max. 10): ");
                trackstraights = straightsInput.nextInt();
            }


        }


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

        ArrayList<Car> finishedCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.Distance >= track.Length) {
                finishedCars.add(car);
            }
        }

        if (!finishedCars.isEmpty()) {
            Car winner = Collections.max(finishedCars, Comparator.comparing(Car::getDistance));
            long count = finishedCars.stream().filter(car -> car.getDistance() == track.Length).count();
            if (count > 1) {
                System.out.println("It was close, but " + winner.Driver.Name + " (" + winner.Team + ") came out on top");
            } else {
                System.out.println("The winner is: " + winner.Driver.Name + " (" + winner.Team + ")");
            }
        }
        return false;
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
        this._drivers.add(new Driver("Verstappen ", 43, 35));
        this._drivers.add(new Driver("Hamilton ", 27, 31));
        this._drivers.add(new Driver("Norris  ", 38, 33));
        this._drivers.add(new Driver("Piastri  ", 35, 36));
        this._drivers.add(new Driver("Perez    ", 29, 30));
        this._drivers.add(new Driver("Russel  ", 30, 29));
        Collections.shuffle(this._drivers);
    }

    private void GenerateCars() {
        this._cars = new ArrayList<Car>();
        this._cars.add(new Car("RedBull", 0, 1.25, 350, 50));
        this._cars.add(new Car("Mercedes", 0, 1.1, 300, 40));
        this._cars.add(new Car("McLaren", 0, 1.2, 320, 30));
        this._cars.add(new Car("RedBull", 0, 1.25, 350, 50));
        this._cars.add(new Car("Mercedes", 0, 1.1, 300, 40));
        this._cars.add(new Car("McLaren", 0, 1.2, 320, 30));
        Collections.shuffle(this._cars);
    }

    private void GenerateTracks() {

    }
}
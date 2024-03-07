import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GameEngine {

    private ArrayList<Driver> _drivers;
    private ArrayList<Car> _cars;
    private ArrayList<Track> _tracks;
    public List<TrackSection> track1;

    public ArrayList<Track> get_tracks() {
        return _tracks;
    }

    public GameEngine() {
        GenerateDrivers();
        GenerateCars();
        GenerateTracks();
    }

    public ArrayList<Car> GetAllRacingCars() {

        ArrayList<Car> allCars  = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Car car = this._cars.get(i);
            car.SetDriver(this._drivers.get(i));
            car.Print();
            allCars.add(car);

            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

        return allCars;
    }

    public void start()
    {
        var cars = GetAllRacingCars();
        Track track = new Track("Monaco", 5000, 8, 6);
        track1 = track.GenerateTrack();

        System.out.println("Track: " + track.Name + " (" + track1 + ")");

        for (TrackSection section : track1){
            System.out.println("                                 ");
            System.out.println("Current Track Section: " + section);

                for (Car car : cars){
                    car.Drive(section);
                    car.Print();
                }
            try
            {
                TimeUnit.SECONDS.sleep(3);
            }

            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Race race = new Race("Monaco", track, cars);

        }
    }


    private int GetRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        return randomNum;
    }

    private void SetRandomDriverIntoRandomCar()
    {


    }

    private void GenerateDrivers(){
        this._drivers = new ArrayList<Driver>();
        this._drivers.add(new Driver("Verstappen", 10, 15));
        this._drivers.add(new Driver("Hamilton", 16, 8));
        this._drivers.add(new Driver("Norris", 11, 19));
        Collections.shuffle(this._drivers);
    }

    private void GenerateCars(){
        this._cars = new ArrayList<Car>();
        this._cars.add(new Car("RedBull", 0, 1.3, 350, 50));
        this._cars.add(new Car("Mercedes", 0, 1.1, 300, 40));
        this._cars.add(new Car("McLaren", 0, 1.2, 330, 30));
        Collections.shuffle(this._cars);
    }

    private void GenerateTracks(){

    }


}
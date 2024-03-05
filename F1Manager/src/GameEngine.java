import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GameEngine {
    public ArrayList<Car> GetAllRacingCars() {

        ArrayList<Car> allCars  = new ArrayList<>();

        ArrayList<Driver> drivers = new ArrayList<>();
        ArrayList<Car> cars  = new ArrayList<>();

        drivers.add(new Driver("Verstappen", 30, 10));
        drivers.add(new Driver("Hamilton", 20, 20));
        drivers.add(new Driver("Norris", 10, 30));

        cars.add(new Car("RedBull", 100, 13, 350));
        cars.add(new Car("Mercedes", 130, 1.1, 300));
        cars.add(new Car("McLaren", 80, 1.2, 330));

        for (int i = 0; i < 3; i++) {
            int randomNum = GetRandomNumber(0, drivers.size());
            int secondRandomNum = GetRandomNumber(0, cars.size());

            Driver driver = drivers.get(randomNum);
            Car car = cars.get(secondRandomNum);

            car.SetDriver(driver);
            allCars.add(car);

            System.out.println("Team " + car.Team);
            System.out.println("Driver " + car.Driver.Name);
            System.out.println("Speed " + car.Speed);
            System.out.println("Acceleration " + car.Acceleration);
            System.out.println("MaxSpeed " + car.MaxSpeed);
            System.out.println("---------------------------------");
            System.out.println("                                 ");

            drivers.remove(randomNum);

            try
            {
                TimeUnit.SECONDS.sleep(3);
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
        Track track = new Track();
        var track1 = track.GenerateTrack("Monaco", 500, 8, 6);

        System.out.println(track1);

        for (TrackSection section : track1){
            System.out.println("                                 ");
            System.out.println("Current Track Section: " + section);


            try
            {
                TimeUnit.SECONDS.sleep(3);
            }

            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                for (Car car : cars){
                    car.Drive(section);
                    System.out.println("Name: " + car.Driver.Name);
                    System.out.println("Speed: " + car.Speed);
                    System.out.println("Acceleration " + car.Acceleration);
                    System.out.println("---------------------------------");
                    System.out.println("                                 ");
                }
        }
    }


    private int GetRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        return randomNum;
    }


}
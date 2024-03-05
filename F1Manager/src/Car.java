import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Car {
    String Team;
    double Speed;
    double Acceleration;
    double MaxSpeed;
    Driver Driver;

    public Car(String Team, double Speed, double Acceleration, double MaxSpeed){
        this.Team = Team;
        this.Speed = Speed;
        this.Acceleration = Acceleration;
        this.MaxSpeed = MaxSpeed;
    }

    public void SetDriver(Driver driver){
        this.Driver = driver;
    }

    public void Drive(TrackSection section)
    {
        // bei Normal, Beschleunigen addieren
        if (section == TrackSection.Normal){
            Speed = Speed * Acceleration;
        }
        // bei Kurve, beschleunigung abziehen
        if (section == TrackSection.Curve){
            Speed = Speed / Acceleration;
        }
        // bei Gerade, beschleunigung 1,5 mal adidieren
        if (section == TrackSection.Straight){
            Speed = Speed * (float)(Acceleration * 1.5);
        }

        if (Speed > MaxSpeed){
            Speed = MaxSpeed;
        }

    }
}

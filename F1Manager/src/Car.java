public class Car {
    String Team;
    double Speed;
    double Acceleration;
    double MaxSpeed;
    Driver Driver;

    int MinSpeed;

    double Distance = 0.0;

    public Car(String Team, double Speed, double Acceleration, double MaxSpeed, int MinSpeed) {
        this.Team = Team;
        this.Speed = Speed;
        this.Acceleration = Acceleration;
        this.MaxSpeed = MaxSpeed;
        this.MinSpeed = MinSpeed;
    }

    public void SetDriver(Driver driver) {
        this.Driver = driver;
    }

    public void Drive(TrackSection section) {
        // bei Normal, Beschleunigen addieren
        if (section == TrackSection.Normal) {
            Speed = Speed + Acceleration * 40;
        }
        // bei Kurve, beschleunigung abziehen
        if (section == TrackSection.Curve) {
            Speed = (Speed + Driver.CornerBonus) / (float) (1.5 * Acceleration);
        }
        // bei Gerade, beschleunigung 1,5 mal adidieren
        if (section == TrackSection.Straight) {
            Speed = (Speed + Driver.SpeedBonus) * (float) (Acceleration * 1.5);
        }

        if (Speed > MaxSpeed) {
            Speed = MaxSpeed;
        }

        if (Speed < MinSpeed) {
            Speed = MinSpeed + Driver.CornerBonus;
        }
        this.Distance += GetDistance();
    }

    public void Print() {
        String output = this.Team + " (" + this.Driver.Name + "): " + this.Speed + " km/h (Acc: " + this.Acceleration + " x , Max: " + this.MaxSpeed + " km/h)" + "MinSpeed: " + this.MinSpeed + "km/h";
        System.out.println(output);
        System.out.println("---------------------------------");
    }

    public double getDistance() {
        return Distance;
    }

    private double GetDistance() {
        return this.Speed / 3.6;
    }

}

public class Driver {
    String Name;
    float SpeedBonus;
    float CornerBonus;

    public Driver(String Name, float SpeedBonus, float Cornerbonus){
        this.Name = Name;
        this.SpeedBonus = SpeedBonus;
        this.CornerBonus = Cornerbonus;
    }

    public String GetName()
    {
        return Name;
    }

    public float GetSpeedBonus()
    {
        return SpeedBonus;
    }

    public float GetCornerBonus()
    {
        return CornerBonus;
    }

}

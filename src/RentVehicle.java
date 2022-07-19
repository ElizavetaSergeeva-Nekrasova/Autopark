import java.util.Date;

public class RentVehicle {
    public static Rent rentVehicle(Vehicle vehicle, double cost,  MechanicService mechanicService)
            throws DefectedVehicleException {
        if (!mechanicService.isBroken(vehicle)) {
            return new Rent(vehicle.getId(), new Date(), cost);
        }

        throw new DefectedVehicleException("Vehicle is defected");
    }
}
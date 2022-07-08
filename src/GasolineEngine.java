public class GasolineEngine extends CombustionEngine{
    public GasolineEngine(String typeName, double taxByEngineType, double engineCapacity, double fuelConsumptionPer100, double fuelTankCapacity) {
        super("Gasoline", 1.1, engineCapacity, fuelConsumptionPer100, fuelTankCapacity);
    }
}
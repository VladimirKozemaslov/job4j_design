package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.CargoCell;
import ru.job4j.ood.lsp.parking.model.ParkingCell;
import ru.job4j.ood.lsp.parking.model.PassengerCell;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutoParking implements Parking {
    private List<ParkingCell> scheme;

    private Map<Car, ParkingCell> parkLoad;

    public AutoParking(int passengerCellCount, int cargoCellCount, int rowSize) {
        if (rowSize < 1) {
            throw new IllegalArgumentException("Row size must be more than 0.");
        } else if (passengerCellCount < 1 && cargoCellCount < 1) {
            throw new IllegalArgumentException("Total cell count must be more than 0.");
        }
        List<ParkingCell> scheme = new ArrayList<>();
        char ch = 'A';
        int num = 1;
        for (int i = 0; i < passengerCellCount; i++) {
            scheme.add(new PassengerCell(Character.toString(ch) + num));
            num++;
            if (num > rowSize) {
                num = 1;
                ch = (char) (ch + 1);
            }
        }
        for (int i = 0; i < cargoCellCount; i++) {
            scheme.add(new CargoCell(Character.toString(ch) + num));
            num++;
            if (num > rowSize) {
                num = 1;
                ch = (char) (ch + 1);
            }
        }
        this.scheme = scheme;
    }

    public AutoParking(int passengerCellCount, int cargoCellCount) {
        new AutoParking(passengerCellCount, cargoCellCount, 5);
    }

    @Override
    public ParkingCell park(Car car) {
        return null;
    }

    @Override
    public void unPark(Car car) {

    }

    public static void main(String[] args) {
        char ch = 'A';
        System.out.println((char) (ch + 1));
    }
}

package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.*;


import java.util.ArrayList;
import java.util.List;

public class AutoParking implements Parking {
    private final List<AbstractCell> scheme;

    public AutoParking(int passengerCellCount, int cargoCellCount, int rowSize) {
        if (rowSize < 1) {
            throw new IllegalArgumentException("Row size must be more than 0.");
        } else if (passengerCellCount < 1 && cargoCellCount < 1) {
            throw new IllegalArgumentException("Total cell count must be more than 0.");
        }
        List<AbstractCell> scheme = new ArrayList<>();
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
        this(passengerCellCount, cargoCellCount, 5);
    }

    @Override
    public List<AbstractCell> park(AbstractCar car) {
        List<AbstractCell> rsl = new ArrayList<>();
        boolean isParked = false;
        for (AbstractCell cell : scheme) {
            if (cell.getSize() == car.getSize() && cell.isEmpty()) {
                isParked = cell.take(car);
                rsl.add(cell);
                break;
            }
        }

        if (!isParked && car.getSize() > 1) {
            rsl = parkOnMultipleCells(car);
        }

        return rsl;
    }

    @Override
    public List<AbstractCell> unPark(AbstractCar car) {
        List<AbstractCell> cells = new ArrayList<>();
        for (AbstractCell cell : scheme) {
            if (car.equals(cell.getCar())) {
                cell.clear();
                cells.add(cell);
            }
        }
        return cells;
    }

    private List<AbstractCell> parkOnMultipleCells(AbstractCar car) {
        List<AbstractCell> rsl = new ArrayList<>();
        int carSize = car.getSize();
        int count = 0;
        for (AbstractCell cell : scheme) {
            if (cell.getSize() == 1 && cell.isEmpty()) {
                count++;
                cell.take(car);
                rsl.add(cell);
            } else {
                count = 0;
                rsl.clear();
            }
            if (count == carSize) {
                break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        char ch = 'A';
        System.out.println((char) (ch + 1));
    }
}

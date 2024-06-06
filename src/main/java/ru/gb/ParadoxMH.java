package ru.gb;

import lombok.Data;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Arrays;

/**
 * Класс служит для демонстрации парадокса Монти Холла
 */
@Data
public class ParadoxMH {
    public int countDoors;
    private int[] doors;
    private DescriptiveStatistics ds;
    private MersenneTwister mt;
    private double percent;
    private int victories;
    private int losses;
    private int numberTests;


    public ParadoxMH() {
        this(Config.COUNT_DOORS);
    }

    public ParadoxMH(int countDoors) {
        this.countDoors = countDoors;
        doors = new int[countDoors];
        mt = new MersenneTwister();
        ds = new DescriptiveStatistics();
    }


    /**
     * Метод проверяет парадокс Монти Холла
     * на выборке numberTests. Возвращает вероятность
     * выигрыша приза при условии подсказки ведущего,
     * согласно парадокса.
     *
     * @param number количество тестов
     */
    public ParadoxMH runTest(int number) {
        ds.clear();
        numberTests = number;
        for (int i = 0; i < numberTests; i++) {
            putDownPrize();
            ds.addValue(choiceDoor());
        }
        calculateStatistics();
        return this;
    }

    /**
     * Метод имитирует выбор двери угадывающим случайным образом.
     * Затем ведущий "открывает" двери, за которыми нет приза,
     * оставляя одну дверь закрытой.
     * В пользу нее угадывающий меняет свой выбор
     *
     * @return 1 - в случае удачи, 0 - в противном случае
     */
    private int choiceDoor() {
        int attempt1 = mt.nextInt(countDoors);
        int openCount = 0;
        for (int i = 0; i < countDoors; i++) {
            if (i != attempt1) {
                if (openCount < countDoors - 1 && doors[i] == 0) openCount++;
                else return 1;
            }
        }
        return 0;
    }

    /* Новый ход: прячем приз */
    private void putDownPrize() {
        Arrays.fill(doors, 0);
        doors[mt.nextInt(countDoors)] = 1;
    }


    /*
     * Рассчет статистических показателей
     */
    private void calculateStatistics() {
        victories = ((Double) ds.getSum()).intValue();
        losses = numberTests - victories;
        percent = ds.getMean();
    }


    private void printDoors() {
        for (int i = 0; i < doors.length; i++) {
            System.out.print("[" + (doors[i] == 0 ? " " : "*") + "] ");
        }
        System.out.println();
    }

    public String getLogs() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ход теста:\n");
        for (int i = 0; i < numberTests; i++) {
            sb.append("шаг: ").append(i + 1).append(", итог: ")
                    .append(ds.getElement(i) == 1 ? "Победа" : "Поражение")
                    .append("\n");
        }
        return sb.toString();
    }

}

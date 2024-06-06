package ru.gb;

/**
 * Pеализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия )
 * и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Подключить зависимость lombok.
 * Можно подумать о подключении какой-нибудь математической библиотеки для работы со статистикой
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 * Работы принимаются в виде ссылки на гит репозиторий со всеми ключевыми файлами проекта
 */

public class Main {
    public static void main(String[] args) {
        ParadoxMH p1 = new ParadoxMH();
        print(p1.runTest(1000));
        print(p1.runTest(1000000));  // 66.(6)

        ParadoxMH p2 = new ParadoxMH(4);  // 75
        print(p2.runTest(1000000));

        ParadoxMH p3 = new ParadoxMH(5);  // 80
        print(p3.runTest(1000000));

        ParadoxMH p4 = new ParadoxMH(10);  // 90
        print(p4.runTest(1000000));

        ParadoxMH p5 = new ParadoxMH(100);  //99
        print(p5.runTest(1000000));

        print(p1.runTest(10));
        System.out.println(p1.getLogs());

    }

    /*
     * В методе демонстрируется использование возможностей аннотации @Data библиотеки Lombok
     * в частности, обращение к автоматически сформированным геттерам для класса ParadoxMH
     */
    private static void print(ParadoxMH p) {
        System.out.printf("Проверка парадокса Монти Холла\nчисло попыток: %d, "
                        + "количество дверей: %d\nпобед: %d, поражений: %d, процент побед: %.2f\n\n",
                p.getNumberTests(), p.getCountDoors(), p.getVictories(), p.getLosses(), p.getPercent() * 100);
    }

}
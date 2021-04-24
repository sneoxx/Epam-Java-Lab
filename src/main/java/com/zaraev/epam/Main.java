package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MainHelper mainHelper = new MainHelper();
//        mainHelper.callRaceCondition();
//        mainHelper.callsSynchronizedRaceCondition();
        mainHelper.deadlock();

    }
}


//        Runnable task = () -> {
////        CommonResource commonResource = new CommonResource();
//            int i = 0;
//                while (!Thread.currentThread().isInterrupted()) {
//                    i++;
//                    for (int y = 0; y < 1000; y++) {
//                        int oldValue = value;
//                        int newValue = ++value;
//                        if (oldValue + 1 != newValue) {
//                            throw new IllegalStateException("Race condition: " + oldValue + " + 1 = " + newValue);
//                        }
//                    }
//                }
//            System.out.println(i);
//    };

//        //Создание потока
//        Thread myThready = new Thread(new Runnable() {
//            public void run() //Этот метод будет выполняться в побочном потоке
//            {
//                System.out.println("Привет из побочного потока!");
//            }
//        });
//        myThready.start();    //Запуск потока
//        System.out.println("Главный поток завершён...");
//    }
//}


//        class CommonResource{
//            volatile static int value=0;
//        }

//        // Имплементация интерфейса Runnable
//        Runnable runnable = () -> {
//            while (!Thread.currentThread().isInterrupted()) {     // пока поток не прервется переменная i будет увеличиваться
//                i++;
//                int x = value;
//                int y = value++;
//
//                if (x + 1 != y){
//                    new IllegalStateException("Race condition" + x + "+ 1 = " + y);
//                }
//            }

// Состояние гонки возникает тогда, когда несколько потоков многопоточного приложения пытаются одновременно получить доступ к данным, причем хотя бы один поток выполняет запись.
//            while (!Thread.currentThread().isInterrupted()) {     // пока поток не прервется переменная i будет увеличиваться
//                i++;
//                System.out.println(i);
//            }
//            System.out.println(i); // 1338117690, когда прервется выведет в консоль
//        };
//        for(int i=1; i < 1000; i++) {
//            new Thread(runnable).start();
//            System.out.println("новый"+ i);
//        }
//        Thread thread = new Thread(runnable);
//        Thread thread1 = new Thread(runnable);
//        thread.start();
//        thread1.start();
//        Thread.sleep(10000);
//        thread.getState();
//        thread1.getState();
//        thread.interrupt();
//        thread1.interrupt();
//        System.out.println(thread.getState()); // RUNNABLE
//        System.out.println(thread1.getState()); // RUNNABLE
//        Thread.sleep(1000);
//        System.out.println(thread.getState()); // TERMINATED
//        System.out.println(thread1.getState()); // TERMINATED


//        //Использование volatile
//        class VolatileExample {
//            int x = 0;
//            volatile boolean v = false;  // volatile на поле
//
//            public void writer() {
//                x = 42;
//                v = true;
//            }
//
//            public void reader() {
//                if (v == true) {
//                    //uses x - гарантированнно видит 42
//                }
//            }
//        }
//
//        //использование семафора
//        Semaphore semaphore = new Semaphore(1); // в конструкторе задаем количество потоков(счетчик) или можно сетать значение
//        try {
//            semaphore.acquire();
//            //critical section
//            semaphore.release();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//   }
//}
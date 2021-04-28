package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import com.zaraev.epam.exceptions.CasheIndexOutOfBoundsException;
import com.zaraev.epam.exceptions.NotExistElementException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Старт программы");
        Cache<Integer> myCache = new Cache<>(10);
        myCache.add(11, 0);
        myCache.add(24, 1);
        myCache.add(34, 2);
        myCache.add(44, 3);
        myCache.add(54, 4);
        myCache.add(64, 5);
        myCache.add(74, 7);
        myCache.add(84, 8);
        myCache.add(94, 9);
        myCache.add(104, 10);
        myCache.add(null, 10);
        myCache.add(114, 11);
        myCache.add(94, 12);
        myCache.isPresent(99);
        myCache.isPresent(9);
        myCache.isPresent((Integer) 99);
        myCache.isPresent((Integer) 94);
        try {
            myCache.isPresent(null);
        } catch (NotExistElementException e) {
            log.warn("Попытка поиска в кэше null элемента", e);
        }
        myCache.delete(11);
        try {
            myCache.delete(null);
        } catch (NullPointerException e) {
            log.warn("Попытка удаления из кеша null элемента", e);
        }
        try {
            myCache.get(99);
        } catch (CasheIndexOutOfBoundsException e) {
            log.warn("Элемента в кэше нет", e);
        }
        try {
            myCache.get(9);
        } catch (CasheIndexOutOfBoundsException e) {
            log.warn("Элемента в кэше нет", e);
        }
        myCache.clear();
        myCache.add(84, 14);
        myCache.add(94, 15);
        myCache.add(104, 16);
        Storage<Integer> myStorageDefault = new Storage<>();
        Storage<Integer> myStorage = new Storage<>(new Integer[]{1, 2, 3, 4, 5});
        myStorage.add(6);
        myStorage.add(7);
        myStorage.getLast();
        try {
            myStorage.add(null);
        } catch (NotExistElementException e) {
            log.warn("Попытка добавить null в хранилище myStorage", e);
        }
        myStorage.getLast();
        try {
            myStorage.get(5);
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        try {
            myStorage.get(5);
        } catch (CasheIndexOutOfBoundsException e) {
            log.warn("Элемента в хранилище Storage нет", e);
        }
        try {
            myStorage.get(7);
        } catch (CasheIndexOutOfBoundsException e) {
            log.warn("Элемента в хранилище Storage нет", e);
        }
        myStorage.delete();
        myStorage.clear();
        Storage<String> myStorageNewTest = new Storage<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        Storage<String> myStorageOversize = new Storage<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});
        myStorageNewTest.add("6");
    }
}